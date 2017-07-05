package CrossWalk.Object;

import java.util.ArrayList;
import CrossWalk.Utilities.Const;
import CrossWalk.Menu.GameSetting;
import java.io.Serializable;

public final class Line implements Serializable {

    // Line Id
    private int Id;
    private int MaxCarSpeed;
    private int MinCarSpeed;
    private boolean Direction;
    private int YPosition;
    private ArrayList<Car> Cars;
    private float[] CrosswalkXPosition;
    private int CreatedCarCount;
    public static int SheepCurrentLine;
    private boolean CanCarOvertaking;

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

    public Line(int Id, int MaxCarSpeed, int MinCarSpeed, boolean Direction, int Position, boolean CanCarOvertaking, int CreatedCarCount) {
        this();
        this.Id = Id;
        this.MaxCarSpeed = MaxCarSpeed;
        this.MinCarSpeed = MinCarSpeed;
        this.Direction = Direction;
        this.YPosition = Position;
        this.CreatedCarCount = CreatedCarCount;
        this.CanCarOvertaking = CanCarOvertaking;
        CrosswalkXPosition = calcuteXCrosswalkPosistion();
    }

    public int getYPosition() {
        return YPosition;
    }

    public boolean getCanCarOvertaking() {
        return CanCarOvertaking;
    }

    public int getCreatedCarCount() {
        return CreatedCarCount;
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
        return String.format("Line,%d,%d,%d,%s,%d,%s,%d", Id, MaxCarSpeed, MinCarSpeed, Direction == true ? "1" : "0", YPosition, CanCarOvertaking == true ? "1" : "0", CreatedCarCount);
    }

    public void addCar(Car newCar) {
        increaseCreatedCarCount();
        Cars.add(newCar);
    }

    public boolean createNewCar(Car newCar) {
        try {
            if (Cars.size() > 0) {
                Car lastCar = Cars.get(Cars.size() - 1);
                if (this.getDirection() == Const.LINE_DIRECTION_LTR) {
                    if (lastCar.getEndPosition() > Const.LINE_DISTANCE_NEED_TO_CREATE_NEW_CAR) {
                        newCar.setId(CreatedCarCount);
                        Cars.add(newCar);
                        CreatedCarCount++;
                        return true;
                    }
                } else if (lastCar.getEndPosition() + Const.LINE_DISTANCE_NEED_TO_CREATE_NEW_CAR < Const.GAME_WINDOWS_WIDTH) {
                    newCar.setId(CreatedCarCount);
                    Cars.add(newCar);
                    CreatedCarCount++;
                    return true;
                }

            } else {
                newCar.setId(CreatedCarCount);
                Cars.add(newCar);
                CreatedCarCount++;
                return true;
            }
            return false;
        } catch (Exception ex) {
            return false;
        }

    }

    public void disposeCar(Car car) {
        Cars.remove(car);
    }

    private float[] calcuteXCrosswalkPosistion() {
        float middle = GameSetting.getCrosswalkMiddlePosition();
        float[] result = new float[2];

        if (Direction == Const.LINE_DIRECTION_LTR) {

            result[0] = middle - Const.CROSSWALK_WIDTH / 2 - Const.CAR_DISTANCE_TO_CROSSWALK_FOR_DECREASE_SPEED;
            result[1] = middle + Const.CROSSWALK_WIDTH / 2;

            return result;

        } else {
            result[0] = middle - Const.CROSSWALK_WIDTH / 2;
            result[1] = middle + Const.CROSSWALK_WIDTH / 2 + Const.CAR_DISTANCE_TO_CROSSWALK_FOR_DECREASE_SPEED;

            return result;
        }
    }

    public void increaseCreatedCarCount() {
        CreatedCarCount++;
    }

}
