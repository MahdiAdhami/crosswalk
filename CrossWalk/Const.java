
package CrossWalk;

import java.io.File;
import java.security.SecureRandom;

public final class Const {

    // SecureRandom for create random object
    public final static SecureRandom RAND_METHOD = new SecureRandom();

    // Root path 
    public final static String ROOT_PATH = new File("").getAbsolutePath();

    // Game name
    public final static String GAME_NAME = "نام بازی";
    public final static String GAME_ICON = "\\src\\resources\\Game\\Icon.jpg";

    // Line
    public final static String LINE_IMAGE_ROOT_PATH = "\\src\\resources\\Line\\MainLines";
    public final static String LINE_IMAGE_PATH_WITH_PLACEHOLDER = "\\src\\resources\\Line\\MainLines\\Line_{0}.png";
    public final static int LINE_IMAGE_HEIGHT = 85;
    public final static int LINE_IMAGE_WIDTH = 87;
    public final static boolean LINE_DIRECTION_RTL = true;
    public final static boolean LINE_DIRECTION_LTR = false;
    public final static int LINE_DISTANCE_NEED_TO_CREATE_NEW_CAR = 100;

    // Middle Line
    public final static String MIDDLE_LINE_IMAGE_PATH = "\\src\\resources\\Line\\MiddleLine\\MiddleLine_1.png";
    public final static int MIDDLE_LINE_IMAGE_WIDTH = 50;
    public final static int MIDDLE_LINE_IMAGE_HEIGHT = 50;

    // Crosswalk
    public final static String CROSSWALK_IMAGE = "\\src\\resources\\Line\\Crosswalk\\Crosswalk_1.png";
    public final static int CROSSWALK_WIDTH = 100;
    public final static int CROSSWALK_HEIGHT = 20;

    // Car 
    public final static String CAR_IMAGE_ROOT_PATH = "\\src\\resources\\Cars";
    public final static int CAR_DISTANCE_TO_CROSSWALK_FOR_DECREASE_SPEED = 100;
    public final static float CAR_SPEED_RATE_NEAR_CROSSWALK = .4f;
    public final static int CAR_COUNT = 4;
    public final static int CAR_INCREASE_POSITION_IN_Y = 7;

    // Cars Speed Rate
    public final static int CAR_SPEED_RATE_NEW_CREATED = 25;
    public final static int CAR_CREATE_MAX_SLEEP_TIME = 3500;
    public final static int CAR_CREATE_MIN_SLEEP_TIME = 500;

    // TakeOver
    public final static int CAR_ENOUTH_SPACE_FOR_TAKEOVER = 80;
    public final static int CAR_SPEED_DISTANCE_FOR_REACH = 100;
    public final static float CAR_SPEED_RATE_DISTANCE_FOR_REACH = .5f;

    //Setting File
    public final static String MAIN_SETTING_FILE = "\\src\\resources\\Setting\\Setting.xml";

    // Game size
    public final static int GAME_WINDOWS_WIDTH = 1000;
    public final static int TOP_MARGIN = 35;

    // Repaint time
    public final static long SLEEP_TIME_RE_PAINTING = 30;

    // Sheep
    public final static String SHEEP_PATH_IMAGE = "\\src\\resources\\Sheep\\{0}.png";
    public final static String SHEEP_ROOT_IMAGE = "\\src\\resources\\Sheep";
    public final static int SHEEP_DISTANCE_LINE_WHEN_GAME_START = 30;
    public final static int SHEEP_IGONORE_DISTANCE_IN_CROSSWALK = 30;

    // Save and load
    public final static String RESUME_ROOT_PATH = "\\src\\resources\\Resume";
    public final static String RESUME_LINE_PATH = "\\src\\resources\\Resume\\Line.txt";
    public final static String RESUME_SHEEP_PATH = "\\src\\resources\\Resume\\Sheep.txt";
    public final static String RESUME_CAR_PATH = "\\src\\resources\\Resume\\Cars.txt";
    public final static String RESUME_SETTING_PATH = "\\src\\resources\\Resume\\Setting.xml";

    // Reply
    public final static String REPLY_ROOT_ADDRESS = "\\src\\resources\\Replies";
    public final static String REPLY_LINE_ADDRESS = "\\Line.txt";
    public final static String REPLY_CAR_ADDRESS = "\\Cars.txt";
    public final static String REPLY_SHEEP_ADDRESS = "\\Sheep.txt";
    public final static String REPLY_SETTING_ADDRESS = "\\Setting.xml";

    // Map
    public final static String MAP_ROOT_ADDRESS = "\\src\\resources\\Map";

}
