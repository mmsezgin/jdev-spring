package com.cybertek.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "users")  //change table name to users, because user is a reserved work in postgres
@NoArgsConstructor
@Getter
public class User {
    //In this class, I will create authentication and authorization in this class.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autogenerate primary key
    private  Long id;

    private String username;
    private String password;
    private int active;  // Is the user active?
    private String roles; // some designers put roles and permission under a separate Authority class
    private String permissions;

    public User(String username, String password,String roles, String permissions) {
        this.username = username;
        this.password = password;
        this.active = 1;
        this.roles = roles;
        this.permissions = permissions;
    }

    public List<String> getRoleList(){
        if(this.roles.length()>0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getPermissionList(){
        if(this.permissions.length()>0){
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();

    }
}
