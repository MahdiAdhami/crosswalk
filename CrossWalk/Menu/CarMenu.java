package CrossWalk.Menu;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import CrossWalk.Utilities.Const;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CarMenu extends Menu {

    public CarMenu() {
        super("نوع ماشین ها را انتخاب کنید", 400, 400);
    }

    @Override
    protected void createPanel() {
        JPanel panel = new JPanel(new BorderLayout(1, 1));
        panel.setBorder(new EmptyBorder(5, 10, 20, 10));

        Frame.setContentPane(panel);

        JPanel controls = new JPanel(new GridLayout(3, 0));
        JPanel submit = new JPanel(new GridLayout(1, 0));

        panel.add(controls, BorderLayout.CENTER);
        panel.add(submit, BorderLayout.SOUTH);

        String[] carImages = new File(Const.ROOT_PATH + Const.CAR_IMAGE_ROOT_PATH + "\\Setting\\Show").list();
        ArrayList<JCheckBox> checkBoxesArrayList = new ArrayList<>();
        
        //getting required information for determine whether a car is selected or not 
        
        String selectedCar = GameSetting.getCarsNumbers();
        String[] selectedCarType = new String[selectedCar.length()];
        int j = 0;
        for (int i = 0; i < selectedCar.length(); i++) {
            selectedCarType[j++] = selectedCar.substring(i, i + 1);
        }
        
        //adding the property of selected or not to a checkbox of car 
        
        boolean isSelected;
        for (String carName : carImages) {
            JCheckBox currentCheckBox;
            String carType = carName.substring(0, 1);

            isSelected = false;

            for (String selectedCarTemp : selectedCarType) {
                if (carType.equals(selectedCarTemp)) {
                    isSelected = true;
                    break;
                }
            }
            
            //creating checkboxes with cars images 
            
            currentCheckBox = new JCheckBox(new ImageIcon(Const.ROOT_PATH + Const.CAR_IMAGE_ROOT_PATH + "\\Setting\\Show\\" + carName));

            if (!isSelected) {
                currentCheckBox.setSelected(false);
            } else {
                currentCheckBox.setSelected(true);
            }
            checkBoxesArrayList.add(currentCheckBox);
            controls.add(currentCheckBox);
        }
        
        String[] carImagesSelected = new File(Const.ROOT_PATH + Const.CAR_IMAGE_ROOT_PATH + "\\Setting\\Select").list();
        
        //set selected icons for selected checkboxes
        for (int i = 0; i < carImagesSelected.length; i++) {
            checkBoxesArrayList.get(i).setSelectedIcon(new ImageIcon(Const.ROOT_PATH + Const.CAR_IMAGE_ROOT_PATH + "\\Setting\\Select\\" + carImagesSelected[i]));
        }
        //getting the indexes and extracting to file with a string
        JButton submitButton = createButton("ذخیره", (ActionEvent e) -> {
            String temp = "";
            for (int i = 0; i < checkBoxesArrayList.size(); i++) {
                if (checkBoxesArrayList.get(i).isSelected()) {
                    temp += (char) (49 + i);
                }
            }
            GameSetting.setCarsNumbers(temp);
            Frame.dispose();
        });
        submit.add(submitButton);
    }

}
