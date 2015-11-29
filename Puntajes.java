/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//package game;

import java.awt.*;


/*-------------------------------------------------
*
* @author  ENMANUEL MARTINEZ GONZALEZ, ITT ,
* DATE     JUNIO 2009
* DERECHOS RESERVADOS
--------------------------------------------------*/
//clase que maneja los puntajes de los jugadores

public class Puntajes {
    Main1 main1;
    public static int puntosjugador1;
    public static int puntosjugador2;

    public Puntajes() {
        puntosjugador1 = 0;
        puntosjugador2 = 0;
    }

    public void dibujar(Graphics2D g2) {
        //pinta puntaje jugador uno en la parte inferior izquierda
        g2.setColor(Color.red);
        g2.drawString("JUGADOR#2=" + puntosjugador2 + "", Main1.ANCHO - 114, Main1.ALTO - 12);
        // //pinta puntaje jugador dos en la parte inferior derecha
        g2.setColor(Color.ORANGE);
        g2.drawString("JUGADOR#1=" + puntosjugador1 + "", 12, Main1.ALTO - 12);
        // //pinta tiempo en la parte superior de pantalla pero en el centro de esta
        g2.drawString(Tiempo.time, Main1.ANCHO / 2, 20);

    }

}