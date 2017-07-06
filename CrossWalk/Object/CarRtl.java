package CrossWalk.Object;

import CrossWalk.Utilities.Const;
import CrossWalk.UI.InitGraphic;

public class CarRtl extends Car {

    public CarRtl(int Speed, CrossWalk.Object.CarType CarType, CrossWalk.Object.Line Line) {
        super(Const.GAME_WINDOWS_WIDTH, Speed, CarType, Line);
    }

    public CarRtl(float HeadPosition, float Speed, String CarType, CrossWalk.Object.Line Line) {
        super(HeadPosition, Speed, CarType, Line);
    }

    @Override
    public void checkSheepAccident() {
        if (!isNearToSheepAccident()) {
            return;
        }
        if (this.getHeadPosition() <= InitGraphic.Sheep.getXPositionForDraw() + InitGraphic.Sheep.getSheepWidth()) {
            InitGraphic.Sheep.gameOver();
        }
    }

    @Override
    public void move() {
        float tempSpeed = getNowSpeed();
        if (getEndPosition() < (-1) * getCarType().getCarWidth()) {
            getLine().disposeCar(this);
            return;
        }

        setHeadPosition(getHeadPosition() - tempSpeed * Const.SLEEP_TIME_RE_PAINTING / 1000);
        checkSheepAccident();
    }

    @Override
    public float getEndPosition() {
        return getHeadPosition() + getCarType().getCarWidth();
    }

    // Get X position of car for draw
    @Override
    public int getXPositionForDraw() {
        return (int) getHeadPosition();
    }

    // Get Y position of car for draw
    @Override
    public int getYPositionForDraw() {
        int linePosition = getLine().getYPosition();
        if (getIsNowOverTaking()) {
            int increasPostion = getIncreasLinePosition();
            
            setIncreasLinePosition(increasPostion + Const.CAR_INCREASE_POSITION_IN_Y);
            if (increasPostion <= Const.LINE_IMAGE_HEIGHT) {
                return linePosition += increasPostion - Const.LINE_IMAGE_HEIGHT;
            }
            setIsNowOverTaking(false);
            setIncreasLinePosition(0);
        }
        return linePosition;
    }

    @Override
    public boolean isEnoughSpaceForOverTaking(Line otherLine) {
        if (otherLine.getCars().stream().noneMatch((cars) -> (cars.getHeadPosition() - Const.CAR_ENOUTH_SPACE_FOR_TAKEOVER < this.getEndPosition()
                && cars.getEndPosition() > this.getHeadPosition() - Const.CAR_ENOUTH_SPACE_FOR_TAKEOVER))) {
        } else {
            return false;
        }
        return true;
    }

 @Override
    public void checkCarAccident(Line otherLine) {
       setTempCarSpeed(0);

        for (Car carTemp2 : getLine().getCars()) {
            if (carTemp2.getHeadPosition() > getHeadPosition() && getHeadPosition() <= carTemp2.getEndPosition() + Const.CAR_SPEED_DISTANCE_FOR_REACH) {
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
        return getLine().getCars().stream().noneMatch((car) -> (car.getHeadPosition() < getHeadPosition()));
    }
}
