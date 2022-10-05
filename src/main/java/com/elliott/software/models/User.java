package com.elliott.software.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity(name= "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Username may not be empty")

    private String username;
    @NotEmpty(message = "Password may not be empty")
    @Size(min=8, message = "Password must size must be greater than 8 characters")
    private String password;
    @NotEmpty(message = "Email may not be empty")
    private String email;
    private Boolean receiveWeeklyEmail;
    private String customerId;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER,orphanRemoval = true,cascade=CascadeType.PERSIST)
    private List<Authority> authorities = new ArrayList<>();

    public User(String username,String email,String password){
        this.email = email;
        this.username = username;
        this.password = password;
    }
    public User(String username,String email,String password,Boolean receiveWeeklyEmail){
        this.email = email;
        this.username = username;
        this.password = password;
        this.receiveWeeklyEmail = receiveWeeklyEmail;
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
    public Boolean getReceiveWeeklyEmail(){return this.receiveWeeklyEmail;}
    public String getCustomerId(){return this.customerId;}

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
    public void setReceiveWeeklyEmail(Boolean receiveWeeklyEmail){this.receiveWeeklyEmail = receiveWeeklyEmail;}
    public void setCustomerId(String customerId){this.customerId = customerId;}
}
