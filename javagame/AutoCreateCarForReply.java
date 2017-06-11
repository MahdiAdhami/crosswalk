package javagame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javagame.Menu.GameSetting;

public class AutoCreateCarForReply extends AutoCreateCar implements Runnable {

    public String Path;

    public AutoCreateCarForReply(String Path) {
        super();
        
        Lines = new ArrayList<>();
        this.Path = Path;
    }

    @Override
    public void run() {

        Scanner reader = null;

        try {
            reader = new Scanner(new File(Path));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AutoCreateCarForReply.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (reader != null) {
            while (reader.hasNextLine()) {
                String[] temp = reader.nextLine().split(",");

                Line tempLine = Lines.get(Integer.parseInt((temp[5])) - 1);

                if (tempLine.getDirection() == Const.LINE_DIRECTION_LTR) {
                    tempLine.getCars().add(new CarLtr(Integer.parseInt(temp[1]), Float.parseFloat(temp[3]), temp[4], tempLine));
                } else {
                    tempLine.getCars().add(new CarRtl(Integer.parseInt(temp[1]), Float.parseFloat(temp[3]), temp[4], tempLine));
                }

                try {
                    Thread.sleep(Const.CREATE_CAR_SLEEP_TIME - GameSetting.getAutoCreateCarRate());
                } catch (InterruptedException ex) {
                    System.out.println("ReplyMovie run() " + ex);
                }
            }
        }
    }

}
