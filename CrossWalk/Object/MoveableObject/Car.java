/*
Car Base Class 
 */
package CrossWalk.Object.MoveableObject;

import CrossWalk.Object.Line;
import CrossWalk.Const;
import CrossWalk.Menu.GameSetting;
import com.sun.javafx.util.TempState;
import java.util.ArrayList;

public abstract class Car implements Moveable {

    private int Id;
    private float HeadPosition;
    private float Speed;
    private final CarType CarType;
    private Line Line;

    ///
    public float TempCarSpeed;
    public boolean isNowOverTaking;

    ///
    // Constructors
    // Create new random car
    public Car(float HeadPosition, int Speed, CarType CarType, Line Line) {
        this.HeadPosition = HeadPosition;
        this.Speed = Speed * GameSetting.getCarsSpeed();
        this.CarType = CarType;
        this.Line = Line;
    }

    // Create new car from file data
    public Car(int Id, float HeadPosition, float Speed, String CarType, Line Line) {
        this.Id = Id;
        this.HeadPosition = HeadPosition;
        this.Speed = Speed;
        this.CarType = new CarType(Line.getDirection(), CarType);
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
        return getSpeed() * Const.CAR_SPEED_RATE_NEAR_CROSSWALK_CHANGE;
    }

    public float getNowSpeed() {
        if (isNearToSheepAccident()) {
            return getSpeedInCrosswalk();
        }
        return getSpeed();
    }

    public float getSpeed() {
        return (TempCarSpeed > 0) ? TempCarSpeed : Speed;
    }

    // Over ride toString Mehod
    @Override
    public String toString() {
        return String.format("Car,%d,%f,%f,%s,%d", Id, HeadPosition, Speed, CarType.getCarNameAndType(), Line.getId());
    }

    // Setter methods
    public void setId(int id) {
        Id = id;
    }

    public void setSpeed(float speed) {
        Speed = speed;
    }

    public void setLine(Line line) {
        Line = line;
    }

    public void setHeadPosition(float headPosition) {
        HeadPosition = headPosition;
    }

    public float getHeadPosition() {
        return HeadPosition;
    }

    // Check for sheep accident with car
    public boolean isNearToSheepAccident() {
        return (getLine().getId() == Line.SheepCurrentLine) && isNeartheCrosswalk();
    }

    //  Is car near to crosswalk Method
    public boolean isNeartheCrosswalk() {
        float[] crosswalkPosition = Line.getCrosswalkPosition();

        return ((getHeadPosition() >= crosswalkPosition[0] && (getHeadPosition() <= crosswalkPosition[1]))
                || (getEndPosition() >= crosswalkPosition[0] && getEndPosition() <= crosswalkPosition[1]));
    }

    // Implements Moveable interface
    @Override
    public int getYPositionForDraw() {
        return (int) Line.getPosition();
    }

    // Abstract mehods
    // Check for distance from other car
    public abstract boolean isNeartheOtherCar();

    // move Car Methods
    public abstract void move();

    // Check for accident car with sheep
    public abstract void checkSheepAccident();

    // Get  speed of other car
    public abstract float getSpeedNearOtherCar();

    // Get end position of car
    public abstract float getEndPosition();

    // Get position of car for draw in InitGraphic
    @Override
    public abstract int getXPositionForDraw();

    public abstract void checkCarAccident(ArrayList<Line> lines);

    public abstract boolean isEnoughSpaceForOverTaking(Line line);

}
