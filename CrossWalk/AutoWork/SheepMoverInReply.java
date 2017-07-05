/*
Auto Move Sheep In Game Reply  
 */
package CrossWalk.AutoWork;

import CrossWalk.Utilities.Const;
import CrossWalk.UI.InitGraphic;
import CrossWalk.Object.Sheep;
import CrossWalk.Utilities.ExceptionWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SheepMoverInReply implements Runnable {

    // Sheep data path
    private final String Path;

    // Constructor
    public SheepMoverInReply(String Path) {
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
            new ExceptionWriter().write(ex);
        }

        if (sheepReader != null) {
            Sheep.AutoMove = true;

            while (sheepReader.hasNextLine()) {
                String[] split = sheepReader.nextLine().split(",");
                

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
                    new ExceptionWriter().write(ex);
                }
                InitGraphic.Sheep.move(Integer.parseInt(split[0]));

            }

            sheepReader.close();
        }
    }
}