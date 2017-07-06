package CrossWalk.Menu;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import CrossWalk.Utilities.Const;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class LineMenu extends Menu {

    public LineMenu() {
        super("نوع خط کشی را انتخاب کنید", 400, 400);
    }

    @Override
    protected void createPanel() {
        
        //initialize the frame and panel
        JPanel panel = new JPanel(new BorderLayout(1, 1));
        panel.setBorder(new EmptyBorder(50, 10, 50, 10));

        Frame.setContentPane(panel);

        JPanel controls = new JPanel(new GridLayout(4, 0));
        JPanel labels = new JPanel(new GridLayout(6, 0));
        JPanel submit = new JPanel(new GridLayout(1, 0));

        panel.add(controls, BorderLayout.WEST);
        panel.add(labels, BorderLayout.EAST);
        panel.add(submit, BorderLayout.SOUTH);

         // getting required images and information about  middle lines and creating a combobox with images
        String[] lineImages = new File(Const.ROOT_PATH + Const.LINE_IMAGE_ROOT_PATH + "\\Show").list();
        Object[] lineItems = new Object[lineImages.length];
        for (int i = 0; i < lineItems.length; i++) {
            lineItems[i] = new ImageIcon(Const.ROOT_PATH + Const.LINE_IMAGE_ROOT_PATH + "\\Show\\" + lineImages[i]);
        }
        JComboBox comboMainLine = new JComboBox(lineItems);
        JLabel comboLineLabel = createLabel("نوع خط کشی را انتخاب کنید:");
        
        // getting required images and information about  middle lines and creating a combobox with images
        String[] middleLineImages = new File(Const.ROOT_PATH + Const.MIDDLE_LINE_IMAGE_ROOT_PATH ).list();
        Object[] middleLineItems = new Object[middleLineImages.length];
        for (int i = 0; i < middleLineItems.length; i++) {
            middleLineItems[i] = new ImageIcon(Const.ROOT_PATH + Const.MIDDLE_LINE_IMAGE_ROOT_PATH + "\\" + middleLineImages[i]);
        }
        JComboBox comboMiddleLine = new JComboBox(middleLineItems);
        JLabel comboMiddleLineLabel = createLabel("نوع خط کشی وسط را انتخاب کنید:");
        
         // getting required images and information about cross lines and creating a combobox with images
        String[] crossWalkImages = new File(Const.ROOT_PATH + Const.CROSSWALK_IMAGE_ROOT_PATH ).list();
        Object[] crossWalkItems = new Object[crossWalkImages.length];
        for (int i = 0; i < crossWalkItems.length; i++) {
            crossWalkItems[i] = new ImageIcon(Const.ROOT_PATH + Const.CROSSWALK_IMAGE_ROOT_PATH + "\\" + crossWalkImages[i]);
        }
        JComboBox comboCrossWalk = new JComboBox(crossWalkItems);
        JLabel comboCrossWalkLabel = createLabel("نوع خط عابر را انتخاب کنید:");
        
        
        
        labels.add(comboLineLabel);
        controls.add(comboMainLine);
        labels.add(comboMiddleLineLabel);
        controls.add(comboMiddleLine);
        labels.add(comboCrossWalkLabel);
        controls.add(comboCrossWalk);
        //getting the indexes and extracting to file with a string
        JButton saveChanges = createButton("ذخیره",
                (ActionEvent e) -> {
                    GameSetting.setLineImageNumber(comboMainLine.getSelectedIndex() + 1);
                    GameSetting.setMiddleLineImageNumber(comboMiddleLine.getSelectedIndex() + 1);
                    GameSetting.setCrossWalkImageNumber(comboCrossWalk.getSelectedIndex() + 1);
                    Frame.dispose();
                });

        submit.add(saveChanges);
    }

}
