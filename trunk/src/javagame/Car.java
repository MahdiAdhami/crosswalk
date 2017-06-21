/*
Car Base Class 
*/

package javagame;

import javagame.Menu.GameSetting;

public abstract class Car {

    protected int Id;
    protected float HeadPosition;
    protected float Speed;
    protected CarType CarType;
    protected Line Line;

    // Constructors
    // Create new random car
    public Car(float HeadPosition, int Speed, CarType CarType, Line Line) {
        this.HeadPosition = HeadPosition;
        this.Speed = Speed * GameSetting.getCarsSpeed()/10;
        this.CarType = CarType;
        this.Line = Line;
    }
    
    // Create new car from file data
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

    // Over ride toString Mehod
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

    //  Is car near to crosswalk Method
    public boolean IsNeartheCrosswalk() {
        float[] crosswalkPosition = Line.getCrosswalkPosition();

        return ((getHeadPosition() >= crosswalkPosition[0] && (getHeadPosition() <= crosswalkPosition[1]))
                || (getEndPosition() >= crosswalkPosition[0] && getEndPosition() <= crosswalkPosition[1]));
    }

    // Abstract mehods

    // Check for distance from other car
    public abstract boolean IsNeartheOtherCar();
    
    // Move Car Methods
    public abstract void Move();

    // Check for accident car with sheep
    public abstract void checkSheepAccident();

    // Get  speed of other car
    public abstract float getSpeedNearOtherCar();

    // Get end position of car
    public abstract float getEndPosition();

    // Get position of car for draw in InitGraphic
    public abstract float getPositionForDraw();
}

