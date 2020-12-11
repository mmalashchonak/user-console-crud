package com.innowise.usersapp.service.factory;

import com.innowise.usersapp.service.UserService;
import com.innowise.usersapp.service.impl.UserServiceImpl;

public class ServiceFactoryImpl extends ServiceFactory {

    @Override
    public UserService getUserService() {
        return new UserServiceImpl();
    }
}
