import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/*--------------------------------------------------
 *
 *  @author  ENMANUEL MARTINEZ GONZALEZ, ITT ,
 *  DATE     JUNIO 2009
 *  DERECHOS RESERVADOS
--------------------------------------------------*/

public class ScrollBar {

    Main main1;
    public static final int ARRIBA = 0, ABAJO = 1, ARRIBA2 = 2, ABAJO2 = 3;
    private boolean Teclas[] = new boolean[4];
    private final double min = 0, max = 230;
    static double desplaza = 25.0, desplaza2 = 25.0;
    double dy = 1, dy2 = 1;
    private final String dir = Resource.getRootPath() + "resources/images" + File.separator;//direccion de las imagenes
    private final String rutaImagen[] = {dir + "paleta1.gif", dir + "paleta2.gif"};//barra uno y dos
    private BufferedImage barImage;//arreglo de BufferedImage


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

    //metodo que evita que se pase de los limite las barras
    public double limitevertical(double posi_y) {

        if (posi_y <= -10) {
            return min;
        }
        if (posi_y >= max) {
            return max;
        }
        return posi_y;
    }

    //metodo que decide cual barra mover
    public void run() {

        final double incremento = 1.70;

        if (Teclas[ARRIBA]) {//arriba del primer player
            dy -= incremento;
            desplaza = dy;
        }
        if (Teclas[ABAJO]) {//abajo del primer player
            dy += incremento;
            desplaza = dy;
        }
        if (Teclas[ABAJO2]) {//arriba del segundo player
            dy2 += incremento;
            desplaza2 = dy2;
        }
        if (Teclas[ARRIBA2]) {//abajo del segundo player
            dy2 -= incremento;
            desplaza2 = dy2;
        }
        //esta parte aqui es para saber cual jugador pulso
        if (Teclas[ARRIBA] || Teclas[ABAJO]) {
            desplaza = limitevertical(desplaza);
        }
        if (Teclas[ARRIBA2] || Teclas[ABAJO2]) {
            desplaza2 = limitevertical(desplaza2);
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(this.barImage, 20, (int) Math.round(desplaza), null);
        g2.drawImage(this.barImage, main1.ANCHO - 60, (int) Math.round(desplaza2), null);

    }
}
