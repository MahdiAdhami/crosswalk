package javagame;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javagame.Menu.GameSetting;
import javax.imageio.ImageIO;

public class Sheep {

    private float[] PositionOfSheep;
    private float[] SheepSize;
    private int Rate;
    private Image[] ImageOfSheep;
    private float[] MaxYPosition;
    private int ImageStatus;

    public Sheep(int Rate, float PositionYOfSheep) {
        this.Rate = Rate;
        SheepSize = new float[]{50, 50};

        this.PositionOfSheep = new float[]{GameSetting.getCrosswalkMiddlePosition() - getSheepWidth() / 2, PositionYOfSheep- getSheepHeight() / 2};

        this.MaxYPosition = new float[]{SheepSize[0], PositionYOfSheep};

        try {
            ImageOfSheep = new Image[4];
            ImageOfSheep[0] = ImageIO.read(new File(Const.PATH + Const.SHEEP_PATH_IMAGE.replace("{0}", "Up")));
            ImageOfSheep[1] = ImageIO.read(new File(Const.PATH + Const.SHEEP_PATH_IMAGE.replace("{0}", "Down")));
            ImageOfSheep[2] = ImageIO.read(new File(Const.PATH + Const.SHEEP_PATH_IMAGE.replace("{0}", "Right")));
            ImageOfSheep[3] = ImageIO.read(new File(Const.PATH + Const.SHEEP_PATH_IMAGE.replace("{0}", "Left")));
        } catch (IOException ex) {
            System.err.println("Sheep Sheep() " + ex);
        }
    }

    public float getXPosition() {
        return PositionOfSheep[0];
    }

    public float getYPosition() {
        return PositionOfSheep[1] ;
    }

    public void setRate(int rate) {
        Rate = rate;
    }

    public int getRate() {
        return Rate;
    }

    public float getSheepWidth() {
        return SheepSize[0];
    }

    public float getSheepHeight() {
        return SheepSize[1];
    }

    public Image getImage() {
        return ImageOfSheep[ImageStatus];
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP || keyCode == 87) {
            if (Const.TOP_MARGIN - getSheepWidth() >= getYPosition()) {
                return;
            }
            ImageStatus = 0;
            PositionOfSheep[1] -= Rate;

        } else if (keyCode == KeyEvent.VK_DOWN || keyCode == 83) {
            if (MaxYPosition[1] <= getYPosition()) {
                return;
            }
            ImageStatus = 1;
            PositionOfSheep[1] += Rate;
        } else if (keyCode == KeyEvent.VK_RIGHT || keyCode == 68) {
            if (GameSetting.getCrosswalkMiddlePosition() + Const.CROSSWALK_WIDTH / 2 <= PositionOfSheep[0] + getSheepWidth()) {
                return;
            }
            ImageStatus = 2;
            PositionOfSheep[0] += Rate;

        } else if (keyCode == KeyEvent.VK_LEFT || keyCode == 65) {
            if (GameSetting.getCrosswalkMiddlePosition() - Const.CROSSWALK_WIDTH / 2 >= PositionOfSheep[0]) {
                return;
            }
            ImageStatus = 3;
            PositionOfSheep[0] -= Rate;
        }
        System.out.println(getXPosition());
        CheckLine();

    }

    public void CheckLine() {
        Line.SheepCurrentLine = (int) Math.floor((PositionOfSheep[1] - Const.TOP_MARGIN) / Const.LINE_HEIGHT) + 1;
    }

//    public static int lineIdForCrash()
//    {
//        int check = (int) ((PositionOfSheep[1]+30)/100);
//        return check+1;
//    }
}
