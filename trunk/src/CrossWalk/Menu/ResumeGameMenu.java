package CrossWalk.Menu;

import CrossWalk.Const;
import CrossWalk.InitGame;
import CrossWalk.StoreData.ResumeAndLoad;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ResumeGameMenu extends Menu{
    public ResumeGameMenu() {
        super("توقف بازی", 400, 300);
    }

    @Override
    protected void createPanel() {
        JPanel panel = new JPanel(new BorderLayout(1, 1));//new BorderLayout(1,1)
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        frame.setContentPane(panel);

        JPanel controls = new JPanel(new GridLayout(6, 0));

        panel.add(controls, BorderLayout.CENTER);

        JButton loadLastGame = createButton("بارگذاری اخرین بازی",
                (ActionEvent event) -> {
                    
                }
        );

        controls.add(loadLastGame);

        JButton startGame = createButton("شروع بازی",
                (ActionEvent e) -> {
                    SelectMapMenu selectMapMenu = new SelectMapMenu(false);
                    selectMapMenu.Show();
                }
        );
        controls.add(startGame);//,BorderLayout.CENTER

        JButton showRepliesButtons = createButton("مشاهده بازی ها",
                (ActionEvent e) -> {
                    if (new File(Const.ROOT_PATH + Const.REPLY_ROOT_ADDRESS).exists()) {
                        ReplyButton replyButton = new ReplyButton();
                        replyButton.Show();
                    } else {
                        JOptionPane.showMessageDialog(null, "اول باید یک بازی انجام دهید", "خطایی رخ داده", JOptionPane.ERROR_MESSAGE);
                    }

                }
        );
        controls.add(showRepliesButtons);

        JButton autoGame = createButton("حرکت خودکار ادمک",
                (ActionEvent e) -> {
                    SelectMapMenu selectMapMenu = new SelectMapMenu(true);
                    selectMapMenu.Show();
                }
        );
        controls.add(autoGame);//,BorderLayout.CENTER

        JButton Setting = createButton("تنظیمات بازی",
                (ActionEvent event) -> {
                    GameSetting.SetDefaultSettingPath();
                    GameSetting.UpdateSettings();

                    SettingMenu settingMenu = new SettingMenu("تنظیمات", 300, 600);
                    settingMenu.Show();
                }
        );
        controls.add(Setting);

        JButton exit = createButton("خروج",
                (ActionEvent event) -> {
                    System.exit(0);
                }
        );
        controls.add(exit);
    }
}
