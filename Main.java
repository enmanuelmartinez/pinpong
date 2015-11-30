import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

/*-------------------------------------------------
 *
 * @author  ENMANUEL MARTINEZ GONZALEZ, ITT ,
 * DATE     JUNIO 2009
 * DERECHOS RESERVADOS
--------------------------------------------------*/
public class Main extends JFrame implements ActionListener, Runnable, KeyListener {

    private static final long serialVersionUID = 1L;
    private Audio audio;
    private Ball ball = null;
    private ScrollBar Barra;
    private Score puntajes;
    private Clock clock;
    private BufferedImage backgroundImage;

    //declaracion de variables de control
    public static final int ANCHO = 600;
    public static final int ALTO = 350;
    private boolean estaFull = false;
    public static boolean modo1juego = false;
    public static boolean inicio = false;
    static GraphicsDevice graphicDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    private Thread hilomain;

    //declaracion de menus, items
    private String Itemnames[] = {"Start", "Recor", "Records", "Exit"};
    private String Menunames[] = {"MAIN", "CREDITS", "HELP"};
    private JMenuItem MenuItems[] = new JMenuItem[Itemnames.length];
    private JMenu Menus[] = new JMenu[Menunames.length];
    private JMenuBar herramientasbar;
    JRadioButtonMenuItem[] settingItems = {
        new JRadioButtonMenuItem("Ball type"),
        new JRadioButtonMenuItem("BgdImage"),
        new JRadioButtonMenuItem("Fullscreen")
    };
    JMenu setting;
    ButtonGroup gruposetting;
    Container con;

    public enum ScreenState {
        STARTSCREEN , ONGAME, PAUSED;
    }

    private ScreenState screenState;

    public Main() {
        super("$----------JUEGO ENMANUEL PINPONG cliente$");
        screenState = ScreenState.STARTSCREEN;
        Barra = new ScrollBar();
        puntajes = new Score();
        try {
            backgroundImage = ImageIO.read(new File(Resource.Image.BACKGROUND_MARIO));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.initComponents();
        con = getContentPane();//se obtiene el contenedor del JFrame
        setSize(ANCHO, ALTO + 20); // +20 por el borde de la ventana
        setResizable(false);//deshabilita cambiar de tamano a la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//activa el cerrado correcto de la ventana
        addKeyListener(this);
        setVisible(true);
        hilomain = new Thread(this);

        hilomain.start();//se habilita hilo de esta clase
        hilomain.setPriority(2);//prioridad al hilo principal o motor del juego
        this.setLocationRelativeTo(null);
    }

    public void initComponents(){
        herramientasbar= new JMenuBar();
        setting = new JMenu("Settings");
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
    }

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

        switch (fuente) {
            case 0:
                JDialog ballSelectorDialog = new BallSelector();
                ballSelectorDialog.setModal(true);
                ballSelectorDialog.setResizable(false);
                ballSelectorDialog.setLocationRelativeTo(this);
                ballSelectorDialog.setVisible(true);
                break;
            case 1:
                JFrame frame2 = new BackgroundSelector();
                frame2.setVisible(true);
                frame2.setResizable(false);
                break;
            case 2://caso para fullscreen
                fullscreen();
                break;
            case 3://caso para inicio
                if (screenState == ScreenState.STARTSCREEN) {
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

    public void keyPressed(KeyEvent e) {
        readKeyBoard(e.getKeyCode(), true);
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            ball.flagball = !ball.flagball;
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
        }
        readKeyBoard(e.getKeyCode(), false);
    }

    public void keyTyped(KeyEvent e) {
    }

    public void readKeyBoard(int tecla, boolean bvalor) {

        switch (tecla) {
            case KeyEvent.VK_UP:
                Barra.setTeclas(ScrollBar.ARRIBA, bvalor);
                break;
            case KeyEvent.VK_DOWN:
                Barra.setTeclas(ScrollBar.ABAJO, bvalor);
                break;
            case KeyEvent.VK_END:
                Barra.setTeclas(ScrollBar.ABAJO2, bvalor);
                break;
            case KeyEvent.VK_HOME:
                Barra.setTeclas(ScrollBar.ARRIBA2, bvalor);
                break;
            case KeyEvent.VK_ENTER:

                break;
            case KeyEvent.VK_1:
                start();
                break;

            case KeyEvent.VK_7:
                restart();
                break;
        }
    }

    public void fullscreen() {
        if (!estaFull) {
            graphicDevice.setFullScreenWindow(this);
        } else {
            graphicDevice.setFullScreenWindow(null);
        }
        estaFull = !estaFull;
    }

    public void start(){
        if (screenState == ScreenState.STARTSCREEN && inicio) {
            screenState = ScreenState.ONGAME;
            if(null == this.ball){
                this.ball = new Ball(Resource.Image.BASEBALL_BALL);
            }
            clock= new Clock();// se inicia objeto tipo Tiempo
            clock.start();//inicia el hilo del cronometro del juego
            audio = new Audio();//inicializa la clase para el sonido
            audio.reproduce(Resource.Media.SONG_ZELDAOVERWORLD_MIDI);//toca el sonido correspondiente al fondo-seleccionado
        }
    }

    public void restart() {
        inicio = false;
        screenState = ScreenState.STARTSCREEN;
        modo1juego = false;
        Score.puntosjugador1 = 0;
        Score.puntosjugador2 = 0;
        Clock.segundos = 0;
        Clock.minuto = 0;
        Ball.flagball = true;
        Ball.x = 64;
        Ball.y = 150;
        ScrollBar.desplaza = 25.0;
        ScrollBar.desplaza2 = 25.0;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(10);//varias veces y hace que se cumpla el dobleBuffer
                ball.run();
                draw(this.getGraphics());
            } catch (Exception e) {
            }
        }
    }

    public void draw(Graphics g ){
        if (inicio) {
            Graphics2D g2 = (Graphics2D) g;//se convierte objeto a Graphics de 2d
            Image mImage = createImage(getWidth(), getHeight());//utiliza el ancho y alto de pantalla y se la hereda-
            Graphics2D g2dDouble = (Graphics2D) mImage.getGraphics();
            g2dDouble.drawImage(backgroundImage, 0, 0, null);

            ball.draw(g2dDouble);
            Barra.Moverbarra(g2dDouble);//mueve barras
            puntajes.dibujar(g2dDouble);//dibuja puntuacion

            g2.drawImage(mImage, 0, 20, this);
        }
    }


    public static void main(String[] ars) {
        new Main();
    }
}