package CrossWalk.AutoWork;

import CrossWalk.Utilities.Const;
import CrossWalk.Object.CarLtr;
import CrossWalk.Object.Line;
import CrossWalk.Object.CarRtl;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import CrossWalk.Menu.GameSetting;
import CrossWalk.Utilities.ExceptionWriter;

public class CreateCarInReply extends CreateCar{
    
    private final String CarsPath;
    
    public CreateCarInReply(String CarsPath) {
        super();
        setLine(new ArrayList<>());
        this.CarsPath = CarsPath;
    }
    
    @Override
    public void run() {
        
        Scanner reader = null;
        
        try {
            reader = new Scanner(new File(CarsPath));
        } catch (FileNotFoundException ex) {
             new ExceptionWriter().write("CreateCarInReply run()", ex, false);
        }
        
        if (reader != null) {
            while (reader.hasNextLine()) {
                String[] temp = reader.nextLine().split(",");
                
                Line tempLine = getLine().get(Integer.parseInt((temp[5])) - 1);
                
                if (tempLine.getDirection() == Const.LINE_DIRECTION_LTR) {
                    tempLine.getCars().add(new CarLtr(Integer.parseInt(temp[1]), Float.parseFloat(temp[3]), temp[4], tempLine));
                } else {
                    tempLine.getCars().add(new CarRtl(Integer.parseInt(temp[1]), Float.parseFloat(temp[3]), temp[4], tempLine));
                }
                
                try {
                    Thread.sleep(Const.CAR_CREATE_MAX_SLEEP_TIME - GameSetting.getAutoCreateCarRate());
                } catch (InterruptedException ex) {
                     new ExceptionWriter().write("CreateCarInReply run()", ex, false);
                }
            }
        }
    }
    
}
