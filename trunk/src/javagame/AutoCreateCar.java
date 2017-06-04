package javagame;

import java.util.ArrayList;
import javagame.Menu.GameSetting;

public class AutoCreateCar implements Runnable {
    
    ArrayList<Line> Lines = new ArrayList<>();
    public int RtlLineCount;
    public int LtrLineCount;

    AutoCreateCar() {
        this.LtrLineCount = GameSetting.getLtrLineCount();
        this.RtlLineCount = GameSetting.getRtlLineCount();
    }

    public void InitLine() {
        for (int i = 1 ; i <= RtlLineCount ; i++) {
            Lines.add(new Line(i, (i + 1), (i), Const.LINE_DIRECTION_RTL, Const.LINE_HEIGHT * (i - 1) + Const.TOP_MARGIN));
        }
        for (int i = RtlLineCount + 1 , j = 1; i <= RtlLineCount + LtrLineCount; i++ , j++) {
            Lines.add(new Line(i, (j + 1), (j) , Const.LINE_DIRECTION_LTR, (Const.LINE_HEIGHT * (j - 1)) + RtlLineCount * Const.LINE_HEIGHT + Const.TOP_MARGIN) );
        }
    }

    // Query method for all lines
    public ArrayList<Line> getLines() {
        return Lines;
    }

    @Override
    public void run() {
        
        while (true) {
            
            // Random int to select line for create new car 
            int randomLine = Const.RAND.nextInt(LtrLineCount + RtlLineCount);
            
            // Get line with random int
            Line tempLine = Lines.get(randomLine);
            
            // Create car speed 
            int speed = tempLine.getMinCarSpeed() +  Const.RAND.nextInt(tempLine.getMaxCarSpeed());
            
            // variables for instance car object
            CarType carType;
            Car newCar;
            
             // Check for direction 
            if (tempLine.getDirection() == Const.LINE_DIRECTION_RTL) {
                carType = new CarType(tempLine.getDirection());
                newCar = new CarRtl(speed, carType, tempLine);
            }else{
                carType = new CarType(tempLine.getDirection());
                newCar = new CarLtr(speed, carType, tempLine);
            }
            // Call create new car method of line 
            tempLine.CreateNewCar(newCar);
            
            // Sleep thread wait for create new car again
            try {
                Thread.sleep(Const.CREATE_CAR_RATE);
            } catch (Exception ex) {
                System.err.println("AutoCreateCar run() " + ex);
            }
        }
    }
//  //  private float[] InitPostion(int carWidth){
//        float[] position = new float[2];
//
//        int CarWidthRate = this.getDirection() == Const.LINE_DIRECTION_LTR
//                ? CarType.getCarWidth() * (-1)
//                : CarWidth;
//        return Position[0] + CarWidthRate;
//        
//        return ;
//    }
}
