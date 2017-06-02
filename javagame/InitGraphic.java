package javagame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class InitGraphic extends JPanel implements Runnable {

    // ArrayList use to keep lines
    public ArrayList<Line> Lines;
    // variable for keeping cross walk middle position
    private final int MiddleOfCrosswalkPosition;
    // Line Image
    private BufferedImage CrosswalkImage;
    // Sheep
    private final Sheep Sheep;

    // Constructor for init lines and window
    public InitGraphic(ArrayList<Line> Lines, int MiddleOfCrosswalkPosition, Sheep Sheep) {
        super();
        SetInit();

        this.Lines = Lines;
        this.MiddleOfCrosswalkPosition = MiddleOfCrosswalkPosition;
        try {
            CrosswalkImage = ImageIO.read(new File(Const.PATH + Const.CROSSWALK_IMAGE));
        } catch (IOException ex) {
            System.out.println(ex);
        }
        this.Sheep = Sheep;
    }

    // Initialize game window
    private void SetInit() {
        JFrame gameFrame = new JFrame(Const.GAME_NAME);
        gameFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {Sheep.keyPressed(e);}
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
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
        super.paintComponent(g);

        for (int i = 1; i < ((Const.GAME_WINDOWS_HEIGHT / 20) - 5); i++) {
            g.drawImage(CrosswalkImage, MiddleOfCrosswalkPosition - Const.CROSSWALK_WIDTH / 2, i * 20, this);
        }

//        g.drawImage(Sheep.getImage(), (int)Sheep.getXPosition(), (int)Sheep.getYPosition(), this);
        g.drawImage(Sheep.getImage(), (int)Sheep.getXPosition(), (int)Sheep.getYPosition(), this);

        Lines.stream().forEach((Linetemp) -> {
            Linetemp.getCars().stream().forEach((carTemp) -> {
                g.drawImage(carTemp.getCarType().getImage(), (int) carTemp.getHeadPosition(), Linetemp.getPosition(), this);
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
                Lines.stream().forEach((Linetemp) -> {
                    Linetemp.getCars().stream().forEach((Car carTemp) -> {
                        carTemp.MoveInLine();
                    });
                });

                try {
                    Thread.sleep(Const.SLEEP_TIME_RE_PAINTING);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }

                repaint();

            } catch (Exception e) {
                System.out.println("InitGraphic run() " + e);
            }
        }
    }

}
