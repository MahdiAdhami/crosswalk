package javagame;

public class CarLtr extends Car {

    public CarLtr(int Id, int Speed, CarType CarType, Line Line) {
        super(Id, new float[]{(-1)*CarType.getCarWidth(), 0}, Speed, CarType, Line);
    }

    public CarLtr(int Id, int Speed, CarType CarType) {
        super(Id, new float[]{(-1)*CarType.getCarWidth(), 0}, Speed, CarType);
    }

    @Override
    public boolean IsIntheCrosswalk() {
        float[] crosswalkPosition = super.Line.getCrosswalkPosition();

        return ((Position[0] + CarType.getCarWidth() >= crosswalkPosition[0] && (Position[0] + CarType.getCarWidth() <= crosswalkPosition[1]))
                || (Position[1] + CarType.getCarWidth() >= crosswalkPosition[0] && Position[1] + CarType.getCarWidth() <= crosswalkPosition[1]));
    }

    @Override
    public void MoveInLine() {
        float tempSpeed = getSpeed();

        if (Position[1] > Const.GAME_WINDOWS_WIDTH) {
            Line.Dispose(getId());
            return;
        }

        Position[0] += tempSpeed * Const.SLEEP_TIME_RE_PAINTING / 1000;
        Position[1] = Position[0] - CarType.getCarWidth();
    }
}
