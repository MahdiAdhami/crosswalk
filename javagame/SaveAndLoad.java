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

        int idLine = 1;
        int j = 1;
        int idCar = 0;
        Car newCar = null;
        CarType newCarType = null;

        if (LinesReader != null && SheepReader != null) {
            ArrayList<Line> lines = new ArrayList<>();

            while (LinesReader.hasNextLine()) {
                String nextLineFromReader = LinesReader.nextLine();

                if (nextLineFromReader.matches("Line Id = \\d true")) {
                    String[] nextLineSplited = nextLineFromReader.split(" ");
                    idLine = Integer.parseInt(nextLineSplited[3]);
                    lines.add(new Line(idLine, idLine + 1, idLine, Const.LINE_DIRECTION_RTL, Const.LINE_HEIGHT * (idLine - 1) + Const.TOP_MARGIN));
                } else if (nextLineFromReader.matches("Line Id = \\d false")) {
                    String[] nextLineSplited = nextLineFromReader.split(" ");
                    idLine = Integer.parseInt(nextLineSplited[3]);
                    lines.add(new Line(idLine, j + 1, j, Const.LINE_DIRECTION_LTR, Const.LINE_HEIGHT * (idLine - 1) + Const.TOP_MARGIN));
                } else {
                    String[] nextLineSplited = nextLineFromReader.split(" ");
                    if (lines.get(idLine - 1).getDirection() == Const.LINE_DIRECTION_LTR) {
                        newCarType = new CarType(nextLineSplited[5], Integer.parseInt(nextLineSplited[3]), Integer.parseInt(nextLineSplited[4]));
                        newCar = new CarLtr(Float.parseFloat(nextLineSplited[1]), (int) Float.parseFloat(nextLineSplited[2]), newCarType, lines.get(idLine - 1));
                        idCar = Integer.parseInt(nextLineSplited[0]);
                        newCar.setId(idCar);
                    } else {
                        newCarType = new CarType(nextLineSplited[5], Integer.parseInt(nextLineSplited[3]), Integer.parseInt(nextLineSplited[4]));
                        newCar = new CarRtl(Float.parseFloat(nextLineSplited[1]), (int) Float.parseFloat(nextLineSplited[2]), newCarType, lines.get(idLine - 1));
                        idCar = Integer.parseInt(nextLineSplited[0]);
                        newCar.setId(idCar);
                    }
                    lines.get(idLine - 1).getCars().add(newCar);
                    lines.get(idLine - 1).setCarId(idCar + 1);
                }
            }

            LinesReader.close();

            String sheepString = SheepReader.nextLine();
            String[] sheepStringSplited = sheepString.split(" ");
            InitGraphic.Sheep = new Sheep(new int[]{Integer.parseInt(sheepStringSplited[0]), Integer.parseInt(sheepStringSplited[1]) }, Float.parseFloat(sheepStringSplited[2])+ InitGraphic.Sheep.getSheepHeight()/2 );
            InitGraphic.Sheep.setPositionX(Float.parseFloat(sheepStringSplited[3]));
            SheepReader.close();

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
            writerForCars.println("Line Id = " + tempLine.getId() + " " + tempLine.getDirection());
            for (Car tempCar : tempLine.getCars()) {
                writerForCars.println(tempCar.getId() + " " + tempCar.getHeadPosition()
                        + " " + tempCar.getSpeed() + " "
                        + tempCar.getCarType().getCarWidth()
                        + " " + tempCar.getCarType().getCarHeight() + " "
                        + tempCar.getCarType().getCarPathString());
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
