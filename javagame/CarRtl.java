package javagame;

public class CarRtl extends Car {

    public CarRtl(int Id, int Speed, CarType CarType, Line Line) {
        super(Id, new float[]{Const.GAME_WINDOWS_WIDTH,0}, Speed, CarType, Line);
    }

    public CarRtl(int Id, float[] Position, int Speed, CarType CarType) {
        super(Id, Position, Speed, CarType);
    }

    @Override
    public boolean IsIntheCrosswalk() {
        float[] crosswalkPosition = super.Line.getCrosswalkPosition();
        return ((Position[0] <= crosswalkPosition[0] && (Position[0] >= crosswalkPosition[1]))
                || (Position[1] <= crosswalkPosition[0] && Position[1] >= crosswalkPosition[1]));

    }

    @Override
    public void MoveInLine() {
        float tempSpeed = getSpeed();

        if (Position[1] < 0) {
            Line.Dispose(getId());
            return;
        }

        Position[0] -= tempSpeed * Const.SLEEP_TIME_RE_PAINTING / 1000;
        Position[1] = Position[0] + CarType.getCarWidth();
    }
}
