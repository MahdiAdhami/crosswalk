package javagame;

public class Car {

    private int Id;
    private float[] Position;
    private float Speed;
    private CarType CarType;
    //private long[] AchieveCrosswalkTime;
    //private Date CreatedDate;
    //private Date AccidentDate;

    private Line Line;

    public Car(int Id, float[] Position, int Speed, CarType CarType, Line Line) {
        this.Position = Position;
        this.Speed = Speed;
        this.CarType = CarType;
        this.Id = Id;
        setLine(Line);
    }

    public Car(int Id, float[] Position, int Speed, CarType CarType) {
        this.Position = Position;
        this.Speed = Speed;
        this.CarType = CarType;
        this.Id = Id;
    }

    public void setLine(Line line) {
        Line = line;
//        AchieveCrosswalkTime = calcuteAchieveCrosswalkTime();
    }

    public CarType getCarType() {
        return CarType;
    }

    public float getHeadPosition() {
        return Position[0];
    }
    
     public float getEndPosition() {
        return Position[1];
    }

    public int getId() {
        return Id;
    }

//    public float[] getAchieveCrosswalkPosistion() {
//        float StartPositionRate = 0f;
//        float crosswalkStartPositionRate = 0f;
//        float DistanceToCrosswalk = 0f;
//
//        if (Line.getDirection() == Const.LINE_DIRECTION_LTR) {
//            StartPositionRate = (-.5f) * Const.CROSSWALK_WIDTH;
//            crosswalkStartPositionRate = Const.CROSSWALK_WIDTH;
//            DistanceToCrosswalk = Const.CHANGE_SPEED_DISTANCE_TO_CROSSWALK;
//        } else {
//            StartPositionRate = (.5f) * Const.CROSSWALK_WIDTH;
//            crosswalkStartPositionRate = (-1) * Const.CROSSWALK_WIDTH;
//            DistanceToCrosswalk = (-1) * Const.CHANGE_SPEED_DISTANCE_TO_CROSSWALK;
//        }
//
//        float[] result = new float[2];
//        result[0] = Crosswalk.getMiddleOfPosition() + StartPositionRate - DistanceToCrosswalk;
//        result[1] = Crosswalk.getMiddleOfPosition() + StartPositionRate + crosswalkStartPositionRate + DistanceToCrosswalk;
//        return result;
//    }
    
    public boolean IsIntheCrosswalk() {
        float[] crosswalkPosition = Line.getCrosswalkPosition();
//        System.out.println(Position[0] + " " + crosswalkPosition[0] + " " + crosswalkPosition[1]);
        return ((Position[0] >= crosswalkPosition[0])
                && (Position[0] <= crosswalkPosition[1]));

    }

//    public void setAchieveCrosswalkTime(long[] achieveCrosswalkTime) {
//        AchieveCrosswalkTime = achieveCrosswalkTime;
//    }
//    public final long[] calcuteAchieveCrosswalkTime() {
//        float[] crosswalkPosition = getAchieveCrosswalkPosistion();
//
//        long timeSinceToNow = Timer.DifferenceTime();
//        long[] Achieve = new long[2];
//
//        Achieve[0] = timeSinceToNow + (long) (crosswalkPosition[0] / Speed);
//        Achieve[1] = timeSinceToNow + (long) (crosswalkPosition[1] / Speed);
//
//        return Achieve;
//    }
    private float getSpeedInCrosswalk() {
        return Speed * .2f;
    }

    public float getSpeed() {
        System.out.println(IsIntheCrosswalk());
        return (IsIntheCrosswalk()) ? getSpeedInCrosswalk() : Speed;
    }

    public void MoveInLine() {
        float tempSpeed = getSpeed();

        if (Line.getDirection() == Const.LINE_DIRECTION_LTR) {
            Position[0] += tempSpeed * Const.SLEEP_TIME_RE_PAINTING;
            Position[1] = Position[0] - CarType.getCarWidth();
        } 
        else {
            Position[0] -= tempSpeed * Const.SLEEP_TIME_RE_PAINTING / 1000;
            Position[1] = Position[0] - CarType.getCarWidth();
        }
        
    }

    public void ChangeLine() {

    }
}
