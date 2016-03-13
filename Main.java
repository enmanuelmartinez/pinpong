
import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

/*
 * @author  ENMANUEL MARTINEZ GONZALEZ, ITT 2015-2016
*/

public class Main extends JFrame implements Runnable, KeyListener {

    private static final long serialVersionUID = 1L;

    private Audio audio;
    private Ball ball = null;
    private ScrollBar bar;
    private ScrollBar bar2;
    private Score scores;
    private Clock clock;
    private BufferedImage backgroundImage;

    public static final int ANCHO = 1024;
    public static final int ALTO = 500;
    private boolean isFullScreen = false;

    private Thread maintThread;
    private int runners = 0;
    private ScreenState screenState;

    public enum ScreenState {
        STARTSCREEN, ONGAME, PAUSED;
    }

    public Main() {
        super("PINPONG GAME");
        setSize(ANCHO, ALTO + 20); // +20 por el borde de la ventana
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(false);
        addKeyListener(this);
        try {
            backgroundImage = ImageIO.read(new File(Resource.Image.BACKGROUND_ENTER));
        } catch (Exception e) {
            e.printStackTrace();
        }
        screenState = ScreenState.STARTSCREEN;
        maintThread = new Thread(this);
        maintThread.setPriority(1);
        maintThread.start();
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
            } else if (screenState == ScreenState.PAUSED) {
                audio.start();
                maintThread.resume();
                screenState = ScreenState.ONGAME;
            }else if (screenState == ScreenState.STARTSCREEN) {
                start();
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
                bar2.setKeys(ScrollBar.DOWN, bvalor);
                break;
            case KeyEvent.VK_HOME:
                bar2.setKeys(ScrollBar.UP, bvalor);
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
        if (null == this.ball) {
            this.ball = new Ball(Resource.Image.MUSHROOM_BALL, 50, 50, 1, 1, this.getSize());
        }
        try {
            backgroundImage = ImageIO.read(new File(Resource.Image.BACKGROUND_MARIO));
        } catch (Exception e) {
            e.printStackTrace();
        }
        clock = new Clock();
        scores = Score.getInstance();
        bar = new ScrollBar(10, 50, 1.70);
        bar2 = new ScrollBar(this.ANCHO - 60, 50, 1.70);
        audio = new Audio();
        audio.reproduce(Resource.Media.SONG_SUPERMARIOWORLD_OVERWORLD_MIDI, 50);
        clock.start();
        screenState = ScreenState.ONGAME;
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
            clock.draw(g2dDouble);
            ball.draw(g2dDouble);
            bar.draw(g2dDouble);
            bar2.draw(g2dDouble);
            scores.draw(g2dDouble);
        }

        g2.drawImage(mImage, 0, 20, null);
    }

    private void detectCollision() {
        System.out.println("---------------------" + this.runners + "------------------------------");
        System.out.println("bar W" + bar.getWidth());
        System.out.println("bar H" + bar.getHeight());
        System.out.println("bar x" + bar.getX());
        System.out.println("bar y" + bar.getY());

        System.out.println("Ball x" + ball.getX());
        System.out.println("Ball y" + ball.getY());
        this.runners += 1;

        if ((ball.getVx() < 0 && ball.getX() >= bar.getX() && ball.getX() <= bar.getWidth()) && (ball.getY() >= bar.getY()
                && ball.getY() <= (bar.getY() + bar.getHeight() + ball.getDiameter() - 5))) {
            ball.setVx(-ball.getVx());

        }

        if ((ball.getVx() > 0 && ball.getX() >= ANCHO - bar2.getWidth() && ball.getX() <= ANCHO - 80) && (ball.getY() >= bar2.getY() - 25 && ball.getY() <= bar2.getY())) {
            ball.setVx(-ball.getVx());
        }
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(8);// Regulardor de frecuencia de refrescado
                if (screenState == ScreenState.ONGAME) {
                    ball.update();
                    bar.update();
                    bar2.update();
                    detectCollision();
                }
                draw(this.getGraphics());

            } catch (Exception e) {
            }

        }
    }

    public static void main(String[] ars) {
        new Main();
    }
}