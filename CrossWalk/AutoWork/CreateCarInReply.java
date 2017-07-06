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
import CrossWalk.UI.InitGame;
import CrossWalk.Utilities.ExceptionWriter;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CreateCarInReply extends CreateCar {

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
            LocalDateTime tempDate = LocalDateTime.now();
            while (reader.hasNextLine()) {
                if (InitGame.GameEnd) {
                    break;
                }
                if (InitGame.GameStop) {
                    continue;
                }

                String[] temp = reader.nextLine().split(",");

                try {
                    Thread.sleep(Long.parseLong((temp[5])));//Const.CAR_CREATE_MAX_SLEEP_TIME - GameSetting.getAutoCreateCarRate());
                } catch (InterruptedException ex) {
                    new ExceptionWriter().write("CreateCarInReply run()", ex, false);
                }

                Line tempLine = getLine().get(Integer.parseInt((temp[4])) - 1);

                if (tempLine.getDirection() == Const.LINE_DIRECTION_LTR) {
                    tempLine.getCars().add(new CarLtr(0, Float.parseFloat(temp[2]), temp[3], tempLine));
                } else {
                    tempLine.getCars().add(new CarRtl(Const.GAME_WINDOWS_WIDTH, Float.parseFloat(temp[2]), temp[3], tempLine));
                }

                LocalDateTime tempDate2 = LocalDateTime.now();
                long diffInMilli = ChronoUnit.MILLIS.between(tempDate, tempDate2);
                System.out.println(diffInMilli);
                tempDate = tempDate2;

            }
        }
    }

}
