package CrossWalk.Utilities;

import java.util.Date;

public class ExceptionWriter {

    public void write(Exception ex) {
        System.err.println(ex);

        StringBuilder error = new StringBuilder();
        error.append(String.format("%s in : %s\n", new Date(), ex));
        for (StackTraceElement el : ex.getStackTrace()) {
            error.append(String.format("\tclass) %s Method )%s Line) ", el.getClassName(), el.getMethodName(), el.getLineNumber()));
        }
    }

    public void write(String cause, Exception ex) {
        System.err.println(cause + " " + ex);
    }

    public void write(Exception ex, boolean isImportante) {
        if (isImportante) {
            write(ex);
            return;
        }
        return;
    }

}
