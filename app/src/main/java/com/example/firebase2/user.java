package com.example.firebase2;

public class user {
    String id;
    String name;
    String cals;

    public user() {

    }

    public user(String id, String name, String cals) {
        this.id = id;
        this.name = name;
        this.cals = cals;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCals() {
        return cals;
    }
}
