
package javagame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutoMoveSheepForReply implements Runnable{
    
   
    File sheepFile;
    File timeFile;
    
    public AutoMoveSheepForReply(String path)
    {
        sheepFile = new File(path  + "\\sheepFile.txt");
        timeFile = new File(path + "\\timerFile.txt");
    }

    @Override
    public void run() {
        
        Scanner sheepReader = null;
        Scanner timeReader = null;
        try {
            sheepReader = new Scanner(sheepFile);
            timeReader = new Scanner(timeFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AutoMoveSheepForReply.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int wait1 = 0;
        while(sheepReader.hasNextLine())
        {
            
            
            int wait2 = Integer.parseInt(timeReader.nextLine());
            
            try {
                Thread.sleep((wait2-wait1));
            } catch (InterruptedException ex) {
                Logger.getLogger(AutoMoveSheepForReply.class.getName()).log(Level.SEVERE, null, ex);
            }
            String[] argsForSheep = sheepReader.nextLine().split(",");
            
            InitGraphic.Sheep.setPosFromReply(new float[]{Float.parseFloat(argsForSheep[1]), Float.parseFloat(argsForSheep[2])});
            InitGraphic.Sheep.setImageStatus(Integer.parseInt(argsForSheep[5]));
            InitGraphic.Sheep.setImageCode(argsForSheep[6]);
            wait1=wait2;
//            InitGraphic.Sheep = new Sheep(new float[]{Float.parseFloat(argsForSheep2[1]), Float.parseFloat(argsForSheep2[2])},
//                    new int[]{Integer.parseInt(argsForSheep2[3]), Integer.parseInt(argsForSheep2[4])},
//                    Integer.parseInt(argsForSheep2[5]), argsForSheep2[6]
//            );
            
        }
        sheepReader.close();
        timeReader.close();
    }
    
    
}
