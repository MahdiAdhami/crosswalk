
package javagame.Save;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javagame.AutoCreateCar;
import javagame.Car;
import javagame.Const;
import javagame.InitGraphic;
import javagame.Line;

public class Save {
    
   // AutoCreateCar objForGettingLines;
    ArrayList<Line> linesArrayList ;
    ArrayList<Car> carArrayList = new ArrayList<Car>();
    PrintWriter writerForCars;
    PrintWriter writerForSheep;
    

    public Save() {
        try {
            this.writerForCars = new PrintWriter(Const.PATH + "Save-File.txt", "UTF-8");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Save.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Save.class.getName()).log(Level.SEVERE, null, ex);
        }
        linesArrayList = AutoCreateCar.Lines;
        
        try {
            this.writerForSheep = new PrintWriter(Const.PATH + "Save-File-Sheep.txt", "UTF-8");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Save.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Save.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void init()
    {
        for(Line tempLine : linesArrayList)
        {
            writerForCars.println("Line Id = " + tempLine.getId() + " " + tempLine.getDirection());
            for(Car tempCar : tempLine.getCars())
            {
                writerForCars.println(tempCar.getId() + " " + tempCar.getHeadPosition() 
                        + " " + tempCar.getSpeed() + " " 
                        + tempCar.getCarType().getCarWidth() +
                        " " + tempCar.getCarType().getCarHeight() + " " +
                            tempCar.getCarType().getCarPathString());
            }
        }
        writerForCars.close();
        
        int[] rateOfSheep = InitGraphic.Sheep.getRate();
        writerForSheep.println(rateOfSheep[0] + " " + rateOfSheep[1] + " " + InitGraphic.Sheep.getYPosition() + " " + InitGraphic.Sheep.getXPosition() );
        writerForSheep.close();
        
    }
}
