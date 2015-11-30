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
    //las im�genes
    private final String dir = Resource.getRootPath() + "resources/images" + File.separator;//direccion de las imagenes
    private final String rutaImagen[] = {dir + "paleta1.gif", dir + "paleta2.gif"};//barra uno y dos
    private BufferedImage palitos[];//arreglo de BufferedImage


    public ScrollBar() {
        palitos = new BufferedImage[rutaImagen.length];
        //aqui se obtienen las imagenes cuando se inicia la clase
        for (int n = 0; n < rutaImagen.length; n++) {
            palitos[n] = this.getImagen(n);
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
    public void Moverbarra(Graphics2D g2) {

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

        //despues de desplazar barras  las pinta
        pintabarra(g2);
    }

    //obtiene una BufferedImage apartir de la ruta especificada y la retorna
    private BufferedImage getImagen(int n) {
        try {
            return ImageIO.read(new File(rutaImagen[n]));
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("static-access")
    public void pintabarra(Graphics2D g2) {

        if (main1.modo1juego) {//si se esta en one player
            g2.drawImage(palitos[0], 20, (int) Math.round(desplaza), null);
            g2.drawImage(palitos[1], main1.ANCHO - 60, (int) Math.round(desplaza2), null);
        }
        if (main1.modo2juego)//si se esta en en dos player
        {
            g2.drawImage(palitos[0], 20, (int) Math.round(desplaza), null);
        }

    }
}
