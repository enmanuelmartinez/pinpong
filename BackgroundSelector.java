import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*--------------------------------------------------
 *
 *  @author  ENMANUEL MARTINEZ GONZALEZ, ITT ,
 *  DATE     JULIO 2009
 *  DERECHOS RESERVADOS
 -------------------------------------------------*/
public class BackgroundSelector extends JFrame implements ActionListener {

    public static int count = 1;
    JButton forwardButton;
    JButton backButton;

    public BackgroundSelector()//constructor
    {
        super("ChooseBackground");
        JPanel panel = new JPanel();
        forwardButton = new JButton("-->");
        backButton = new JButton("<--");
        forwardButton.addActionListener(this);
        backButton.addActionListener(this);
        panel.add(backButton);
        panel.add(forwardButton);
        add(panel, BorderLayout.SOUTH);
        setSize(300, 180);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == forwardButton) {
            count++;
            if (count > 8) {
                count = 1;
            }
        }else if (e.getSource() == backButton) {
            count--;
            if (count < 1) {
                count = 8;
            }
        }
        repaint();
    }

    public void paint(Graphics gr) {
        super.paint(gr);
        gr.drawImage(this.getToolkit().getImage(Resource.Image.BACKGROUND_MARIO), 110, 40, 110, 100, this);
    }

}