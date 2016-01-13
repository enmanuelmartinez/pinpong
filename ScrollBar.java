
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/*-
 *  @author  ENMANUEL MARTINEZ GONZALEZ, ITT ,
*/

public class ScrollBar {

    public static final int UP = 0;
    public static final int DOWN = 1;

    private boolean keys[] = new boolean[2];
    private int minY = 0;
    private int maxY = 350;
    private int x;
    private int y;
    private double dy = 1;
    private double yIncrement;
    private int width;
    private int height;

    private BufferedImage image;


    public ScrollBar(int x, int y, double yIncrement) {
        try {
            image = ImageIO.read(new File(Resource.Image.V_BAR));
            width = image.getWidth();
            height = image.getHeight();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.x = x;
        this.y = y;
        this.yIncrement = yIncrement;
    }
    public int getHeight(){
        return this.height;
    }

    public int getWidth(){
        return this.width;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setMinY(int minY) {
        this.minY = minY;
    }

    public void setKeys(int nKey, boolean bvalor) {
        if (nKey >= 0 && nKey <= 3) {
            keys[nKey] = bvalor;
        }
    }

    public double limitVertical(double dy) {
        if (dy <= -10) {
            return minY;
        }
        if (dy >= maxY) {
            return maxY;
        }
        return dy;
    }

    public void run() {

        if (keys[UP]) {
            dy -= yIncrement;
        } else if (keys[DOWN]) {
            dy += yIncrement;
        }

        if (keys[UP] || keys[DOWN]) {
            dy = limitVertical(dy);
        }
        y = (int) Math.round(dy);
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, null);
    }
}
