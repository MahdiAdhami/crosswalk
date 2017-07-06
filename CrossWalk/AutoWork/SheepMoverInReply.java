/*
Auto Move Sheep In Game Reply  
 */
package CrossWalk.AutoWork;

import CrossWalk.Utilities.Const;
import CrossWalk.UI.InitGraphic;
import CrossWalk.Object.Sheep;
import CrossWalk.UI.InitGame;
import CrossWalk.Utilities.ExceptionWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SheepMoverInReply implements Runnable {

    // Sheep data path
    private final String SheepPath;

    // Constructor
    public SheepMoverInReply(String SheepPath) {
        this.SheepPath = SheepPath;
    }

    // Implemets thread method
    @Override
    public void run() {

        Scanner sheepReader = null;

        // Find sheep file from path
        try {
            sheepReader = new Scanner(new File(SheepPath + Const.REPLY_SHEEP_ADDRESS));
        } catch (FileNotFoundException ex) {
            new ExceptionWriter().write("SheepMoverInReply run()", ex, false);
        }

        if (sheepReader != null) {
            Sheep.AutoMove = true;

            while (sheepReader.hasNextLine()) {
                if (InitGame.GameEnd) {
                    break;
                }
                if (InitGame.GameStop) {
                    continue;
                }
                String[] split = sheepReader.nextLine().split(",");

                // Sleep time
                try {
                    long sleepTime = Long.parseLong(split[1]);

                    // Write data time
                    if(sleepTime >= 5) {
                        sleepTime -= 5;
                    }

                    Thread.sleep(sleepTime);
                } catch (InterruptedException ex) {
                    new ExceptionWriter().write("SheepMoverInReply run()", ex, false);
                }
                InitGraphic.Sheep.move(Integer.parseInt(split[0]));
            }

            sheepReader.close();
        }
    }
}
