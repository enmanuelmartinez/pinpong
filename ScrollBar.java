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

    Main main1;
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int UP2 = 2;
    public static final int DOWN2 = 3;

    private boolean Teclas[] = new boolean[4];
    private double min_y_cordenate = 0;
    private double max_y_cordenate = 230;

    private double displacement = 25.0;
    private double displacement2 = 25.0;
    private double dy = 1;
    private double dy2 = 1;
    private int posX;
    private final String dir = Resource.getRootPath() + "resources/images" + File.separator;//direccion de las imagenes
    private final String rutaImagen[] = {dir + "paleta1.gif", dir + "paleta2.gif"};//barra uno y dos
    private BufferedImage barImage;


    public ScrollBar() {
        try {
            barImage = ImageIO.read(new File(Resource.Image.TRUNK_BAR));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Este metodo es el que registra cuales teclas fueron pulsadas en KeyListener
    public void setTeclas(int nTecla, boolean bvalor) {
        if (nTecla >= 0 && nTecla <= 3) {
            Teclas[nTecla] = bvalor;
        }
    }

    public double limitevertical(double posi_y) {

        if (posi_y <= -10) {
            return min_y_cordenate;
        }
        if (posi_y >= max_y_cordenate) {
            return max_y_cordenate;
        }
        return posi_y;
    }

    public void run() {

        final double incremento = 1.70;

        if (Teclas[UP]) {//UP del primer player
            dy -= incremento;
            displacement = dy;
        }
        if (Teclas[DOWN]) {//DOWN del primer player
            dy += incremento;
            displacement = dy;
        }
        if (Teclas[DOWN2]) {//UP del segundo player
            dy2 += incremento;
            displacement2 = dy2;
        }
        if (Teclas[UP2]) {//DOWN del segundo player
            dy2 -= incremento;
            displacement2 = dy2;
        }
        //esta parte aqui es para saber cual jugador pulso
        if (Teclas[UP] || Teclas[DOWN]) {
            displacement = limitevertical(displacement);
        }
        if (Teclas[UP2] || Teclas[DOWN2]) {
            displacement2 = limitevertical(displacement2);
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(this.barImage, 20, (int) Math.round(displacement), null);
        g2.drawImage(this.barImage, main1.ANCHO - 60, (int) Math.round(displacement2), null);

    }
}
