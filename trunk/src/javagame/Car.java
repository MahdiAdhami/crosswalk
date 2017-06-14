package javagame;

import javagame.Menu.GameSetting;

public abstract class Car {

    protected int Id;
    protected float HeadPosition;
    protected float Speed;
    protected CarType CarType;
    protected Line Line;

    // Constructors
    public Car(float HeadPosition, int Speed, CarType CarType, Line Line) {
        this.HeadPosition = HeadPosition;
        this.Speed = Speed * GameSetting.getCarsSpeed()/10;
        this.CarType = CarType;
        this.Line = Line;
    }

    public Car(int Id, float HeadPosition, float Speed, String CarType, Line Line) {
        this.Id = Id;
        this.HeadPosition = HeadPosition;
        this.Speed = Speed;
        this.CarType = new CarType(Line.getDirection(),CarType);
        this.Line = Line;
    }
    
    // Getter methods
    public int getId() {
        return Id;
    }

    public CarType getCarType() {
        return CarType;
    }

    public Line getLine() {
        return Line;
    }

    private float getSpeedInCrosswalk() {
        return Speed * Const.CAR_SPEED_RATE_NEAR_CROSSWALK_CHANGE;
    }

    public float getSpeed() {
        if (isNearToSheepAccident()) {
            return getSpeedInCrosswalk();
        }
        return Speed;
    }

    @Override
    public String toString() {
        return String.format("Car,%d,%f,%f,%s,%d", Id, HeadPosition, Speed, CarType.getCarNameAndType(),Line.getId());
    }

    // Setter methods
    public void setId(int id) {
        Id = id;
    }
    
     public void setLine(Line line) {
        Line = line;
    }

    public void setSpeedFromLoad(float speed) {
        Speed = speed;
    }

    public void setHeadPositionFromLoad(float head) {
        HeadPosition = head;
    }

    public float getHeadPosition() {
        return HeadPosition;
    }

    // Check for sheep accident with car
    public boolean isNearToSheepAccident() {
        return (getLine().getId() == Line.SheepCurrentLine) && IsNeartheCrosswalk();
    }

    // Change line of car
    public void ChangeLine() {
    }

    // Is near to crosswalk
    public boolean IsNeartheCrosswalk() {
        float[] crosswalkPosition = Line.getCrosswalkPosition();

        return ((getHeadPosition() >= crosswalkPosition[0] && (getHeadPosition() <= crosswalkPosition[1]))
                || (getEndPosition() >= crosswalkPosition[0] && getEndPosition() <= crosswalkPosition[1]));
    }

    public abstract boolean IsNeartheOtherCar();

    public abstract void MoveInLine();

    public abstract void checkSheepAccident();

    public abstract float getSpeedNearOtherCar();

    public abstract float getEndPosition();

    public abstract float getPositionForDraw();
}

///////////////////
//private long[] AchieveCrosswalkTime;
//private Date CreatedDate;
//private Date AccidentDate;
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
//    public boolean IsIntheCrosswalk() {
//        float[] crosswalkPosition = Line.getCrosswalkPosition();
//        if(Line.getDirection() == Const.LINE_DIRECTION_LTR)
//        {
//            return ((Position[0] + CarType.getCarWidth() >= crosswalkPosition[0]&& (Position[0]+ CarType.getCarWidth() <= crosswalkPosition[1])) 
//                    || (Position[1]+ CarType.getCarWidth() >= crosswalkPosition[0] && Position[1]+ CarType.getCarWidth() <= crosswalkPosition[1]) );
//        }
//        else
//        {
//            return ((Position[0] <= crosswalkPosition[0] && (Position[0] >= crosswalkPosition[1]))
//                    || (Position[1] <= crosswalkPosition[0] && Position[1] >= crosswalkPosition[1]) );
//        }
//    }
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
//}
//   public abstract boolean ReachedFrontCar();
//        float tempSpeed = getSpeed();
//
//        if (Line.getDirection() == Const.LINE_DIRECTION_LTR) {
//            if (Position[0] >= 700) {
//                Line.Dispose(getId());
//                return;
//            }
//        } else if (Position[1] <= 0) {
//            Line.Dispose(getId());
//            return;
//        }
//
//        if (Line.getDirection() == Const.LINE_DIRECTION_LTR) {
//            Position[0] += tempSpeed * Const.SLEEP_TIME_RE_PAINTING / 1000;
//            Position[1] = Position[0] - CarType.getCarWidth();
//        } else {
//            Position[0] -= tempSpeed * Const.SLEEP_TIME_RE_PAINTING / 1000;
//            Position[1] = Position[0] + CarType.getCarWidth();
//        }
//
//    }
