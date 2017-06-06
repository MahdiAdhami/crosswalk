
package javagame.Load;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javagame.AutoCreateCar;
import javagame.Car;
import javagame.CarLtr;
import javagame.CarRtl;
import javagame.CarType;
import javagame.Const;
import javagame.InitGraphic;
import javagame.Line;
import javagame.Sheep;

public class Load {
            int j = 1;
            int idLine  = 1;
            int idCar = 0 ;
            Car newCar = null;
            CarType newCarType = null;
    public Load()
    {
        Start();
    }
    public void Start()
    {
        Scanner reader = null ;
        try {
            reader = new Scanner(new File(Const.PATH + "Save-File.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Load.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        while(reader.hasNextLine()){
            
            
            String nextLineFromReader = reader.nextLine();
            
            if(nextLineFromReader.matches("Line Id = \\d true"))
            {
                String[] nextLineSplited = nextLineFromReader.split(" ");
                idLine = Integer.parseInt(nextLineSplited[3]);
                AutoCreateCar.Lines.add(new Line(idLine ,idLine + 1 ,idLine , Const.LINE_DIRECTION_RTL, Const.LINE_HEIGHT * (idLine - 1) + Const.TOP_MARGIN));
            }
            else if(nextLineFromReader.matches("Line Id = \\d false"))
            {
                String[] nextLineSplited = nextLineFromReader.split(" ");
                idLine = Integer.parseInt(nextLineSplited[3]);
                AutoCreateCar.Lines.add(new Line(idLine ,j + 1 ,j , Const.LINE_DIRECTION_LTR, Const.LINE_HEIGHT * (idLine - 1) + Const.TOP_MARGIN));
            }
            else{
                String[] nextLineSplited = nextLineFromReader.split(" ");
                if(AutoCreateCar.Lines.get(idLine-1).getDirection() == Const.LINE_DIRECTION_LTR){
                    newCarType = new CarType(nextLineSplited[5],Integer.parseInt(nextLineSplited[3]),Integer.parseInt(nextLineSplited[4]));
                    newCar = new CarLtr(Float.parseFloat(nextLineSplited[1]), (int) Float.parseFloat(nextLineSplited[2]),newCarType,AutoCreateCar.Lines.get(idLine-1));
                    newCar.setId(Integer.parseInt(nextLineSplited[0]));
                }
                else
                {
                    newCarType = new CarType(nextLineSplited[5],Integer.parseInt(nextLineSplited[3]),Integer.parseInt(nextLineSplited[4]));
                    newCar = new CarRtl(Float.parseFloat(nextLineSplited[1]),(int) Float.parseFloat(nextLineSplited[2]),newCarType,AutoCreateCar.Lines.get(idLine-1));
                    idCar = Integer.parseInt(nextLineSplited[0]);
                    newCar.setId(idCar);
                }
                AutoCreateCar.Lines.get(idLine-1).getCars().add(newCar);
                AutoCreateCar.Lines.get(idLine-1).setCarId(idCar);
            }
        }
        
        reader.close();
        
        
        Scanner sheepReader = null;
        try {
            sheepReader = new Scanner(new File(Const.PATH + "Save-File-Sheep.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Load.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String sheepString = sheepReader.nextLine();
        String[] sheepStringSplited = sheepString.split(" ");
        InitGraphic.Sheep = new Sheep(new int[]{Integer.parseInt(sheepStringSplited[0]),Integer.parseInt(sheepStringSplited[1])},Float.parseFloat(sheepStringSplited[2]));
        InitGraphic.Sheep.setPositionX(Float.parseFloat(sheepStringSplited[3]));
        
        InitGraphic base = new InitGraphic(AutoCreateCar.Lines);
        Thread threadBase = new Thread(base);
        threadBase.start();
        
        AutoCreateCar auto = new AutoCreateCar();
        Thread threadAuto = new Thread(auto);
        threadAuto.start();
    }
}

//
//else if(nextLineFromReader.matches("Line Id = \\d false"))
//            {
//                String[] nextLineSplited = nextLineFromReader.split(" ");
//                int id = Integer.parseInt(nextLineSplited[3]);
//                AutoCreateCar.Lines.add(new Line(id,id + 1,id ,Const.LINE_DIRECTION_RTL ,  Const.LINE_HEIGHT * (id - 1) + Const.TOP_MARGIN));
//            }