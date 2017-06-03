package javagame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javagame.Menu.Setting;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
    private final Sheep Sheep;

    // Constructor for init lines and window
    public InitGraphic(ArrayList<Line> Lines, int MiddleOfCrosswalkPosition) {
           super();
        this.Lines = Lines;

        this.MiddleOfCrosswalkPosition = MiddleOfCrosswalkPosition;
        try {
            CrosswalkImage = ImageIO.read(new File(Const.PATH + Const.CROSSWALK_IMAGE));
            LineImage = ImageIO.read(new File(Const.PATH + Const.LINE_IMAGE));
        } catch (IOException ex) {
            System.err.println("InitGraphic InitGraphic() " + ex);
        }
        this.Sheep = new Sheep(5, (Const.LINE_HEIGHT * (Setting.getTopLineCount() + Setting.getBottomLineCount())) + Const.TOP_MARGIN);
        SetInit();
    }

    // Initialize game window
    private void SetInit() {
        JFrame gameFrame = new JFrame(Const.GAME_NAME);
        gameFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                Sheep.keyPressed(e);
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(Const.GAME_WINDOWS_WIDTH, Const.GAME_WINDOWS_HEIGHT);
        gameFrame.setVisible(true);
        gameFrame.setAlwaysOnTop(true);
        gameFrame.setResizable(false);

        gameFrame.setBackground(Color.white);
        gameFrame.setFont(new Font("tahoma", 0, 12));
        gameFrame.setForeground(Color.red);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        gameFrame.setLocation((dimension.width / 2) - (Const.GAME_WINDOWS_WIDTH / 2), (dimension.height / 2) - (Const.GAME_WINDOWS_HEIGHT / 2));

        gameFrame.add(this);

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
        Lines.stream().forEach((Linetemp) -> {
            Linetemp.getCars().stream().forEach((carTemp) -> {
                g.drawImage(carTemp.getCarType().getImage(), (int) carTemp.getHeadPosition(), Linetemp.getPosition() + carTemp.getCarType().getCarHeight() / 2, this);
            });
        });
    }

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
     */
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
    // Implements  Thread 
    @Override
    public void run() {
        while (true) {

            try {
                Lines.stream().forEach((Line Linetemp) -> {
                    Linetemp.getCars().stream().forEach((Car carTemp) -> {
                        carTemp.MoveInLine();
                    });
                });

                try {
                    Thread.sleep(Const.SLEEP_TIME_RE_PAINTING);
                } catch (InterruptedException e) {
                    System.err.println("InitGraphic run() " + e);
                }
//                Sheep.MoveInCrossWalk();
                repaint();

            } catch (Exception ex) {
                System.err.println("CarLtr run() " + ex);
            }
        }
    }

}
