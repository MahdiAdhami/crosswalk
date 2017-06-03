package javagame;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Sheep {

    static  private float[] PositionOfSheep;
    private float[] SheepSize;
    private int Rate;
    private BufferedImage ImageOfSheep;

    public Sheep(int Rate) {
        this.Rate = Rate;
        SheepSize = new float[]{24, 21};
        PositionOfSheep = new float[]{(Const.GAME_WINDOWS_WIDTH - SheepSize[0]) / 2, Const.GAME_WINDOWS_HEIGHT - 80};
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

        if ((keyCode == KeyEvent.VK_UP || keyCode == 87)&& PositionOfSheep[1] >= 20) {
            PositionOfSheep[1] -= Rate;
            if(PositionOfSheep[1]<=20){
                
                JOptionPane.showMessageDialog(InitGraphic.gameFrame,"شما برنده شده اید " , "بابا خفن" , JOptionPane.INFORMATION_MESSAGE);
                InitGraphic.gameFrame.setVisible(false);
                
            }
            System.out.println(PositionOfSheep[1]);
        } else if ((keyCode == KeyEvent.VK_DOWN || keyCode == 83)&& PositionOfSheep[1] != Const.GAME_WINDOWS_HEIGHT - 80) {
            PositionOfSheep[1] += Rate;
            System.out.println(PositionOfSheep[1]);
        } else if ((keyCode == KeyEvent.VK_RIGHT || keyCode == 68)&& PositionOfSheep[0] != 428) {
            PositionOfSheep[0] += Rate;
            System.out.println(PositionOfSheep[0]);
        } else if ((keyCode == KeyEvent.VK_LEFT || keyCode == 65)&& PositionOfSheep[0] != 348)  {
            PositionOfSheep[0] -= Rate;
            System.out.println(PositionOfSheep[0]);
        }

    }
    
    public static int lineIdForCrash()
    {
        double check = (int) ((PositionOfSheep[1])/100);
        return (int) Math.ceil(check)+1;
    }

}
