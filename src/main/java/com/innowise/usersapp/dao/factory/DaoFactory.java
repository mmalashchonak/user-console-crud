package com.innowise.usersapp.dao.factory;

import com.innowise.usersapp.dao.UserDao;
import com.innowise.usersapp.dao.exception.UnknownDaoTypeException;
import com.innowise.usersapp.dao.memory.MemoryDaoFactory;
import com.innowise.usersapp.dao.xml.XmlDaoFactory;

public abstract class DaoFactory {

    public abstract UserDao getUserDao();

    public static DaoFactory getFactory() {
        return getFactory(DaoTypes.XmlDao);
    }

    public static DaoFactory getFactory(DaoTypes type) {
        switch (type) {
            case MemoryDao: {
                return new MemoryDaoFactory();
            }

            case XmlDao: {
                return new XmlDaoFactory();
            }
        }

        throw new UnknownDaoTypeException(type);
    }
}
