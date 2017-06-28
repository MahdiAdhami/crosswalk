/*
Auto Move Sheep In Game Reply  
 */
package CrossWalk.AutoWork;

import CrossWalk.Const;
import CrossWalk.InitGraphic;
import CrossWalk.Object.MoveableObject.Sheep;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutoMoveSheepInReply implements Runnable {

    // Sheep data path
    private final String Path;

    // Constructor
    public AutoMoveSheepInReply(String Path) {
        this.Path = Path;
    }

    // Implemets thread method
    @Override
    public void run() {
        Scanner sheepReader = null;

        // Find sheep file from path
        try {
            sheepReader = new Scanner(new File(Path + Const.REPLY_SHEEP_ADDRESS));
        } catch (FileNotFoundException ex) {
            System.out.println("AutoMoveSheepForReply run()" + ex);
        }

        if (sheepReader != null) {
            Sheep.AutoMove = true;

            while (sheepReader.hasNextLine()) {
                String[] split = sheepReader.nextLine().split(",");
                InitGraphic.Sheep.keyPressed(Integer.parseInt(split[0]));

                // Sleep time
                try {
                    long sleepTime = Long.parseLong(split[1]);
                    
                    // Write data time
                    if (sleepTime > 30) {
                        sleepTime -= 30;
                    } else if (sleepTime > 15) {
                        sleepTime -= 15;
                    } else if (sleepTime > 5) {
                        sleepTime -= 3;
                    }
                    
                    Thread.sleep(sleepTime);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AutoMoveSheepInReply.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            sheepReader.close();
        }
    }
}