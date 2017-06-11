package javagame;

import java.util.ArrayList;
import javagame.Menu.GameSetting;

public class InitGame {

    public static boolean GameStop;
    public static boolean RunAfterWait;

    public InitGame() {
        InitGame.GameStop = false;
        InitGame.RunAfterWait = true;
    }

    public void AutoMoveSheep(long sleepInMilliSecond, int randRate) {
        AutoCreateCar();

        Sheep.AutoMove = true;

        // Create instance an object for auto move sheep
        Thread threadAutoMoveSheep = new Thread(new AutoMoveSheep(sleepInMilliSecond, randRate));
        threadAutoMoveSheep.start();
    }

    // Auto create Cars Method
    public void AutoCreateCar() {
        boolean CreateReply = true;

        GameSetting.setDefaultSettingPath();
        GameSetting.UpdateSettings();

        Sheep.AutoMove = false;
        InitGraphic.Sheep.SaveChanges = CreateReply;

        // Create instance an object for create cars in a thread
        AutoCreateCarForCreateNewCar autoCreateCar = new AutoCreateCarForCreateNewCar(CreateReply);

        // Create instance an object for game graphics
        InitGraphic base = new InitGraphic(autoCreateCar.getLines());
        Thread threadBase = new Thread(base);
        threadBase.start();

        // Create instance an object for auto create car
        Thread threadAutoCreateCar = new Thread(autoCreateCar);
        autoCreateCar.InitLine();
        threadAutoCreateCar.start();
    }

    public void LoadResumeGame(ArrayList<Line> lines) {
        boolean CreateReply = false;

        GameSetting.setSettingPath(Const.SAVE_FILE_ADDRESS_SETTING);
        GameSetting.UpdateSettings();

        InitGraphic.Sheep.SaveChanges = CreateReply;
        Sheep.AutoMove = false;

        // Create instance an object for create cars in a thread
        AutoCreateCarForCreateNewCar autoCreateCar = new AutoCreateCarForCreateNewCar(CreateReply);

        // Create instance an object for game graphics
        InitGraphic base = new InitGraphic(autoCreateCar.getLines());
        autoCreateCar.InitLine(lines);

        Thread threadBase = new Thread(base);
        threadBase.start();

        // Create instance an object for auto create car
        Thread threadAutoCreateCar = new Thread(autoCreateCar);
        threadAutoCreateCar.start();
    }

    public void replyTheMovie(String path) {
        boolean CreateReply = false;

        GameSetting.setSettingPath(Const.REPLY_ROOT_ADDRESS + path + Const.REPLY_SETTING_ADDRESS);
        GameSetting.UpdateSettings();

        InitGraphic.Sheep.SaveChanges = CreateReply;
        Sheep.AutoMove = false;

        AutoCreateCarForReply reply = new AutoCreateCarForReply(Const.ROOT_PATH + Const.REPLY_ROOT_ADDRESS + path + Const.REPLY_CAR_ADDRESS);
        reply.InitLine();
        Thread threadReply = new Thread(reply);
        threadReply.start();

        InitGraphic base = new InitGraphic(reply.getLines());
        Thread threadBase = new Thread(base);
        threadBase.start();

        AutoMoveSheepForReply sheepReply = new AutoMoveSheepForReply(Const.ROOT_PATH + Const.REPLY_ROOT_ADDRESS + path);
        Thread threadReplySheep = new Thread(sheepReply);
        threadReplySheep.start();
    }

}
