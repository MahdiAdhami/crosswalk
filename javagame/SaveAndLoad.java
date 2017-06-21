package javagame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import javagame.Menu.GameSetting;

public class SaveAndLoad {

    private String LineFile;
    private String SheepFile;
    private String CarFile;
    private String SettingPathFile;

    public SaveAndLoad() {
    }

    public SaveAndLoad(String LineFile, String SheepFile, String CarFile, String SettingPathFile) {
        this.LineFile = LineFile;
        this.SheepFile = SheepFile;
        this.CarFile = CarFile;
        this.SettingPathFile = SettingPathFile;
    }

    public final void initForSaveGame() {
        File resumeFolder = new File(Const.PATH);
        if (!resumeFolder.exists()) {
            resumeFolder.mkdir();
        }
        SettingPathFile = Const.SAVE_FILE_ADDRESS_SETTING;
        LineFile = Const.SAVE_FILE_ADDRESS_LINE;
        SheepFile = Const.SAVE_FILE_ADDRESS_SHEEP;
        CarFile = Const.SAVE_FILE_ADDRESS_CAR;
    }

    public boolean isExistResumeFile() {
        return (new File(Const.PATH + Const.SAVE_FILE_ADDRESS_LINE).exists() && new File(Const.PATH + Const.SAVE_FILE_ADDRESS_SHEEP).exists());
    }

    public ArrayList<Line> LoadGame() {
        Scanner LinesReader = null;
        Scanner SheepReader = null;
        Scanner CarReader = null;

        try {
            LinesReader = new Scanner(new File(Const.PATH + LineFile));
            CarReader = new Scanner(new File(Const.PATH + CarFile));
            SheepReader = new Scanner(new File(Const.PATH + SheepFile));

            GameSetting.setSettingPath(SettingPathFile);

        } catch (FileNotFoundException ex) {
            System.err.println("Load LoadGame()");
        }
        String nextLineFromReader;

        ArrayList<Line> lines = new ArrayList<>();

        if (CarReader != null && LinesReader != null && SheepReader != null) {
//
//            // Read Lines
            while (LinesReader.hasNextLine()) {
                nextLineFromReader = LinesReader.nextLine();
                String[] temp = nextLineFromReader.split(",");
                lines.add(new Line(Integer.parseInt(temp[1]), Integer.parseInt(temp[2]), Integer.parseInt(temp[3]), "1".equals(temp[4]), Integer.parseInt(temp[5]), "1".equals(temp[6]), Integer.parseInt(temp[7])));
            }

            while (CarReader.hasNextLine()) {
                nextLineFromReader = CarReader.nextLine();
                String[] temp = nextLineFromReader.split(",");
                Line tempLine = lines.get(Integer.parseInt(temp[5]) - 1);

                if (tempLine.getDirection() == Const.LINE_DIRECTION_LTR) {
                    tempLine.getCars().add(new CarLtr(Integer.parseInt(temp[1]), Float.parseFloat(temp[2]), Float.parseFloat(temp[3]), temp[4], tempLine));
                } else {
                    tempLine.getCars().add(new CarRtl(Integer.parseInt(temp[1]), Float.parseFloat(temp[2]), Float.parseFloat(temp[3]), temp[4], tempLine));
                }

            }

            String sheepString = SheepReader.nextLine();
            String[] sheepStringSplited = sheepString.split(",");
            InitGraphic.Sheep = new Sheep(new float[]{Float.parseFloat(sheepStringSplited[1]), Float.parseFloat(sheepStringSplited[2])},
                    new int[]{Integer.parseInt(sheepStringSplited[3]), Integer.parseInt(sheepStringSplited[4])},
                    Integer.parseInt(sheepStringSplited[5]), sheepStringSplited[6]
            );

//            String[] argsForSheep = SheepReader.nextLine().split(",");
                
//            InitGraphic.Sheep.setPosFromReply(new float[]{Float.parseFloat(argsForSheep[1]), Float.parseFloat(argsForSheep[2])});
//            InitGraphic.Sheep.setImageStatus(Integer.parseInt(argsForSheep[5]));
//            InitGraphic.Sheep.setImageCode(argsForSheep[6]);
//            InitGraphic.Sheep.CheckLine();
            
            SheepReader.close();
            LinesReader.close();
            CarReader.close();

            return lines;
        }

        return null;
    }

    public void SaveGame(ArrayList<Line> line) {
        PrintWriter writerForLine = null;
        PrintWriter writerForCars = null;

        try {
            writerForCars = new PrintWriter(Const.PATH + CarFile, "UTF-8");
            writerForLine = new PrintWriter(Const.PATH + LineFile, "UTF-8");

            GameSetting.writeSetting(SettingPathFile);

        } catch (FileNotFoundException | UnsupportedEncodingException ex) {

        }
        for (Line tempLine : line) {
            writerForLine.println(tempLine.toString());
            for (Car tempCar : tempLine.getCars()) {
                writerForCars.println(tempCar.toString());
            }
        }
        if (writerForLine != null) {
            writerForLine.close();
        }
        if (writerForCars != null) {
            writerForCars.close();
        }
    }

    private void SaveSheepForResume() {
        PrintWriter writerForSheep = null;
        try {
            writerForSheep = new PrintWriter(Const.PATH + SheepFile, "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {

        }
        writerForSheep.println(InitGraphic.Sheep.toString());
        writerForSheep.close();
    }

    public void SaveGameForResume(ArrayList<Line> line) {
        initForSaveGame();
        SaveGame(line);
        SaveSheepForResume();
    }

    public ArrayList<Line> LoadForResume() {
        initForSaveGame();

        return LoadGame();
    }
}

//
//else if(nextLineFromReader.matches("Line Id = \\d false"))
//            {
//                String[] nextLineSplited = nextLineFromReader.split(" ");
//                int id = Integer.parseInt(nextLineSplited[3]);
//                AutoCreateCar.Lines.add(new Line(id,id + 1,id ,Const.LINE_DIRECTION_RTL ,  Const.LINE_HEIGHT * (id - 1) + Const.TOP_MARGIN));
//            }
