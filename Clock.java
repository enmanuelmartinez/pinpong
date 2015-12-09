
import java.awt.Graphics2D;

/*
 * @author  ENMANUEL MARTINEZ GONZALEZ, ITT ,
*/

public class Clock extends Thread {

    private int seconds;
    private int minutes;
    public static String time = "";

    public Clock() {
        seconds = 0;
        minutes = 0;
    }

    public void setSeconds(int seconds){
        this.seconds = seconds;
    }

    public int getSeconds(){
        return seconds;
    }

    public void setMinutes(int minutes){
        this.minutes = minutes;
    }

    public int getMinutes(){
        return minutes;
    }

    public void reset(){
        seconds = 0;
        minutes = 0;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                seconds ++;
                if (seconds == 60) {
                    seconds = 0;
                    minutes ++;
                }
                time = "" + minutes + ":" + "" + seconds;
            } catch (Exception es) {
            }
        }
    }

    public void draw(final Graphics2D g2) {
    }

}
