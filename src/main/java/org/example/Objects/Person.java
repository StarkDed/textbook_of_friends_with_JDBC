package org.example.Objects;

public class Person {

    private int id=-1;
    private String name;
    private String surname;
    private Gender gender;

    public Person(){
        name="undefined";
        surname="undefined";
    }

    public Person(String name,String surname,Gender gender){
        this.name=name;
        this.surname=surname;
        this.gender=gender;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Gender getGender() {
        return gender;
    }


}
