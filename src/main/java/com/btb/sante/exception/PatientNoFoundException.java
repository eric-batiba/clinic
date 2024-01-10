package com.btb.sante.exception;

public class PatientNoFoundException extends RuntimeException {
    public PatientNoFoundException(String message) {
        super(message);
    }
}
