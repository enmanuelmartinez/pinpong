import java.awt.Graphics2D;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

/*
 *  @author  ENMANUEL MARTINEZ GONZALEZ, ITT ,
 *  DATE     JUNIO 2009
 *  DERECHOS RESERVADOS
*/

public class Ball extends JComponent {

    private BallSelector ge;
    private Score puntajes;
    private ScrollBar bar;

    private BufferedImage image;

    final int diameter = 35;
    final static int ANCHO = 600;
    final static int ALTO = 350;
    private int x;
    private int y;
    private int vx;
    private int vy;
    int deltaTime, aun = 0;
    static int chosebola = 0;

    public static final String rutabolas[] = {
        Resource.Image.BASEBALL_BALL
    };

    public Ball(String ballImageResource, int x, int y, int vx, int vy) {

        deltaTime = 10;
        this.x = x;
        this.y= y;
        this.vx = vx;
        this.vy = vy;
        try {
            this.image = ImageIO.read(new File(ballImageResource));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void run(){
        accion(deltaTime);
    }

    public void draw(final Graphics2D g2) {
        g2.drawImage(this.image, Math.round(x), Math.round(y), null);
    }

    private void accion(float delta_t) {
        x += vx * delta_t;
        y += vy * delta_t;

        if (vy < 0 && y <= 10 || vy > 0 && y + diameter >= ALTO) {//rebote vertical
            vy = -vy;
        }

        /*if ((vx < 0 && x >= 5 && x <= 50) && (y >= bar.desplaza - 25 && y <= 100 + bar.desplaza)) {
            vx = -vx;
            aun += 5;//esto me da la posibilidad de aumentar velocidad cada vez que se pegue a la bola
            delta_t = aun;
        }// si pega tabla uno*/
        /*if ((vx > 0 && x >= ANCHO - 100 && x <= ANCHO - 80) && (y >= bar.desplaza2 - 25 && y <= 100 + bar.desplaza2)) {
            vx = -vx;
            aun += 5;//esto me da la posibilidad de aumentar velocidad cada vez que se pegue a la bola
            delta_t = aun;
        }// si pega tabla2*/
        if (vx < 0 && x <= -diameter - 14) {
            puntajes.puntosjugador2 ++;
            bolaposi();
        }// si se le anota a player1
        if (vx > 0 && x >= ANCHO + diameter) {
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
        deltaTime = 4;
        if (aleat() % 2 == 0) {
            vx = -vx;
        } else {
            vy = -vy;
        }

    }

    public int aleat()
    {
        int num = 0;
        num = (int) Math.random() * 12;
        return num;
    }

}