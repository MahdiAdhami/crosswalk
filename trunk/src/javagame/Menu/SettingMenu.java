package javagame.Menu;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import javagame.Const;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
        panel.setBorder(new EmptyBorder(5, 10, 5, 10));

        frame.setContentPane(panel);

        JPanel controls = new JPanel(new GridLayout(6, 0));
        JPanel labels = new JPanel(new GridLayout(7, 0));
        JPanel submit = new JPanel(new GridLayout(2, 0));
        JPanel otherSetting = new JPanel(new GridLayout(1, 0));

        panel.add(controls, BorderLayout.WEST);
        panel.add(labels, BorderLayout.CENTER);
        panel.add(otherSetting, BorderLayout.NORTH);
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

        JSpinner crosswalkPosition = CreateSpinner("موقعیت خط عابر پیاده", GameSetting.getCrosswalkMiddlePosition(), MenuConst.MIN_CROSSWALK_POS - 100, MenuConst.MAX_CROSSWALK_POS + 100, 50, null);
        controls.add(crosswalkPosition);
        controls.add(CreateMargin(10, 0, 10, 0));

        JLabel lblcrosswalkPosition = CreateLabel(String.format("%s %s(%d-%d)", "موقعیت خط عابر پیاده", "بین", MenuConst.MIN_CROSSWALK_POS, MenuConst.MAX_CROSSWALK_POS));
        labels.add(lblcrosswalkPosition);
        labels.add(CreateMargin(10, 0, 10, 0));

        JSpinner createCarRate = CreateSpinner("سرعت تولید ماشین", GameSetting.getAutoCreateCarRate(), MenuConst.MIN_CREATE_CAR_RATE - 200, MenuConst.MAX_CREATE_CAR_RATE + 100, 250, null);
        controls.add(createCarRate);
        controls.add(CreateMargin(10, 0, 10, 0));

        JLabel lblcreateCarRate = CreateLabel(String.format("%s %s(%d-%d)", "سرعت تولید ماشین", "بین", MenuConst.MIN_CREATE_CAR_RATE, MenuConst.MAX_CREATE_CAR_RATE));
        labels.add(lblcreateCarRate);
        labels.add(CreateMargin(10, 0, 10, 0));

        JSpinner carsSpeed = CreateSpinner("سرعت ماشین ها ", (int) GameSetting.getCarsSpeed(), MenuConst.MIN_CARS_SPEED - 2, MenuConst.MAX_CARS_SPEED + 2, 1, null);
        controls.add(carsSpeed);
        controls.add(CreateMargin(10, 0, 10, 0));

        JLabel lblcarsSpeed = CreateLabel(String.format("%s %s(%d-%d)", "سرعت ماشین ها", "بین", MenuConst.MIN_CARS_SPEED, MenuConst.MAX_CARS_SPEED));
        labels.add(lblcarsSpeed);
        labels.add(CreateMargin(10, 0, 10, 0));

        JCheckBox changeDirection = CreateCheckBox("خیابان چپ راست بالاتر از خیابان راست به چپ", (GameSetting.getChangedLinesDirections() == 1));
        labels.add(changeDirection);
        labels.add(CreateMargin(10, 0, 10, 0));

        JButton goToSheepSelectMenu = CreateButton("انتخاب آدمک", (e) -> {
            SheepMenu sheepSelectMenu = new SheepMenu("انتخاب نوع آدمک", 400, 400);
            sheepSelectMenu.Execute();
        });

        JButton goToCarSelectMenu = CreateButton("انتخاب ماشین ها", (e) -> {
            CarMenu carSelectMenu = new CarMenu("نوع ماشین ها را انتخاب کنید", 400, 400);
            carSelectMenu.Execute();
        });

        JButton goToLineSelectMenu = CreateButton("انتخاب خط کشی ها", (e) -> {
            LineMenu lineSelectMenu = new LineMenu("نوع خط کشی را انتخاب کنید", 400, 400);
            lineSelectMenu.Execute();
        });

        otherSetting.add(goToCarSelectMenu);
        otherSetting.add(goToSheepSelectMenu);
        otherSetting.add(goToLineSelectMenu);

        
        JButton saveChanges = CreateButton("ذخیره",
                (ActionEvent e) -> {
                    boolean error = false;
                    if (!GameSetting.setRtlLineCount(topLineCount.getValue())) {
                        JOptionPane.showMessageDialog(null, String.format("%s %s %s(%d-%d)", "مقدار لاین بالایی غیر معتبر", "تعداد لاین چپ به راست", "بین", MenuConst.MIN_TOP_LINE_COUNT, MenuConst.MAX_TOP_LINE_COUNT), "خطایی رخ داده", JOptionPane.ERROR_MESSAGE);
                        error = true;
                    } else if (!GameSetting.setLtrLineCount(bottomLineCount.getValue())) {
                        JOptionPane.showMessageDialog(null, String.format("%s %s %s(%d-%d)", "مقدار لاین پایینی غیر معتبر", "تعداد لاین چپ به راست", "بین", MenuConst.MIN_BOTTOM_LINE_COUNT, MenuConst.MAX_BOTTOM_LINE_COUNT), "خطایی رخ داده", JOptionPane.ERROR_MESSAGE);
                        error = true;
                    } else if (!GameSetting.setCrosswalkMiddlePosition(crosswalkPosition.getValue())) {
                        JOptionPane.showMessageDialog(null, String.format("%s %s %s(%d-%d)", " موقعیت خط عابر پیاده نامعتبر", "موقعیت خط عابر پیاده", "بین", MenuConst.MIN_CROSSWALK_POS, MenuConst.MAX_CROSSWALK_POS), "خطایی رخ داده", JOptionPane.ERROR_MESSAGE);
                        error = true;
                    } else if (!GameSetting.setAutoCreateCarRate(createCarRate.getValue())) {
                        JOptionPane.showMessageDialog(null, String.format("%s %s %s(%d-%d)", "سرعت تولید ماشین نامعتبر", "سرعت تولید ماشین", "بین", MenuConst.MIN_CREATE_CAR_RATE, MenuConst.MAX_CREATE_CAR_RATE), "خطایی رخ داده", JOptionPane.ERROR_MESSAGE);
                        error = true;
                    } else if (!GameSetting.setChangedLinesDirections((changeDirection.isSelected()) ? 1 : 0)) {
                        JOptionPane.showMessageDialog(null, "جهت لاین نا معتبر است", "خطایی رخ داده", JOptionPane.ERROR_MESSAGE);
                        error = true;
                    } else if (!GameSetting.setCarsSpeed(carsSpeed.getValue())) {
                        JOptionPane.showMessageDialog(null, String.format("%s %s %s(%d-%d)", "سرعت ماشین نامعتبر ها", "سرعت ماشین ها", "بین", MenuConst.MIN_CARS_SPEED, MenuConst.MAX_CARS_SPEED), "خطایی رخ داده", JOptionPane.ERROR_MESSAGE);
                        error = true;
                    } else {
                        GameSetting.SetDefaultSettingPath();
                        GameSetting.SaveChanges();
                        frame.dispose();
                    }
                });

        submit.add(saveChanges);
        
        JButton saveMap = CreateButton("ذخیره به عنوان مپ",
                (ActionEvent e) -> {
                    boolean error = false;
                    if (!GameSetting.setRtlLineCount(topLineCount.getValue())) {
                        JOptionPane.showMessageDialog(null, String.format("%s %s %s(%d-%d)", "مقدار لاین بالایی غیر معتبر", "تعداد لاین چپ به راست", "بین", MenuConst.MIN_TOP_LINE_COUNT, MenuConst.MAX_TOP_LINE_COUNT), "خطایی رخ داده", JOptionPane.ERROR_MESSAGE);
                        error = true;
                    } else if (!GameSetting.setLtrLineCount(bottomLineCount.getValue())) {
                        JOptionPane.showMessageDialog(null, String.format("%s %s %s(%d-%d)", "مقدار لاین پایینی غیر معتبر", "تعداد لاین چپ به راست", "بین", MenuConst.MIN_BOTTOM_LINE_COUNT, MenuConst.MAX_BOTTOM_LINE_COUNT), "خطایی رخ داده", JOptionPane.ERROR_MESSAGE);
                        error = true;
                    } else if (!GameSetting.setCrosswalkMiddlePosition(crosswalkPosition.getValue())) {
                        JOptionPane.showMessageDialog(null, String.format("%s %s %s(%d-%d)", " موقعیت خط عابر پیاده نامعتبر", "موقعیت خط عابر پیاده", "بین", MenuConst.MIN_CROSSWALK_POS, MenuConst.MAX_CROSSWALK_POS), "خطایی رخ داده", JOptionPane.ERROR_MESSAGE);
                        error = true;
                    } else if (!GameSetting.setAutoCreateCarRate(createCarRate.getValue())) {
                        JOptionPane.showMessageDialog(null, String.format("%s %s %s(%d-%d)", "سرعت تولید ماشین نامعتبر", "سرعت تولید ماشین", "بین", MenuConst.MIN_CREATE_CAR_RATE, MenuConst.MAX_CREATE_CAR_RATE), "خطایی رخ داده", JOptionPane.ERROR_MESSAGE);
                        error = true;
                    } else if (!GameSetting.setChangedLinesDirections((changeDirection.isSelected()) ? 1 : 0)) {
                        JOptionPane.showMessageDialog(null, "جهت لاین نا معتبر است", "خطایی رخ داده", JOptionPane.ERROR_MESSAGE);
                        error = true;
                    } else if (!GameSetting.setCarsSpeed(carsSpeed.getValue())) {
                        JOptionPane.showMessageDialog(null, String.format("%s %s %s(%d-%d)", "سرعت ماشین نامعتبر ها", "سرعت ماشین ها", "بین", MenuConst.MIN_CARS_SPEED, MenuConst.MAX_CARS_SPEED), "خطایی رخ داده", JOptionPane.ERROR_MESSAGE);
                        error = true;
                    } else {
                        String name;
                        while (true) {
                            name = JOptionPane.showInputDialog("نامی برای مپ انتخاب کنید");
                            if ("".equals(name.trim())) {
                                JOptionPane.showMessageDialog(null, "یک نام معتبر وارد کنید", "خطا", JOptionPane.INFORMATION_MESSAGE);
                            } else if (new File(Const.ROOT_PATH + Const.MAP_ROOT_ADDRESS + "\\" + name).exists()) {
                                JOptionPane.showMessageDialog(null, "این نام قبلا ثبت شده", "خطا", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                break;
                            }
                        }
                        new File(Const.ROOT_PATH + Const.MAP_ROOT_ADDRESS + "\\" + name).mkdir();
                        GameSetting.setSettingPath(Const.MAP_ROOT_ADDRESS + "\\" + name + "\\Setting.xml");
                        GameSetting.SaveChanges();
                        frame.dispose();
                    }
                });

        submit.add(saveMap);
    }
}
