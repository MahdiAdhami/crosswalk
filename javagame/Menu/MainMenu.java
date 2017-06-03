package javagame.Menu;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import javagame.InitGame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainMenu extends Menu {

    public MainMenu(String Title, int Height, int Width) {
        super(Title, Height, Width);
    }

    @Override
    protected void CreatePanel() {
        JPanel panel = new JPanel(new BorderLayout(1,1));//new BorderLayout(1,1)
        panel.setBorder(new EmptyBorder(10,10,10,10));

        frame.setContentPane(panel);

        JPanel controls = new JPanel(new GridLayout(6,0));
        
        panel.add(controls, BorderLayout.CENTER);
        
        JButton startGame = CreateButton("شروع بازی",
                (ActionEvent e) -> {
                    InitGame start = new InitGame();
                    start.AutoCreateCar();
                }
        );
        controls.add(startGame);//,BorderLayout.CENTER
        
        JButton Setting = CreateButton("تنظیمات بازی",
                (ActionEvent event) -> {
                    SettingMenu settingMenu = new SettingMenu("تنظیمات", 200, 400);
                    settingMenu.Execute();
                }
        );
        controls.add(Setting);
        
        JButton exit = CreateButton("خروج",
                (ActionEvent event) -> {
                    System.exit(0);
                }
        );
        controls.add(exit);
    }
  
}
