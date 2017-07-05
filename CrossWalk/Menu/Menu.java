package CrossWalk.Menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import CrossWalk.Utilities.Const;
import CrossWalk.Utilities.ExceptionWriter;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;

public abstract class Menu {

    private final String Title;
    private final int Height;
    private final int Width;
    public JFrame Frame;

    public Menu(String Title, int Height, int Width) {
        this.Height = Height;
        this.Width = Width;
        this.Title = Title;
        Frame = new JFrame();
    }

    public void Show() {
        createPanel();
        setInit();
    }

    public void setInit() {
        Frame.setResizable(false);
        Frame.pack();
        Frame.setTitle(Title);
        Frame.setSize(Width, Height);
        Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Frame.setVisible(true);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        Frame.setLocation((dimension.width / 2) - (Width / 2), (dimension.height / 2) - (Height / 2));
        
        try {
            Frame.setIconImage(ImageIO.read(new File(Const.ROOT_PATH + Const.GAME_ICON)));
        } catch (IOException ex) {
            new ExceptionWriter().write(ex);
        }
    }

    protected JButton createButton(String Title, ActionListener actionListener) {
        JButton button = new JButton();

        button.setText(String.format("%s", Title));
        button.setToolTipText(Title);
        button.setName(Title);

        button.setFont(new Font("tahoma", 0, 12));
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);
        button.setFocusPainted(false);

        button.addActionListener(actionListener);
        return button;
    }

    protected JSpinner createSpinner(String Title, int defaultValue, int minValue, int maxValue, int step, ChangeListener changeListener) {
        SpinnerModel model = new SpinnerNumberModel(defaultValue, minValue, maxValue, step);
        JSpinner spinner = new JSpinner(model);

        spinner.setName(Title);
        spinner.setToolTipText(Title);

        spinner.addChangeListener(changeListener);
        return spinner;
    }

    protected JLabel createLabel(String Title) {
        JLabel label = new JLabel();
        label.setText(Title);
        label.setFont(new Font("tahoma", 0, 12));

        return label;
    }

    protected JLabel createMargin(int top, int right, int bottom, int left) {
        JLabel label = new JLabel();
        label.setBorder(new EmptyBorder(top, left, bottom, right));
        return label;
    }

    protected JCheckBox createCheckBox(String title, boolean isSelected) {
        JCheckBox checkBox = new JCheckBox(title, isSelected);
        return checkBox;
    }

    protected abstract void createPanel();
}
