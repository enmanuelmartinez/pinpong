
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/*
 *  @author  ENMANUEL MARTINEZ GONZALEZ, ITT ,
*/

public class Ball extends Node{

    private int diameter = 35;
    private int x;
    private int y;
    private int vx;
    private int vy;
    int deltaTime;

    private Dimension screenSize;

    private BufferedImage image;

    public Ball(String ballImageResource, int x, int y, int vx, int vy, Dimension screenSize) {

        deltaTime = 10;
        this.x = x;
        this.y= y;
        this.vx = vx;
        this.vy = vy;
        this.screenSize = screenSize;
        try {
            this.image = ImageIO.read(new File(ballImageResource));
            this.diameter = this.image.getHeight();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setVx(int vx){
        this.vx = vx;
    }

    public int getDiameter(){
        return this.diameter;
    }

    public int getVx(){
        return this.vx;
    }

    public void setVy(int vy){
        this.vy = vy;
    }

    public int getVy(){
        return this.vy;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getX(){
        return this.x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getY(){
        return this.y;
    }

    public void update(){
        move(deltaTime);
    }

    public void draw(final Graphics2D g2) {
        g2.drawImage(this.image, Math.round(x), Math.round(y), null);
    }

    private void move(float delta_t) {
        x += vx * delta_t;
        y += vy * delta_t;

        if (vy < 0 && y <= 10 || vy > 0 && y + diameter >= this.screenSize.height) {//rebote vertical
            vy = -vy;
        }

        if (vx < 0 && x <= -diameter - 14) {
            Score.getInstance().incrementPoints(2);
            bolaposi();
        }// si se le anota a player1
        if (vx > 0 && x >= this.screenSize.width + diameter) {
            Score.getInstance().incrementPoints(1);
            bolaposi();
        }

    }

    public void bolaposi() {

        try {
            Thread.sleep(1200);
        } catch (final Exception s) {
        }
        x = (this.screenSize.width / 2);
        y = (this.screenSize.height/ 2) + aleat() + 10 - aleat() % 2;

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