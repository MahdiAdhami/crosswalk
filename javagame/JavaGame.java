package javagame;

import javagame.Menu.MainMenu;

public class JavaGame {

    public static void main(String[] args) {

        // Remove
        ////////////////////////////
        //  SettingMenuResult x = new SettingMenuResult(2,13,12);
        //  x.SaveChanges();
       // InitGame init = new InitGame();
      //  init.AutoCreateCar();
//        init.AutoMoveSheep(250,10);
        // Remove
        ////////////////////////////

         //Initialize main menu     
         MainMenu mainMenu = new MainMenu("شروع بازی", 200, 200);
                 mainMenu.Execute();
    }

}
