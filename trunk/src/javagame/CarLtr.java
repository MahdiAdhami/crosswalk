package javagame;

public class CarLtr extends Car {

    // Constructor
    public CarLtr(int Speed, CarType CarType, Line Line) {
        super((-1) * CarType.getCarWidth(), Speed, CarType, Line);
    }
    
    public CarLtr(float head,int Speed, CarType CarType, Line Line) {
        super(head, Speed, CarType, Line);
    }

    @Override
    public float getEndPosition() {
        return HeadPosition - CarType.getCarWidth();
    }

    @Override
    public float getPositionForDraw() {
        return getEndPosition();
    }

    @Override
    public void checkSheepAccident() {
        if (!isNearToSheepAccident()) {
            return;
        }
        if (getHeadPosition() >= InitGraphic.Sheep.getXPosition() && getEndPosition() <= InitGraphic.Sheep.getXPosition() + InitGraphic.Sheep.getSheepWidth()) {
            InitGame.GameStop = true;
        }
    }

    @Override
    public void MoveInLine() {
        float tempSpeed = getSpeed();
        if (HeadPosition - CarType.getCarWidth() > Const.GAME_WINDOWS_WIDTH) {
            try {
                Line.Dispose(this);
            } catch (Exception ex) {
                System.err.println("CarLtr MoveInLine() " + ex);
            }
            return;
        }
        HeadPosition += tempSpeed * Const.SLEEP_TIME_RE_PAINTING / 1000;
        checkSheepAccident();
    }

    ///////////////////////////////////////////////////////////////////////////    
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public boolean IsNeartheOtherCar() {
        int carId = super.getId();
        try {
            if (getLine().getCars().get(carId - 1).getEndPosition() - this.getHeadPosition() <= Const.CHANGE_SPEED_DISTANCE_FOR_REACH) {
                return true;
            }
        } catch (Exception ex) {
            System.err.println("CarLtr IsNeartheOtherCar() " + ex);
            return false;
        }
        return false;
    }

    @Override
    public float getSpeedNearOtherCar() {
        if (IsNeartheOtherCar()) {
            try {
//                Car temp = getLine().getCars().get(super.getId() - 1);
//                if (temp.getSpeed() <= getSpeed()) {
                return getSpeed();
                //             }
            } catch (Exception ex) {
//                System.err.println("CarLtr IsNeartheOtherCar() " + ex);
            }
        }
        return super.getSpeed();
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
