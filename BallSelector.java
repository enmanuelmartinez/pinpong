
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*
 *  @author  ENMANUEL MARTINEZ GONZALEZ, ITT ,
 */

public class BallSelector extends JDialog implements ActionListener {

    private int count;
    private JButton forwardButton;
    private JButton backButton;
    private JPanel panel;
    private Container container;

    public BallSelector() {
        super();
        container = getContentPane();
        panel = new JPanel();
        forwardButton = new JButton("-->");
        backButton = new JButton("<--");
        forwardButton.addActionListener(this);
        backButton.addActionListener(this);
        panel.add(backButton);
        panel.add(forwardButton);
        add(panel, BorderLayout.SOUTH);
        count = 0;
        setSize(300, 180);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == forwardButton) {
            count ++;
            if (count > 11) {
                count = 0;
            }
        }
        if (e.getSource() == backButton) {
            count--;
            if (count < 0) {
                count = 11;
            }
        }
        repaint();
    }

    public void paint(Graphics gr) {
        super.paint(gr);
        gr.drawImage(getToolkit().getImage(Resource.Image.BASEBALL_BALL), 110, 40, 55, 55, this);
    }

}