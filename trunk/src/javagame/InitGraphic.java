package javagame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javagame.Menu.GameSetting;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicArrowButton;

public class InitGraphic extends JPanel implements Runnable {

    // ArrayList use to keep lines
    public ArrayList<Line> Lines;

    // variable for keeping cross walk middle position
    private final int MiddleOfCrosswalkPosition;

    // Crosswalk Image
    private Image CrosswalkImage;

    // Line Image
    private Image LineImage;

    // Sheep
    public static final Sheep Sheep = new Sheep(5, (Const.LINE_HEIGHT * (GameSetting.getRtlLineCount() + GameSetting.getLtrLineCount())) + Const.TOP_MARGIN + Const.SHEEP_DISTANCE_LINE_WHEN_GAME_START);

    // Constructor for init lines and window
    public InitGraphic(ArrayList<Line> Lines) {
        super();
        SetInit();

        this.Lines = Lines;
        this.MiddleOfCrosswalkPosition = GameSetting.getCrosswalkMiddlePosition();
//        this.Sheep = new Sheep(5, (Const.LINE_HEIGHT * (GameSetting.getRtlLineCount() + GameSetting.getLtrLineCount())) + Const.TOP_MARGIN);

    }

    // Initialize game window
    private void SetInit() {
        JFrame gameFrame = new JFrame(Const.GAME_NAME);

        try {
            CrosswalkImage = ImageIO.read(new File(Const.PATH + Const.CROSSWALK_IMAGE));
            LineImage = ImageIO.read(new File(Const.PATH + Const.LINE_IMAGE));
            gameFrame.setIconImage(ImageIO.read(new File(Const.PATH + Const.GAME_ICON)));

        } catch (IOException ex) {
            System.err.println("InitGraphic SetInit() " + ex);
        }
        
        // Add listeners for mouse and keyboard event
        GameListener gameListener = new GameListener();
        gameFrame.addKeyListener(gameListener.KeyListener);
        gameFrame.addMouseListener(gameListener.MouseListener);

        //
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(Const.GAME_WINDOWS_WIDTH, Const.GAME_WINDOWS_HEIGHT);
        gameFrame.setVisible(true);
//        gameFrame.setAlwaysOnTop(true);
        gameFrame.setResizable(false);

        gameFrame.setBackground(Color.white);
        gameFrame.setFont(new Font("tahoma", 0, 12));
        gameFrame.setForeground(Color.red);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        gameFrame.setLocation((dimension.width / 2) - (Const.GAME_WINDOWS_WIDTH / 2), (dimension.height / 2) - (Const.GAME_WINDOWS_HEIGHT / 2));
        gameFrame.add(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        // Call super methods for initialize
        super.paintComponent(g);

        // Draw lines
        Lines.stream().forEach((lineTemp) -> {
            for (int i = 1; i < (Const.GAME_WINDOWS_WIDTH); i += Const.LINE_WIDTH) {
                g.drawImage(LineImage, i, lineTemp.getPosition(), this);
            }
        });

        // Draw crosswalk
        for (int i = 0; i < (Const.LINE_HEIGHT * (Lines.size())) / Const.CROSSWALK_HEIGHT; i++) {
            g.drawImage(CrosswalkImage, MiddleOfCrosswalkPosition - Const.CROSSWALK_WIDTH / 2, (i * Const.CROSSWALK_HEIGHT) + Const.TOP_MARGIN, this);
        }

        // Draw Sheep
        g.drawImage(Sheep.getImage(), (int) Sheep.getXPosition(), (int) Sheep.getYPosition(), this);

        // Draw cars
        try {
            Lines.stream().forEach((Linetemp) -> {
                Linetemp.getCars().stream().forEach((carTemp) -> {
                    g.drawImage(carTemp.getCarType().getImage(), (int) carTemp.getPositionForDraw(),Linetemp.getPosition() + carTemp.getCarType().getCarHeight() / 2, this);
                });
            });

        } catch (Exception ex) {
            System.err.println("InitGraphic paintComponent() " + ex.getMessage());
        }

        //Buttons
        g.setFont(new Font("tahoma", 0, 12));
        g.drawString(String.format("مرحله ی %d ", 5), Const.GAME_WINDOWS_WIDTH - 100, 20);
        g.drawString(String.format("امتیاز %d ", 5), Const.GAME_WINDOWS_WIDTH - 175, 20);

        g.drawRoundRect(10, 5, 70, 23, 5, 5);
        g.drawString("توقف بازی", 20, 20);

    }

    // Implements  Thread 
    @Override
    public void run() {
        while (true) {
            if(InitGame.GameStop){
               continue; 
            }
            try {
                Lines.stream().forEach((Line Linetemp) -> {
                    Linetemp.getCars().stream().forEach((Car carTemp) -> {
                        carTemp.MoveInLine();
                    });
                });

                // Repaint panel
                repaint();
                // Sleep time for repaint again
                Thread.sleep(Const.SLEEP_TIME_RE_PAINTING);

            } catch (Exception ex) {
                System.err.println("InitGraphic run() " + ex.getMessage());
            }
        }
    }

}

//////Comments Code
/*@Override
    protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(CrosswalkImage,MiddleOfCrosswalkPosition - Const.CROSSWALK_WIDTH/2, 0, this); // see javadoc for more info on the parameters
    Lines.stream().forEach((Linetemp) -> {
    Linetemp.getCars().stream().forEach((carTemp) -> {
    g.drawImage(carTemp.getCarType().getImage(), (int) carTemp.getHeadPosition(), Linetemp.getPosition(), this);
    });
    });
    }
 */ /*@O
    // use for draw object in window
    /* @Override
    public void paint(Graphics g) {
    g.drawImage(CrosswalkImage, MiddleOfCrosswalkPosition - Const.CROSSWALK_WIDTH/2, 0, this);
    Lines.stream().forEach((Linetemp) -> {
    Linetemp.getCars().stream().forEach((carTemp) -> {
    g.drawImage(carTemp.getCarType().getImage(), (int) carTemp.getHeadPosition(), Linetemp.getPosition(), this);
    });
    });
    super.paint(g);
    }*/
//////////////

/* setTitle(Const.GAME_NAME);
        
        setSize(Const.GAME_WINDOWS_WIDTH, Const.GAME_WINDOWS_HEIGHT);
        setVisible(true);
        setAlwaysOnTop(true);
        setResizable(false);
        
        setBackground(Color.yellow);
        setFont(new Font("tahoma", 0, 12));
        setForeground(Color.red);
        
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dimension.width / 2) - (Const.GAME_WINDOWS_WIDTH / 2), (dimension.height / 2) - (Const.GAME_WINDOWS_HEIGHT / 2));*/
