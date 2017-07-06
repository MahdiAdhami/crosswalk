package CrossWalk.Menu;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import CrossWalk.Utilities.Const;
import CrossWalk.UI.InitGame;
import CrossWalk.StoreData.ResumeAndLoad;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainMenu extends Menu {

    public MainMenu() {
        super("شروع", 400, 300);
    }

    @Override
    protected void createPanel() {
        GridLayout grid = new GridLayout(6, 1, 10, 10);
        Frame.setLayout(grid);

        JPanel controls = new JPanel();
        controls.setLayout(grid);
        controls.setBorder(new EmptyBorder(10,10,10,10));

        JButton loadLastGame = createButton("بارگذاری اخرین بازی",
                (ActionEvent event) -> {
                    ResumeAndLoad load = new ResumeAndLoad();
                    if (load.isExistResumeFile()) {
                        InitGame start = new InitGame();
                        start.loadResumeGame(load.loadGame());
                    } else {
                        JOptionPane.showMessageDialog(null, "اول باید یک بازی را دخیره کنید", "خطایی رخ داده", JOptionPane.ERROR_MESSAGE);
                    }
                }
        );

        controls.add(loadLastGame);

        JButton startGame = createButton("شروع بازی",
                (ActionEvent e) -> {
                    SelectMapMenu selectMapMenu = new SelectMapMenu(false);
                    selectMapMenu.show();
                }
        );
        controls.add(startGame);//,BorderLayout.CENTER

        JButton showRepliesButtons = createButton("مشاهده بازی ها",
                (ActionEvent e) -> {
                    if (new File(Const.ROOT_PATH + Const.REPLY_ROOT_ADDRESS).exists()) {
                        ReplyMenu replyButton = new ReplyMenu();
                        replyButton.show();
                    } else {
                        JOptionPane.showMessageDialog(null, "اول باید یک بازی انجام دهید", "خطایی رخ داده", JOptionPane.ERROR_MESSAGE);
                    }

                }
        );
        controls.add(showRepliesButtons);

        JButton autoGame = createButton("حرکت خودکار ادمک",
                (ActionEvent e) -> {
                    SelectMapMenu selectMapMenu = new SelectMapMenu(true);
                    selectMapMenu.show();
                }
        );
        controls.add(autoGame);//,BorderLayout.CENTER

        JButton Setting = createButton("تنظیمات بازی",
                (ActionEvent event) -> {
                    GameSetting.SetDefaultSettingPath();
                    GameSetting.UpdateSettings();

                    SettingMenu settingMenu = new SettingMenu("تنظیمات", 300, 600);
                    settingMenu.show();
                }
        );
        controls.add(Setting);

        JButton exit = createButton("خروج",
                (ActionEvent event) -> {
                    System.exit(0);
                }
        );
        controls.add(exit);

        Frame.setContentPane(controls);

    }

}
