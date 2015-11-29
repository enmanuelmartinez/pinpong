import java.awt.Graphics2D;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

/*--------------------------------------------------
 *
 *  @author  ENMANUEL MARTINEZ GONZALEZ, ITT ,
 *  DATE     JUNIO 2009
 *  DERECHOS RESERVADOS
 -------------------------------------------------*/

@SuppressWarnings("serial")
public class Bola extends JComponent {

    @SuppressWarnings("unused")
    Main1 main1;
    private ChooseBall ge;
    private Puntajes puntajes;
    private barras bar;

    final int diambola = 35;
    final static int ANCHO = 600;
    final static int ALTO = 350;
    private final String dir = Datos.Ruta() + "imagenes" + File.separator;// direccion
    static int x, y;
    static int vx, vy;
    int delta_t, aun = 0;
    int posita1, posita2;
    static boolean flagball;
    static int chosebola = 0;
    public static boolean mandame = false;//variable que habilita la enviada de bola por red
    BufferedImage bolas[];
    //rutas de las imagenes de las pelotas
    final String rutabolas[] = {dir + "pelota0.gif", dir + "pelota1.gif",
            dir + "pelota2.gif", dir + "pelota3.gif", dir + "pelota4.gif",
            dir + "pelota5.gif", dir + "pelota6.gif", dir + "pelota7.gif",
            dir + "pelota8.gif", dir + "pelota9.gif", dir + "pelota10.gif",
            dir + "pelota11.gif"};

    // constructor de la clase Bola

    public Bola() {

        delta_t = 4;//factor que multiplica el desplazamiento de la bola
        flagball = true;// habilita el pintado y el desplazamiento de la bola
        x = 64;//posicion inicial
        y = 150;//posicion inicial
        bolas = new BufferedImage[rutabolas.length];
        vx = 1;// define velocidad en x
        vy = 1;//define velocidad en y
        //obtiene las imagenes de las bolas
        for (int n = 0; n < rutabolas.length; n++) {
            bolas[n] = this.getImagen(n);
        }

    }

    public void Correrbola(final Graphics2D g2) {

        if (flagball)// aqui se controla movimiento de bola pintado y cambio de posicion
        {
            accion(delta_t);//cambia de posicion la bola y hace comparaciones de limite
            repinta(g2);//la pinta en pantalla
        }
    }

    @SuppressWarnings("static-access")
    // Metodo que cambia de posicion la bola
    private void accion(float delta_t) {
        x += vx * delta_t;
        y += vy * delta_t;

        if (vy < 0 && y <= 10 || vy > 0 && y + diambola >= ALTO) {//rebote vertical
            vy = -vy;
        }

        if (Main1.modo1juego) {// Si se esta en modo de juego uno

            if ((vx < 0 && x >= 5 && x <= 50)
                    && (y >= bar.desplaza - 25 && y <= 100 + bar.desplaza)) {
                vx = -vx;
                aun += 5;//esto me da la posibilidad de aumentar velocidad cada vez que se pegue a la bola
                delta_t = aun;
            }// si pega tabla uno
            if ((vx > 0 && x >= ANCHO - 100 && x <= ANCHO - 80)
                    && (y >= bar.desplaza2 - 25 && y <= 100 + bar.desplaza2)) {
                vx = -vx;
                aun += 5;//esto me da la posibilidad de aumentar velocidad cada vez que se pegue a la bola
                delta_t = aun;
            }// si pega tabla2
            if (vx < 0 && x <= -diambola - 14) {
                puntajes.puntosjugador2++;
                bolaposi();
            }// si se le anota a player1
            if (vx > 0 && x >= ANCHO + diambola) {
                puntajes.puntosjugador1++;
                bolaposi();
            }// si se le anota a player2

        }

        if (Main1.modo2juego)// Si se esta en modo de juego dos
        {

            if (vx > 0 && x + diambola >= ANCHO + 130) {
                flagball = false;
                mandame = true;
            }// se detiene el cambio de posicion y repintado
            // y se procede a dar paso a la condicion de mandar los datos por
            // Socket
            if ((vx < 0 && x >= 5 && x <= 50)
                    && (y >= bar.desplaza - 25 && y <= 100 + bar.desplaza)) {
                vx = -vx;
            }// si choca en paleta uno
            if (vx < 0 && x <= -diambola - 14) {
                puntajes.puntosjugador2++;
                bolaposi();
            }// si se le anota a player1
        }

    }

    // Metodo que posiciona la bola en el centro de la pantalla y le da un
    // direccion aleatoria
    public void bolaposi() {

        try {

            Thread.sleep(1200);
        } catch (final Exception s) {
        }
        x = (ANCHO / 2);
        y = (ALTO / 2) + aleat() + 10 - aleat() % 2;
        delta_t = 4;
        if (aleat() % 2 == 0) {
            vx = -vx;
        } else {
            vy = -vy;
        }

    }

    // Este metodo obtiene una imagen en la ruta especificada y la almacena en
    // memoria
    private BufferedImage getImagen(int n) {
        try {
            return ImageIO.read(new File(rutabolas[n]));
        } catch (Exception e) {
            return null;
        }
    }

    public int aleat()// Da un numero aleatorio de 0-12
    {
        int num = 0;
        num = (int) Math.random() * 12;
        return num;
    }

    // Este metodo pinta la imagen de la bola con la variable graphics que se le
    // fue pasada
    public void repinta(final Graphics2D g2) {

        g2.drawImage(bolas[chosebola], Math.round(x), Math.round(y), null);

    }
}