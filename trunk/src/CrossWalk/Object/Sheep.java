package CrossWalk.Object;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import CrossWalk.Utilities.Const;
import CrossWalk.UI.InitGame;
import CrossWalk.Menu.GameSetting;
import CrossWalk.StoreData.WriteReplyData;
import java.io.Serializable;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public final class Sheep implements Drawable, Serializable {

    private float[] PositionOfSheep;
    private float[] SheepSize;
    private int[] MoveRate = new int[]{5, 25};
    private BufferedImage[] ShapeImage;
    private int ImageStatus;
    private final String ImageCode;
    private boolean SaveChanges;
    private int Level;
    private int Score;
    private int Life;
    private WriteReplyData WriteReplyData;

    public static boolean AutoMove = false;

    public Sheep(float[] PositionOfSheep, int[] Rate, int ImageStatus, String ImageCode, int Level, int Score, int Life) {
        this.Level = Level;
        this.Score = Score;
        this.Life = Life;
        this.PositionOfSheep = PositionOfSheep;
        this.MoveRate = Rate;
        this.ImageStatus = ImageStatus;
        this.ImageCode = ImageCode;
        initImage();
    }

    public Sheep() {
        Level = 1;
        Score = 0;
        Life = 3;
        this.ImageCode = String.valueOf(GameSetting.getSheepImageNumber());
        this.PositionOfSheep = new float[2];
        initImage();
        setDefaultPosition();
    }

    public void newSheep() {
        if (Sheep.AutoMove) {
            Level = 1;
            Score = 0;
            Life = 3;
            setDefaultPosition();
            Line.SheepCurrentLine = -1;
            ImageStatus = 0;
        }
    }

    @Override
    public String toString() {
        return String.format("Sheep,%d,%d,%d,%d,%d,%s,%d,%d,%d", getXPositionForDraw(), getYPositionForDraw(), MoveRate[0], MoveRate[1], ImageStatus, ImageCode, Level, Score, Life);
    }

    public final void setDefaultPosition() {
        PositionOfSheep[0] = GameSetting.getCrosswalkMiddlePosition() - getSheepWidth() / 2;
        PositionOfSheep[1] = (Const.LINE_IMAGE_HEIGHT * (GameSetting.getRtlLineCount() + GameSetting.getLtrLineCount()))
                + Const.TOP_MARGIN + Const.MIDDLE_LINE_IMAGE_HEIGHT + Const.SHEEP_DISTANCE_LINE_WHEN_GAME_START;
    }

    public void setWriteReplyData(WriteReplyData writeReplyData) {
        WriteReplyData = writeReplyData;
    }

    private void initImage() {
        try {
            ShapeImage = new BufferedImage[4];

            ShapeImage[0] = ImageIO.read(new File(Const.ROOT_PATH + Const.SHEEP_PATH_IMAGE.replace("{0}", ImageCode + "-Up")));
            ShapeImage[1] = ImageIO.read(new File(Const.ROOT_PATH + Const.SHEEP_PATH_IMAGE.replace("{0}", ImageCode + "-Down")));
            ShapeImage[2] = ImageIO.read(new File(Const.ROOT_PATH + Const.SHEEP_PATH_IMAGE.replace("{0}", ImageCode + "-Right")));
            ShapeImage[3] = ImageIO.read(new File(Const.ROOT_PATH + Const.SHEEP_PATH_IMAGE.replace("{0}", ImageCode + "-Left")));

            SheepSize = new float[]{ShapeImage[0].getWidth(), ShapeImage[0].getHeight()};

        } catch (IOException ex) {
            System.err.println("Sheep Sheep() " + ex);
        }
    }

    @Override
    public int getXPositionForDraw() {
        return (int) PositionOfSheep[0];
    }

    @Override
    public int getYPositionForDraw() {
        return (int) PositionOfSheep[1];
    }

    public void setPosition(float[] positionOfSheep) {
        PositionOfSheep = positionOfSheep;
    }

    public int[] getRate() {
        return MoveRate;
    }

    public float getSheepWidth() {
        return SheepSize[0];
    }

    public float getSheepHeight() {
        return SheepSize[1];
    }

    public void setSaveChanges(boolean saveChanges) {
        SaveChanges = saveChanges;
    }

    private void goUp() {
        if (Const.TOP_MARGIN - getSheepWidth() >= getYPositionForDraw()) {
            return;
        }
        Score += Const.SHEEP_SCORE_INCREASE / 10;
        ImageStatus = 0;
        PositionOfSheep[1] -= MoveRate[1];
    }

    private void goDown() {
        if (((GameSetting.getLtrLineCount() + GameSetting.getRtlLineCount()) * Const.LINE_IMAGE_HEIGHT)
                + Const.TOP_MARGIN + Const.MIDDLE_LINE_IMAGE_HEIGHT + Const.SHEEP_DISTANCE_LINE_WHEN_GAME_START <= getYPositionForDraw()) {
            return;
        }
        ImageStatus = 1;
        PositionOfSheep[1] += MoveRate[1];
    }

    private void goRight() {
        if (GameSetting.getCrosswalkMiddlePosition() + Const.CROSSWALK_WIDTH / 2 <= PositionOfSheep[0] + getSheepWidth()) {
            return;
        }
        ImageStatus = 2;
        PositionOfSheep[0] += MoveRate[0];
    }

    private void goLeft() {
        if (GameSetting.getCrosswalkMiddlePosition() - Const.CROSSWALK_WIDTH / 2 >= PositionOfSheep[0]) {
            return;
        }
        ImageStatus = 3;
        PositionOfSheep[0] -= MoveRate[0];
    }

    public void move(int keyCode) {
        if (SaveChanges) {
            WriteReplyData.appendSheepToFile(keyCode);
        }
        if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) {
            goUp();
        } else if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) {
            goDown();
        } else if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
            goLeft();
        } else if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D) {
            goRight();
        }
        checkLine();
        if (PositionOfSheep[1] == -25) {
            goToNextLevel();
        }
    }

    public void checkLine() {
        if (PositionOfSheep[1] <= GameSetting.getRtlLineCount() * Const.LINE_IMAGE_HEIGHT) {
            if (PositionOfSheep[1] % Const.LINE_IMAGE_HEIGHT == 0) {
                Line.SheepCurrentLine = -1;
            } else {
                Line.SheepCurrentLine = (int) Math.ceil(PositionOfSheep[1] / Const.LINE_IMAGE_HEIGHT);
            }
        } else if ((PositionOfSheep[1] <= (GameSetting.getRtlLineCount() * Const.LINE_IMAGE_HEIGHT) + Const.MIDDLE_LINE_IMAGE_HEIGHT)) {
            Line.SheepCurrentLine = -1;
        } else if ((PositionOfSheep[1] - Const.MIDDLE_LINE_IMAGE_HEIGHT) % Const.LINE_IMAGE_HEIGHT == 0) {
            Line.SheepCurrentLine = -1;
        } else {
            Line.SheepCurrentLine = (int) Math.ceil((PositionOfSheep[1] - Const.MIDDLE_LINE_IMAGE_HEIGHT) / Const.LINE_IMAGE_HEIGHT);
        }
    }

    public void setRate(int[] rate) {
        MoveRate = rate;
    }

    public void win() {
        InitGame.GameEnd = true;
        JOptionPane.showMessageDialog(null, "بردی دیگه !", "اینجا آخر خطه!", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    public void gameOver() {
        if (Life > 1) {
            Life--;
            setDefaultPosition();
            checkLine();
        } else {
            InitGame.GameEnd = true;
            JOptionPane.showMessageDialog(null, "باختی جیگر!", "له شدی عزیزم", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Implements Drawable
    @Override
    public BufferedImage getImage() {
        return ShapeImage[ImageStatus];
    }

    private void goToNextLevel() {
        Level++;
        Score += Const.SHEEP_SCORE_INCREASE;
        setDefaultPosition();
        checkLine();
    }

    public int getLevel() {
        return Level;
    }

    public int getScore() {
        return Score;
    }

    public int getLife() {
        return Life;
    }
}
