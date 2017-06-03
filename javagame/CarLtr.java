package javagame;

public class CarLtr extends Car {

    public CarLtr(int Speed, CarType CarType, Line Line) {
        super(new float[]{(-1) * CarType.getCarWidth(), (-2) * CarType.getCarWidth()}, Speed, CarType, Line);
    }

    public CarLtr(int Id, int Speed, CarType CarType) {
        super(new float[]{(-1) * CarType.getCarWidth(), (-2) * CarType.getCarWidth()}, Speed, CarType);
    }

    @Override
    public boolean IsNeartheCrosswalk() {
        float[] crosswalkPosition = Line.getCrosswalkPosition();

        return ((Position[0] + CarType.getCarWidth() >= crosswalkPosition[0] && (Position[0] + CarType.getCarWidth() <= crosswalkPosition[1]))
                || (Position[1] + CarType.getCarWidth() >= crosswalkPosition[0] && Position[1] + CarType.getCarWidth() <= crosswalkPosition[1]));
    }

    @Override
    public float getSpeedNearOtherCar(float speed) {
        try {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        } catch (Exception ex) {
            System.err.println("Car getSpeedNearOtherCar() :" + ex);
            return Speed;
        }
    }

    @Override
    public void MoveInLine() {

        float tempSpeed = getSpeed();

        if (Position[1] > Const.GAME_WINDOWS_WIDTH) {
            try {
                Line.Dispose(this);
            } catch (Exception ex) {
                System.err.println("CarLtr MoveInLine() " + ex);
            }
            return;
        }

//        if (this.getLine().getCars().size() >= 3) {
//            int id1 = this.getId();
//            int id2 = id1 - 1;
//
//            //  int lineid1 = this.getLine().getId();
//            //  int lineid2 = ;
//            if (this.getLine().getDirection() == Const.LINE_DIRECTION_LTR) {
//                if (this.getLine().getCars().get(id1 - 1).getHeadPosition() >= this.getLine().getCars().get(id2 - 1).getEndPosition() - Const.CHANGE_SPEED_DISTANCE_FOR_REACH) {
//                    tempSpeed = getSpeedNearOtherCar();
//                }
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
    @Override
    public void checkSheepAccident() {
        if (!super.isNearToSheepAccident()) {
            return;
        }

        System.out.print("Death");
    }
}
