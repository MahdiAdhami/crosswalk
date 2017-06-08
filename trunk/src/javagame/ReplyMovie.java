
package javagame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javagame.Menu.GameSetting;

public class ReplyMovie implements Runnable{
    
    File mainFolder;
    File subFolder;
    File carFile;
    String filePathForRun;
    File fileForRun;
    ArrayList<Line> Lines;
    
    Date nowTime = new Date();
    SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
    
    public ReplyMovie(String path , ArrayList<Line> lines)
    {
        filePathForRun = path;
        try {
            fileForRun = new File(path);
        } catch (Exception e) {
        }
        Lines = lines;
        
        
        mainFolder = new File(Const.PATH + "\\src\\resources\\Replies");
        if(!mainFolder.exists())
        {
            mainFolder.mkdir();
        }
        subFolder = new File(mainFolder + "\\ReplyOf-" + timeFormat.format(nowTime));
        if(!subFolder.exists())
        {
            subFolder.mkdir();
        }
        
        GameSetting.writeSetting("\\src\\resources\\Replies\\ReplyOf-" + timeFormat.format(nowTime) + "\\Setting.xml");
        
        carFile = new File(subFolder.toString() + "\\carFile.txt");
    }
    
    public void appendCarsToFile(Car tempCar)
    {
        FileWriter temp = null;
        BufferedWriter carWriter = null;
        try {
           temp = new FileWriter(carFile,true);
        } catch (IOException ex) {
            Logger.getLogger(ReplyMovie.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        carWriter = new BufferedWriter(temp);
        
        try {
            carWriter.append(tempCar.toString());
            carWriter.newLine();
          //  carWriter.
        } catch (IOException ex) {
            Logger.getLogger(ReplyMovie.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            carWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(ReplyMovie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        Scanner reader = null ;
        try {
            reader = new Scanner(fileForRun);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReplyMovie.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while(reader.hasNextLine())
        {
            String[] temp = reader.nextLine().split(",");
            Line tempLine = Lines.get(Integer.parseInt((temp[5]))-1);
            
            if(tempLine.getDirection() == Const.LINE_DIRECTION_LTR){
                tempLine.getCars().add(new CarLtr(Integer.parseInt(temp[1]), Float.parseFloat(temp[2]), Float.parseFloat(temp[3]), temp[4], tempLine));
            }
            else
            {
                tempLine.getCars().add(new CarRtl(Integer.parseInt(temp[1]), Float.parseFloat(temp[2]), Float.parseFloat(temp[3]), temp[4], tempLine));
            }
            try {
                Thread.sleep(GameSetting.getAutoCreateCarRate());
            } catch (InterruptedException ex) {
                Logger.getLogger(ReplyMovie.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
    
}
