package com.elliott.software.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name= "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER,orphanRemoval = true)
    private List<Authority> authorities = new ArrayList<>();

    public User(String username,String email,String password,Authority authority){
        this.email = email;
        this.username = username;
        this.password = password;
        this.authorities.add(authority);

    }
    public User(){}

    //GETTERS
    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public String getEmail(){
        return this.email;
    }
    public Long getId(){
        return this.id;
    }
    public List<Authority> getAuthorities(){
        return this.authorities;
    }

    //SETTERS
    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void addAuthority(Authority authority){
        this.authorities.add(authority);
    }
}
