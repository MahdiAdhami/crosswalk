package CrossWalk.Menu;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import CrossWalk.Utilities.Const;
import CrossWalk.UI.InitGame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ReplyMenu extends Menu {

    public ReplyMenu() {
        super("بازی های قابل مشاهده", 500, 500);
    }

    @Override
    protected void createPanel() {
        File buttons = new File(Const.ROOT_PATH + Const.REPLY_ROOT_ADDRESS);
        String[] buttonsName = buttons.list();

        GridLayout grid = new GridLayout(6, 1, 10, 10);
        Frame.setLayout(grid);

        JPanel controls = new JPanel();
        controls.setLayout(grid);
        controls.setBorder(new EmptyBorder(10, 10, 10, 10));

        for (int i = 1; i <= buttonsName.length; i++) {
            JButton currentButton = createButton(buttonsName[i - 1], (ActionEvent event) -> {
                JButton temp = (JButton) event.getSource();

                InitGame initReply = new InitGame();
                initReply.replyTheMovie("\\" + temp.getText());
            });
            controls.add(currentButton);
        }
        
        Frame.setContentPane(controls);
    }

}
