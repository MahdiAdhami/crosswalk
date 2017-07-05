package CrossWalk.AutoWork;

import CrossWalk.Utilities.Const;
import CrossWalk.UI.InitGame;
import CrossWalk.UI.InitGraphic;
import CrossWalk.Object.CarLtr;
import CrossWalk.Object.Car;
import CrossWalk.Object.Line;
import CrossWalk.Object.CarType;
import CrossWalk.Object.CarRtl;
import CrossWalk.Menu.GameSetting;
import CrossWalk.StoreData.WriteReplyData;
import CrossWalk.Utilities.ExceptionWriter;

public class CreateCarInNewGame extends CreateCar implements Runnable {

    public boolean SaveReply;
    public WriteReplyData WriteReplyData;

    public CreateCarInNewGame(boolean SaveReply) {
        super();

        this.SaveReply = SaveReply;
        if (SaveReply) {
            WriteReplyData temp = new WriteReplyData();
            this.WriteReplyData = temp;
            InitGraphic.Sheep.WriteReplyData = temp;
        }
    }

    @Override
    public void run() {
        while (true) {
            if (InitGame.GameStop) {
                continue;
            }
            if (InitGame.GameEnd) {
                break;
            }
            // Random int to select line for create new car 
            int randomLine = Const.RAND_METHOD.nextInt(super.getLtrLineCount() + super.getRtlLineCount());

            // Get line with random int
            Line tempLine = super.getLine().get(randomLine);

            // Create car speed 
            int speed = tempLine.getMinCarSpeed() + Const.RAND_METHOD.nextInt(tempLine.getMaxCarSpeed());

            // variables for instance car object
            CarType carType;
            Car newCar;

            // Check for direction 
            if (tempLine.getDirection() == Const.LINE_DIRECTION_RTL) {
                carType = new CarType(tempLine.getDirection());
                newCar = new CarRtl(speed, carType, tempLine);
            } else {
                carType = new CarType(tempLine.getDirection());
                newCar = new CarLtr(speed, carType, tempLine);
            }
            // Call create new car method of line 
            boolean temp = tempLine.createNewCar(newCar);

            if (SaveReply && temp) {
                WriteReplyData.appendCarsToFile(newCar);
            }

            // Sleep thread wait for create new car again
            try {
                Thread.sleep(150);//Const.CAR_CREATE_MAX_SLEEP_TIME - GameSetting.getAutoCreateCarRate()
            } catch (Exception ex) {
                new ExceptionWriter().write(ex);
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
