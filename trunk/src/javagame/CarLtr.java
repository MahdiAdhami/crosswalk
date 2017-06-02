package javagame;

public class CarLtr extends Car {

    public CarLtr(int Id, int Speed, CarType CarType, Line Line) {
        super(Id, new float[]{(-1)*CarType.getCarWidth(), (-2)*CarType.getCarWidth()}, Speed, CarType, Line);
    }

    public CarLtr(int Id, int Speed, CarType CarType) {
        super(Id, new float[]{(-1)*CarType.getCarWidth(), (-2)*CarType.getCarWidth()}, Speed, CarType);
    }

    @Override
    public boolean IsIntheCrosswalk() {
        float[] crosswalkPosition = super.Line.getCrosswalkPosition();

        return ((Position[0] + CarType.getCarWidth() >= crosswalkPosition[0] && (Position[0] + CarType.getCarWidth() <= crosswalkPosition[1]))
                || (Position[1] + CarType.getCarWidth() >= crosswalkPosition[0] && Position[1] + CarType.getCarWidth() <= crosswalkPosition[1]));
    }

    @Override
    public void MoveInLine() {
        float tempSpeed= getSpeedV2(false);
        
        if (Position[1] > Const.GAME_WINDOWS_WIDTH) {
            try{
            Line.Dispose(Line.getCars().indexOf(this));
            }
            catch(Exception ex)
            {
                System.err.println("CarLtr MoveInLine() " + ex);
            }
        return;
        }
        
        
//        if(this.getLine().getCars().size()>=2){
//            if(super.Line.getCars().get(super.getLine().carId).getHeadPosition() >= Line.getCars().get(super.getLine().carId-1).getEndPosition() - Const.CHANGE_SPEED_DISTANCE_FOR_REACH)
//            {
//                tempSpeed = getSpeedV2(true);
//            }
//            else {
//                tempSpeed = getSpeedV2(false);
//            }
//        }
        

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
