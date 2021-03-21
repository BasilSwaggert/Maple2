package com.Helpdesk.Maple2.entities;

import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role implements java.io.Serializable {

    private Integer id;
    private String name;
    private Set<User> users = new HashSet<User>(0);

    public Role() {
        super();
    }

    public Role(String name, Set<User> users) {
        this.name = name;
        this.users = users;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
