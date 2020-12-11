package com.innowise.usersapp.service;

import com.innowise.usersapp.entity.User;

import java.util.List;

public interface UserService {

    List<User> loadAll();

    User loadById(Long id);

    List<User> loadByFirstNameAndLastName(String firstName, String lastName);

    int save(User user);

    int update(User user);

    int delete(Long id);
}
