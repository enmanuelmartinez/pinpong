import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*--------------------------------------------------
 *
 *  @author  ENMANUEL MARTINEZ GONZALEZ, ITT ,
 *  DATE     JULIO 2009
 *  DERECHOS RESERVADOS
 -------------------------------------------------*/
//En esta clase se da la posibilidad de elegir el background(fondo)
@SuppressWarnings("serial")
public class ChooseBackground extends JFrame implements ActionListener {

    public static int count = 1;
    Estadopantalla k;
    JButton alante;
    JButton atras;

    public ChooseBackground()//constructor
    {
        super("ChooseBackground");
        k = new Estadopantalla();
        JPanel p = new JPanel();
        alante = new JButton("-->");
        atras = new JButton("<--");
        alante.addActionListener(this);
        atras.addActionListener(this);
        p.add(atras);
        p.add(alante);
        add(p, BorderLayout.SOUTH);
        setSize(300, 180);
    }

    @SuppressWarnings("static-access")
    public void actionPerformed(ActionEvent e) {
        //aqui se controla los botones atras y adelante para cambiar de fondo
        if (e.getSource() == alante) {
            count++;
            if (count > 8) {
                count = 1;
            }
        }
        if (e.getSource() == atras) {
            count--;
            if (count < 1) {
                count = 8;
            }
        }
        k.numfondo = count;
        repaint();
    }

    public void paint(Graphics gr) {
        super.paint(gr);
        //pinta una imagen de fondo en esta ventana para elegir el fondo del juego
        //aquin se utiliza las imagenes que fueron cargadas en la clase
        //Estapantalla
        gr.drawImage(getToolkit().getImage(k.rutaImagen[count]), 110, 40, 110, 100, this);
    }

}