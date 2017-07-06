package CrossWalk.Menu;

import CrossWalk.Object.Line;
import CrossWalk.StoreData.ResumeAndLoad;
import CrossWalk.UI.InitGame;
import CrossWalk.Utilities.Const;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ResumeGameMenu extends Menu {

    private ArrayList<Line> Lines;

    public ResumeGameMenu(ArrayList<Line> Lines) {
        super("توقف بازی", 200, 200);

        this.Lines = Lines;

        Frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                InitGame.GameStop = false;
                e.getWindow().dispose();
            }
        });
        Frame.setAlwaysOnTop(true);
    }

    @Override
    protected void createPanel() {
        GridLayout grid = new GridLayout(4, 1, 10, 10);
        Frame.setLayout(grid);

        JPanel controls = new JPanel();
        controls.setLayout(grid);
        controls.setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton loadLastGame = createButton("ادامه ی بازی",
                (ActionEvent event) -> {
                    InitGame.GameStop = false;

                    Frame.setVisible(false);
                    Frame.dispose();
                }
        );

        controls.add(loadLastGame);

        JButton startGame = createButton("ذخیره ی بازی",
                (ActionEvent e) -> {
                    ResumeAndLoad saveGame = new ResumeAndLoad();
                    saveGame.SaveGameForResume(Lines);
                    Frame.setVisible(false);
                    JOptionPane.showMessageDialog(null, "بازی با موفقیت ذخیره شد و در منوی اصلی قابل بازیابی است!", "ذخیره شد", JOptionPane.INFORMATION_MESSAGE);
                    Frame.setVisible(true);
                }
        );
        controls.add(startGame);

        JButton showRepliesButtons = createButton("بازگشت به منو",
                (ActionEvent e) -> {
                    Frame.dispose();
                    InitGame.GameEnd = true;
                }
        );
        controls.add(showRepliesButtons);

        JButton exit = createButton("خروج از کل بازی",
                (ActionEvent event) -> {
                    System.exit(0);
                }
        );
        controls.add(exit);

        Frame.setContentPane(controls);

    }
}
