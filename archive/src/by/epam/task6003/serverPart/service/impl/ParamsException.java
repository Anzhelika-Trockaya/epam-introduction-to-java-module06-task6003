package by.epam.task6003.serverPart.service.impl;

public class ParamsException extends Exception{
    public ParamsException() {
        super();
    }

    public ParamsException(String message) {
        super(message);
    }

    public ParamsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamsException(Throwable cause) {
        super(cause);
    }
}
