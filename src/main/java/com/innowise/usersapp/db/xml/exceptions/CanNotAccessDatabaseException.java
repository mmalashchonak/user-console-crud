package com.innowise.usersapp.db.xml.exceptions;

public class CanNotAccessDatabaseException extends RuntimeException {
    public CanNotAccessDatabaseException() {
        super("Can not get access to database!");
    }

    public CanNotAccessDatabaseException(String message) {
        super("Can not get access to database! " + message);
    }
}