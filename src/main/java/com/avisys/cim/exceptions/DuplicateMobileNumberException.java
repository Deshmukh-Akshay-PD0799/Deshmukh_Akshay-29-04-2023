package com.avisys.cim.exceptions;

//CustomException Handling with Message 
public class DuplicateMobileNumberException extends Exception {

    public DuplicateMobileNumberException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Unable to create Customer. " + getMessage();
    }
}
