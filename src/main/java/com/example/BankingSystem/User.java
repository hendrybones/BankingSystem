package com.example.BankingSystem;

public class User {
    private String name;
    private String country;
    private String email;


    public User(String name, String country, String email) {
        this.name = name;
        this.country = country;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
