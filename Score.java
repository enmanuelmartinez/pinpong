
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Font;
import java.awt.Color;

/*
* @author  ENMANUEL MARTINEZ GONZALEZ, ITT
*/

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

    public void draw(Graphics2D g2) {
        //pinta puntaje jugador uno en la parte inferior izquierda
        Font myFont = new Font ("Courier New", Font.BOLD, 40);
        g2.setFont(myFont);
        g2.setColor(Color.red);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawString("JUGADOR#2=" + points2 + "", Main.ANCHO - 350, Main.ALTO - 10);
        // //pinta puntaje jugador dos en la parte inferior derecha
        g2.setColor(Color.WHITE);
        g2.drawString("JUGADOR#1=" + points1 + "", 12, Main.ALTO - 10);
    }

}