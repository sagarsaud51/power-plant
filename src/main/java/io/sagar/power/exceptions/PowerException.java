package io.sagar.power.exceptions;

public class PowerException extends RuntimeException{

    public PowerException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
