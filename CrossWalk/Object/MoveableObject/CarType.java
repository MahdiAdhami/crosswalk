package CrossWalk.Object.MoveableObject;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import CrossWalk.Const;
import CrossWalk.Menu.GameSetting;
import javax.imageio.ImageIO;

public class CarType {

    private int CarWidth;
    private int CarHeight;

    private BufferedImage Image;
    private String CarNameAndType;

    // Create random car type
    public CarType(boolean LineDirection) {
        char[] tempCharForGetImage = GameSetting.getCarsNumbers().toCharArray();
        CarNameAndType = (tempCharForGetImage[Const.RAND.nextInt(tempCharForGetImage.length)]) + ((LineDirection == Const.LINE_DIRECTION_LTR) ? "1" : "2");
        initCarImage();
    }

    // Create car type from file data
    public CarType(boolean LineDirection, String CarNameAndType) {
        this.CarNameAndType = CarNameAndType;
        initCarImage();
    }

    // initialize car image
    private void initCarImage() {
        File CarImage = new File(Const.ROOT_PATH + "\\src\\resources\\Cars\\" + CarNameAndType + ".png");
        try {
            Image = ImageIO.read(CarImage);
            CarWidth = Image.getWidth();
            CarHeight = Image.getHeight();
        } catch (IOException ex) {
            System.err.println("CarType InitialCarImage() " + ex);
        }
    }

    // Get car width
    public int getCarWidth() {
        return CarWidth;
    }

    // Get car height
    public int getCarHeight() {
        return CarHeight;
    }

    // Get car image
    public Image getImage() {
        return Image;
    }

    // Get car name and car Type   
    public String getCarNameAndType() {
        return CarNameAndType;
    }

    // Set car name and car Type   
    public void setCarNameAndType(String path) {
        CarNameAndType = path;
    }

}
