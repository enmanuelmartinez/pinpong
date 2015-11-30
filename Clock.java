import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/*--------------------------------------------------
 *
 * @author  ENMANUEL MARTINEZ GONZALEZ, ITT ,
   DATE  29 JUNIO 2009
 * DERECHOS RESERVADOS
 --------------------------------------------------*/
//Esta clase es la que actua como un cronometro
public class Clock extends Thread {
    static int segundos = 0, minuto = 0;

    public static String time = "";

    public Clock() {

    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);

                segundos++;
                if (segundos == 60) {
                    segundos = 0;
                    minuto++;
                }

                time = "" + minuto + ":" + "" + segundos;
            } catch (Exception es) {
            }
        }
    }

}
