package CrossWalk.Object;

import CrossWalk.Utilities.Const;
import CrossWalk.UI.InitGraphic;

public class CarLtr extends Car {

    // Constructor
    public CarLtr(int Speed, CarType CarType, Line Line) {
        super(0, Speed, CarType, Line);
    }

    public CarLtr(float head, int Speed, CarType CarType, Line Line) {
        super(head, Speed, CarType, Line);
    }

    public CarLtr(int Id, float HeadPosition, float Speed, String CarType, Line Line) {
        super(Id, HeadPosition, Speed, CarType, Line);
    }

    public CarLtr(int Id, float Speed, String CarType, Line Line) {
        super(Id, 0, Speed, CarType, Line);
    }

    @Override
    public float getEndPosition() {
        return getHeadPosition() - getCarType().getCarWidth();
    }

    @Override
    public int getXPositionForDraw() {
        return (int) getEndPosition();
    }

    @Override
    public void checkSheepAccident() {
        if (!isNearToSheepAccident()) {
            return;
        }
        if (getHeadPosition() >= InitGraphic.Sheep.getXPositionForDraw() && getEndPosition() <= InitGraphic.Sheep.getXPositionForDraw() + InitGraphic.Sheep.getSheepWidth()) {
            InitGraphic.Sheep.gameOver();
        }
    }

    // Car mover  method
    @Override
    public void move() {
        if (getHeadPosition() - getCarType().getCarWidth() > Const.GAME_WINDOWS_WIDTH) {
            try {
                getLine().disposeCar(this);
            } catch (Exception ex) {
                System.err.println("CarLtr MoveInLine() " + ex);
            }
            return;
        }
        setHeadPosition(getHeadPosition() + (getNowSpeed() * Const.SLEEP_TIME_RE_PAINTING / 1000));
        checkSheepAccident();
    }

    @Override
    public boolean isEnoughSpaceForOverTaking(Line otherLine) {
        if (otherLine.getCars().stream().noneMatch((cars) -> (cars.getHeadPosition() + Const.CAR_ENOUTH_SPACE_FOR_TAKEOVER > this.getEndPosition()
                && cars.getEndPosition() <= this.getHeadPosition() + Const.CAR_ENOUTH_SPACE_FOR_TAKEOVER))) {
        } else {
            return false;
        }
        return true;
    }

    @Override
    public int getYPositionForDraw() {
        int linePosition = getLine().getYPosition();
        if (IsNowOverTaking) {
            IncreasLinePosition += Const.CAR_INCREASE_POSITION_IN_Y;
            if (IncreasLinePosition <= Const.LINE_IMAGE_HEIGHT) {
                return linePosition += Const.LINE_IMAGE_HEIGHT - IncreasLinePosition;
            }
            IsNowOverTaking = false;
            IncreasLinePosition = 0;
        }
        return linePosition;
    }

    @Override
    public void checkCarAccident(Line otherLine) {
        TempCarSpeed = 0;

        for (Car carTemp2 : getLine().getCars()) {
            if (carTemp2.getHeadPosition() > getHeadPosition() && getHeadPosition() + Const.CAR_SPEED_DISTANCE_FOR_REACH >= carTemp2.getEndPosition()) {// 
                if (getLine().getCanCarOvertaking()
                        && isEnoughSpaceForOverTaking(otherLine) && !isFirstCar()) {

                    getLine().disposeCar(this);
                    setLine(otherLine);
                    otherLine.addCar(this);
                    IsNowOverTaking = true;

                } else {
                    TempCarSpeed = carTemp2.getNowSpeed();
                }

                break;
            }
        }
    }

    @Override
    public boolean isFirstCar() {
        return getLine().getCars().stream().noneMatch((car) -> (car.getHeadPosition() > getHeadPosition()));
    }

}
