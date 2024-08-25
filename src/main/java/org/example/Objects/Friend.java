package org.example.Objects;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Friend extends Person {
    private String phone_number;
    private String email;
    private Date birthday;
    private String description;

    public Friend(){

    }

    public Friend(String name, String surname, Gender gender){
        super(name,surname,gender);
    }

    public Friend(String name,String surname,Gender gender,String phone_number,String email,Date birthday){
        super(name,surname,gender);
        this.phone_number=phone_number;
        this.email=email;
        this.birthday=birthday;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getEmail() {
        return email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getDescription() {
        return description;
    }

    public String toString(){
        String phonenumber=this.phone_number==null ? "Неопределено" : this.phone_number;
        String email=this.email==null ? "Неопределено" : this.email;


        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String birthday;
        // Преобразуем sql.Date в строку
        if(this.birthday!=null){
            birthday = formatter.format(this.birthday);
        }else
            birthday="Неопределено";



        String description=this.description==null ? "Неопределено" : this.description;

        return "id = "+getId()+
               ", name="+getName()+
               ", surname="+getSurname()+
               ", gender="+getGender().toString()+
               ", phonenumber= "+phonenumber+
               ", email= "+email+
               ", birthday= "+birthday+
               ", description= "+description;

    }
}
