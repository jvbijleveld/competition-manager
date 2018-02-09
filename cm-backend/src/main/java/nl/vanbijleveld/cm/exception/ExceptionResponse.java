package nl.vanbijleveld.cm.exception;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class ExceptionResponse implements Serializable {

    private static final long serialVersionUID = 37677839223L;
    private HttpStatus statusCode;
    private String message;

    public ExceptionResponse(Exception e, HttpStatus s) {
        this.message = e.getMessage();
        this.statusCode = s;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return this.statusCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
