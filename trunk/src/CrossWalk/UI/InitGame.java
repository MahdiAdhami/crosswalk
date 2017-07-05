package CrossWalk.UI;

import CrossWalk.Utilities.Const;
import CrossWalk.AutoWork.SheepMoverInBotGame;
import CrossWalk.AutoWork.SheepMoverInReply;
import CrossWalk.AutoWork.CreateCarInReply;
import CrossWalk.AutoWork.CreateCarInNewGame;
import CrossWalk.Object.Line;
import CrossWalk.Object.Sheep;
import java.util.ArrayList;
import CrossWalk.Menu.GameSetting;

public class InitGame {

    public static boolean GameStop;
    public static boolean GameEnd;

    public InitGame() {
        InitGame.GameStop = false;
        InitGame.GameEnd = false;
    }

    public void autoMoveSheep(long sleepInMilliSecond, int randRate) {
        newGame();

        Sheep.AutoMove = true;

        // Create instance an object for auto move sheep
        Thread threadAutoMoveSheep = new Thread(new SheepMoverInBotGame(sleepInMilliSecond, randRate));
        threadAutoMoveSheep.start();
    }

    // Auto create Cars Method
    public void newGame() {
        boolean CreateReply = true;

        Sheep.AutoMove = false;
        InitGraphic.Sheep.setSaveChanges(CreateReply);

        // Create instance an object for create cars in a thread
        CreateCarInNewGame autoCreateCar = new CreateCarInNewGame(CreateReply);

        // Create instance an object for game graphics
        InitGraphic base = new InitGraphic(autoCreateCar.getLine());
        Thread threadBase = new Thread(base);
        threadBase.start();

        // Create instance an object for auto create car
        Thread threadAutoCreateCar = new Thread(autoCreateCar);
        autoCreateCar.InitLine();
        threadAutoCreateCar.start();
    }

    public void loadResumeGame(ArrayList<Line> lines) {
        boolean CreateReply = false;

        GameSetting.setSettingPath(Const.RESUME_SETTING_PATH);
        GameSetting.UpdateSettings();

        InitGraphic.Sheep.setSaveChanges(CreateReply);
        Sheep.AutoMove = false;

        // Create instance an object for create cars in a thread
        CreateCarInNewGame autoCreateCar = new CreateCarInNewGame(CreateReply);

        // Create instance an object for game graphics
        InitGraphic base = new InitGraphic(autoCreateCar.getLine());
        autoCreateCar.InitLineWithCars(lines);

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

        InitGraphic.Sheep.setSaveChanges(CreateReply);
        Sheep.AutoMove = false;

        CreateCarInReply reply = new CreateCarInReply(Const.ROOT_PATH + Const.REPLY_ROOT_ADDRESS + path + Const.REPLY_CAR_ADDRESS);
        reply.InitLine();
        Thread threadReply = new Thread(reply);
        threadReply.start();

        InitGraphic base = new InitGraphic(reply.getLine());
        Thread threadBase = new Thread(base);
        threadBase.start();

        SheepMoverInReply sheepReply = new SheepMoverInReply(Const.ROOT_PATH + Const.REPLY_ROOT_ADDRESS + path);
        Thread threadReplySheep = new Thread(sheepReply);
        threadReplySheep.start();
    }

}
