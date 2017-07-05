/*
Create Game
 */
package CrossWalk;

import CrossWalk.Menu.GameSetting;
import CrossWalk.Menu.MainMenu;
import CrossWalk.UI.InitGame;

public class StartGame {

    // start program 
    public static void main(String[] args) {
//        //Initialize main menu     
//        MainMenu mainMenu = new MainMenu();
//        mainMenu.show();
        GameSetting.SetDefaultSettingPath();
        GameSetting.UpdateSettings();
        InitGame start = new InitGame();
        start.newGame();

    }
}
