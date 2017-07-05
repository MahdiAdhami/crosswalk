package CrossWalk.Object;

import CrossWalk.Object.Line;
import CrossWalk.Utilities.Const;
import CrossWalk.UI.InitGraphic;
import java.util.ArrayList;

public class CarRtl extends Car {

    public CarRtl(int Speed, CarType CarType, Line Line) {
        super(Const.GAME_WINDOWS_WIDTH, Speed, CarType, Line);
    }

    public CarRtl(float head, int Speed, CarType CarType, Line Line) {
        super(head, Speed, CarType, Line);
    }

    public CarRtl(int Id, float HeadPosition, float Speed, String CarType, Line Line) {
        super(Id, HeadPosition, Speed, CarType, Line);
    }

    public CarRtl(int Id, float Speed, String CarType, Line Line) {
        super(Id, Const.GAME_WINDOWS_WIDTH, Speed, CarType, Line);
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
        if (IsNowOverTaking) {
            IncreasLinePosition += Const.CAR_INCREASE_POSITION_IN_Y;
            if (IncreasLinePosition <= Const.LINE_IMAGE_HEIGHT) {
                return linePosition += IncreasLinePosition - Const.LINE_IMAGE_HEIGHT;
            }
            IsNowOverTaking = false;
            IncreasLinePosition = 0;
        }
        return linePosition;
    }

    @Override
    public boolean isEnoughSpaceForOverTaking(Line otherLine) {
        if (otherLine.getCars().stream().noneMatch((cars) -> (
                cars.getHeadPosition() - Const.CAR_ENOUTH_SPACE_FOR_TAKEOVER < this.getEndPosition()
                && cars.getEndPosition() > this.getHeadPosition() - Const.CAR_ENOUTH_SPACE_FOR_TAKEOVER))) {
        } else {
            return false;
        }
        return true;
    }
    
    @Override
    public void checkCarAccident(Line otherLine) {
        Car carTemp = this;
        getLine().getCars().stream().forEach((carTemp2) -> {
            carTemp.TempCarSpeed = 0;
            if (carTemp.getHeadPosition() <= carTemp2.getEndPosition() + Const.CAR_SPEED_DISTANCE_FOR_REACH && carTemp != carTemp2) {
                if (carTemp.getLine().getCanCarOvertaking()
                        && carTemp.isEnoughSpaceForOverTaking(otherLine) && !isFirstCar() && !IsNowOverTaking) {

                    carTemp.getLine().disposeCar(carTemp);
                    carTemp.setLine(otherLine);
                    otherLine.addCar(carTemp);
                    carTemp.IsNowOverTaking = true;

                } else {
                    carTemp.TempCarSpeed = (carTemp2.getNowSpeed());
                }
            }
        });
    }

}
