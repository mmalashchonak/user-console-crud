package com.innowise.usersapp.dao.exception;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException() {
        super("Object not found!");
    }
}
