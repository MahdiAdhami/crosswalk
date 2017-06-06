package javagame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import javagame.Menu.GameSetting;
import javax.swing.JOptionPane;

public class SaveAndLoad {

    private File Linefile;
    private File SheepFile;

    public SaveAndLoad() {
        File resumeFolder = new File(Const.PATH);
        if (!resumeFolder.exists()) {
            resumeFolder.mkdir();
        }
        Linefile = new File(Const.PATH + Const.SAVE_FILE_ADDRESS_LINE);
        SheepFile = new File(Const.PATH + Const.SAVE_FILE_ADDRESS_SHEEP);
    }

    public boolean isExistResumeFile() {
        return (SheepFile.exists() && Linefile.exists());
    }

    public ArrayList<Line> LoadGame() {
        if (!isExistResumeFile()) {
            return null;
        }

        Scanner LinesReader = null;
        Scanner SheepReader = null;

        try {
            LinesReader = new Scanner(Linefile);
            SheepReader = new Scanner(SheepFile);

            GameSetting.setSettingPath(Const.SAVE_FILE_ADDRESS_SETTING);

        } catch (FileNotFoundException ex) {
            System.err.println("Load LoadGame()");
        }

        int lineId = 0;

        if (LinesReader != null && SheepReader != null) {
            ArrayList<Line> lines = new ArrayList<>();

            while (LinesReader.hasNextLine()) {
                String nextLineFromReader = LinesReader.nextLine();

                if (nextLineFromReader.startsWith("Line")) {
                    String[] temp = nextLineFromReader.split(",");
                    lines.add(new Line(Integer.parseInt(temp[1]), Integer.parseInt(temp[2]), Integer.parseInt(temp[3]), "1".equals(temp[4]), Integer.parseInt(temp[5]), "1".equals(temp[6]), Integer.parseInt(temp[7])));
                    lineId++;
                } else if (nextLineFromReader.startsWith("Car")) {
                    String[] temp = nextLineFromReader.split(",");
                    Line tempLine = lines.get(lineId - 1);
                    if (tempLine.getDirection() == Const.LINE_DIRECTION_LTR) {
                        tempLine.getCars().add(new CarLtr(Integer.parseInt(temp[1]), Float.parseFloat(temp[2]), Float.parseFloat(temp[3]), temp[4],tempLine));
                    } else {
                        tempLine.getCars().add(new CarRtl(Integer.parseInt(temp[1]), Float.parseFloat(temp[2]), Float.parseFloat(temp[3]), temp[4],tempLine));
                    }
                }
            }

            String sheepString = SheepReader.nextLine();
            String[] sheepStringSplited = sheepString.split(" ");
            InitGraphic.Sheep = new Sheep(new int[]{Integer.parseInt(sheepStringSplited[0]), Integer.parseInt(sheepStringSplited[1])}, Float.parseFloat(sheepStringSplited[2]) + InitGraphic.Sheep.getSheepHeight() / 2);
            InitGraphic.Sheep.setPositionX(Float.parseFloat(sheepStringSplited[3]));

            SheepReader.close();
            LinesReader.close();

            return lines;
        }
        return null;
    }

    public void SaveGame(ArrayList<Line> line) {

        PrintWriter writerForCars = null;
        PrintWriter writerForSheep = null;

        try {
            writerForCars = new PrintWriter(Const.PATH + Const.SAVE_FILE_ADDRESS_LINE, "UTF-8");
            writerForSheep = new PrintWriter(Const.PATH + Const.SAVE_FILE_ADDRESS_SHEEP, "UTF-8");

            GameSetting.writeSetting(Const.SAVE_FILE_ADDRESS_SETTING);

        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        for (Line tempLine : line) {
            writerForCars.println(tempLine.SaveToFile());
            for (Car tempCar : tempLine.getCars()) {
                writerForCars.println(tempCar.SaveToFile());
            }
        }

        int[] rateOfSheep = InitGraphic.Sheep.getRate();
        writerForSheep.println(rateOfSheep[0] + " " + rateOfSheep[1] + " " + InitGraphic.Sheep.getYPosition() + " " + InitGraphic.Sheep.getXPosition());

        writerForSheep.close();
        writerForCars.close();
    }
}

//
//else if(nextLineFromReader.matches("Line Id = \\d false"))
//            {
//                String[] nextLineSplited = nextLineFromReader.split(" ");
//                int id = Integer.parseInt(nextLineSplited[3]);
//                AutoCreateCar.Lines.add(new Line(id,id + 1,id ,Const.LINE_DIRECTION_RTL ,  Const.LINE_HEIGHT * (id - 1) + Const.TOP_MARGIN));
//            }
