package javagame;

import javagame.Menu.SettingMenuResult;

public class InitGame{
    // Auto create Cars Method
    public void AutoCreateCar(int RtlLineCount,int LtrLineCount,int middleOfCrosswalkPosition) {
        System.out.println(SettingMenuResult.getTopLineCount());
        System.out.println(SettingMenuResult.getBottomLineCount());
        
        // Instance an object for create cars in a thread
        AutoCreateCar autoCreateCar = new AutoCreateCar(RtlLineCount, LtrLineCount, middleOfCrosswalkPosition);

        Thread threadAutoCreateCar = new Thread(autoCreateCar);
        autoCreateCar.InitLine();
        threadAutoCreateCar.start();
        
         // Instance an object for game graphics
        InitGraphic base = new InitGraphic(autoCreateCar.getLines() , middleOfCrosswalkPosition);
        Thread threadBase = new Thread(base);
        threadBase.start();
    }

}
