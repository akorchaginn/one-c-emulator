package org.pes.onecemulator.exception.util;

public final class ExceptionUtils {

    public ExceptionUtils() {
        throw new UnsupportedOperationException();
    }

    public static Throwable getCause(final Throwable e) {
        Throwable cause;
        Throwable result = e;

        while (null != (cause = result.getCause()) && result != cause) {
            result = cause;
        }

        return result;
    }
}
