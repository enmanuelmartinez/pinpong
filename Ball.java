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

public class Ball extends JComponent {

    Main main1;
    private BallSelector ge;
    private Score puntajes;
    private ScrollBar bar;

    private BufferedImage image;

    final int diambola = 35;
    final static int ANCHO = 600;
    final static int ALTO = 350;
    static int x, y;
    static int vx, vy;
    int delta_t, aun = 0;
    static boolean flagball;
    static int chosebola = 0;
    public static boolean mandame = false;//variable que habilita la enviada de bola por red

    public static final String rutabolas[] = {
        Resource.Image.BASEBALL_BALL
    };

    public Ball(String ballImageResource) {

        delta_t = 4;//factor que multiplica el desplazamiento de la bola
        flagball = true;// habilita el pintado y el desplazamiento de la bola
        x = 64;//posicion inicial
        y = 150;//posicion inicial
        vx = 1;// define velocidad en x
        vy = 1;//define velocidad en y
        try {
            this.image = ImageIO.read(new File(ballImageResource));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void run(){
        accion(delta_t);//cambia de posicion la bola y hace comparaciones de limite
    }

    public void draw(final Graphics2D g2) {
        g2.drawImage(this.image, Math.round(x), Math.round(y), null);
    }

    // Metodo que cambia de posicion la bola
    private void accion(float delta_t) {
        x += vx * delta_t;
        y += vy * delta_t;

        if (vy < 0 && y <= 10 || vy > 0 && y + diambola >= ALTO) {//rebote vertical
            vy = -vy;
        }

        if ((vx < 0 && x >= 5 && x <= 50) && (y >= bar.desplaza - 25 && y <= 100 + bar.desplaza)) {
            vx = -vx;
            aun += 5;//esto me da la posibilidad de aumentar velocidad cada vez que se pegue a la bola
            delta_t = aun;
        }// si pega tabla uno
        if ((vx > 0 && x >= ANCHO - 100 && x <= ANCHO - 80) && (y >= bar.desplaza2 - 25 && y <= 100 + bar.desplaza2)) {
            vx = -vx;
            aun += 5;//esto me da la posibilidad de aumentar velocidad cada vez que se pegue a la bola
            delta_t = aun;
        }// si pega tabla2
        if (vx < 0 && x <= -diambola - 14) {
            puntajes.puntosjugador2 ++;
            bolaposi();
        }// si se le anota a player1
        if (vx > 0 && x >= ANCHO + diambola) {
            puntajes.puntosjugador1++;
            bolaposi();
        }

    }

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

    public int aleat()// Da un numero aleatorio de 0-12
    {
        int num = 0;
        num = (int) Math.random() * 12;
        return num;
    }

}