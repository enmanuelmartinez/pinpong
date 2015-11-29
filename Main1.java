import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.Image;
import java.nio.file.Path;
import java.nio.file.Paths;

/*-------------------------------------------------
 *
 * @author  ENMANUEL MARTINEZ GONZALEZ, ITT ,
 * DATE     JUNIO 2009
 * DERECHOS RESERVADOS
--------------------------------------------------*/
public class Main1 extends JFrame implements ActionListener, Runnable, KeyListener {

    private static final long serialVersionUID = 1L;
    midifiles midis;
    Cliente cliente;
    Bola ball;
    barras Barra;
    Estadopantalla estadopantalla;
    Datos datos;
    Puntajes puntajes;

    //declaracion de variables de control
    public static final int ANCHO = 600;
    public static final int ALTO = 350;
    private boolean estaFull = false;
    public static boolean modo1juego = false, modo2juego = false, inicio = false;
    boolean flag = true, estado = true;
    static GraphicsDevice grafica = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    static Thread hilomain;
    static Thread copiahilomain;

    //declaracion de menus, items
    private String Itemnames[] = {"Inicio", "Recor", "Records", "Exit"};
    private String Menunames[] = {"OPCIONES", "CREDITS", "HELP"};
    private JMenuItem MenuItems[] = new JMenuItem[Itemnames.length];
    private JMenu Menus[] = new JMenu[Menunames.length];
    private JMenuBar herramientasbar = new JMenuBar();
    JRadioButtonMenuItem[] settingItems = {
        new JRadioButtonMenuItem("Ball type"),
        new JRadioButtonMenuItem("BgdImage"),
        new JRadioButtonMenuItem("Fullscreen")
    };
    JMenu setting = new JMenu("Settings");
    ButtonGroup gruposetting;

    Graphics dg;
    JLabel letrero;
    Container con;
    Thread hilotiempo;
    Thread hilocliente2;

    public Main1() {
        super("$----------JUEGO ENMANUEL PINPONG cliente$");
        estadopantalla = new Estadopantalla();//inicializacion de clase Estadopantalla
        ball = new Bola();//inicializacion de clase Bola
        Barra = new barras();////inicializacion de clase barras
        puntajes = new Puntajes();////inicializacion de clase puntajes    
        this.setSize(ANCHO, ALTO + 20); // +20 por el borde de la ventana
        this.setResizable(false);//deshabilita cambiar de tamano a la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//activa el cerrado correcto de la ventana
        con = getContentPane();//se obtiene el contenedor del JFrame
        setJMenuBar(herramientasbar);

        for (int i = 0; i < Menunames.length; i++) {//se agregan los menus a la barra
            Menus[i] = new JMenu(Menunames[i]);//de herramientas
            herramientasbar.add(Menus[i]);
            if (i > 0) {
                Menus[i].addActionListener(this);//se agrega los ActionListener
            }
        }

        for (int i = 0; i < Itemnames.length; i++) {
            MenuItems[i] = new JMenuItem(Itemnames[i]);//se inician MenuItems
            if (i == 1) {
                Menus[0].add(setting);
            } else {
                Menus[0].add(MenuItems[i]);
                MenuItems[i].addActionListener(this);
            }
        }

        gruposetting = new ButtonGroup();//grupo de botones para agregar en settings

        for (int i = 0; i < 3; i++) {
            setting.add(settingItems[i]);
            gruposetting.add(settingItems[i]);
            settingItems[i].addActionListener(this);
        }
        addKeyListener(this);
        this.setVisible(true);
        hilomain = new Thread(this);
        copiahilomain = hilomain;

        hilomain.start();//se habilita hilo de esta clase
        hilomain.setPriority(2);//prioridad al hilo principal o motor del juego
        this.setLocationRelativeTo(null);


    }//FIN DEL CONSTRUCTOR DE ESTE JFRAME

    /************************************************
     * ****AQUI ESTAN LOS METODOS DE ATENDER A LAS ACCIONS ACTION PERFORMED
     ************************************************/
    public void actionPerformed(ActionEvent e) {
        int fuente = 0;

        //se revisa cual de las opciones se pulsan en los menus
        for (int i = 0; i < 3; i++) {
            if (e.getSource() == settingItems[i]) {
                fuente = i;
                break;
            }
        }
        for (int i = 0; i < MenuItems.length; i++) {
            if (e.getSource() == MenuItems[i]) {
                fuente = i + 3;
                break;
            }
        }

        //luego de identificar cual se pulso se hace un switch con el numero obtenido
        switch (fuente) {
            case 0://ahora sera selector de bola
                JFrame frame = new ChooseBall();
                frame.setVisible(true);
                frame.setResizable(false);
                System.out.println(Datos.Ruta() + "KLK");
                System.out.println(estadopantalla.dir);
                break;
            case 1://caso para seleccionar fondo
                JFrame frame2 = new ChooseBackground();
                frame2.setVisible(true);
                frame2.setResizable(false);
                break;
            case 2://caso para fullscreen
                fullscreen();
                break;
            case 3://caso para inicio
                if (estadopantalla.pantallaPrincipal) {
                    inicio = true;
                    System.out.println("has iniciado el juego");
                }
                break;
            case 5://caso para record
                break;
            case 6://caso de salida exit
                System.exit(0);
                break;
        }
    }

