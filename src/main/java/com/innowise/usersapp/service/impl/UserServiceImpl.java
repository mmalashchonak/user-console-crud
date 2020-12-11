package com.innowise.usersapp.service.impl;

import com.innowise.usersapp.dao.UserDao;
import com.innowise.usersapp.dao.factory.DaoFactory;
import com.innowise.usersapp.entity.User;
import com.innowise.usersapp.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private UserDao dao;

    public UserServiceImpl() {
        this.dao = DaoFactory.getFactory().getUserDao();
    }

    @Override
    public List<User> loadAll() {
        return dao.loadAll().stream().sorted().collect(Collectors.toList());
    }

    @Override
    public User loadById(Long id) {
        return dao.loadById(id);
    }

    @Override
    public List<User> loadByFirstNameAndLastName(String firstName, String lastName) {
        return dao.loadByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public int save(User user) {

        return dao.save(user);
    }

    @Override
    public int update(User user) {
        return dao.update(user);
    }

    @Override
    public int delete(Long id) {
        return dao.delete(id);
    }

}