package javagame;

import java.io.File;
import java.security.SecureRandom;

public final class Const {

    // SecureRandom for create random object
    public final static SecureRandom RAND = new SecureRandom();

    // Root path 
    public final static String PATH = new File(".").getAbsolutePath();

    // Car count
    public final static int CAR_COUNT = 2;
    // Game name
    public final static String GAME_NAME = "نام بازی";
    public final static String GAME_ICON = "\\src\\resources\\Line\\Line2.png";

    // Line
    public final static String LINE_IMAGE = "\\src\\resources\\Line\\Line2.png";
    public final static int LINE_HEIGHT = 100;
    public final static int LINE_WIDTH = 87;

    public final static boolean LINE_DIRECTION_RTL = true;
    public final static boolean LINE_DIRECTION_LTR = false;

    // Crosswalk
    public final static String CROSSWALK_IMAGE = "\\src\\resources\\Line\\Crosswalk2.png";
    public final static int CROSSWALK_WIDTH = 100;
    public final static int CROSSWALK_HEIGHT = 20;
    public final static int CROSSWALK_CHANGE_SPEED_DISTANCE = 30;
    public final static float CROSSWALK_CHANGE_SPEED_RATE = .4f;

    //Setting File
    public final static String SETTING_FILE = "\\src\\resources\\Setting\\Setting.xml";

    // Game size
    public final static int GAME_WINDOWS_WIDTH = 800;
    public final static int GAME_WINDOWS_HEIGHT = 700; // اینو عوض نکن
    public final static int TOP_MARGIN = 20;
    public final static int BOTTOM_MARGIN = 100;

    // Create car rate
    public final static long CREATE_CAR_RATE = 10000 - 9900;

    // Repaint time
    public final static long SLEEP_TIME_RE_PAINTING = 10;

    // Cars Speed Rate
    public final static int CREATE_CAR_SPEED_RATE = 70;

    // Sheep
    public final static String SHEEP_PATH_IMAGE = "\\src\\resources\\Sheep\\Sheep{0}.png";

    // Change speed near other car 
    public final static int CHANGE_SPEED_DISTANCE_FOR_REACH = 100;
    public final static float CHANGE_SPEED_RATE_DISTANCE_FOR_REACH = .5f;

}