    public void run() {
        while (true) {
            try {                 //con este run,se ejecuta repaint
                Thread.sleep(10);//varias veces y hace que se cumpla el dobleBuffer
                repaint();
            } catch (Exception e) {
            }
        }
    }

    /*******************************************************
     * * AQUI SE ATIENDE A LAS ACCIONES DE LAS TECLAS
     * *
     *********************************************************/
    public void keyPressed(KeyEvent e) {
        desideTecla(e.getKeyCode(), true);
    }

    @SuppressWarnings("static-access")
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            ball.flagball = !ball.flagball;
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
        }
        desideTecla(e.getKeyCode(), false);
    }

    public void keyTyped(KeyEvent e) {
    }

    //ESTADOS DE TECLAS PARA BARRAS SE ADMINISTRAN AQUI
    @SuppressWarnings("static-access")
    public void desideTecla(int tecla, boolean bvalor) {

        switch (tecla) {
            case KeyEvent.VK_UP:
                Barra.setTeclas(barras.ARRIBA, bvalor);
                break;
            case KeyEvent.VK_DOWN:
                Barra.setTeclas(barras.ABAJO, bvalor);
                break;
            case KeyEvent.VK_END:
                Barra.setTeclas(barras.ABAJO2, bvalor);
                break;
            case KeyEvent.VK_HOME:
                Barra.setTeclas(barras.ARRIBA2, bvalor);
                break;
            case KeyEvent.VK_ENTER:

                break;
            case KeyEvent.VK_1:
                if (estadopantalla.pantallaPrincipal && inicio) {
                    modo1juego = true;//activa modo juego1 y comienza a jugar
                    estadopantalla.pantallaPrincipal = false;
                    estadopantalla.pantallaJuego = true;//habilita escenario de juego
                    Tiempo ten = new Tiempo();// se inicia objeto tipo Tiempo 
                    Thread hilotiempo = new Thread(ten);//se le pasa a un Thread
                    hilotiempo.start();//inicia el hilo del cronometro del juego 
                    midis = new midifiles();//inicializa la clase para el sonido
                    midis.repronum(estadopantalla.numfondo - 1);//toca el sonido correspondiente al fondo-seleccionado
                }
                break;
            case KeyEvent.VK_2:
                if (estadopantalla.pantallaPrincipal && inicio) {
                    Cliente hilon1 = new Cliente();//inicializa objeto		
                    hilon1.start();    //inicia hilo de la clase Cliente
                    estadopantalla.pantallaPrincipal = false;//desabilita pantalla inicial
                    estadopantalla.pantallaJuego = true;//habilita fondo de juego
                    modo2juego = true;//activa modo juego dos y por ello comienza a jugar
                    Tiempo ten = new Tiempo();// se inicia objeto tipo Tiempo  
                    hilotiempo = new Thread(ten);//se le pasa a un Thread
                    hilotiempo.start(); //comienza hilo de reloj de tiempo
                    estadopantalla.numfondo = 2;//si es en red se elige un fondo constante
                    Bola.chosebola = 3;//se elige una bola constante
                }
                break;

            case KeyEvent.VK_7:
                reiniciar();
                break;
        }
    }

    //Metodo maximiza pantalla
    public void fullscreen() {
        if (!estaFull) {
            grafica.setFullScreenWindow(this);
        } else {
            grafica.setFullScreenWindow(null);
        }
        estaFull = !estaFull;
    }

    //------------------------------------------------------------------------------
//este metodo es el corazon visual del juego le manda el objeto
//Graphics a todos los metodos de pintar en cada clase    
    public void dobleBuffer(Graphics2D g2) {
        estadopantalla.dibujar(g2);//DIBUJA LA PANTALLA
        if (modo1juego || modo2juego) {
            ball.Correrbola(g2);//dibuja bola
            Barra.Moverbarra(g2);//mueve barras
            puntajes.dibujar(g2);//dibuja puntuacion
        }
    }

    //reinicia el juego
    public void reiniciar() {
        inicio = false;
        estadopantalla.pantallaPrincipal = true;
        estadopantalla.pantallaJuego = false;
        modo1juego = false;
        modo2juego = false;
        Puntajes.puntosjugador1 = 0;
        Puntajes.puntosjugador2 = 0;
        Tiempo.segundos = 0;
        Tiempo.minuto = 0;
        Bola.flagball = true;
        Bola.x = 64;
        Bola.y = 150;
        barras.desplaza = 25.0;
        barras.desplaza2 = 25.0;
    }


    //@Override
    public void paint(Graphics g) {
        if (inicio) {
            Graphics2D g2 = (Graphics2D) g;//se convierte objeto a Graphics de 2d
            Image mImage = createImage(getWidth(), getHeight());//utiliza el ancho y alto de pantalla y se la hereda-
            //a la variable mImage de tipo Image
            dobleBuffer((Graphics2D) mImage.getGraphics());//llama a la funcion dobleBuffer y le manda la variable graphics
            g2.drawImage(mImage, 0, 20, this);
        } else {
            super.paint(g);//si no se esta en inicio se pinta al padre que es el JFrame
        }

    }

    //MAIN PRINCIPAL AQUI SE INICIA TODO
    public static void main(String[] ars) {
        new Main1();
    }
}
