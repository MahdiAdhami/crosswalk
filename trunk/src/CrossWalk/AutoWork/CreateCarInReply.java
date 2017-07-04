package CrossWalk.AutoWork;

import CrossWalk.Const;
import CrossWalk.Object.MoveableObject.CarLtr;
import CrossWalk.Object.Line;
import CrossWalk.Object.MoveableObject.CarRtl;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import CrossWalk.Menu.GameSetting;
import CrossWalk.Utilities.ExceptionWriter;

public class CreateCarInReply extends CreateCar implements Runnable {
    
    private final String Path;
    
    public CreateCarInReply(String Path) {
        super();
        setLine(new ArrayList<>());
        this.Path = Path;
    }
    
    @Override
    public void run() {
        
        Scanner reader = null;
        
        try {
            reader = new Scanner(new File(Path));
        } catch (FileNotFoundException ex) {
            new ExceptionWriter().write(ex);
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
                    new ExceptionWriter().write(ex);
                }
            }
        }
    }
    
}
