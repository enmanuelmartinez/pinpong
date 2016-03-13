
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;

/*
 * @author  ENMANUEL MARTINEZ GONZALEZ, ITT ,
*/

public class Clock extends Node implements Runnable {

    private int seconds;
    private int minutes;
    private String time = "";
    private Font font;
    private Thread thread;

    public Clock() {
        seconds = 0;
        minutes = 0;
        font = new Font ("Courier New", Font.BOLD, 40);
        thread = new Thread(this);
    }

    public  void start(){
        thread.start();
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

    public String getStringTime(){
        return this.time;
    }

    public Font getFont(){
        return this.font;
    }

    public void setFont(Font font){
        this.font = font;
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

    @Override
    public void update() {

    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.ORANGE);
        g2.setFont(font);
        g2.drawString(time, Main.ANCHO / 2, 40);
    }
}