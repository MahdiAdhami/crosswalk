package javagame;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sheep {

    private float[] PositionOfSheep;
    private float[] SheepSize;
    private int Rate;
    private BufferedImage ImageOfSheep;

    public Sheep(int Rate) {
        this.Rate = Rate;
        SheepSize = new float[]{24, 50};
        PositionOfSheep = new float[]{(Const.GAME_WINDOWS_WIDTH - SheepSize[0]) / 2, Const.GAME_WINDOWS_HEIGHT - SheepSize[1]};
        try {
            ImageOfSheep = ImageIO.read(new File(Const.PATH + Const.SHEEP_PATH_IMAGE));
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public float getXPosition() {
        return PositionOfSheep[0];
    }

    public float getYPosition() {
        return PositionOfSheep[1];
    }

    public void setRate(int rate) {
        Rate = rate;
    }

    public int getRate() {
        return Rate;
    }

    public BufferedImage getImage() {
        return ImageOfSheep;
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
            case 87:
                PositionOfSheep[1] -= Rate;
                break;
            case KeyEvent.VK_DOWN:
            case 83:
                PositionOfSheep[1] += Rate;
                break;
            case KeyEvent.VK_RIGHT:
            case 68:
                //&& PositionOfSheep[0] != Const.GAME_WINDOWS_WIDTH / 2 + Const.CROSSWALK_WIDTH / 2) {
                PositionOfSheep[0] += Rate;
                break;
            case KeyEvent.VK_LEFT:
            case 65:
                //&& PositionOfSheep[0] != Const.GAME_WINDOWS_WIDTH / 2 - Const.CROSSWALK_WIDTH / 2) {
                PositionOfSheep[0] -= Rate;
                break;
            default:
                break;
        }

    }

}
