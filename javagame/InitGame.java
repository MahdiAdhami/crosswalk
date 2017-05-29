package javagame;

import javagame.Menu.SettingMenuResult;

public class InitGame{
    
    // Auto create Cars Method
    public void AutoCreateCar(int RtlLineCount,int LtrLineCount,int middleOfCrosswalkPosition) {
        
        
        // Instance an object for create cars in a thread
        AutoCreateCar autoCreateCar = new AutoCreateCar(RtlLineCount, LtrLineCount, middleOfCrosswalkPosition);

         // Instance an object for game graphics
        InitGraphic base = new InitGraphic(autoCreateCar.getLines() , middleOfCrosswalkPosition);
        Thread threadBase = new Thread(base);
        threadBase.start();
        
        Thread threadAutoCreateCar = new Thread(autoCreateCar);
        autoCreateCar.InitLine();
        threadAutoCreateCar.start();
        
    }

}
