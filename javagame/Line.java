package javagame;

import java.util.ArrayList;
import javagame.Menu.GameSetting;

public final class Line {

    private int Id;
    private int MaxCarSpeed;
    private int MinCarSpeed;
    private boolean Direction;
    private int Position;
    private ArrayList<Car> Cars;
    public static int CarCount = 0;
    private float[] CrosswalkPosition;
    private int CarId = 1;
    public static int SheepCurrentLine; 

    public Line() {
        this.Cars = new ArrayList<>();
    }

    public Line(int Id, int MaxCarSpeed, int MinCarSpeed, boolean Direction, int Position) {
        this();
        this.Id = Id;
        this.Position = Position;
        this.MaxCarSpeed = MaxCarSpeed * Const.CREATE_CAR_SPEED_RATE;
        this.MinCarSpeed = MinCarSpeed * Const.CREATE_CAR_SPEED_RATE;
        this.Direction = Direction;
        CrosswalkPosition = getAchieveCrosswalkPosistion();
    }

    public Line(int Id, int MaxCarSpeed, int MinCarSpeed, boolean Direction, ArrayList<Car> Cars, int Position) {
        this.Id = Id;
        this.Position = Position;
        this.MaxCarSpeed = MaxCarSpeed;
        this.MinCarSpeed = MinCarSpeed;
        this.Direction = Direction;
        this.Cars = Cars;
        CrosswalkPosition = getAchieveCrosswalkPosistion();
    }

    public int getPosition() {
        return Position;
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

    public void AccidentCheck() {

    }

    public int getId() {
        return Id;
    }
    
    public void CreateNewCar(Car newCar) {
        //  newCar.setLine(this);

        // Cars.add(newCar);
        // System.out.println(Cars.indexOf(newCar));
        // System.out.println("size " + Cars.size());
        if (Cars.size() > 0) {
            Car lastCar = Cars.get(Cars.size() - 1);
            if (lastCar.getLine().getDirection() == newCar.getLine().getDirection()) {

                if (newCar.getLine().getDirection() == Const.LINE_DIRECTION_LTR) {
                    if (lastCar.getEndPosition() >= -lastCar.getCarType().getCarWidth() + 50) {
                        newCar.setId(CarId);
                        Cars.add(newCar);
                        CarId++;
                    }
                } else if (lastCar.getEndPosition() <= (Const.GAME_WINDOWS_WIDTH - lastCar.getCarType().getCarWidth())) {
                    newCar.setId(CarId);
                    Cars.add(newCar);
                    CarId++;
                }
            }
        } else {
            newCar.setId(CarId);
            Cars.add(newCar);
            CarId++;
        }

    }

    public void Dispose(Car car) {
        Cars.remove(car);
        
    }

    public final float[] getAchieveCrosswalkPosistion() {
        float middle = GameSetting.getCrosswalkMiddlePosition();
        float[] result = new float[2];

        if (Direction == Const.LINE_DIRECTION_LTR) {

            result[0] = middle - Const.CROSSWALK_WIDTH / 2 - Const.CROSSWALK_CHANGE_SPEED_DISTANCE;
            result[1] = middle + Const.CROSSWALK_WIDTH / 2;

            return result;

        } else {
            result[0] = middle + Const.CROSSWALK_WIDTH / 2 + Const.CROSSWALK_CHANGE_SPEED_DISTANCE;
            result[1] = middle - Const.CROSSWALK_WIDTH / 2;

            return result;
        }
    }

    public int getLineIdByCar(Car car)
    {
        return car.getLine().getId();
    }
}
