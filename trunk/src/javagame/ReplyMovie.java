package javagame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javagame.Menu.GameSetting;

public class ReplyMovie implements Runnable {

    File mainFolder;
    File subFolder;
    File carFile;
    File sheepFile;
    File timerFile;
    String filePathForRun;
    File fileForRun;
    ArrayList<Line> Lines;
    private Timer timer;

    Date nowTime = new Date();
    SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    LocalDateTime StartedDateTime = LocalDateTime.now();

    public ReplyMovie(Timer timer) {
        mainFolder = new File(Const.PATH + "\\src\\resources\\Replies");
        if (!mainFolder.exists()) {
            mainFolder.mkdir();
        }
        subFolder = new File(mainFolder + "\\ReplyOf-" + timeFormat.format(nowTime));
        if (!subFolder.exists()) {
            subFolder.mkdir();
        }

        GameSetting.writeSetting("\\src\\resources\\Replies\\ReplyOf-" + timeFormat.format(nowTime) + "\\Setting.xml");

        carFile = new File(subFolder.toString() + "\\carFile.txt");
        sheepFile = new File(subFolder.toString() + "\\sheepFile.txt");
        timerFile = new File(subFolder.toString() + "\\timerFile.txt");
        this.timer = timer;
    }

    public ReplyMovie(String path, ArrayList<Line> lines) {
        filePathForRun = path;
        try {
            fileForRun = new File(path);
        } catch (Exception e) {
        }
        Lines = lines;
    }

    public void appendCarsToFile(Car tempCar) {
        FileWriter temp = null;
        BufferedWriter carWriter = null;
        try {
            temp = new FileWriter(carFile, true);
        } catch (IOException ex) {
            Logger.getLogger(ReplyMovie.class.getName()).log(Level.SEVERE, null, ex);
        }

        carWriter = new BufferedWriter(temp);

        try {
            carWriter.append(tempCar.toString());
            carWriter.newLine();

        } catch (IOException ex) {
            Logger.getLogger(ReplyMovie.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            carWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(ReplyMovie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void appendSheepToFile(Sheep tempSheep) {
        FileWriter temp = null;
        BufferedWriter sheepWriter = null;

        FileWriter temp2 = null;
        BufferedWriter timeWriter = null;
        try {
            temp = new FileWriter(sheepFile, true);
            temp2 = new FileWriter(timerFile, true);
        } catch (IOException ex) {
            Logger.getLogger(ReplyMovie.class.getName()).log(Level.SEVERE, null, ex);
        }

        sheepWriter = new BufferedWriter(temp);
        timeWriter = new BufferedWriter(temp2);

        try {
            sheepWriter.append(tempSheep.toString());
            sheepWriter.newLine();

            timeWriter.append(timer.getTimeForChange() + "");
            timeWriter.newLine();

        } catch (IOException ex) {
            Logger.getLogger(ReplyMovie.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            sheepWriter.close();
            timeWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(ReplyMovie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void appendSheepToFile(int keyCode) {
        FileWriter temp = null;
        BufferedWriter sheepWriter = null;

        try {
            temp = new FileWriter(sheepFile, true);
        } catch (IOException ex) {
            Logger.getLogger(ReplyMovie.class.getName()).log(Level.SEVERE, null, ex);
        }

        sheepWriter = new BufferedWriter(temp);

        try {
            LocalDateTime tempDate = LocalDateTime.now();
            long diffInMilli = ChronoUnit.MILLIS.between(StartedDateTime, tempDate);
            sheepWriter.append(String.format("%d,%d", keyCode, diffInMilli));
            sheepWriter.newLine();

            StartedDateTime = tempDate;
        } catch (IOException ex) {
            Logger.getLogger(ReplyMovie.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        try {
            sheepWriter.close();

        } catch (IOException ex) {
            Logger.getLogger(ReplyMovie.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        Scanner reader = null;
        try {
            reader = new Scanner(fileForRun);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReplyMovie.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        while (reader.hasNextLine()) {
            String[] temp = reader.nextLine().split(",");
            Line tempLine = Lines.get(Integer.parseInt((temp[5])) - 1);

            if (tempLine.getDirection() == Const.LINE_DIRECTION_LTR) {
                tempLine.getCars().add(new CarLtr(Integer.parseInt(temp[1]), Float.parseFloat(temp[3]), temp[4], tempLine));
            } else {
                tempLine.getCars().add(new CarRtl(Integer.parseInt(temp[1]), Float.parseFloat(temp[3]), temp[4], tempLine));
            }

            try {
                Thread.sleep(GameSetting.getAutoCreateCarRate());

            } catch (InterruptedException ex) {
                Logger.getLogger(ReplyMovie.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}