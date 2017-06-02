package javagame;

import java.io.File;
import javagame.Menu.SettingMenuResult;

public final class Const {

    // Project path from root 
    public final static String PATH = new File(".").getAbsolutePath();

    // Car count image
    public final static int CAR_COUNT = 2;

    // Game name
    public final static String GAME_NAME = "نام بازی";

    // Line 
    public final static int LINE_HEIGHT = 100;
    public final static boolean LINE_DIRECTION_RTL = true;
    public final static boolean LINE_DIRECTION_LTR = false;
    
    // Crosswalk
    public final static String CROSSWALK_IMAGE = "\\src\\resources\\Line\\Line2.png";
    public final static int CROSSWALK_WIDTH = 100;
    public final static int CROSSWALK_CHANGE_SPEED_DISTANCE = 30;
    public final static float CROSSWALK_CHANGE_SPEED_RATE = .2f;

    // Settings
    public final static String SETTING_FILE = "\\src\\resources\\Setting\\Setting.xml";

    // Game Size
    public final static int GAME_WINDOWS_WIDTH = 800;
    public final static int GAME_WINDOWS_HEIGHT = (SettingMenuResult.getBottomLineCount() + SettingMenuResult.getTopLineCount() + 1) * 100;
    
    // Create Car rate
    public final static long CREATE_CAR_RATE = 10000 - 9999;

    // Repaint game
    public final static long SLEEP_TIME_RE_PAINTING = 10;

    // Cars Speed Rate
    public final static int CREATE_CAR_SPEED_RATE = 120;

    //Sheep
    public final static String SHEEP_PATH_IMAGE = "\\src\\resources\\Sheep\\Sheep.png";

    //  Change speed in distance for each
    public final static int CHANGE_SPEED_DISTANCE_FOR_REACH = 30;
}
