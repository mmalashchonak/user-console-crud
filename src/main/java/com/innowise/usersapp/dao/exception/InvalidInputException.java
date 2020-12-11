package com.innowise.usersapp.dao.exception;

public class InvalidInputException extends RuntimeException {
        public InvalidInputException() {
            super("Invalid input data!");
        }
    }