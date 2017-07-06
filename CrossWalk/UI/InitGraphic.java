package CrossWalk.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import CrossWalk.Menu.GameSetting;
import CrossWalk.Utilities.ExceptionWriter;
import CrossWalk.Utilities.Const;
import CrossWalk.Object.Car;
import CrossWalk.Object.Line;
import CrossWalk.Object.Sheep;

public class InitGraphic extends JPanel implements Runnable {

    // ArrayList use to keep lines
    private ArrayList<Line> Lines;

    // variable for keeping cross walk middle position
    private final int MiddleOfCrosswalkPosition;

    // Crosswalk Image
    private BufferedImage CrosswalkImage;

    // Line Image
    private BufferedImage LineImage;

    // Middle line Image
    private BufferedImage MiddleLineImage;

    // Top line count
    private final int TopLineCount;

    // Sheep
    public static Sheep Sheep = new Sheep();

    // Middle line Image
    private BufferedImage HeartImage;

    // Main Frame of game 
    private JFrame GameFrame;

    // Constructor for init lines and window
    public InitGraphic(ArrayList<Line> Lines) {
        super();
        this.Lines = Lines;
        setInit();
        this.MiddleOfCrosswalkPosition = GameSetting.getCrosswalkMiddlePosition();
        TopLineCount = (GameSetting.getChangedLinesDirections() == 0) ? GameSetting.getRtlLineCount() : GameSetting.getLtrLineCount();
    }

