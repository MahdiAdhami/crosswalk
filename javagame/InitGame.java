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
        GameSetting.setDefaultSettingPath();
        GameSetting.UpdateSettings();

        AutoCreateCar();
        Sheep.AutoMove = true;

        // Create instance an object for auto move sheep
        Thread threadAutoMoveSheep = new Thread(new AutoMoveSheep(sleepInMilliSecond, randRate));
        threadAutoMoveSheep.start();
    }

    // Auto create Cars Method
    public void AutoCreateCar() {
        GameSetting.setDefaultSettingPath();
        GameSetting.UpdateSettings();
        
        Timer timer = new Timer();
        ReplyMovie replySaving = new ReplyMovie(timer);
        
        

        // Create instance an object for create cars in a thread
        AutoCreateCar autoCreateCar = new AutoCreateCar(replySaving);

        // Create instance an object for game graphics
        InitGraphic base = new InitGraphic(autoCreateCar.getLines());
        InitGraphic.Sheep.replySaving = replySaving;
        
        
        Thread threadBase = new Thread(base);
        threadBase.start();
        
       

        // Create instance an object for auto create car
        Thread threadAutoCreateCar = new Thread(autoCreateCar);
        autoCreateCar.InitLine();
        threadAutoCreateCar.start();
        
        

    }

    public void LoadResumeGame(ArrayList<Line> lines) {
        GameSetting.setSettingPath(Const.SAVE_FILE_ADDRESS_SETTING);
        GameSetting.UpdateSettings();
         
        Timer timer = new Timer();
        ReplyMovie replySaving = new ReplyMovie(timer);
        
        
        // Create instance an object for create cars in a thread
        AutoCreateCar autoCreateCar = new AutoCreateCar(replySaving);
        autoCreateCar.Lines = lines;

        // Create instance an object for game graphics
        InitGraphic base = new InitGraphic(autoCreateCar.getLines());
        InitGraphic.Sheep.replySaving = replySaving;
        
        Thread threadBase = new Thread(base);
        threadBase.start();
        
        

        // Create instance an object for auto create car
        Thread threadAutoCreateCar = new Thread(autoCreateCar);
        threadAutoCreateCar.start();
    }
    
    
    public void replyTheMovie(String path)
    {
        GameSetting.setSettingPath("\\src\\resources\\Replies\\" + path + "\\Setting.xml");
        GameSetting.UpdateSettings();
        
        AutoCreateCar autoCreateCar = new AutoCreateCar();
        autoCreateCar.InitLine();
        
        InitGraphic base = new InitGraphic(autoCreateCar.getLines());
        Thread threadBase = new Thread(base);
        threadBase.start();
        
        ReplyMovie reply = new ReplyMovie(Const.PATH + "\\src\\resources\\Replies\\" + path + "\\carFile.txt" , autoCreateCar.getLines());
        Thread threadReply = new Thread(reply);
        threadReply.start();
        
        AutoMoveSheepForReply sheepReply = new AutoMoveSheepForReply(Const.PATH + "\\src\\resources\\Replies\\" + path);
        Thread threadReplySheep = new Thread(sheepReply);
        threadReplySheep.start();
        
        
    }

}
