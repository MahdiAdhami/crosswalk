/*
Select Game Map 
 */
package CrossWalk.Menu;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import CrossWalk.Utilities.Const;
import CrossWalk.UI.InitGame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SelectMapMenu extends Menu {

    private final boolean AutoSheepMover;

    public SelectMapMenu(boolean AutoSheepMover) {
        super("انتخاب مپ بازی", 500, 500);
        this.AutoSheepMover = AutoSheepMover;
    }

    @Override
    protected void createPanel() {
        File buttons = null;

        JPanel panel = new JPanel(new BorderLayout(1, 1));
        panel.setBorder(new EmptyBorder(5, 10, 20, 10));

        Frame.setContentPane(panel);

        JPanel controls = new JPanel(new GridLayout(6, 0));
        panel.add(controls, BorderLayout.CENTER);

        buttons = new File(Const.ROOT_PATH + Const.MAP_ROOT_ADDRESS);
        if (!buttons.exists()) {
            buttons.mkdir();
        }

        String[] buttonsName = buttons.list();

        controls.add(createButton("بازی با تنظیمات ذخیره شده", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                GameSetting.SetDefaultSettingPath();
                GameSetting.UpdateSettings();

                if (AutoSheepMover) {
                    InitGame start = new InitGame();
                    start.autoMoveSheep(250, 10);
                } else {
                    TipsMenu tipsMenu = new TipsMenu();
                    //tipsMenu.show();

                    InitGame start = new InitGame();
                    start.newGame();
                }

                Frame.dispose();

            }
        }));

        for (int i = 1; i <= buttonsName.length; i++) {
            JButton currentButton = createButton(buttonsName[i - 1], (ActionEvent event) -> {
                JButton temp = (JButton) event.getSource();

                GameSetting.setSettingPath(Const.MAP_ROOT_ADDRESS + "\\" + temp.getText() + "\\Setting.xml");
                GameSetting.UpdateSettings();

                if (AutoSheepMover) {
                    InitGame start = new InitGame();
                    start.autoMoveSheep(250, 10);
                } else {
                    InitGame start = new InitGame();
                    start.newGame();
                }

                Frame.dispose();

            });
            controls.add(currentButton);
        }
    }
}
