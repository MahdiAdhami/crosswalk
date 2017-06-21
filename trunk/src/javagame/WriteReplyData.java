package javagame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javagame.Menu.GameSetting;

public class WriteReplyData {

    LocalDateTime StartedTime;
    String Path;

    public WriteReplyData() {

        StartedTime = LocalDateTime.now();
        File mainFolder = new File(Const.ROOT_PATH + Const.REPLY_ROOT_ADDRESS);
        
        if (!mainFolder.exists()) {
            mainFolder.mkdir();
        }
        
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Path = "\\" + timeFormat.format(new Date());

        File subFolder = new File(Const.ROOT_PATH + Const.REPLY_ROOT_ADDRESS + Path);
        
        if (!subFolder.exists()) {
            subFolder.mkdir();
        }

        GameSetting.writeSetting(Const.REPLY_ROOT_ADDRESS + Path + Const.REPLY_SETTING_ADDRESS);
    }


    public void appendCarsToFile(Car tempCar) {
        try {
            FileWriter temp = new FileWriter(Const.ROOT_PATH + Const.REPLY_ROOT_ADDRESS + Path + Const.REPLY_CAR_ADDRESS, true);
            try (BufferedWriter carWriter = new BufferedWriter(temp);) {
                carWriter.append(tempCar.toString());
                carWriter.newLine();
            }
        } catch (IOException ex) {
            System.out.println("ReplyMovie appendCarsToFile() " + ex);
        }
    }

    public void appendSheepToFile(int keyCode) {
        try {
            FileWriter temp = new FileWriter(Const.ROOT_PATH + Const.REPLY_ROOT_ADDRESS + Path + Const.REPLY_SHEEP_ADDRESS, true);

            LocalDateTime tempDate = LocalDateTime.now();
            long diffInMilli = ChronoUnit.MILLIS.between(StartedTime, tempDate);

            try (BufferedWriter sheepWriter = new BufferedWriter(temp)) {
                sheepWriter.append(String.format("%d,%d", keyCode, diffInMilli));
                sheepWriter.newLine();
            }

            StartedTime = tempDate;
        } catch (IOException ex) {
            System.out.println("ReplyMovie appendSheepToFile() " + ex);
        }
    }
}
