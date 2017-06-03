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

    public CarType(boolean LineDirection) {
        this.LineDirection = LineDirection;
        InitialCarImage();
    }

    private void InitialCarImage() {
        String Car = (char) (65 + Const.RAND.nextInt(Const.CAR_COUNT)) + "-" + ((LineDirection == Const.LINE_DIRECTION_LTR) ? "1" : "2");
        File CarImage = new File(Const.PATH + "\\src\\resources\\Cars\\" + Car + ".png");
        try {
            Image = ImageIO.read(CarImage);
            CarWidth = Image.getWidth();
            CarHeight = Image.getHeight();
        } catch (IOException ex) {
            System.err.println("CarType InitialCarImage() " + ex);
        }
    }

    public void setCarWidth(int carWidth) {
        CarWidth = carWidth;
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
}
