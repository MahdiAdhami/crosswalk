package javagame;

public class CarRtl extends Car {

    public CarRtl(int Speed, CarType CarType, Line Line) {
        super(new float[]{Const.GAME_WINDOWS_WIDTH,CarType.getCarWidth() + Const.GAME_WINDOWS_WIDTH}, Speed, CarType, Line);
    }

    @Override
    public void checkSheepAccident() {
        if (!isNearToSheepAccident()) {
            return;
        }
//       System.out.println(getHeadPosition()+"  " + InitGraphic.Sheep.getXPosition() + "   "+InitGraphic.Sheep.getSheepWidth()/2);
        if (this.getHeadPosition() <= InitGraphic.Sheep.getXPosition() + InitGraphic.Sheep.getSheepWidth()){
            InitGame.GameStop = true;
        }

    }
 
    @Override
    public void MoveInLine() {
        int i = this.getId();

        float tempSpeed = getSpeedV2(false);

        if (getEndPosition() < (-1) * CarType.getCarWidth()) {
            Line.Dispose(this);
            return;
        }

        Position[0] -= tempSpeed * Const.SLEEP_TIME_RE_PAINTING / 1000;
        
        checkSheepAccident();
    }

    @Override
    public float getSpeedNearOtherCar() {
        try {
            float otherCarSpeed = Line.getCars().get(super.getId() - 1).getSpeed();
            return otherCarSpeed * Const.CHANGE_SPEED_RATE_DISTANCE_FOR_REACH;
        } catch (Exception ex) {
            System.err.println("Car getSpeedNearOtherCar() :" + ex);
            return getSpeed();
        }
    }

    @Override
    public boolean IsNeartheOtherCar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float getEndPosition() {
        return Position[0] +  CarType.getCarWidth();
    }
    
    @Override
    public float getPositionForDraw(){
        return getHeadPosition();
    }
}


//////////////////////////////

//        
//       if (super.getLine().getCars().size() >= 3) {
//            //for(int i = 2 ; i <= this.getLine().getCars().size() ; i++ )
//            //{
//                if(!(this.getLine().isDisposed(this.getLine().getCars().get(i - 1)) && this.getLine().isDisposed(this.getLine().getCars().get(i - 2)))){
//                    if (this.getLine().getCars().get(i - 1).getHeadPosition() <= this.getLine().getCars().get(i - 2).getEndPosition() + Const.CHANGE_SPEED_DISTANCE_FOR_REACH) {
//                    tempSpeed = getSpeedV2(true);
//
//                    }
//                }
//           //}
//       }
//        
//        
//        try {
//            int id1 = super.getId();
//
//            // int lineid1 = this.getLine().getId();
//            //int lineid2 = Line.getLineIdByCar(Line.getCars().get(id2));
//            if (super.Line.getCars().get(id1 - 1).getEndPosition() >= super.Line.getCars().get(id1).getHeadPosition() - Const.CHANGE_SPEED_DISTANCE_FOR_REACH) {
//                tempSpeed = getSpeedNearOtherCar(.2f);
//            }
//
//        } catch (Exception ex) {
//            System.err.println("CarLtr MoveInLine() " + ex);
//        }











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
//        ArrayList<Car> tempCars = null;
//        try {
//            tempCars = tempLine.getCars();
//        } catch (Exception e) {
//            System.out.println("checkaccident" + e.getClass() + " " + e + "tempcars arraylist");
//        }
//
//        int tempIndex = 0;
//        try {
//            tempIndex = tempCars.indexOf(thisCar);
//        } catch (Exception e) {
//            System.out.println("checkaccident" + e.getClass() + " " + e + "tempcars index");
//        }
//
////        if (this.getLine().getCars().size() >= 3) {
////            for(int i = this.getLine().getCars().size() ; i >= this.getLine().getCars().size()-this.getLine().getRemovedCount() ; i-- )
////            {
////                if (this.getLine().getCars().get(i - 1).getHeadPosition() >= this.getLine().getCars().get(i - 2).getEndPosition() + Const.CHANGE_SPEED_DISTANCE_FOR_REACH) {
////                tempSpeed = getSpeedV2(true);
////        
////           
//
////            }
//               
//                    
//                        if (tempCars.get(tempIndex).getHeadPosition() <= tempCars.get(tempIndex - 1).getEndPosition() + Const.CHANGE_SPEED_DISTANCE_FOR_REACH) {
//                            return true;
//                        }
//
//                    
//                
//
//            
//        
//        return false;
//    }
//
//
//
//    
//    
//   





