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
    private final boolean LineDirection ;
    private String CarPathString;
    
    public CarType(boolean LineDirection) {
        this.LineDirection = LineDirection;
        InitialCarImage();
    }
    
    public CarType(String carPath , int width , int height) {
        CarPathString = carPath;
        CarWidth = width;
        CarHeight = height;
        LineDirection = true ;  // برای رفع خطا
        InitialCarImageForLoad();
    }

    private void InitialCarImage() {
        CarPathString = (char) (65 + Const.RAND.nextInt(Const.CAR_COUNT)) + "-" + ((LineDirection == Const.LINE_DIRECTION_LTR) ? "1" : "2");
        File CarImage = new File(Const.PATH + "\\src\\resources\\Cars\\" + CarPathString + ".png");
        try {
            Image = ImageIO.read(CarImage);
            CarWidth = Image.getWidth();
            CarHeight = Image.getHeight();
        } catch (IOException ex) {
            System.err.println("CarType InitialCarImage() " + ex);
        }
    }
    
    private void InitialCarImageForLoad() {
        
        File CarImage = new File(Const.PATH + "\\src\\resources\\Cars\\" + CarPathString + ".png");
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
    public String getCarPathString()
    {
        return CarPathString;
    }
    
    public void setCarPathString(String path)
    {
        CarPathString = path;
    }
    
}
