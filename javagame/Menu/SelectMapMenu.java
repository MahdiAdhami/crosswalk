/*
Select Game Map 
 */
package javagame.Menu;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import javagame.Const;
import javagame.InitGame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SelectMapMenu extends Menu {

    public SelectMapMenu() {
        super("انتخاب مپ بازی", 500, 500);
    }

    @Override
    protected void CreatePanel() {
        File buttons = null;

        JPanel panel = new JPanel(new BorderLayout(1, 1));
        panel.setBorder(new EmptyBorder(5, 10, 20, 10));

        frame.setContentPane(panel);

        JPanel controls = new JPanel(new GridLayout(6, 0));
        panel.add(controls, BorderLayout.CENTER);

        buttons = new File(Const.ROOT_PATH + Const.MAP_ROOT_ADDRESS);
        if (!buttons.exists()) {
            buttons.mkdir();
        }

        String[] buttonsName = buttons.list();

        for (int i = 1; i <= buttonsName.length; i++) {
            JButton currentButton = CreateButton(buttonsName[i - 1], (ActionEvent event) -> {
                JButton temp = (JButton) event.getSource();
                System.out.println(temp.getText());
            });
            controls.add(currentButton);
        }
    }

}
