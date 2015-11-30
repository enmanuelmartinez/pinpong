import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Estadopantalla {

	public final String dir = "/home/enmanuel/Desktop/PROYECTO2006-1909/PROYECTO2006-1909/pinpong/resources/images" + File.separator;
	final String rutaImagen[] = { dir + "inicio.jpg", dir + "mario.jpg",
			dir + "mario.jpg", dir + "mario.jpg", dir + "mario.jpg",
			dir + "mario.jpg", dir + "mario.jpg", dir + "mario.jpg",
			dir + "mario.gif" };
	private BufferedImage pantalla[];
	// Estados del juego
	public boolean pantallaPrincipal;
	public boolean pantallaJuego;
	public static int numfondo = 1;

	public Estadopantalla() {
		pantallaPrincipal = true;
		pantallaJuego = false;

		pantalla = new BufferedImage[rutaImagen.length];
		for (int n = 0; n < rutaImagen.length; n++) {
			pantalla[n] = this.getImagen(n);
		}
	}

	private BufferedImage getImagen(int n) {
		try {
			return ImageIO.read(new File(rutaImagen[n]));
		} catch (Exception e) {
			return null;
		}
	}

	public void setFalse() {
		pantallaPrincipal = false;
		pantallaJuego = false;
	}

	public void dibujar(Graphics2D g2) {

		if (pantallaPrincipal == true) {
			g2.drawImage(pantalla[0], 0, 0, null);
		}

		if (pantallaJuego == true) {
			g2.drawImage(pantalla[numfondo], 0, 0, null);
		}
	}
}
