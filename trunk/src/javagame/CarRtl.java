package javagame;

public class CarRtl extends Car {

    public CarRtl(int Id, float Speed, CarType CarType, Line Line) {
        super(Id,  new float[]{Const.GAME_WINDOWS_WIDTH,0}, Speed, CarType, Line);
    }

    @Override
    public boolean IsIntheCrosswalk() {
        float[] crosswalkPosition = super.Line.getCrosswalkPosition();
        return ((Position[0] <= crosswalkPosition[0] && (Position[0] >= crosswalkPosition[1]))
                || (Position[1] <= crosswalkPosition[0] && Position[1] >= crosswalkPosition[1]));

    }

    @Override
    public void MoveInLine() {
        
//        float tempSpeed= getSpeedV2(false);
//        if(Line.getCars().size()>=2){
//            if(Line.getCars().get(Id-1).getHeadPosition() <= Line.getCars().get(Id).getEndPosition() + Const.CHANGE_SPEED_DISTANCE_FOR_REACH)
//            {
//                tempSpeed = getSpeedV2(true);
//            }
//            else {
//                tempSpeed = getSpeedV2(false);
//            }
//        }
        if (Position[1] < 0) {
            Line.Dispose(this);
            return;
        }

        Position[0] -= getSpeed() * Const.SLEEP_TIME_RE_PAINTING / 1000;
        Position[1] = Position[0] + CarType.getCarWidth();
    }
}
