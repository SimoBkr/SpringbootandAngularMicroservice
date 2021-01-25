package com.bkr.microservice.springmicroservice.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "groups")
public class GroupEntity implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    private String name;

    @ManyToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinTable(name = "groups_users" ,joinColumns = {@JoinColumn(name = "groups_id")}
    ,inverseJoinColumns = {@JoinColumn(name = "users_id")})
    private Set<UserEntity> users = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }
}
