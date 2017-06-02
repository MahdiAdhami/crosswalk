package javagame;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class CarType {

    private int CarWidth;
    private int CarHeight;

    private BufferedImage Image;
    private final boolean LineDirection;
    private final int CarRandom;

    public CarType(int CarRandom, boolean LineDirection) {
        this.LineDirection = LineDirection;
        this.CarRandom = CarRandom;
        InitialCarImage();
    }

    private void InitialCarImage() {
        {
            String Car = (char) CarRandom + "-" + ((LineDirection == Const.LINE_DIRECTION_LTR) ? "1" : "2");
            File CarImage = new File(Const.PATH + "\\src\\resources\\Cars\\" + Car + ".png");
            try {
                Image = ImageIO.read(CarImage);
                BufferedImage tempIMGForGetTheWidth = ImageIO.read(CarImage);
                CarWidth = tempIMGForGetTheWidth.getWidth();
                CarHeight = tempIMGForGetTheWidth.getHeight();
            } catch (IOException ex) {
                System.err.println("CarType InitialCarImage() " + ex);
            }
        }
    }

    public void setCarWidth(int carWidth) {
        CarWidth = carWidth;
    }

    public int getCarWidth() {
        return CarWidth;
    }
    public int getCarHeight(){
        return CarHeight;
    }
    public Image getImage() {
        return Image;
    }
}
