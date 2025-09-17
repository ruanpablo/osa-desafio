package com.osa.desafio.exception.custom;

public class ResourceAlreadyExistsException extends Exception {
    public ResourceAlreadyExistsException(String message){
        super(message);
    }
}
