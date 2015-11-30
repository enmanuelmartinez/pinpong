/*--------------------------------------------------
 *
 *  @author  ENMANUEL MARTINEZ GONZALEZ, ITT ,
 *  DATE     JULIO 2009
 *  DERECHOS RESERVADOS
 -------------------------------------------------*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")

//tiene el mismo funcionamiento que la clase hooseBackground
//solo que ahora se elige la bola
public class ChooseBall extends JFrame implements ActionListener {

    public static int count = 0;
    Ball ballacceso;
    Estadopantalla k;
    private JButton forwardButton;
    private JButton backButton;
    private Container container;

    public ChooseBall() {
        super("ChooseBall");
        container = getContentPane();
        ballacceso = new Ball();
        k = new Estadopantalla();
        JPanel panel = new JPanel();
        this.forwardButton = new JButton("-->");
        this.backButton = new JButton("<--");
        this.forwardButton.addActionListener(this);
        this.backButton.addActionListener(this);
        panel.add(this.backButton);
        panel.add(this.forwardButton);
        add(panel, BorderLayout.SOUTH);
        setSize(300, 180);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.forwardButton) {
            count++;
            if (count > 11) {
                count = 0;
            }
        }
        if (e.getSource() == this.backButton) {
            count--;
            if (count < 0) {
                count = 11;
            }
        }
        ballacceso.chosebola = count;
        repaint();
    }

    public void paint(Graphics gr) {
        super.paint(gr);
        gr.drawImage(getToolkit().getImage(ballacceso.rutabolas[count]), 110, 40, 55, 55, this);

    }


}