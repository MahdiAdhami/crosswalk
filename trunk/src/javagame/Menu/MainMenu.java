package javagame.Menu;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import javagame.Const;
import javagame.InitGame;
import javagame.SaveAndLoad;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainMenu extends Menu {

    public MainMenu() {
        super("شروع", 400, 300);
    }

    @Override
    protected void CreatePanel() {
        JPanel panel = new JPanel(new BorderLayout(1, 1));//new BorderLayout(1,1)
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        frame.setContentPane(panel);

        JPanel controls = new JPanel(new GridLayout(6, 0));

        panel.add(controls, BorderLayout.CENTER);

        JButton loadLastGame = CreateButton("بارگذاری اخرین بازی",
                (ActionEvent event) -> {
                    SaveAndLoad load = new SaveAndLoad();
                    if (load.isExistResumeFile()) {
                        InitGame start = new InitGame();
                        start.LoadResumeGame(load.LoadForResume());
                    } else {
                        JOptionPane.showMessageDialog(null, "اول باید یک بازی را دخیره کنید", "خطایی رخ داده", JOptionPane.ERROR_MESSAGE);
                    }
                }
        );

        controls.add(loadLastGame);

        JButton startGame = CreateButton("شروع بازی",
                (ActionEvent e) -> {
                    SelectMapMenu selectMapMenu = new SelectMapMenu(false);
                    selectMapMenu.Execute();
                }
        );
        controls.add(startGame);//,BorderLayout.CENTER

        JButton showRepliesButtons = CreateButton("مشاهده بازی ها",
                (ActionEvent e) -> {
                    if (new File(Const.ROOT_PATH + Const.REPLY_ROOT_ADDRESS).exists()) {
                        ReplyButton replyButton = new ReplyButton("بازی های قابل مشاهده", 500, 500);
                        replyButton.Execute();
                    } else {
                        JOptionPane.showMessageDialog(null, "اول باید یک بازی انجام دهید", "خطایی رخ داده", JOptionPane.ERROR_MESSAGE);
                    }

                }
        );
        controls.add(showRepliesButtons);

        JButton autoGame = CreateButton("حرکت خودکار ادمک",
                (ActionEvent e) -> {
                    SelectMapMenu selectMapMenu = new SelectMapMenu(true);
                    selectMapMenu.Execute();
                }
        );
        controls.add(autoGame);//,BorderLayout.CENTER

        JButton Setting = CreateButton("تنظیمات بازی",
                (ActionEvent event) -> {
                    GameSetting.SetDefaultSettingPath();
                    GameSetting.UpdateSettings();

                    SettingMenu settingMenu = new SettingMenu("تنظیمات", 300, 600);
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
