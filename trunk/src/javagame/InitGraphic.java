package javagame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javagame.Menu.GameSetting;
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

    // Middle line Image
    private Image MiddleLineImage;

    // Sheep
    public static Sheep Sheep = new Sheep(new int[]{5, 25}, (Const.LINE_HEIGHT * (GameSetting.getRtlLineCount() + GameSetting.getLtrLineCount())) + Const.TOP_MARGIN + Const.MIDDLE_LINE_HEIGHT + Const.SHEEP_DISTANCE_LINE_WHEN_GAME_START);

    // Constructor for init lines and window
    public InitGraphic(ArrayList<Line> Lines) {
        super();
        this.Lines = Lines;
        SetInit();
        this.MiddleOfCrosswalkPosition = GameSetting.getCrosswalkMiddlePosition();
    }

    // Initialize game window
    private void SetInit() {
        JFrame gameFrame = new JFrame(Const.GAME_NAME);

        try {
            CrosswalkImage = ImageIO.read(new File(Const.ROOT_PATH + Const.CROSSWALK_IMAGE));
            LineImage = ImageIO.read(new File(Const.ROOT_PATH + Const.LINE_IMAGE.replace("{0}", String.valueOf(GameSetting.getLineImageNumber()))));
            MiddleLineImage = ImageIO.read(new File(Const.ROOT_PATH + Const.MIDDLE_LINE_IMAGE));
            gameFrame.setIconImage(ImageIO.read(new File(Const.ROOT_PATH + Const.GAME_ICON)));
        } catch (IOException ex) {
            System.err.println("InitGraphic SetInit() " + ex);
        }

        // Add listeners for mouse and keyboard events
        GameListener gameListener = new GameListener(this.Lines);

        gameFrame.addKeyListener(gameListener.KeyListener);
        gameFrame.addMouseListener(gameListener.MouseListener);

        //
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int gameHeight = (GameSetting.getLtrLineCount() + GameSetting.getRtlLineCount()) * Const.LINE_HEIGHT + 4 * Const.TOP_MARGIN + Const.MIDDLE_LINE_HEIGHT;
        gameFrame.setSize(Const.GAME_WINDOWS_WIDTH, gameHeight);
        gameFrame.setVisible(true);
//        gameFrame.setAlwaysOnTop(true);
        gameFrame.setResizable(false);

        gameFrame.setBackground(Color.white);
        gameFrame.setFont(new Font("tahoma", 0, 12));
        gameFrame.setForeground(Color.red);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        gameFrame.setLocation((dimension.width / 2) - (Const.GAME_WINDOWS_WIDTH / 2), (dimension.height / 2) - (gameHeight / 2));
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

        // Draw Midde lines
        for (int i = 1; i < (Const.GAME_WINDOWS_WIDTH); i += Const.MIDDLE_LINE_WIDTH) {
            g.drawImage(MiddleLineImage, i, Const.TOP_MARGIN + (Const.LINE_HEIGHT * GameSetting.getRtlLineCount()), this);
        }

        // Draw crosswalk
        for (int i = 0; i < (Const.LINE_HEIGHT * (Lines.size()) + Const.MIDDLE_LINE_WIDTH) / Const.CROSSWALK_HEIGHT; i++) {
            g.drawImage(CrosswalkImage, MiddleOfCrosswalkPosition - Const.CROSSWALK_WIDTH / 2, (i * Const.CROSSWALK_HEIGHT) + Const.TOP_MARGIN, this);
        }

        // Draw Sheep
        g.drawImage(Sheep.getImage(), (int) Sheep.getXPosition(), (int) Sheep.getYPosition(), this);

        // Draw cars
        try {
            Lines.stream().forEach((Linetemp) -> {
                Linetemp.getCars().stream().forEach((carTemp)
                        -> {
                    g.setFont(new Font("tahoma", 0, 30));
                    g.setColor(Color.red);
                    //  g.drawString(String.format("%d", carTemp.getId()), (int) carTemp.getPositionForDraw(), Linetemp.getPosition() + carTemp.getCarType().getCarHeight() / 2);

                    g.drawImage(carTemp.getCarType().getImage(), (int) carTemp.getPositionForDraw(), Linetemp.getPosition() + (Const.LINE_HEIGHT - carTemp.getCarType().getCarHeight()) / 2, this);
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

        g.drawRoundRect(100, 5, 120, 23, 6, 6);
        g.drawString("ذخیره سازی بازی", 120, 20);

    }

    // Implements  Thread 
    @Override
    public void run() {
        while (true) {
            if (InitGame.GameStop) {
                continue;
            }
            try {
                Lines.stream().forEach((Line Linetemp) -> {
                    Linetemp.getCars().stream().forEach((Car carTemp) -> {
                        carTemp.MoveInLine();
                    });
                });

                Lines.stream().forEach((Line Linetemp) -> {
                    Linetemp.getCars().stream().forEach((Car carTemp) -> {
                        Linetemp.getCars().stream().forEach((Car carTemp2) -> {
                            if (Linetemp.getDirection() == Const.LINE_DIRECTION_LTR) {
                                if (carTemp.getId() - 1 == carTemp2.getId()) {
                                    if (carTemp.getHeadPosition() >= carTemp2.getEndPosition() - Const.CHANGE_SPEED_DISTANCE_FOR_REACH) {
//                                        if (carTemp.Line.CanCarOvertaking && carTemp.Line.isEmptyForTakover(carTemp)) {
//                                            Line li = Lines.get(carTemp.Line.getId());
//                                            carTemp.Line.Dispose(carTemp);
//                                            li.addCar(carTemp);
//                                        } else {
                                            carTemp.Speed = carTemp2.getSpeed();
//                                        }
                                    }
                                }
                            } else if (carTemp.getId() - 1 == carTemp2.getId()) {
                                if (carTemp.getHeadPosition() <= carTemp2.getEndPosition() + Const.CHANGE_SPEED_DISTANCE_FOR_REACH) {
                                    carTemp.Speed = carTemp2.getSpeed();
                                }
                            }
                        });
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
