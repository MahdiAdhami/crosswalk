package CrossWalk.Menu;

import CrossWalk.UI.InitGame;
import CrossWalk.Utilities.Const;
import CrossWalk.Utilities.ExceptionWriter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TipsMenu extends JPanel {

    private JFrame Frame;


    public TipsMenu() {
        Frame = new JFrame();
        Frame.add(this);

        Frame.setSize(350, 350);

        Frame.setVisible(true);
        Frame.setResizable(false);

        Frame.setBackground(Color.white);
        Frame.setFont(new Font("tahoma", 0, 12));
        Frame.setForeground(Color.red);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        Frame.setLocation((dimension.width / 2) - (350 / 2), (dimension.height / 2) - (350 / 2));
        Frame.add(this);

        JPanel submit = new JPanel(new GridLayout(1, 1));

        JButton button = new JButton();

        button.setText(String.format("%s", "شروع بازی"));
        button.setToolTipText("شروع بازی");
        button.setName("شروع بازی");

        button.setFont(new Font("tahoma", 0, 12));
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);
        button.setFocusPainted(false);

        button.addActionListener((ActionEvent e) -> {
            InitGame start = new InitGame();
            start.newGame();
            Frame.dispose();
        });
        submit.add(button);
        
        add(submit,BorderLayout.SOUTH);
    }

    @Override
    public void paintComponent(Graphics g) {
        // Call super methods for initialize
        super.paintComponent(g);

        try {
            g.drawImage(ImageIO.read(new File(Const.ROOT_PATH + Const.TIPS_IMAGE_PATH)), 0, 0, this);
        } catch (IOException ex) {
            new ExceptionWriter().write("InitGraphic setInit()", ex, false);
        }
    }
}
