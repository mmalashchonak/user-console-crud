package com.innowise.usersapp.entity;

import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements Comparable<User> {
    @XmlAttribute
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    @XmlElementWrapper(name = "roles")
    @XmlElement(name = "role")
    private List<Roles> roles;
    @XmlElementWrapper(name = "phones")
    @XmlElement(name = "phone")
    private List<String> phones;

    public User() {
    }

    public User(String firstName, String lastName, String email, List<Roles> roles, List<String> phones) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
        this.phones = phones;
    }

    public User(Long id, String firstName, String lastName, String email, List<Roles> roles, List<String> phones) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
        this.phones = phones;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId().equals(user.getId()) &&
                getFirstName().equals(user.getFirstName()) &&
                getLastName().equals(user.getLastName()) &&
                getEmail().equals(user.getEmail()) &&
                getRoles().equals(user.getRoles()) &&
                getPhones().equals(user.getPhones());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLastName(), getFirstName());
    }

    @Override
    public int compareTo(User o) {
        int value = this.lastName.compareTo(o.lastName);

        if (value != 0) {
            return value;
        }

        value = this.firstName.compareTo(o.firstName);
        return value;
    }

    @Override
    public String toString() {
        return "Last Name: '" + lastName + '\'' +
                ", First Name: '" + firstName + '\'' +
                ", ID: " + id +
                ", e-mail: '" + email + '\'' +
                ", Roles: " + roles +
                ", Phones: " + phones;
    }
}

