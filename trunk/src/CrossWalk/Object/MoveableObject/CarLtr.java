package CrossWalk.Object.MoveableObject;

import CrossWalk.Object.Line;
import CrossWalk.Const;
import CrossWalk.InitGame;
import CrossWalk.InitGraphic;
import java.util.ArrayList;

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
        float tempSpeed = getNowSpeed();

        if (getHeadPosition() - getCarType().getCarWidth() > Const.GAME_WINDOWS_WIDTH) {
            try {
                getLine().disposeCar(this);
            } catch (Exception ex) {
                System.err.println("CarLtr MoveInLine() " + ex);
            }
            return;
        }
        setHeadPosition(getHeadPosition() + tempSpeed * Const.SLEEP_TIME_RE_PAINTING / 1000);
        checkSheepAccident();
    }

    ///////////////////////////////////////////////////////////////////////////    
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public boolean isNeartheOtherCar() {
        try {
//            Car car = Line.Cars.get(Line.CarId - Id - 1);
//            if (car.Id == Id - 1) {
//
//                if (car.getEndPosition() < 0 || HeadPosition < 0 || car.HeadPosition == getHeadPosition() || car.HeadPosition == getHeadPosition()) {
//                    return false;
//                }
//
//                if (car.getEndPosition() <= HeadPosition + Const.CHANGE_SPEED_DISTANCE_FOR_REACH) {
////                System.out.printf("%d== %d\n", Id, car.Id);
//                    OtherCarSpeed = car.getSpeedNearOtherCar();
//                    System.out.printf("%d== %d\n", Id, car.Id);
//                    return true;
//                }
//            }
            return false;
        } catch (Exception ex) {
//            System.err.println("CarLtr isNeartheOtherCar() " + ex);
            return false;
        }
    }

    @Override
    public float getSpeedNearOtherCar() {

        if (isNeartheOtherCar()) {
//                InitGame.RunAfterWait = true;
            return 0;
        }
        return super.getNowSpeed();

//        } catch (Exception ex) {
////                System.err.println("CarLtr isNeartheOtherCar() " + ex);
//        }
    }

    @Override
    public boolean isEnoughSpaceForOverTaking(Line line) {
//        for (Car cars : line.getCars()) {
////            if (cars.getHeadPosition() + cars.getCarType().getCarWidth() < this.getHeadPosition()
////                    && cars.getEndPosition() - cars.getCarType().getCarWidth() > this.getEndPosition()) {
////                return false;
////            }
//            if (cars.getHeadPosition() + cars.getCarType().getCarWidth() < this.getHeadPosition()
//                    && cars.getEndPosition() - cars.getCarType().getCarWidth() > this.getEndPosition()) {
//                return false;
//            }
//        }
        return true;
    }

    @Override
    public void checkCarAccident(ArrayList<Line> lines) {
        Car carTemp = this;
        for (Car carTemp2 : super.getLine().getCars()) {
            carTemp.TempCarSpeed = 0;
            if (carTemp.getId() - 1 == carTemp2.getId()) {
                if (carTemp.getHeadPosition() >= carTemp2.getEndPosition() - Const.CHANGE_SPEED_DISTANCE_FOR_REACH) {

                    carTemp.TempCarSpeed = (carTemp2.getNowSpeed());
//                    if (carTemp.getLine().getCanCarOvertaking()) {
//                        if (carTemp.isEnoughSpaceForOverTaking()) {
//                            carTemp.TempCarSpeed = (carTemp2.getNowSpeed());
//                        } else {
//                            carTemp.TempCarSpeed = (carTemp2.getNowSpeed());
//                        }
//                    } else {
//                        carTemp.TempCarSpeed = (carTemp2.getNowSpeed());
//                    }

                }
                return;

            }
        }
    }

}

///////////
//        if (this.getLine().getCars().size() >= 2) {
//          //  for(int i = 2 ; i <= this.getLine().getCars().size() ; i++ )
//            //{
//                if(!(this.getLine().isDisposed(this.getLine().getCars().get(i - 1)) && this.getLine().isDisposed(this.getLine().getCars().get(i - 2)))){
//                    if (this.getLine().getCars().get(i - 1).getHeadPosition() >= this.getLine().getCars().get(i - 2).getEndPosition() - Const.CHANGE_SPEED_DISTANCE_FOR_REACH) {
//                    tempSpeed = getSpeedV2(true);
//
//                    }
//                }      
//            //}
//
//        
//        }
//        
//        if (this.getLine().getCars().size() >= 3) {
//            int id1 = this.getId();
//            int id2 = id1 - 1;
//
//            //  int lineid1 = this.getLine().getId();
//            //  int lineid2 = ;
//            if (this.getLine().getDirection() == Const.LINE_DIRECTION_LTR) {
//                if (this.getLine().getCars().get(id1 - 1).getHeadPosition() >= this.getLine().getCars().get(id2 - 1).getEndPosition() - Const.CHANGE_SPEED_DISTANCE_FOR_REACH) {
//                    tempSpeed = getSpeedNearOtherCar();
//                }
//            }
//        }
//    
//    public boolean checkAccident(Car thisCar) {
//        int lineID = 1;
//        Line tempLine = thisCar.getLine();
////        for (int i = 1; i <= GameSetting.getLtrLineCount() + GameSetting.getRtlLineCount(); i++) {
////            tempLine = Lines.get(i - 1);
////
////            if (tempLine.getCars().contains(thisCar)) {
////                lineID = tempLine.getId();
////                break;
////            }
////        }
//     ArrayList<Car> tempCars = null;
//        try {
//            tempCars = tempLine.getCars();
//        } catch (Exception e) {
//            System.out.println("checkaccident" + e.getClass() + " " + e + "tempcars arraylist");
//        }
//        
//        
//        
//        int tempIndex = 0;
//        try {
//            tempIndex = tempCars.indexOf(thisCar);
//        } catch (Exception e) {
//            System.out.println("checkaccident" + e.getClass() + " " + e + "tempcars index");
//        }
//
//        
//           
//
//                
//                    if (tempCars.get(tempIndex).getHeadPosition() >= tempCars.get(tempIndex - 1).getEndPosition() - Const.CHANGE_SPEED_DISTANCE_FOR_REACH) {
//                        return true;
//                    } 
//
//            
//        
//        return false;
//    }
