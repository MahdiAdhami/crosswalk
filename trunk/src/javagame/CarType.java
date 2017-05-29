package javagame;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import javax.imageio.ImageIO;

public class CarType {

    private int CarWidth;
    private Image Image;
    private final boolean LineDirection;
    private final int CarRandom;

    public CarType(int CarRandom, boolean LineDirection) {
        this.LineDirection = LineDirection;
        this.CarRandom = CarRandom;
        InitialCarImage();
    }

    private void InitialCarImage() {
        String Car = (char) CarRandom + "-" + ((LineDirection == Const.LINE_DIRECTION_LTR) ? "1" : "2");
        try {
            Image = ImageIO.read(new File(Const.PATH + "\\src\\resources\\Cars\\" + Car + ".png"));
        } catch (IOException ex) {
        }
        CarWidth = 100;
    }

    public void setCarWidth(int carWidth) {
        CarWidth = carWidth;
    }

    public int getCarWidth() {
        return CarWidth;
    }

    public Image getImage() {
        return Image;
    }
}