package com.gpnu.core.exception;

import lombok.Getter;

@Getter
public class GPNUException extends RuntimeException{
    private final String errorMessage;
    private final int errorCode;

    public GPNUException(String errorMessage, int errorCode, Throwable cause){
        super(cause);
        this.errorMessage=errorMessage;
        this.errorCode=errorCode;
    }

    public GPNUException(String errorMessage, int errorCode){
        super(errorMessage);
        this.errorMessage=errorMessage;
        this.errorCode=errorCode;
    }
}
