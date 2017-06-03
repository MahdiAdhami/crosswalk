package javagame;

import javagame.Menu.MainMenu;
import javagame.Menu.Setting;
import javagame.Menu.SettingMenu;
import javagame.Menu.SettingMenuResult;

public class JavaGame {

    public static void main(String[] args) {

        // Remove
        ////////////////////////////
        //  SettingMenuResult x = new SettingMenuResult(2,13,12);
        //  x.SaveChanges();
        InitGame init = new InitGame();
        init.AutoCreateCar(Setting.getBottomLineCount(), Setting.getTopLineCount(), Setting.getCrosswalkMiddlePosition());
        // Remove
        ////////////////////////////
        
        
        // Initialize main menu     
//          MainMenu mainMenu = new MainMenu("شروع بازی", 200, 200);
//          mainMenu.Execute();
    }

}
