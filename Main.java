
import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

/*
 * @author  ENMANUEL MARTINEZ GONZALEZ, ITT
*/

public class Main extends JFrame implements ActionListener, Runnable, KeyListener {

    private static final long serialVersionUID = 1L;
    private String Itemnames[] = {"Start", "Recor", "Records", "Exit"};
    private String Menunames[] = {"MAIN", "CREDITS", "HELP"};
    private JMenuItem MenuItems[] = new JMenuItem[Itemnames.length];
    private JMenu Menus[] = new JMenu[Menunames.length];
    private JMenuBar menuBar;
    JRadioButtonMenuItem[] settingItems = {
            new JRadioButtonMenuItem("Ball type"),
            new JRadioButtonMenuItem("BgdImage"),
            new JRadioButtonMenuItem("Fullscreen")
    };
    JMenu setting;
    ButtonGroup gruposetting;

    private Audio audio;
    private Ball ball = null;
    private ScrollBar bar;
    private Score scores;
    private Clock clock;
    private BufferedImage backgroundImage;

    public static final int ANCHO = 1024;
    public static final int ALTO = 500;
    private boolean isFullScreen = false;

    private Thread maintThread;
    private int runners = 0;
    public enum ScreenState {
        STARTSCREEN, ONGAME, PAUSED;
    }

    private ScreenState screenState;

    public Main() {
        super("PINPONG GAME");
        this.initComponents();
        setSize(ANCHO, ALTO + 20); // +20 por el borde de la ventana
        setResizable(false);
        addKeyListener(this);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            backgroundImage = ImageIO.read(new File(Resource.Image.BACKGROUND_MARIO));
        } catch (Exception e) {
            e.printStackTrace();
        }
        screenState = ScreenState.STARTSCREEN;
        maintThread = new Thread(this);
        maintThread.start();
        maintThread.setPriority(1);
    }

    public void initComponents() {
        menuBar = new JMenuBar();
        setting = new JMenu("Settings");
        setJMenuBar(menuBar);
        for (int i = 0; i < Menunames.length; i++) {
            Menus[i] = new JMenu(Menunames[i]);
            menuBar.add(Menus[i]);
            if (i > 0) {
                Menus[i].addActionListener(this);
            }
        }
        for (int i = 0; i < Itemnames.length; i++) {
            MenuItems[i] = new JMenuItem(Itemnames[i]);
            if (i == 1) {
                Menus[0].add(setting);
            } else {
                Menus[0].add(MenuItems[i]);
                MenuItems[i].addActionListener(this);
            }
        }
        gruposetting = new ButtonGroup();
        for (int i = 0; i < 3; i++) {
            setting.add(settingItems[i]);
            gruposetting.add(settingItems[i]);
            settingItems[i].addActionListener(this);
        }
    }

    public void actionPerformed(ActionEvent e) {
        int fuente = 0;

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
                JDialog backgroundSelector = new BackgroundSelector();
                backgroundSelector.setModal(true);
                backgroundSelector.setResizable(false);
                backgroundSelector.setLocationRelativeTo(this);
                backgroundSelector.setVisible(true);
                break;
            case 2:
                fullscreen();
                break;
            case 3:
                if (screenState == ScreenState.STARTSCREEN) {
                    menuBar.setVisible(false);
                   // menuBar.setBorderPainted(false);
                    //menuBar.setDoubleBuffered(false);
                    System.out.println("has iniciado el juego");
                }
                break;
            case 5:
                break;
            case 6:
                this.dispose();
                System.exit(0);
                break;
        }
    }

    public void keyPressed(KeyEvent e) {
        readKeyBoard(e.getKeyCode(), true);
    }

    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (screenState == ScreenState.ONGAME) {
                screenState = ScreenState.PAUSED;
                audio.stop();
                maintThread.suspend();
            } else {
                audio.start();
                maintThread.resume();
                screenState = ScreenState.ONGAME;
            }
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
                bar.setKeys(ScrollBar.UP, bvalor);
                break;
            case KeyEvent.VK_DOWN:
                bar.setKeys(ScrollBar.DOWN, bvalor);
                break;
            case KeyEvent.VK_END:
                //bar.setTeclas(ScrollBar.DOWN2, bvalor);
                break;
            case KeyEvent.VK_HOME:
                //bar.setTeclas(ScrollBar.UP2, bvalor);
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
        GraphicsDevice graphicDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if (!isFullScreen) {
            graphicDevice.setFullScreenWindow(this);
        } else {
            graphicDevice.setFullScreenWindow(null);
        }
        isFullScreen = !isFullScreen;
    }

    public void start() {
        if (screenState == ScreenState.STARTSCREEN) {
            screenState = ScreenState.ONGAME;
            if (null == this.ball) {
                this.ball = new Ball(Resource.Image.MUSHROOM_BALL, 50, 50, 1, 1, this.getSize());
            }
            clock = new Clock();
            scores = Score.getInstance();
            bar = new ScrollBar(20, 50, 1.70);
            clock.start();
            audio = new Audio();
            audio.reproduce(Resource.Media.SONG_SUPERMARIOWORLD_OVERWORLD_MIDI, 50);
        }
    }

    public void restart() {
        screenState = ScreenState.STARTSCREEN;
        clock.reset();
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Image mImage = createImage(getWidth(), getHeight());
        Graphics2D g2dDouble = (Graphics2D) mImage.getGraphics();
        g2dDouble.drawImage(backgroundImage, 0, 0, null);

        if (screenState == ScreenState.ONGAME) {
            ball.draw(g2dDouble);
            bar.draw(g2dDouble);
            scores.dibujar(g2dDouble);
        }
        g2.drawImage(mImage, 0, 20, null);
    }

    private void detectCollision(){

        System.out.println("---------------------"+this.runners+"------------------------------");
        System.out.println("bar W" + bar.getWidth());
        System.out.println("bar H" + bar.getHeight());
        System.out.println("bar x" + bar.getX());
        System.out.println("bar y" + bar.getY());

        System.out.println("Ball x" + ball.getX());
        System.out.println("Ball y" + ball.getY());
        this.runners +=1;

        if ((ball.getVx() < 0 && ball.getX() >= bar.getX() && ball.getX() <= bar.getWidth()) && (ball.getY() >= bar.getY()
                && ball.getY() <= (bar.getY() + bar.getHeight()  + ball.getDiameter()-5))) {
            ball.setVx(-ball.getVx());
            //vx = -vx;
            //aun += 5;//esto me da la posibilidad de aumentar velocidad cada vez que se pegue a la bola
            //delta_t = aun;
        }// si pega tabla uno*/
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(10);//varias veces y hace que se cumpla el dobleBuffer
                ball.run();
                bar.run();
                detectCollision();
                draw(this.getGraphics());
            } catch (Exception e) {
            }

        }
    }

    public static void main(String[] ars) {
        new Main();
    }
}