/*
Car Base Class 
 */
package CrossWalk.Object;

import CrossWalk.Utilities.Const;
import CrossWalk.Menu.GameSetting;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public abstract class Car implements Moveable, Drawable, Serializable {

    private float HeadPosition;
    public float Speed;
    private final CarType CarType;
    private float TempCarSpeed;
    private boolean IsNowOverTaking;
    private int IncreasLinePosition;
    private Line Line;

    // Constructors
    // Create new random car
    public Car(float HeadPosition, int Speed, CarType CarType, Line Line) {
        this.HeadPosition = HeadPosition;
        this.Speed = Speed * GameSetting.getCarsSpeed();
        this.CarType = CarType;
        this.Line = Line;
    }

    // Create new car from file data
    public Car(float HeadPosition, float Speed, String CarType, Line Line) {
        this.HeadPosition = HeadPosition;
        this.Speed = Speed;
        this.CarType = new CarType(Line.getDirection(), CarType);
        this.Line = Line;
    }

    // Getter methods
    public CarType getCarType() {
        return CarType;
    }

    public Line getLine() {
        return Line;
    }

    private float getSpeedInCrosswalk() {
        return getSpeed() * Const.CAR_SPEED_RATE_NEAR_CROSSWALK;
    }

    public float getNowSpeed() {
        if (isNearToSheepAccident()) {
            return getSpeedInCrosswalk();
        }
        return getSpeed();
    }

    private float getSpeed() {
        return (TempCarSpeed > 0) ? TempCarSpeed : Speed;
    }
    
    public boolean getIsNowOverTaking(){
        return IsNowOverTaking;
    }
    public int getIncreasLinePosition(){
        return IncreasLinePosition;
    }

    // Over ride toString Mehod
    @Override
    public String toString() {
        return String.format("Car,%f,%f,%s,%d", HeadPosition, Speed, CarType.getCarNameAndType(), Line.getId());
    }

    // Setter methods
    public void setSpeed(float speed) {
        Speed = speed;
    }

    public void setLine(Line line) {
        Line = line;
    }

    public void setTempCarSpeed(float tempCarSpeed) {
        TempCarSpeed = tempCarSpeed;
    }

    public void setIsNowOverTaking(boolean isNowOverTaking) {
        IsNowOverTaking = isNowOverTaking;
    }

    public void setIncreasLinePosition(int increasLinePosition) {
        IncreasLinePosition = increasLinePosition;
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
        float[] crosswalkPosition = Line.getCrosswalkXPosition();

        return ((getHeadPosition() >= crosswalkPosition[0] && (getHeadPosition() <= crosswalkPosition[1]))
                || (getEndPosition() >= crosswalkPosition[0] && getEndPosition() <= crosswalkPosition[1]));
    }

    public abstract boolean isFirstCar();

    // Abstract mehods
    @Override
    public abstract int getYPositionForDraw();

    // Check for accident car with sheep
    public abstract void checkSheepAccident();

    // Get end position of car
    public abstract float getEndPosition();

    public abstract void checkCarAccident(Line otherLine);

    public abstract boolean isEnoughSpaceForOverTaking(Line otherLine);

    // Implements Drawable
    @Override
    public BufferedImage getImage() {
        return getCarType().getImage();
    }

    // Implements Moveable
    @Override
    public abstract int getXPositionForDraw();

}
