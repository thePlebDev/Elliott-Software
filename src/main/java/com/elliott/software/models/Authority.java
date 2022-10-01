package com.elliott.software.models;

import javax.persistence.*;

@Entity
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @JoinColumn
    @ManyToOne
    private User user;

    public Authority(String title,User user){
        this.title = title;
        this.user = user;
    }

    public Authority(){}

    //GETTERS
    public String getTitle(){
        return this.title;
    }
    public User getUser(){
        return this.user;
    }
    //SETTERS
    public void setUser(User user){
        this.user = user;
    }
    public void setTitle(String title){
        this.title = title;
    }
}
