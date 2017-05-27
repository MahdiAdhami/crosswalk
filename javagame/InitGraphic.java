package javagame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class InitGraphic extends Frame implements Runnable {

    // ArrayList use to keep lines
    public ArrayList<Line> Lines;
    // variable for keeping cross walk middle position
    private final int MiddleOfCrosswalkPosition;
    private Image CrosswalkImage;

    // Constructor for init lines and window
    public InitGraphic(ArrayList<Line> Lines, int MiddleOfCrosswalkPosition) {
        this.Lines = Lines;
        this.MiddleOfCrosswalkPosition = MiddleOfCrosswalkPosition;
        try {
            CrosswalkImage = ImageIO.read(new File(Const.PATH + Const.CROSSWALK_IMAGE));
        } 
        catch (IOException ex) {
            System.out.println(ex);

        }
        SetInit();
    }

    // Initialize game window
    private void SetInit() {
        setTitle(Const.GAME_NAME);

        setSize(Const.GAME_WINDOWS_WIDTH, Const.GAME_WINDOWS_HEIGHT);
        setVisible(true);
        setAlwaysOnTop(true);
        setResizable(false);

        setBackground(Color.yellow);
        setFont(new Font("tahoma", 0, 12));
        setForeground(Color.red);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dimension.width / 2) - (Const.GAME_WINDOWS_WIDTH / 2), (dimension.height / 2) - (Const.GAME_WINDOWS_HEIGHT / 2));

    }

    // use for draw object in window
    @Override
    public void paint(Graphics g) {
        g.drawImage(CrosswalkImage, MiddleOfCrosswalkPosition - Const.CROSSWALK_WIDTH/2, 0, this);
        Lines.stream().forEach((Linetemp) -> {
            Linetemp.getCars().stream().forEach((carTemp) -> {
                g.drawImage(carTemp.getCarType().getImage(), (int) carTemp.getHeadPosition(), Linetemp.getPosition(), this);
            });
        });
    }

    // Implements  Thread 
    @Override
    public void run() {
        while (true) {
            try {
                Lines.stream().forEach((Linetemp) -> {
                    Linetemp.getCars().stream().forEach((carTemp) -> {
                        carTemp.MoveInLine();
                    });
                });
                try {
                    Thread.sleep(Const.SLEEP_TIME_RE_PAINTING);
                } catch (Exception e) {
                    System.out.println(e);
                }
                repaint();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
