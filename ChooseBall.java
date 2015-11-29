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
public class ChooseBall extends JFrame implements ActionListener{

        boolean c=true;
        public static int count=0;
        Bola ballacceso;
        Estadopantalla k;
        JButton alante;JButton atras;
        Container con;
        Thread t;
      
        public ChooseBall() 
        {
        super("ChooseBall");
        con=getContentPane();
        ballacceso=new Bola(); 
        k=new Estadopantalla();
        JPanel p=new JPanel();
        alante=new JButton("-->");
        atras=new JButton("<--");
        alante.addActionListener(this);
        atras.addActionListener(this);
        p.add(atras);
        p.add(alante);
        add(p,BorderLayout.SOUTH);
        setSize(300,180);
    
        }


	
	@SuppressWarnings("static-access")
	public void actionPerformed(ActionEvent e)
{
	
if(e.getSource()==alante){count++;if(count>11){count=0;}}
if(e.getSource()==atras){count--;if(count<0){count=11;}}
ballacceso.chosebola=count;
repaint();
}
public void paint(Graphics gr)
{
	super.paint(gr);
	gr.drawImage(getToolkit().getImage(ballacceso.rutabolas[count]),110,40,55 ,55 , this);
	
}



}
        
        

