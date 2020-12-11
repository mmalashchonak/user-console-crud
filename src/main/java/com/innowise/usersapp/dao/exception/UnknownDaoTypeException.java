package com.innowise.usersapp.dao.exception;

import com.innowise.usersapp.dao.factory.DaoTypes;

public class UnknownDaoTypeException extends RuntimeException {
    public UnknownDaoTypeException(DaoTypes type) {
        super("Can not find DAO Factory for  " + type + "!");
    }
}
