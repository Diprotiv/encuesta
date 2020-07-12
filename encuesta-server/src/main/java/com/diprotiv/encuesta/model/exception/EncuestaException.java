package com.diprotiv.encuesta.model.exception;

import java.util.Arrays;
import java.util.Objects;

public class EncuestaException extends RuntimeException {
    private static final long serialVersionUID = -1L;

    public EncuestaException() {
    }

    public EncuestaException(Throwable cause) {
        initCause(cause);
    }

    public EncuestaException(String message) {
        super(message);
    }

    public EncuestaException(String message, Throwable cause) {
        super(message);
        initCause(cause);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    private static final int classNameHashCode =
            internalHashCodeCompute("com.diprotiv.encuesta.model.exception.EncuestaException");

    /**
     * HashCode implementation for EncuestaException
     * based on java.util.Arrays.hashCode
     */
    @Override
    public int hashCode() {
        return internalHashCodeCompute(
                classNameHashCode,
                getMessage());
    }

    private static int internalHashCodeCompute(java.lang.Object... objects) {
        return Arrays.hashCode(objects);
    }

    /**
     * Equals implementation for GLOBASException
     * based on instanceof and Object.equals().
     */
    @Override
    public boolean equals(final java.lang.Object other) {
        if (!(other instanceof EncuestaException)) {
            return false;
        }

        EncuestaException that = (EncuestaException) other;

        return
                Objects.equals(getMessage(), that.getMessage());
    }
}
