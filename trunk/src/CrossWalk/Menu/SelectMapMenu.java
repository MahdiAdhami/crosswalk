/*
Select Game Map 
 */
package CrossWalk.Menu;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import CrossWalk.Utilities.Const;
import CrossWalk.UI.InitGame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SelectMapMenu extends Menu {

    private final boolean AutoSheepMover;

    public SelectMapMenu(boolean AutoSheepMover) {
        super("انتخاب مپ بازی", 450, 450);
        this.AutoSheepMover = AutoSheepMover;
    }

    @Override
    protected void createPanel() {

        File buttons = new File(Const.ROOT_PATH + Const.MAP_ROOT_ADDRESS);
        if (!buttons.exists()) {
            buttons.mkdir();
        }

        String[] buttonsName = buttons.list();

        GridLayout grid = new GridLayout(6, 1, 10, 10);
        Frame.setLayout(grid);

        JPanel controls = new JPanel();
        controls.setLayout(grid);
        controls.setBorder(new EmptyBorder(2, 10, 2, 10));

        controls.add(createButton("بازی با تنظیمات ذخیره شده", (ActionEvent e) -> {
            GameSetting.SetDefaultSettingPath();
            GameSetting.UpdateSettings();
            if (AutoSheepMover) {
                InitGame start = new InitGame();
                start.autoMoveSheep(250, 25);
            } else {
                TipsMenu tipsMenu = new TipsMenu();
            }

            Frame.dispose();
        }));

        for (int i = 1; i <= buttonsName.length; i++) {
            JButton currentButton = createButton(buttonsName[i - 1], (ActionEvent event) -> {
                JButton temp = (JButton) event.getSource();
                String path = Const.MAP_ROOT_ADDRESS + "\\" + temp.getText() + "\\Setting.xml";

                GameSetting.setSettingPath(path);
                GameSetting.UpdateSettings();
                
                if (AutoSheepMover) {
                    InitGame start = new InitGame();
                    start.autoMoveSheep(250, 25);
                } else {
                    TipsMenu tipsMenu = new TipsMenu();
                }

                Frame.dispose();

            });
            controls.add(currentButton);
        }

        Frame.setContentPane(controls);
    }
}
