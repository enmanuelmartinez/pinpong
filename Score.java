
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
    private int points1 = 0;
    private int points2 = 0;
    private static Score instance;

    protected Score() {

    }

    public static Score getInstance(){
        if(null == instance){
            instance = new Score();
        }
        return instance;
    }

    public void incrementPoints(int player){
        if(1 == player){
            points1 ++;
        }else if (2 == player){
            points2 ++;
        }
    }

    public void clear(){
        points1 = 0;
        points2 = 0;
    }

    public void dibujar(Graphics2D g2) {
        //pinta puntaje jugador uno en la parte inferior izquierda
        g2.setColor(Color.red);
        g2.drawString("JUGADOR#2=" + points2 + "", Main.ANCHO - 114, Main.ALTO - 12);
        // //pinta puntaje jugador dos en la parte inferior derecha
        g2.setColor(Color.ORANGE);
        g2.drawString("JUGADOR#1=" + points1 + "", 12, Main.ALTO - 12);
        // //pinta tiempo en la parte superior de pantalla pero en el centro de esta
        g2.drawString(Clock.time, Main.ANCHO / 2, 20);

    }

}