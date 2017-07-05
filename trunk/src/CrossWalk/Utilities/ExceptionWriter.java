package CrossWalk.Utilities;

import java.util.Date;

public class ExceptionWriter {

    public void write(String cause, Exception ex, boolean isImportante) {
        if (isImportante) {
            StringBuilder error = new StringBuilder();
            error.append(String.format("%s %s in : %s\n", new Date(), cause, ex));
            for (StackTraceElement el : ex.getStackTrace()) {
                error.append(String.format("\tclass) %s Method )%s Line) ", el.getClassName(), el.getMethodName(), el.getLineNumber()));
            }
        } else {
            System.err.println(ex);

        }
    }
}
