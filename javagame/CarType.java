package javagame;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class CarType {

    private int CarWidth;
    private BufferedImage Image;
    private final boolean LineDirection;
    private final int CarRandom;

    public CarType(int CarRandom, boolean LineDirection) {
        this.LineDirection = LineDirection;
        this.CarRandom = CarRandom;
        InitialCarImage();
    }

     private void InitialCarImage()
     {
             {
        String Car = (char) CarRandom + "-" + ((LineDirection == Const.LINE_DIRECTION_LTR) ? "1" : "2");
        File CarImage = new File(Const.PATH + "\\src\\resources\\Cars\\" + Car + ".png");
        try {
            Image = ImageIO.read(CarImage);
        } catch (IOException ex) {
        }
        try {
            BufferedImage tempIMGForGetTheWidth = ImageIO.read(CarImage);
            CarWidth = tempIMGForGetTheWidth.getWidth();
        } catch (IOException ex) {
            Logger.getLogger(CarType.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

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
