package javagame;

import java.io.File;
import javagame.Menu.SettingMenuResult;

public final class Const {
    public final static int CAR_COUNT = 2;

    public final static String PATH = new File(".").getAbsolutePath();

    public final static String GAME_NAME = "نام بازی";

    public final static int LINE_HEIGHT = 100;

    public final static String CROSSWALK_IMAGE = "\\src\\resources\\Line\\Line.png";
    public final static int CROSSWALK_WIDTH = 100;
    public final static int CROSSWALK_CHANGE_SPEED_DISTANCE = 40;
    public final static float CROSSWALK_CHANGE_SPEED = 50;
    
    public final static String SETTING_FILE = "\\src\\resources\\Setting\\Setting.xml";

    public final static boolean LINE_DIRECTION_RTL = true;
    public final static boolean LINE_DIRECTION_LTR = false;

    public final static int GAME_WINDOWS_WIDTH = 800;
    public final static int GAME_WINDOWS_HEIGHT = (SettingMenuResult.getBottomLineCount() + SettingMenuResult.getTopLineCount() + 1 ) * 100;
    
    
    public final static long CREATE_CAR_RATE = 10000 - 9999;
    
    public final static long SLEEP_TIME_RE_PAINTING = 500;

    
    // Cars Speed Rate
    public final static int CREATE_CAR_SPEED_RATE = 40;
}
