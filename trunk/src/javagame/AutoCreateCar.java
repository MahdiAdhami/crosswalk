package javagame;

import java.security.SecureRandom;
import java.util.ArrayList;

public class AutoCreateCar implements Runnable {

    ArrayList<Line> Lines = new ArrayList<>();
    public int RtlLineCount;
    public int LtrLineCount;
    public static SecureRandom SecureRandom = new SecureRandom();

    AutoCreateCar(int RtlLineCount, int LtrLineCount, int middleOfCrosswalkPosition) {
        Crosswalk.setMiddlePosition(middleOfCrosswalkPosition);
        this.LtrLineCount = LtrLineCount;
        this.RtlLineCount = RtlLineCount;
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
            int randomLine = SecureRandom.nextInt(LtrLineCount + RtlLineCount);
            Line tempLine = Lines.get(randomLine);

            int speed = tempLine.getMinCarSpeed() + SecureRandom.nextInt(tempLine.getMaxCarSpeed());
            CarType carType;
            Car newCar;
            
            if (tempLine.getDirection() == Const.LINE_DIRECTION_RTL) {
                carType = new CarType(65 + SecureRandom.nextInt(Const.CAR_COUNT), tempLine.getDirection());
                newCar = new CarRtl(tempLine.carId, speed, carType, tempLine);
            }else{
                carType = new CarType(65 + SecureRandom.nextInt(Const.CAR_COUNT), tempLine.getDirection());
                newCar = new CarLtr(tempLine.carId, speed, carType, tempLine);
            }
            
            tempLine.CreateNewCar(newCar);
            
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
