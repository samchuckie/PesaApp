package com.example.pesaapp.Data;

public class PesaUsers {
    private Long phone;
    private String password;
    private String email;

    public PesaUsers() {
    }

    public PesaUsers(Long phone, String password, String email) {
        this.phone = phone;
        this.password = password;
        this.email = email;
    }
    public PesaUsers(Long phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
