package CrossWalk.Object;

import java.util.ArrayList;
import CrossWalk.Utilities.Const;
import CrossWalk.Menu.GameSetting;
import CrossWalk.Utilities.ExceptionWriter;
import java.io.Serializable;

public final class Line implements Serializable {
    // Fields
        // Line Id
    private int Id;
    private int MaxCarSpeed;
    private int MinCarSpeed;
    private boolean Direction;
    private int YPosition;
    private ArrayList<Car> Cars;
    private float[] CrosswalkXPosition;
    private boolean CanCarOvertaking;

    public static int SheepCurrentLine;

    public Line() {
        this.Cars = new ArrayList<>();
    }

    public Line(int Id, int MaxCarSpeed, int MinCarSpeed, boolean Direction, int YPosition, boolean CanCarOvertaking) {
        this();
        this.Id = Id;
        this.YPosition = YPosition;
        this.MaxCarSpeed = MaxCarSpeed * Const.CAR_SPEED_RATE_NEW_CREATED;
        this.MinCarSpeed = MinCarSpeed * Const.CAR_SPEED_RATE_NEW_CREATED;
        this.Direction = Direction;
        this.CanCarOvertaking = CanCarOvertaking;
        CrosswalkXPosition = calcuteXCrosswalkPosistion();
    }

    public int getYPosition() {
        return YPosition;
    }

    public boolean getCanCarOvertaking() {
        return CanCarOvertaking;
    }

    public int getMinCarSpeed() {
        return MinCarSpeed;
    }

    public int getMaxCarSpeed() {
        return MaxCarSpeed;
    }

    public ArrayList<Car> getCars() {
        return Cars;
    }

    public float[] getCrosswalkXPosition() {
        return CrosswalkXPosition;
    }

    public boolean getDirection() {
        return Direction;
    }

    public int getId() {
        return Id;
    }

    @Override
    public String toString() {
        return String.format("Line,%d,%d,%d,%s,%d,%s", Id, MaxCarSpeed, MinCarSpeed, Direction == true ? "1" : "0", YPosition, CanCarOvertaking == true ? "1" : "0");
    }

    public void addCar(Car newCar) {
        Cars.add(newCar);
    }
    
    // Determine if Car Has gone a specified Distance to create a new car 
    public boolean createNewCar(Car newCar) {
        try {
            if (Cars.size() > 0) {
                Car lastCar = Cars.get(Cars.size() - 1);
                if (this.getDirection() == Const.LINE_DIRECTION_LTR) {
                    if (lastCar.getEndPosition() > Const.LINE_DISTANCE_NEED_TO_CREATE_NEW_CAR) {
                        Cars.add(newCar);
                        return true;
                    }
                } else if (lastCar.getEndPosition() + Const.LINE_DISTANCE_NEED_TO_CREATE_NEW_CAR < Const.GAME_WINDOWS_WIDTH) {
                    Cars.add(newCar);
                    return true;
                }
            } else {
                Cars.add(newCar);
                return true;
            }
            return false;
        } catch (Exception ex) {
            new ExceptionWriter().write("Line createNewCar()", ex, false);
            return false;
        }

    }

    public void disposeCar(Car car) {
        Cars.remove(car);
    }
    
    //calculate distance before cross line for decreasing speed 
    private float[] calcuteXCrosswalkPosistion() {
        float middle = GameSetting.getCrosswalkMiddlePosition();
        float[] result = new float[2];

        if (Direction == Const.LINE_DIRECTION_LTR) {

            result[0] = middle - Const.CROSSWALK_WIDTH / 2 - Const.CAR_DISTANCE_TO_CROSSWALK_FOR_DECREASE_SPEED;
            result[1] = middle + Const.CROSSWALK_WIDTH / 2;

            return result;

        } 
        else {
            result[0] = middle - Const.CROSSWALK_WIDTH / 2;
            result[1] = middle + Const.CROSSWALK_WIDTH / 2 + Const.CAR_DISTANCE_TO_CROSSWALK_FOR_DECREASE_SPEED;

            return result;
        }
    }
}
