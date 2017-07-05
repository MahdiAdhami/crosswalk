package CrossWalk;

import CrossWalk.Object.MoveableObject.Car;
import CrossWalk.Object.Line;
import CrossWalk.Object.MoveableObject.Sheep;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import CrossWalk.Menu.GameSetting;
import CrossWalk.Utilities.ExceptionWriter;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class InitGraphic extends JPanel implements Runnable {

    // ArrayList use to keep lines
    private ArrayList<Line> Lines;

    // variable for keeping cross walk middle position
    private final int MiddleOfCrosswalkPosition;

    // Crosswalk Image
    private Image CrosswalkImage;

    // Line Image
    private Image LineImage;

    // Middle line Image
    private Image MiddleLineImage;

    // Top line count
    private final int TopLineCount;

    // Sheep
    public static Sheep Sheep = new Sheep(new int[]{5, 25}, (Const.LINE_IMAGE_HEIGHT * (GameSetting.getRtlLineCount() + GameSetting.getLtrLineCount())) + Const.TOP_MARGIN + Const.MIDDLE_LINE_IMAGE_HEIGHT + Const.SHEEP_DISTANCE_LINE_WHEN_GAME_START);

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
        JFrame gameFrame = new JFrame(Const.GAME_NAME);

        try {
            CrosswalkImage = ImageIO.read(new File(Const.ROOT_PATH + Const.CROSSWALK_IMAGE_WITH_PLACEHOLDER.replace("{0}", String.valueOf(GameSetting.getCrossWalkImageNumber()))));
            LineImage = ImageIO.read(new File(Const.ROOT_PATH + Const.LINE_IMAGE_PATH_WITH_PLACEHOLDER.replace("{0}", String.valueOf(GameSetting.getLineImageNumber()))));
            MiddleLineImage = ImageIO.read(new File(Const.ROOT_PATH + Const.MIDDLE_LINE_IMAGE_PATH.replace("{0}", String.valueOf(GameSetting.getMiddleLineImageNumber()))));
            gameFrame.setIconImage(ImageIO.read(new File(Const.ROOT_PATH + Const.GAME_ICON)));
        } catch (IOException ex) {
            new ExceptionWriter().write(ex);
        }

        // Add listeners for mouse and keyboard events
        GameListener gameListener = new GameListener(this.Lines);

        gameFrame.addKeyListener(gameListener.KeyListener);
        gameFrame.addMouseListener(gameListener.MouseListener);

        //
        //gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        gameFrame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.out.println("Closed");
                InitGame.GameEnd = true;
                e.getWindow().dispose();
            }
        });

        int gameHeight = (GameSetting.getLtrLineCount() + GameSetting.getRtlLineCount()) * Const.LINE_IMAGE_HEIGHT + 4 * Const.TOP_MARGIN + Const.MIDDLE_LINE_IMAGE_HEIGHT;//+ Const.SHEEP_DISTANCE_LINE_WHEN_GAME_START;
        gameFrame.setSize(Const.GAME_WINDOWS_WIDTH, gameHeight);

        gameFrame.setVisible(true);
        gameFrame.setResizable(false);

        gameFrame.setBackground(Color.white);
        gameFrame.setFont(new Font("tahoma", 0, 12));
        gameFrame.setForeground(Color.red);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        gameFrame.setLocation((dimension.width / 2) - (Const.GAME_WINDOWS_WIDTH / 2), (dimension.height / 2) - (gameHeight / 2));
        gameFrame.add(this);
    }

    // Over ride paint method for draw object 
    @Override
    public void paintComponent(Graphics g) {
        // Call super methods for initialize
        super.paintComponent(g);

        // Draw lines
        Lines.stream().forEach((lineTemp) -> {
            for (int i = 1; i < (Const.GAME_WINDOWS_WIDTH); i += Const.LINE_IMAGE_WIDTH) {
                g.drawImage(LineImage, i, lineTemp.getPosition(), this);
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

        // Draw Sheep
        g.drawImage(Sheep.getImage(), Sheep.getXPositionForDraw(), Sheep.getYPositionForDraw(), this);

        // Draw cars
        try {
            Lines.stream().forEach((Linetemp) -> {
                Linetemp.getCars().stream().forEach((carTemp)
                        -> {
                    g.setFont(new Font("tahoma", 0, 30));
                    g.setColor(Color.BLUE);
//                    g.drawString(String.format("%d-%.1f-%.1f", carTemp.getId(), carTemp.getSpeed(), carTemp.TempCarSpeed), carTemp.getXPositionForDraw(), carTemp.getYPositionForDraw() + (Const.LINE_IMAGE_HEIGHT - carTemp.getCarType().getCarHeight()) / 2);
//                    g.drawString(String.format("%d -- %d -- %d -- %b", carTemp.getLine().getCarId(),carTemp.getId(),carTemp.getLine().getCars().size(),carTemp.IsFirstCar), carTemp.getXPositionForDraw(), carTemp.getYPositionForDraw() + (Const.LINE_IMAGE_HEIGHT - carTemp.getCarType().getCarHeight()) / 2);
//                    g.drawString(String.format("%b", carTemp.isFirstCar()), carTemp.getXPositionForDraw(), carTemp.getYPositionForDraw() + (Const.LINE_IMAGE_HEIGHT - carTemp.getCarType().getCarHeight()) / 2);
                    g.drawImage(carTemp.getCarType().getImage(), carTemp.getXPositionForDraw(), carTemp.getYPositionForDraw() + (Const.LINE_IMAGE_HEIGHT - carTemp.getCarType().getCarHeight()) / 2, this);
                });
            });

        } catch (Exception ex) {
            new ExceptionWriter().write(ex);
        }

        //Buttons
        g.setFont(new Font("tahoma", 0, 12));
        g.drawString(String.format("مرحله ی %d ", Sheep.getLevel()), Const.GAME_WINDOWS_WIDTH - 100, 20);
        g.drawString(String.format("امتیاز %d ", Sheep.getScore()), Const.GAME_WINDOWS_WIDTH - 175, 20);
        
        for(int i = 1 ; i <= Sheep.getLife() ; i++)
        {
            g.drawString(String.format("%c",254), Const.GAME_WINDOWS_WIDTH - 225 - (i*10), 20);
        }

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
            if (InitGame.GameEnd) {
                break;
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
                new ExceptionWriter().write(ex);
            }
        }
    }
}
