package edu.mum.ea.passenger.backend.entity;

public class Result {

    public Result() {
        this.success = true;
        this.message = "";
    }

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    private boolean success;

    private String message;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

}
