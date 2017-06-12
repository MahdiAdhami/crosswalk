
package javagame.Menu;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javagame.Const;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SheepMenu extends Menu{

    public SheepMenu(String Title, int Height, int Width) {
        super(Title, Height, Width);
    }

    @Override
    protected void CreatePanel() {
        
        JPanel panel = new JPanel(new BorderLayout(1, 1));
        panel.setBorder(new EmptyBorder(5, 10, 20, 10));

        frame.setContentPane(panel);

        JPanel controls = new JPanel(new GridLayout(6, 0));

        JPanel labels = new JPanel(new GridLayout(6, 0));

        JPanel submit = new JPanel(new GridLayout(1, 0));

        panel.add(controls, BorderLayout.CENTER);
        panel.add(labels, BorderLayout.EAST);
        panel.add(submit, BorderLayout.SOUTH);
        
        Object[] items = new Object[3];
        items[0] = new ImageIcon(Const.ROOT_PATH + Const.SHEEP_PATH_IMAGE.replace("{0}", "1-Down"));
        items[1] = new ImageIcon(Const.ROOT_PATH + Const.SHEEP_PATH_IMAGE.replace("{0}", "2-Down"));
        items[2] = new ImageIcon(Const.ROOT_PATH + Const.SHEEP_PATH_IMAGE.replace("{0}", "3-Down"));
       // items[3] = new ImageIcon(Const.ROOT_PATH + Const.SHEEP_PATH_IMAGE.replace("{0}", "4-Down"));
        JComboBox combo = new JComboBox(items);
        
        JLabel comboLabel = CreateLabel("نوع آدمک را انتخاب کنید:");
        labels.add(comboLabel);
        labels.add(CreateMargin(10, 0, 10, 0));
        
        controls.add(combo);
        
        
        JButton saveChanges = CreateButton("ذخیره",
                (ActionEvent e) -> {
                        GameSetting.setSheepImageNumber(combo.getSelectedIndex());
                        
                        frame.dispose();
                });
        
        submit.add(saveChanges);
        
    }
    
}
