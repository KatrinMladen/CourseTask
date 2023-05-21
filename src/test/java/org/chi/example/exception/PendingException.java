package org.chi.example.exception;

public class PendingException extends Exception {
    public PendingException(){
    }

    public PendingException(String message){
        super(message);
    }
}