    // Initialize game window
    private void setInit() {
        GameFrame = new JFrame(Const.GAME_NAME);
        try {
            HeartImage = ImageIO.read(new File(Const.ROOT_PATH + Const.SHEEP_HEART_PATH_IMAGE));
            CrosswalkImage = ImageIO.read(new File(Const.ROOT_PATH + Const.CROSSWALK_IMAGE_WITH_PLACEHOLDER.replace("{0}", String.valueOf(GameSetting.getCrossWalkImageNumber()))));
            LineImage = ImageIO.read(new File(Const.ROOT_PATH + Const.LINE_IMAGE_PATH_WITH_PLACEHOLDER.replace("{0}", String.valueOf(GameSetting.getLineImageNumber()))));
            MiddleLineImage = ImageIO.read(new File(Const.ROOT_PATH + Const.MIDDLE_LINE_IMAGE_PATH.replace("{0}", String.valueOf(GameSetting.getMiddleLineImageNumber()))));
            GameFrame.setIconImage(ImageIO.read(new File(Const.ROOT_PATH + Const.GAME_ICON)));
        } catch (IOException ex) {
            new ExceptionWriter().write("InitGraphic setInit()", ex, false);
        }

        // Add listeners for mouse and keyboard events
        GameListener gameListener = new GameListener(this.Lines);
        GameFrame.addKeyListener(gameListener.getKeyListener());

        GameFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                InitGame.GameEnd = true;
                e.getWindow().dispose();
            }
        });

        int gameHeight = (GameSetting.getLtrLineCount() + GameSetting.getRtlLineCount()) * Const.LINE_IMAGE_HEIGHT + 4 * Const.TOP_MARGIN + Const.MIDDLE_LINE_IMAGE_HEIGHT;//+ Const.SHEEP_DISTANCE_LINE_WHEN_GAME_START;
        GameFrame.setSize(Const.GAME_WINDOWS_WIDTH, gameHeight);

        GameFrame.setVisible(true);
        GameFrame.setResizable(false);

        GameFrame.setBackground(Color.white);
        GameFrame.setFont(new Font("tahoma", 0, 12));
        GameFrame.setForeground(Color.red);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        GameFrame.setLocation((dimension.width / 2) - (Const.GAME_WINDOWS_WIDTH / 2), (dimension.height / 2) - (gameHeight / 2));
        GameFrame.add(this);

    }

    // Over ride paint method for draw object 
    @Override
    public void paintComponent(Graphics g) {
        // Call super methods for initialize
        super.paintComponent(g);

        // Draw lines
        Lines.stream().forEach((lineTemp) -> {
            for (int i = 1; i < (Const.GAME_WINDOWS_WIDTH); i += Const.LINE_IMAGE_WIDTH) {
                g.drawImage(LineImage, i, lineTemp.getYPosition(), this);
            }
        });

        // Draw Midde line
        for (int i = 1; i < (Const.GAME_WINDOWS_WIDTH); i += Const.MIDDLE_LINE_IMAGE_WIDTH) {
            g.drawImage(MiddleLineImage, i, Const.TOP_MARGIN + (Const.LINE_IMAGE_HEIGHT * TopLineCount), this);
        }

        // Draw crosswalk
        for (int i = 0; i < (Const.LINE_IMAGE_HEIGHT * (Lines.size()) + Const.MIDDLE_LINE_IMAGE_WIDTH) / Const.CROSSWALK_HEIGHT; i++) {
            g.drawImage(CrosswalkImage, MiddleOfCrosswalkPosition - Const.CROSSWALK_WIDTH / 2, (i * Const.CROSSWALK_HEIGHT) + Const.TOP_MARGIN, this);
        }

        // Draw cars
        try {
            Lines.stream().forEach((Linetemp) -> {
                Linetemp.getCars().stream().forEach((carTemp)
                        -> {
                    g.setFont(new Font("tahoma", 0, 30));
                    g.setColor(Color.BLUE);
//                    g.drawString(String.format("%d-%.1f-%.1f", carTemp.getId(), carTemp.getSpeed(), carTemp.TempCarSpeed), carTemp.getXPositionForDraw(), carTemp.getYPositionForDraw() + (Const.LINE_IMAGE_HEIGHT - carTemp.getCarType().getCarHeight()) / 2);
//                    g.drawString(String.format("%d -- %d -- %d -- %b", carTemp.getLine().getCreatedCarCount(),carTemp.getId(),carTemp.getLine().getCars().size(),carTemp.IsFirstCar), carTemp.getXPositionForDraw(), carTemp.getYPositionForDraw() + (Const.LINE_IMAGE_HEIGHT - carTemp.getCarType().getCarHeight()) / 2);
                    g.drawImage(carTemp.getCarType().getImage(), carTemp.getXPositionForDraw(), carTemp.getYPositionForDraw() + (Const.LINE_IMAGE_HEIGHT - carTemp.getCarType().getCarHeight()) / 2, this);
                });
            });

        } catch (Exception ex) {
            new ExceptionWriter().write("InitGraphic paintComponents()", ex, false);
        }

        // Draw Sheep
        g.drawImage(Sheep.getImage(), Sheep.getXPositionForDraw(), Sheep.getYPositionForDraw(), this);

        //Buttons
        g.setFont(new Font("tahoma", 0, 12));
        g.drawString(String.format("مرحله ی %d ", Sheep.getLevel()), Const.GAME_WINDOWS_WIDTH - 100, 20);
        g.drawString(String.format("امتیاز %d ", Sheep.getScore()), Const.GAME_WINDOWS_WIDTH - 175, 20);

        for (int i = 1; i <= Sheep.getLife(); i++) {
            g.drawImage(HeartImage, Const.GAME_WINDOWS_WIDTH - 225 - (i * 15), 5, this);
        }

    }

    // Implements  Thread 
    @Override
    public void run() {
        while (true) {
            if (InitGame.GameEnd) {
                break;
            }
            if (InitGame.GameStop) {
                continue;
            }
            try {
                Lines.stream().forEach((Line tempLine) -> {
                    tempLine.getCars().stream().forEach((Car carTemp) -> {
                        Line temp = null;
                        try {
                            if (tempLine.getDirection() == Const.LINE_DIRECTION_LTR) {
                                temp = Lines.get(tempLine.getId() - 2);
                            } else {
                                temp = Lines.get(tempLine.getId());
                            }
                        } catch (Exception ex) {
                            temp = null;
                        }
                        carTemp.checkCarAccident(temp);
                        carTemp.move();
                    });
                });

                // Check cars accident
                // Repaint panel
                repaint();

                // Sleep time for repaint again
                Thread.sleep(Const.SLEEP_TIME_RE_PAINTING);

            } catch (Exception ex) {
                new ExceptionWriter().write("InitGraphic run()", ex, false);
            }
        }
        GameFrame.dispose();

    }
}
