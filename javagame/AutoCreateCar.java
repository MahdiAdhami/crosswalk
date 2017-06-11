package javagame;

import java.util.ArrayList;
import javagame.Menu.GameSetting;

public class AutoCreateCar {

    public ArrayList<Line> Lines = new ArrayList<>();
    public int RtlLineCount;
    public int LtrLineCount;

    public AutoCreateCar() {
        this.LtrLineCount = GameSetting.getLtrLineCount();
        this.RtlLineCount = GameSetting.getRtlLineCount();
    }

    public void InitLine() {
        boolean canCarTakeOver;
        for (int i = 1; i <= RtlLineCount; i++) {
            canCarTakeOver = i != RtlLineCount;
            Lines.add(new Line(i, (i + 1), (i), Const.LINE_DIRECTION_RTL, (i - 1) * Const.LINE_HEIGHT + Const.TOP_MARGIN, canCarTakeOver));
        }

        for (int i = RtlLineCount + 1, j = LtrLineCount; i <= RtlLineCount + LtrLineCount; i++, j--) {
            canCarTakeOver = i != RtlLineCount + 1;
            Lines.add(new Line(i, (j + 1), (j), Const.LINE_DIRECTION_LTR, (i - 1) * Const.LINE_HEIGHT + Const.TOP_MARGIN + Const.MIDDLE_LINE_HEIGHT, canCarTakeOver));
        }
    }

    public void InitLine(ArrayList<Line> line) {
        InitLine();

        line.stream().forEach((tempLine) -> {
            tempLine.getCars().stream().forEach((car) -> {
                car.setLine(Lines.get(car.Line.getId() - 1));
                Lines.get(car.Line.getId() - 1).getCars().add(car);
            });
        });
    }

    // Query method for all lines
    public ArrayList<Line> getLines() {
        return Lines;
    }

}
