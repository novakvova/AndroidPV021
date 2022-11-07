package com.example.shop.account.dto;

public class RegisterErrorDTO {
    private String[] email;
    private String[] firstName;

    public String[] getEmail() {
        return email;
    }

    public void setEmail(String[] email) {
        this.email = email;
    }

    public String[] getFirstName() {
        return firstName;
    }

    public void setFirstName(String[] firstName) {
        this.firstName = firstName;
    }
}