
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


public class ReplyButton extends Menu{

    public ReplyButton(String Title, int Height, int Width) {
        super(Title, Height, Width);
    }

    @Override
    protected void CreatePanel() {
        File buttons = null;
        
        JPanel panel = new JPanel(new BorderLayout(1, 1));
        panel.setBorder(new EmptyBorder(5, 10, 20, 10));

        frame.setContentPane(panel);

        JPanel controls = new JPanel(new GridLayout(6, 0));
        panel.add(controls, BorderLayout.CENTER);
        
        buttons = new File(Const.PATH + "\\src\\resources\\Replies");
        String[] buttonsName = buttons.list();
        
        for(int i = 1; i <= buttonsName.length ;i++)
        {
            JButton currentButton = CreateButton(buttonsName[i-1],(ActionEvent event) -> {
                       InitGame replyTheMovie2 = new InitGame();
                        JButton temp = (JButton)event.getSource();
                        replyTheMovie2.replyTheMovie(temp.getText());    // problem
                    });
            controls.add(currentButton);
        }
    }
    
}
