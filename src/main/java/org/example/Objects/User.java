package org.example.Objects;


public class User {
    private String name;
    private String surname;
    private String password;
    private String login; // nameTable

    public User(String login,String password,String name,String surname){
        this.login=login;
        this.password=password;
        this.name=name;
        this.surname=surname;
    }

    public User(String login,String password){
        this.login=login;
        this.password=password;
    }

    public User(){

    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

}
