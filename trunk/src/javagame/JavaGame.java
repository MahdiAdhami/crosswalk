package javagame;

import javagame.Menu.MainMenu;
import javagame.Menu.GameSetting;

public class JavaGame {

    public static void main(String[] args) {

        // Remove
        ////////////////////////////
        //  SettingMenuResult x = new SettingMenuResult(2,13,12);
        //  x.SaveChanges();
//        Setting.UpdateSettings();
//        InitGame init = new InitGame();
//        init.AutoCreateCar(Setting.getRtlLineCount(), Setting.getLtrLineCount(),Setting.getCrosswalkMiddlePosition());
        // Remove
        ////////////////////////////

         //Initialize main menu     
            MainMenu mainMenu = new MainMenu("شروع بازی", 200, 200);
            mainMenu.Execute();
    }

}
