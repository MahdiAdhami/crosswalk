package javagame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutoMoveSheepForReply implements Runnable {

    private String Path;

    public AutoMoveSheepForReply(String Path) {
        this.Path = Path;
    }

    @Override
    public void run() {
        Scanner sheepReader = null;
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

                try {
                    long sleepTime = Long.parseLong(split[1]);
//                    if (sleepTime > 15) {
//                        sleepTime -= 15;
//                    }
                    Thread.sleep(sleepTime);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AutoMoveSheepForReply.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            sheepReader.close();
        }
    }
}

//            String[] argsForSheep = sheepReader.nextLine().split(",");
//
//            InitGraphic.Sheep.setPosFromReply(new float[]{Float.parseFloat(argsForSheep[1]), Float.parseFloat(argsForSheep[2])});
//            InitGraphic.Sheep.setImageStatus(Integer.parseInt(argsForSheep[5]));
//            InitGraphic.Sheep.setImageCode(argsForSheep[6]);
//            InitGraphic.Sheep.CheckLine();
//            wait1 = wait2;
//            InitGraphic.Sheep = new Sheep(new float[]{Float.parseFloat(argsForSheep2[1]), Float.parseFloat(argsForSheep2[2])},
//                    new int[]{Integer.parseInt(argsForSheep2[3]), Integer.parseInt(argsForSheep2[4])},
//                    Integer.parseInt(argsForSheep2[5]), argsForSheep2[6]
//            );
