package com.innowise.usersapp.dao.xml;

import com.innowise.usersapp.dao.UserDao;
import com.innowise.usersapp.dao.exception.InvalidIdException;
import com.innowise.usersapp.dao.exception.ObjectAlreadyStoredException;
import com.innowise.usersapp.dao.exception.ObjectNotFoundException;
import com.innowise.usersapp.db.xml.XmlDb;
import com.innowise.usersapp.db.xml.XmlDbPath;
import com.innowise.usersapp.entity.User;
import com.innowise.usersapp.entity.Users;

import java.util.ArrayList;
import java.util.List;

public class XmlUserDao implements UserDao {

    private XmlDb xmlDb = XmlDb.getDb();

    @Override
    public List<User> loadByFirstNameAndLastName(String firstName, String lastName) {
        List<User> all = loadAll();
        List<User> selected = new ArrayList<>();

        for (User user : all) {
            if (user.getFirstName().equals(firstName) && user.getLastName().equals(lastName)) {
                selected.add(user);
            }
        }

        if(selected.size() != 0) {
            return selected;
        }

        throw new ObjectNotFoundException();
    }

    @Override
    public List<User> loadAll() {
        Users users = xmlDb.getDbContent(XmlDbPath.USERS_DEFAULT);
        return users.getUsers();
    }

    @Override
    public User loadById(Long id) {
        if (id == null) {
            throw new InvalidIdException();
        }

        List<User> all = loadAll();
        for (User user : all) {
            if (user.getId().equals(id)) {
                return user;
            }
        }

        throw new ObjectNotFoundException();
    }

    @Override
    public int save(User user) {
        Long id = user.getId();
        if (id != null) {
            throw new ObjectAlreadyStoredException();
        }

        id = xmlDb.getLastId();
        id++;
        user.setId(id);

        Users users = xmlDb.getDbContent(XmlDbPath.USERS_DEFAULT);
        List<User> all = users.getUsers();
        all.add(user);
        users.setUsers(all);
        xmlDb.setDbContent(users, XmlDbPath.USERS_DEFAULT);
        xmlDb.setLastId(id);
        return 1;
    }

    @Override
    public int update(User user) {
        Long id = user.getId();
        if (id == null) {
            throw new ObjectNotFoundException();
        }

        Users users = xmlDb.getDbContent(XmlDbPath.USERS_DEFAULT);
        List<User> all = users.getUsers();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getId().equals(id)) {
                all.remove(i);
                all.add(user);
                users.setUsers(all);
                xmlDb.setDbContent(users, XmlDbPath.USERS_DEFAULT);
                return 1;
            }
        }
        throw new ObjectNotFoundException();
    }

    @Override
    public int delete(Long id) {
        if (id == null) {
            throw new ObjectNotFoundException();
        }

        Users users = xmlDb.getDbContent(XmlDbPath.USERS_DEFAULT);
        List<User> all = users.getUsers();

        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getId().equals(id)) {
                all.remove(i);
                users.setUsers(all);
                xmlDb.setDbContent(users, XmlDbPath.USERS_DEFAULT);
                return 1;
            }
        }

        throw new ObjectNotFoundException();
    }
}
