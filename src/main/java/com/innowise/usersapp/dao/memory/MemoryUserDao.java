package com.innowise.usersapp.dao.memory;

import com.innowise.usersapp.dao.UserDao;
import com.innowise.usersapp.dao.exception.InvalidIdException;
import com.innowise.usersapp.dao.exception.InvalidInputException;
import com.innowise.usersapp.dao.exception.ObjectAlreadyStoredException;
import com.innowise.usersapp.dao.exception.ObjectNotFoundException;
import com.innowise.usersapp.db.xml.XmlDb;
import com.innowise.usersapp.db.xml.XmlDbPath;
import com.innowise.usersapp.entity.Roles;
import com.innowise.usersapp.entity.User;
import com.innowise.usersapp.entity.Users;

import java.util.ArrayList;
import java.util.List;

public class MemoryUserDao implements UserDao {

    private static Long idCounter = 3L;
    private static Users users = new Users();

    {
        User user1 = new User();
        user1.setEmail("email1@gmail.com");
        user1.setFirstName("Nik");
        user1.setId(1L);
        user1.setLastName("Ivanov");
        ArrayList<Roles> roles = new ArrayList<>();
        roles.add(Roles.ADMIN);
        roles.add(Roles.PROVIDER);
        user1.setRoles(roles);
        ArrayList<String> phones = new ArrayList<>();
        phones.add("375001234567");
        phones.add("375111234567");
        user1.setPhones(phones);

        User user2 = new User();
        user2.setEmail("email2@gmail.com");
        user2.setFirstName("Alex");
        user2.setId(2L);
        user2.setLastName("Petrov");
        ArrayList<Roles> roles2 = new ArrayList<>();
        roles2.add(Roles.CUSTOMER);
        roles2.add(Roles.USER);
        user2.setRoles(roles);
        ArrayList<String> phones2 = new ArrayList<>();
        phones2.add("375221234567");
        phones2.add("375331234567");
        user2.setPhones(phones);

        users.addUser(user1);
        users.addUser(user2);
    }

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

        user.setId(idCounter + 1);

        List<User> all = users.getUsers();
        all.add(user);
        users.setUsers(all);
        idCounter++;
        return 1;
    }

    @Override
    public int update(User user) {
        Long id = user.getId();
        if (id == null) {
            throw new ObjectNotFoundException();
        }

        List<User> all = users.getUsers();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getId().equals(id)) {
                all.remove(i);
                all.add(user);
                users.setUsers(all);
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

        List<User> all = users.getUsers();

        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getId().equals(id)) {
                all.remove(i);
                users.setUsers(all);
                return 1;
            }
        }

        throw new ObjectNotFoundException();
    }
}