package CrossWalk.Object.MoveableObject;

import CrossWalk.Object.Line;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import CrossWalk.Const;
import CrossWalk.GameListener;
import CrossWalk.InitGame;
import CrossWalk.Menu.GameSetting;
import CrossWalk.StoreData.SaveAndLoad;
import CrossWalk.StoreData.WriteReplyData;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public final class Sheep {

    private float[] PositionOfSheep;
    private float[] SheepSize;
    private int[] Rate;
    private BufferedImage[] ImageOfSheep;
    private int ImageStatus;
    private final String ImageCode;
    private boolean SaveChanges;

    public static boolean AutoMove = false;
    public WriteReplyData WriteReplyData;

    @Override
    public String toString() {
        return String.format("Sheep,%d,%d,%d,%d,%d,%s", getXPositionForDraw(), getYPositionForDraw(), Rate[0], Rate[1], ImageStatus, ImageCode);
    }

    public Sheep(float[] PositionOfSheep, int[] Rate, int ImageStatus, String ImageCode) {
        this.PositionOfSheep = PositionOfSheep;
        this.Rate = Rate;
        this.ImageStatus = ImageStatus;
        this.ImageCode = ImageCode;
        initImage();
    }

    public Sheep(int[] Rate, float PositionYOfSheep) {
        this.ImageCode = String.valueOf(GameSetting.getSheepImageNumber() + 1);
        initImage();
        this.Rate = Rate;
        this.PositionOfSheep = new float[]{GameSetting.getCrosswalkMiddlePosition() - getSheepWidth() / 2, PositionYOfSheep - getSheepHeight() / 2};
    }

    private void initImage() {
        try {
            ImageOfSheep = new BufferedImage[4];

            ImageOfSheep[0] = ImageIO.read(new File(Const.ROOT_PATH + Const.SHEEP_PATH_IMAGE.replace("{0}", ImageCode + "-Up")));
            ImageOfSheep[1] = ImageIO.read(new File(Const.ROOT_PATH + Const.SHEEP_PATH_IMAGE.replace("{0}", ImageCode + "-Down")));
            ImageOfSheep[2] = ImageIO.read(new File(Const.ROOT_PATH + Const.SHEEP_PATH_IMAGE.replace("{0}", ImageCode + "-Right")));
            ImageOfSheep[3] = ImageIO.read(new File(Const.ROOT_PATH + Const.SHEEP_PATH_IMAGE.replace("{0}", ImageCode + "-Left")));

            SheepSize = new float[]{ImageOfSheep[0].getWidth(), ImageOfSheep[0].getHeight()};

        } catch (IOException ex) {
            System.err.println("Sheep Sheep() " + ex);
        }
    }

    public int getXPositionForDraw() {
        return (int) PositionOfSheep[0];
    }

    public int getYPositionForDraw() {
        return (int) PositionOfSheep[1];
    }

    public void setPosition(float[] positionOfSheep) {
        PositionOfSheep = positionOfSheep;
    }

    public int[] getRate() {
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
    
    public void setSaveChanges(boolean saveChanges){
        SaveChanges = saveChanges;
    }
    
    private void goUp() {
        if (Const.TOP_MARGIN - getSheepWidth() >= getYPositionForDraw()) {
            return;
        }
        ImageStatus = 0;
        PositionOfSheep[1] -= Rate[1];
        System.out.println(PositionOfSheep[0] + " " + PositionOfSheep[1]);
    }

    private void goDown() {
        if (((GameSetting.getLtrLineCount() + GameSetting.getRtlLineCount()) * Const.LINE_HEIGHT)+ Const.MIDDLE_LINE_HEIGHT + Const.TOP_MARGIN <= getYPositionForDraw()) {
            return;
        }
        ImageStatus = 1;
        PositionOfSheep[1] += Rate[1];
        System.out.println(PositionOfSheep[0] + " " + PositionOfSheep[1]);
    }

    private void goRight() {
        if (GameSetting.getCrosswalkMiddlePosition() + Const.CROSSWALK_WIDTH / 2 <= PositionOfSheep[0] + getSheepWidth()) {
            return;
        }
        ImageStatus = 2;
        PositionOfSheep[0] += Rate[0];
    }

    private void goLeft() {
        if (GameSetting.getCrosswalkMiddlePosition() - Const.CROSSWALK_WIDTH / 2 >= PositionOfSheep[0]) {
            return;
        }
        ImageStatus = 3;
        PositionOfSheep[0] -= Rate[0];
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == 84) {
            InitGame.GameStop = !InitGame.GameStop;
            SaveAndLoad saveGame = new SaveAndLoad();
            saveGame.SaveGameForResume(GameListener.Lines);
        }
        if (Sheep.AutoMove) {
            keyPressed(keyCode);
            return;
        }
        if (keyCode == KeyEvent.VK_UP || keyCode == 87) {
            keyPressed(87);
        } else if (keyCode == KeyEvent.VK_DOWN || keyCode == 83) {
            keyPressed(83);

        } else if (keyCode == KeyEvent.VK_RIGHT || keyCode == 68) {
            keyPressed(68);

        } else if (keyCode == KeyEvent.VK_LEFT || keyCode == 65) {
            keyPressed(65);
        }
        if (PositionOfSheep[1] == -30) {
            win();
        }
    }

    public void keyPressed(int keyCode) {
        if (SaveChanges) {
            WriteReplyData.appendSheepToFile(keyCode);
        }
        if (keyCode == 87) {
            goUp();
            CheckLine();
        } else if (keyCode == 83) {
            goDown();
            CheckLine();
        } else if (keyCode == 68) {
            goRight();
        } else if (keyCode == 65) {
            goLeft();
        }
    }

    public void CheckLine() {
        
        if(PositionOfSheep[1] <= GameSetting.getRtlLineCount() * 100)
        {
            if(PositionOfSheep[1] % 100 ==0)
            {
                Line.SheepCurrentLine = -1;
                System.out.println(Line.SheepCurrentLine);
            }
            else{
                Line.SheepCurrentLine = (int) Math.ceil(PositionOfSheep[1]/100);
                System.out.println(Line.SheepCurrentLine);
            }
        }
        else if ((PositionOfSheep[1] > GameSetting.getRtlLineCount() * 100 )&&( PositionOfSheep[1] <= GameSetting.getRtlLineCount() * 100 + Const.MIDDLE_LINE_HEIGHT)){
            Line.SheepCurrentLine = -1;
            System.out.println(Line.SheepCurrentLine);
        }
        else{
                if((PositionOfSheep[1] - Const.MIDDLE_LINE_HEIGHT) % 100 == 0)
                {
                    Line.SheepCurrentLine = -1;
                    System.out.println(Line.SheepCurrentLine);
                }
                else
                {
                    Line.SheepCurrentLine = (int) Math.ceil((PositionOfSheep[1] - Const.MIDDLE_LINE_HEIGHT)/100);
                    System.out.println(Line.SheepCurrentLine);
                }
        }
        
        
        
        
        
        
        
        
        
        
//        if(PositionOfSheep[1] < GameSetting.getRtlLineCount()*100 + Const.TOP_MARGIN)
//        {
//            if((PositionOfSheep[1]-5)%95==0 || (PositionOfSheep[1]%95==0))
//            {
//                Line.SheepCurrentLine = -1;
//                System.out.println(Line.SheepCurrentLine);
//                return;
//            }
//            
//        }
       // Line.SheepCurrentLine = (int) Math.ceil((PositionOfSheep[1] - Const.TOP_MARGIN) / Const.LINE_HEIGHT) ;
       // System.out.println(Line.SheepCurrentLine);
    }

    public void setRate(int[] rate) {
        Rate = rate;
    }

    public void win() {
        InitGame.GameEnd = true;
        JOptionPane.showMessageDialog(null, "بردی دیگه !", "اینجا آخر خطه!", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    public void gameOver() {
        InitGame.GameEnd = true;
        JOptionPane.showMessageDialog(null, "باختی جیگر!", "له شدی عزیزم", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

}
