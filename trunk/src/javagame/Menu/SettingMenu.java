package javagame.Menu;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;

public class SettingMenu extends Menu {

    public SettingMenu(String Title, int Height, int Width) {
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

        JSpinner topLineCount = CreateSpinner("تعداد لاین راست به چپ", GameSetting.getRtlLineCount(), MenuConst.MIN_TOP_LINE_COUNT - 10, MenuConst.MAX_TOP_LINE_COUNT + 10, 1, null);
        controls.add(topLineCount);
        controls.add(CreateMargin(10, 0, 10, 0));

        JLabel lblTopLineCount = CreateLabel(String.format("%s %s(%d-%d)", "تعداد لاین راست به چپ", "بین", MenuConst.MIN_TOP_LINE_COUNT, MenuConst.MAX_TOP_LINE_COUNT));
        labels.add(lblTopLineCount);
        labels.add(CreateMargin(10, 0, 10, 0));

        JSpinner bottomLineCount = CreateSpinner("تعداد لاین چپ به راست", GameSetting.getLtrLineCount(), MenuConst.MIN_BOTTOM_LINE_COUNT - 10, MenuConst.MAX_BOTTOM_LINE_COUNT + 10, 1, null);
        controls.add(bottomLineCount);
        controls.add(CreateMargin(10, 0, 10, 0));

        JLabel lblbottomLineCount = CreateLabel(String.format("%s %s(%d-%d)", "تعداد لاین چپ به راست", "بین", MenuConst.MIN_TOP_LINE_COUNT, MenuConst.MAX_TOP_LINE_COUNT));
        labels.add(lblbottomLineCount);
        labels.add(CreateMargin(10, 0, 10, 0));
        
        JSpinner lineImage = CreateSpinner("نوع خط کشی جاده ها", GameSetting.getLineImageNumber(), MenuConst.MIN_LINE_IMAGE - 2, MenuConst.MAX_LINE_IMAGE + 2, 1, null);
        controls.add(lineImage);
        controls.add(CreateMargin(10, 0, 10, 0));

        JLabel lblLineImage = CreateLabel(String.format("%s %s(%d-%d)", "نوع خط کشی جاده ها", "بین", MenuConst.MIN_LINE_IMAGE, MenuConst.MAX_LINE_IMAGE));
        labels.add(lblLineImage);
        labels.add(CreateMargin(10, 0, 10, 0));
        
        JSpinner createCarRate = CreateSpinner("سرعت تولید ماشین", GameSetting.getAutoCreateCarRate(), MenuConst.MIN_CREATE_CAR_RATE - 200, MenuConst.MAX_CREATE_CAR_RATE + 100, 100, null);
        controls.add(createCarRate);
        controls.add(CreateMargin(10, 0, 10, 0));

        JLabel lblcreateCarRate = CreateLabel(String.format("%s %s(%d-%d)", "سرعت تولید ماشین", "بین", MenuConst.MIN_CREATE_CAR_RATE, MenuConst.MAX_CREATE_CAR_RATE));
        labels.add(lblcreateCarRate);
        labels.add(CreateMargin(10, 0, 10, 0));

        JButton saveChanges = CreateButton("ذخیره",
                (ActionEvent e) -> {
                    boolean error = false;
                    if (!GameSetting.setRtlLineCount(topLineCount.getValue())) {
                        JOptionPane.showMessageDialog(null, String.format("%s %s %s(%d-%d)", "مقدار لاین بالایی غیر معتبر", "تعداد لاین چپ به راست", "بین", MenuConst.MIN_TOP_LINE_COUNT, MenuConst.MAX_TOP_LINE_COUNT), "خطایی رخ داده", JOptionPane.ERROR_MESSAGE);
                        error = true;
                    } else if (!GameSetting.setLtrLineCount(bottomLineCount.getValue())) {
                        JOptionPane.showMessageDialog(null, String.format("%s %s %s(%d-%d)", "مقدار لاین پایینی غیر معتبر", "تعداد لاین چپ به راست", "بین", MenuConst.MIN_BOTTOM_LINE_COUNT, MenuConst.MAX_BOTTOM_LINE_COUNT), "خطایی رخ داده", JOptionPane.ERROR_MESSAGE);
                        error = true;
                    } else if (!GameSetting.setLineImageNumber(lineImage.getValue())) {
                        JOptionPane.showMessageDialog(null, String.format("%s %s %s(%d-%d)", "مقدار نوع خط کشی نا معتبر", "نوع خط کشی", "بین", MenuConst.MIN_LINE_IMAGE, MenuConst.MAX_LINE_IMAGE), "خطایی رخ داده", JOptionPane.ERROR_MESSAGE);
                        error = true;
                    }
                     else if (!GameSetting.setAutoCreateCarRate(createCarRate.getValue())) {
                        JOptionPane.showMessageDialog(null, String.format("%s %s %s(%d-%d)", "سرعت تولید ماشین نامعتبر", "سرعت تولید ماشین", "بین", MenuConst.MIN_CREATE_CAR_RATE, MenuConst.MAX_CREATE_CAR_RATE), "خطایی رخ داده", JOptionPane.ERROR_MESSAGE);
                        error = true;
                     }else  {
                        GameSetting.setDefaultSettingPath();
                        GameSetting.SaveChanges();
                        frame.dispose();
                    }
                });
        submit.add(saveChanges);
    }
}
