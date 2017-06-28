package CrossWalk.Object;

import CrossWalk.Object.*;
import CrossWalk.Object.MoveableObject.Car;
import java.util.ArrayList;
import CrossWalk.Const;
import CrossWalk.Menu.GameSetting;
import CrossWalk.Object.MoveableObject.Car;

public final class Line {

    // Line Id
    private int Id;
    private int MaxCarSpeed;
    private int MinCarSpeed;
    private boolean Direction;
    private int Position;
    private ArrayList<Car> Cars;
    private float[] CrosswalkPosition;
    private int CarId;
    public static int SheepCurrentLine;
    private boolean CanCarOvertaking;

    public Line() {
        this.Cars = new ArrayList<>();
    }

    public Line(int Id, int MaxCarSpeed, int MinCarSpeed, boolean Direction, int Position, boolean CanCarOvertaking) {
        this();
        this.Id = Id;
        this.Position = Position;
        this.MaxCarSpeed = MaxCarSpeed * Const.CREATE_CAR_SPEED_RATE;
        this.MinCarSpeed = MinCarSpeed * Const.CREATE_CAR_SPEED_RATE;
        this.Direction = Direction;
        this.CanCarOvertaking = CanCarOvertaking;
        CrosswalkPosition = getAchieveCrosswalkPosistion();
    }

    public Line(int Id, int MaxCarSpeed, int MinCarSpeed, boolean Direction, int Position, boolean CanCarOvertaking, int CarId) {
        this();
        this.Id = Id;
        this.MaxCarSpeed = MaxCarSpeed;
        this.MinCarSpeed = MinCarSpeed;
        this.Direction = Direction;
        this.Position = Position;
        this.CarId = CarId;
        this.CanCarOvertaking = CanCarOvertaking;
        CrosswalkPosition = getAchieveCrosswalkPosistion();
    }

    public int getPosition() {
        return Position;
    }

    public boolean getCanCarOvertaking() {
        return CanCarOvertaking;
    }

    public int getCarId() {
        return CarId;
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

    public float[] getCrosswalkPosition() {
        return CrosswalkPosition;
    }

    public boolean getDirection() {
        return Direction;
    }

    public void checkAccident() {

    }

    public void addCar(Car newCar) {
        Cars.add(newCar);
    }

    @Override
    public String toString() {
        return String.format("Line,%d,%d,%d,%s,%d,%s,%d", Id, MaxCarSpeed, MinCarSpeed, Direction == true ? "1" : "0", Position, CanCarOvertaking == true ? "1" : "0", CarId);
    }

    public int getId() {
        return Id;
    }

    public boolean createNewCar(Car newCar) {
        try {
            if (Cars.size() > 0) {
                Car lastCar = Cars.get(Cars.size() - 1);
                if (this.getDirection() == Const.LINE_DIRECTION_LTR) {
                    if (lastCar.getEndPosition() > Const.LINE_DISTANCE_TO_CREATE_NEW_CAR) {
                        newCar.setId(CarId);
                        Cars.add(newCar);
                        CarId++;
                        return true;
                    }
                } else if (lastCar.getEndPosition() + Const.LINE_DISTANCE_TO_CREATE_NEW_CAR < Const.GAME_WINDOWS_WIDTH) {
                    newCar.setId(CarId);
                    Cars.add(newCar);
                    CarId++;
                    return true;
                }

            } else {
                newCar.setId(CarId);
                Cars.add(newCar);
                CarId++;
                return true;
            }
            return false;
        } catch (Exception ex) {
            return false;
        }

    }
    //            if (lastCar.getLine().getDirection() == newCar.getLine().getDirection()) {
    //                if (newCar.getLine().getDirection() == Const.LINE_DIRECTION_LTR) {
    //                    if (lastCar.getEndPosition() >= -lastCar.getCarType().getCarWidth() + 50) {
    //                        newCar.setId(CarId);
    //                        Cars.add(newCar);
    //                        CarId++;
    //                        return true;
    //                    }
    //                } else if (lastCar.getEndPosition() <= (Const.GAME_WINDOWS_WIDTH - lastCar.getCarType().getCarWidth())) {
    //                    newCar.setId(CarId);
    //                    Cars.add(newCar);
    //                    CarId++;
    //                    return true;
    //                }
    //            }
    //        } else {
    //            newCar.setId(CarId);
    //            Cars.add(newCar);
    //            CarId++;
    //            return true;
    //        }

    public void disposeCar(Car car) {
        Cars.remove(car);
    }

    public final float[] getAchieveCrosswalkPosistion() {
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

    public void setCarId(int id) {
        CarId = id;
    }

    public void rtlTakeOver() {

    }

    public void ltrTakeOver(int lineId) {

    }

    public boolean isEmptyForTakover(Car newCar) {
//        for (Car car : Cars) {
//            if (car.getHeadPosition() >= newCar.getEndPosition() || newCar.getHeadPosition() >= car.getEndPosition()) {
//                return false;
//            }
//        }
        return true;
    }
}
