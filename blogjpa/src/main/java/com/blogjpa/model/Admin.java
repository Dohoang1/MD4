package com.blogjpa.model;

public class Admin {
    private static Admin instance;
    private String username;
    private String password;

    private Admin() {
        this.username = "dohoang";
        this.password = "adminpro";
    }

    public static synchronized Admin getInstance() {
        if (instance == null) {
            instance = new Admin();
        }
        return instance;
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }
}