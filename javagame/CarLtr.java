package javagame;

import java.util.ArrayList;

public class CarLtr extends Car {

    public CarLtr(int Id, int Speed, CarType CarType, Line Line) {
        super(Id, new float[]{(-1)*CarType.getCarWidth(), 0}, Speed, CarType, Line);
    }

    public CarLtr(int Id, int Speed, CarType CarType) {
        super(Id, new float[]{(-1)*CarType.getCarWidth(), 0}, Speed, CarType);
    }

    @Override
    public boolean IsIntheCrosswalk() {
        float[] crosswalkPosition = super.Line.getCrosswalkPosition();

        return ((Position[0] + CarType.getCarWidth() >= crosswalkPosition[0] && (Position[0] + CarType.getCarWidth() <= crosswalkPosition[1]))
                || (Position[1] + CarType.getCarWidth() >= crosswalkPosition[0] && Position[1] + CarType.getCarWidth() <= crosswalkPosition[1]));
    }

    @Override
    public void MoveInLine(ArrayList<Car> cars,int index) {
        float tempSpeed;
        if(cars.get(index).getHeadPosition() >= cars.get(index+1).getHeadPosition()-changeSpeedForReach)
        {
            tempSpeed = getSpeedV2(true);
        }
        else {
            tempSpeed = getSpeedV2(false);
        }
        if (Position[1] > Const.GAME_WINDOWS_WIDTH) {
            Line.Dispose(getId());
            return;
        }

        Position[0] += tempSpeed * Const.SLEEP_TIME_RE_PAINTING / 1000;
        Position[1] = Position[0] - CarType.getCarWidth();
    }

    /* @Override
    public boolean ReachedFrontCar() {
    ArrayList<Car> tempCars = super.Line.getCars();
    
    for(int i=0;i <= tempCars.size() ; i++)
    {
    if(tempCars.get(i).getHeadPosition() >= tempCars.get(i++).getHeadPosition() - Const.CHANGE_SPEED_DISTANCE_FOR_REACH)
    {
    
    }
    }
    
    }
    
    
    */
}
