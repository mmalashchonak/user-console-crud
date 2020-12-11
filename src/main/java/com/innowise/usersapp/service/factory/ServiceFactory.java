package com.innowise.usersapp.service.factory;

import com.innowise.usersapp.service.UserService;

public abstract class ServiceFactory {

    public abstract UserService getUserService();

    public static ServiceFactory getServiceFactory(){
        return new ServiceFactoryImpl();
    }

}
