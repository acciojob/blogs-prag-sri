package com.driver.models;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private List<Blog> list_of_blogs;

    public User() {
    }

    public User(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<Blog> getList_of_blogs() {
        return list_of_blogs;
    }

    public void setList_of_blogs(List<Blog> list_of_blogs) {
        this.list_of_blogs = list_of_blogs;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Blog blog;

}