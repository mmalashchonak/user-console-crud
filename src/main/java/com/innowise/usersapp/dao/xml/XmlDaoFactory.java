package com.innowise.usersapp.dao.xml;

import com.innowise.usersapp.dao.UserDao;
import com.innowise.usersapp.dao.factory.DaoFactory;

public class XmlDaoFactory extends DaoFactory {

    @Override
    public UserDao getUserDao() {
        return new XmlUserDao();
    }

}
