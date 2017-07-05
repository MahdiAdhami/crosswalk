package CrossWalk.Menu;

import CrossWalk.UI.InitGame;
import CrossWalk.Utilities.Const;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ResumeGameMenu extends Menu {

    public ResumeGameMenu() {
        super("توقف بازی", 200, 200);
        Frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                InitGame.GameStop = false;
                e.getWindow().dispose();
            }
        });
    }

    @Override
    protected void createPanel() {
        JPanel panel = new JPanel(new BorderLayout(1, 1));//new BorderLayout(1,1)
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        Frame.setContentPane(panel);

        JPanel controls = new JPanel(new GridLayout(6, 0));

        panel.add(controls, BorderLayout.CENTER);

        JButton loadLastGame = createButton("ادامه ی بازی",
                (ActionEvent event) -> {
                    InitGame.GameStop = true;

                    Frame.setVisible(false);
                    Frame.dispose();

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
    }
}
