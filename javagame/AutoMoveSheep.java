package javagame;

import java.awt.event.KeyEvent;

public class AutoMoveSheep implements Runnable {

    public long SleepInMilliSecond;
    public int RandRate;

    public AutoMoveSheep(long SleepInMilliSecond, int RandRate) {
        this.SleepInMilliSecond = SleepInMilliSecond;
        this.RandRate = (RandRate > 5) ? RandRate : 5 ;
    }

    @Override
    public void run() {
        while (true) {
            if (InitGame.GameStop) {
                continue;
            }

            int key = Const.RAND.nextInt(RandRate);
            if (key == 1 || key == 2) {
                InitGraphic.Sheep.keyPressed(83);
            } else if (key == 3) {
                InitGraphic.Sheep.keyPressed(68);
            } else if (key == 4) {
                InitGraphic.Sheep.keyPressed(65);
            } else {
                InitGraphic.Sheep.keyPressed(87);
            }
            try {
                Thread.sleep(SleepInMilliSecond);
            } catch (Exception ex) {
                System.err.println("AutoMoveSheep run() " + ex);
            }
        }

    }

}
