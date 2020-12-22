package com.example.myapplication.model;

public class Response {
    String name;
    com.example.myapplication.model.owner owner;

    public com.example.myapplication.model.owner getOwner() {
        return owner;
    }

    public void setOwner(com.example.myapplication.model.owner owner) {
        this.owner = owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
