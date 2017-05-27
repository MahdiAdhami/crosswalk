package javagame;

import javagame.Menu.MainMenu;
import javagame.Menu.SettingMenu;

public class JavaGame {

    public static void main(String[] args) {        
//  SettingMenuResult x = new SettingMenuResult(2,13,12);
//  x.SaveChanges();
                    
        MainMenu w = new MainMenu("شروع بازی", 200, 200);
        w.Execute();

//        InitGame init = new InitGame();
//        init.AutoCreateCar(1, 0, 400);
}

}
