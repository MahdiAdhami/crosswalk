package javagame.Menu;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import javagame.Const;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;

public class LineMenu extends Menu {

    public LineMenu(String Title, int Height, int Width) {
        super(Title, Height, Width);
    }

    @Override
    protected void CreatePanel() {
        JPanel panel = new JPanel(new BorderLayout(1, 1));
        panel.setBorder(new EmptyBorder(50, 10, 50, 10));

        frame.setContentPane(panel);

        JPanel controls = new JPanel(new GridLayout(4, 0));
        JPanel labels = new JPanel(new GridLayout(6, 0));
        JPanel submit = new JPanel(new GridLayout(1, 0));

        panel.add(controls, BorderLayout.WEST);
        panel.add(labels, BorderLayout.EAST);
        panel.add(submit, BorderLayout.SOUTH);

        String[] lineImages = new File(Const.ROOT_PATH + Const.LINE_ROOT_IMAGE).list();

        Object[] items = new Object[lineImages.length];
        for (int i = 0; i < items.length; i++) {
            items[i] = new ImageIcon(Const.ROOT_PATH + Const.LINE_ROOT_IMAGE + "\\" + lineImages[i]);
        }

        JComboBox combo = new JComboBox(items);

        JLabel comboLabel = CreateLabel("نوع خط کشی را انتخاب کنید:");
        labels.add(comboLabel);

        controls.add(combo);

        JButton saveChanges = CreateButton("ذخیره",
                (ActionEvent e) -> {
                    GameSetting.setLineImageNumber(combo.getSelectedIndex() + 1);
                    frame.dispose();
                });

        submit.add(saveChanges);
    }

}
