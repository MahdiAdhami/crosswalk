package javagame;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CarType {

    private int CarWidth;
    private int CarHeight;

    private BufferedImage Image;
    private final boolean LineDirection;
    private String CarNameAndType;

    public CarType(boolean LineDirection) {
        this.LineDirection = LineDirection;
        CarNameAndType = (char) (65 + Const.RAND.nextInt(Const.CAR_COUNT)) + "-" + ((LineDirection == Const.LINE_DIRECTION_LTR) ? "1" : "2");
        InitialCarImage();
    }

    public CarType(boolean LineDirection, String CarNameAndType) {
        this.LineDirection = LineDirection;
        this.CarNameAndType = CarNameAndType;
        InitialCarImage();
    }

    private void InitialCarImage() {
        File CarImage = new File(Const.ROOT_PATH + "\\src\\resources\\Cars\\" + CarNameAndType + ".png");
        try {
            Image = ImageIO.read(CarImage);
            CarWidth = Image.getWidth();
            CarHeight = Image.getHeight();
        } catch (IOException ex) {
            System.err.println("CarType InitialCarImage() " + ex);
        }
    }

    public int getCarWidth() {
        return CarWidth;
    }

    public int getCarHeight() {
        return CarHeight;
    }

    public Image getImage() {
        return Image;
    }

    public String getCarNameAndType() {
        return CarNameAndType;
    }

    public void setCarNameAndType(String path) {
        CarNameAndType = path;
    }

}
