package javagame;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class CarRtl extends Car {

    public CarRtl(int Speed, CarType CarType, Line Line) {
        super(new float[]{Const.GAME_WINDOWS_WIDTH, Const.GAME_WINDOWS_WIDTH + CarType.getCarWidth()}, Speed, CarType, Line);
    }

    public CarRtl(int Id, float[] Position, int Speed, CarType CarType) {
        super(new float[]{Const.GAME_WINDOWS_WIDTH, Const.GAME_WINDOWS_WIDTH + CarType.getCarWidth()}, Speed, CarType);
    }

    @Override
    public void checkSheepAccident() {
        if (!super.isNearToSheepAccident()) {
            return;
        }
        if (super.getHeadPosition() <= (InitGraphic.Sheep.getSheepWidth() / 2) + 5) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(CarRtl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public boolean IsNeartheCrosswalk() {
        float[] crosswalkPosition = super.Line.getCrosswalkPosition();
        return ((Position[0] <= crosswalkPosition[0] && (Position[0] >= crosswalkPosition[1]))
                || (Position[1] <= crosswalkPosition[0] && Position[1] >= crosswalkPosition[1]));

    }

    @Override
    public void MoveInLine() {
        float tempSpeed = getSpeedV2(false);
        
        
        if (this.getLine().getCars().size() >= 3) {
            for(int i = this.getLine().getCars().size() ; i >= this.getLine().getCars().size()-this.getLine().getRemovedCount() ; i-- )
            {
                if (this.getLine().getCars().get(i - 1).getHeadPosition() >= this.getLine().getCars().get(i - 2).getEndPosition() + Const.CHANGE_SPEED_DISTANCE_FOR_REACH) {
                tempSpeed = getSpeedV2(true);
  
            }
        
                }

        
        }
        
        
        
        
        
        
        
        
        
        
        
        
//
//        if (Position[1] < 0) {
//            Line.Dispose(this);
//            return;
//        }
//
//        try {
//            int id1 = super.getId();
//
//            // int lineid1 = this.getLine().getId();
//            //int lineid2 = Line.getLineIdByCar(Line.getCars().get(id2));
//            if (super.Line.getCars().get(id1 - 1).getEndPosition() >= super.Line.getCars().get(id1).getHeadPosition() - Const.CHANGE_SPEED_DISTANCE_FOR_REACH) {
//                tempSpeed = getSpeedNearOtherCar(.2f);
//            }
//
//        } catch (Exception ex) {
//            System.err.println("CarLtr MoveInLine() " + ex);
//        }

        Position[0] -= tempSpeed * Const.SLEEP_TIME_RE_PAINTING / 1000;
        Position[1] = Position[0] + CarType.getCarWidth();
    }

    @Override
    public float getSpeedNearOtherCar(float speed) {
        try {
            float otherCarSpeed = Line.getCars().get(super.getId() - 1).getSpeed();
            return otherCarSpeed * Const.CHANGE_SPEED_RATE_DISTANCE_FOR_REACH;
        } catch (Exception ex) {
            System.err.println("Car getSpeedNearOtherCar() :" + ex);
            return getSpeed();
        }
    }
}
