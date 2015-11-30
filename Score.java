
import java.awt.Graphics2D;
import java.awt.Color;


/*-------------------------------------------------
*
* @author  ENMANUEL MARTINEZ GONZALEZ, ITT ,
* DATE     JUNIO 2009
* DERECHOS RESERVADOS
--------------------------------------------------*/
//clase que maneja los puntajes de los jugadores

public class Score {
    public static int puntosjugador1;
    public static int puntosjugador2;

    public Score() {
        puntosjugador1 = 0;
        puntosjugador2 = 0;
    }

    public void dibujar(Graphics2D g2) {
        //pinta puntaje jugador uno en la parte inferior izquierda
        g2.setColor(Color.red);
        g2.drawString("JUGADOR#2=" + puntosjugador2 + "", Main.ANCHO - 114, Main.ALTO - 12);
        // //pinta puntaje jugador dos en la parte inferior derecha
        g2.setColor(Color.ORANGE);
        g2.drawString("JUGADOR#1=" + puntosjugador1 + "", 12, Main.ALTO - 12);
        // //pinta tiempo en la parte superior de pantalla pero en el centro de esta
        g2.drawString(Clock.time, Main.ANCHO / 2, 20);

    }

}