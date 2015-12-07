import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/*-
 *  @author  ENMANUEL MARTINEZ GONZALEZ, ITT ,
 *  DATE     JUNIO 2009
 *  DERECHOS RESERVADOS
*/

public class ScrollBar {

    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int UP2 = 2;
    public static final int DOWN2 = 3;

    private boolean keys[] = new boolean[2];
    private int minY = 0;
    private int maxY = 230;
    private int x;
    private int y;
    private double dy = 1;
    private double yIncrement;
    private final String dir = Resource.getRootPath() + "resources/images" + File.separator;
    private final String rutaImagen[] = {dir + "paleta1.gif", dir + "paleta2.gif"};
    private BufferedImage image;


    public ScrollBar(int x, int y, double yIncrement) {
        try {
            image = ImageIO.read(new File(Resource.Image.TRUNK_BAR));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.x = x;
        this.y = y;
        this.yIncrement = yIncrement;
    }

    public void setx(int x) {
        this.x = x;
    }

    public int getx() {
        return x;
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
