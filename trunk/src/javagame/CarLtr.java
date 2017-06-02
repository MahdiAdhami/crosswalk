package javagame;

public class CarLtr extends Car {

    public CarLtr(int Id, float Speed, javagame.CarType CarType, javagame.Line Line) {
        super(Id, new float[]{(-1) * CarType.getCarWidth(), 0}, Speed, CarType, Line);
    }

    @Override
    public boolean IsIntheCrosswalk() {
        float[] crosswalkPosition = super.Line.getCrosswalkPosition();
        return ((Position[0] + CarType.getCarWidth() >= crosswalkPosition[0] && (Position[0] + CarType.getCarWidth() <= crosswalkPosition[1]))
                || (Position[1] + CarType.getCarWidth() >= crosswalkPosition[0] && Position[1] + CarType.getCarWidth() <= crosswalkPosition[1]));
    }

    @Override
    public void MoveInLine() {
        try {
            if (Line.getCars().size() > 1) {
                if (Line.getCars().get(Id - 1).getHeadPosition() >= Line.getCars().get(Id).getEndPosition() - Const.CHANGE_SPEED_DISTANCE_FOR_REACH) {
                    ChangeSpeed();
                }
            }
        } catch (Exception ex) {
            System.out.println("Move in Line()" + ex);
        }

        if (Position[1] > Const.GAME_WINDOWS_WIDTH) {
            Line.Dispose(this);
            return;
        }

        Position[0] += getSpeed() * Const.SLEEP_TIME_RE_PAINTING / 1000;
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
