/*
    Auto Move Sheep In Game 
 */
package CrossWalk.AutoWork;

import CrossWalk.Utilities.Const;
import CrossWalk.UI.InitGame;
import CrossWalk.UI.InitGraphic;
import CrossWalk.Utilities.ExceptionWriter;

public class SheepMoverInBotGame implements Runnable {

    // Time beetween two action
    private final long SleepInMilliSecond;

    // Random rate for change sheep position
    private final int RandRate;

    // Constructor
    public SheepMoverInBotGame(long SleepInMilliSecond, int RandRate) {
        this.SleepInMilliSecond = (SleepInMilliSecond > 0) ? 200 : SleepInMilliSecond;
        this.RandRate = (RandRate > 5) ? RandRate : 5;
    }

    // Implements thread methods
    @Override
    public void run() {
        while (true) {
            if (InitGame.GameStop) {
                continue;
            }
            if (InitGame.GameEnd) {
                break;
            }

            // New random int
            int key = Const.RAND_METHOD.nextInt(RandRate);

            if (key == 1 || key == 2) {
                InitGraphic.Sheep.move(83);
            } else if (key == 3) {
                InitGraphic.Sheep.move(68);

            } else if (key == 4) {
                InitGraphic.Sheep.move(65);
            } else {
                InitGraphic.Sheep.move(87);
            }

            // Sleep time
            try {
                Thread.sleep(SleepInMilliSecond);
            } catch (Exception ex) {
                new ExceptionWriter().write(ex);
            }
        }
    }
}
