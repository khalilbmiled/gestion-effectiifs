package com.bpi.exception;

public class FunctionalException extends Exception{

    private int errorCode;

    public FunctionalException(final int errorCode, final String message){
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
