
package javagame.Menu;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;
import javagame.Const;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class CarMenu extends Menu{

    public CarMenu(String Title, int Height, int Width) {
        super(Title, Height, Width);
    }

    @Override
    protected void CreatePanel() {
        JPanel panel = new JPanel(new BorderLayout(1, 1));
        panel.setBorder(new EmptyBorder(5, 10, 20, 10));

        frame.setContentPane(panel);

        JPanel controls = new JPanel(new GridLayout(3, 0));
        JPanel submit = new JPanel(new GridLayout(1, 0));

        panel.add(controls, BorderLayout.CENTER); 
        panel.add(submit, BorderLayout.SOUTH);
        
        String[] carImages = new File(Const.ROOT_PATH + Const.CAR_ROOT_IMAGE ).list();
        ArrayList<JCheckBox> checkBoxesArrayList = new ArrayList<JCheckBox>();
        
        for(int i = 0 ; i < carImages.length ; i+=2 )
        {
            JCheckBox currentCheckBox = new JCheckBox(new ImageIcon(Const.ROOT_PATH + Const.CAR_ROOT_IMAGE + "\\" + carImages[i]));
            checkBoxesArrayList.add(currentCheckBox);
            controls.add(currentCheckBox);
        }
        
        String[] carImagesSelected = new File(Const.ROOT_PATH + Const.CAR_ROOT_IMAGE + "\\Selected" ).list();
        for(int i = 0 ; i < carImagesSelected.length ; i++)
        {
            checkBoxesArrayList.get(i).setSelectedIcon(new ImageIcon(Const.ROOT_PATH + Const.CAR_ROOT_IMAGE + "\\Selected\\" + carImagesSelected[i]));
        }
        
        JButton submitButton = CreateButton("ذخیره", (e) -> {
            
            int[] tempNumbers = null ;
            String temp ="";
            for(int i = 0 ;i < checkBoxesArrayList.size() ; i++)
            {
                //tempNumbers = new int[checkBoxesArrayList.size()];
                if(checkBoxesArrayList.get(i).isSelected())
                {
                   temp += (char) (49 + i) ;
                }
                //else {
                //    tempNumbers[i] = 0;
                //}
                
            }
            
//            for(int i = 0 ; i < tempNumbers.length ; i++)
//            {   
//                
//                temp += tempNumbers[i] ;
//            }

            GameSetting.setCarsNumbers(temp);
            frame.dispose();
        });
        
        submit.add(submitButton);
        
        
    }
    
}
