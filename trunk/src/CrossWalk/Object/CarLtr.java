package CrossWalk.Object;

import CrossWalk.Utilities.Const;
import CrossWalk.UI.InitGraphic;

public class CarLtr extends Car {

    // Constructor
    public CarLtr(int Speed, CrossWalk.Object.CarType CarType, CrossWalk.Object.Line Line) {
        super(0, Speed, CarType, Line);
    }

    public CarLtr(float HeadPosition, float Speed, String CarType, CrossWalk.Object.Line Line) {
        super(HeadPosition, Speed, CarType, Line);
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
        if (getIsNowOverTaking()) {
            int increasPostion = getIncreasLinePosition();
            
            setIncreasLinePosition(increasPostion + Const.CAR_INCREASE_POSITION_IN_Y);
            if (increasPostion <= Const.LINE_IMAGE_HEIGHT) {
                return linePosition += Const.LINE_IMAGE_HEIGHT - increasPostion;
            }
            setIsNowOverTaking(false);
            setIncreasLinePosition(0);
        }
        return linePosition;
    }

    @Override
    public void checkCarAccident(Line otherLine) {
        setTempCarSpeed(0);

        for (Car carTemp2 : getLine().getCars()) {
            if (carTemp2.getHeadPosition() > getHeadPosition() && getHeadPosition() + Const.CAR_SPEED_DISTANCE_FOR_REACH >= carTemp2.getEndPosition()) {// 
                if (getLine().getCanCarOvertaking()
                        && isEnoughSpaceForOverTaking(otherLine) && !isFirstCar()) {

                    getLine().disposeCar(this);
                    setLine(otherLine);
                    otherLine.addCar(this);
                    setIsNowOverTaking(true);

                } else {
                    setTempCarSpeed(carTemp2.getNowSpeed());
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
