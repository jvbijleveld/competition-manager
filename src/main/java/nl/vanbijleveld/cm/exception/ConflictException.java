package nl.vanbijleveld.cm.exception;

public class ConflictException extends Exception {

    private static final long serialVersionUID = -1651652142501799417L;

    public ConflictException() {
    }

    public ConflictException(final String message) { 
        super(message);
    }

    public ConflictException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ConflictException(final Throwable cause) {
        super(cause);
    }
    
}
