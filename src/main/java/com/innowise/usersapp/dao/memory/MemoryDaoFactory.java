package com.innowise.usersapp.dao.memory;

import com.innowise.usersapp.dao.UserDao;
import com.innowise.usersapp.dao.factory.DaoFactory;

public class MemoryDaoFactory extends DaoFactory {

    @Override
    public UserDao getUserDao() {
        return new MemoryUserDao();
    }
}
