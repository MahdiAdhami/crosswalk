package CrossWalk.AutoWork;

import CrossWalk.Menu.GameSetting;
import CrossWalk.Utilities.Const;
import CrossWalk.UI.InitGame;
import CrossWalk.UI.InitGraphic;
import CrossWalk.Object.CarLtr;
import CrossWalk.Object.Car;
import CrossWalk.Object.Line;
import CrossWalk.Object.CarType;
import CrossWalk.Object.CarRtl;
import CrossWalk.StoreData.WriteReplyData;
import CrossWalk.Utilities.ExceptionWriter;

public class CreateCarInNewGame extends CreateCar {

    public boolean SaveReply;
    public WriteReplyData WriteReplyData;

    public CreateCarInNewGame(boolean SaveReply) {
        super();

        this.SaveReply = SaveReply;
        if (SaveReply) {
            WriteReplyData temp = new WriteReplyData();
            this.WriteReplyData = temp;
            InitGraphic.Sheep.setWriteReplyData(temp);
        }
    }

    @Override
    public void run() {
        while (true) {
            if (InitGame.GameEnd) {
                break;
            }
            if (InitGame.GameStop) {
                continue;
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
                Thread.sleep(250);//Const.CAR_CREATE_MAX_SLEEP_TIME - GameSetting.getAutoCreateCarRate());
            } catch (Exception ex) {
                new ExceptionWriter().write("CreateCarInNewGame run()", ex, false);
            }

        }
    }
}
