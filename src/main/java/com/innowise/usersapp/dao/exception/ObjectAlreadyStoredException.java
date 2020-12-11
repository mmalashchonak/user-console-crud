package com.innowise.usersapp.dao.exception;

public class ObjectAlreadyStoredException extends RuntimeException {
    public ObjectAlreadyStoredException() {
        super("Object is Already Stored!");
    }
    public ObjectAlreadyStoredException(String message) {
        super("Object is Already Stored! " + message);
    }
}
