package org.example.Verification;

import org.example.Objects.Friend;
import org.example.Objects.User;

import java.sql.Date;
import java.util.HashMap;

//class which checks all type of object friend for validation
public class VerificationFriend implements Verification{
    private HashMap<String,RegexObject> hashMap;

    private RegexObject regexName;
    private RegexObject regexSurname;
    private RegexObject regexPhonenumber;
    private RegexObject regexEmail;
    private RegexObject regexBirthday;
    private RegexObject regexDescription;

    public VerificationFriend(){
        String descriptionName="The name must be between 2 and 45 characters long";
        regexName=new RegexObject("^.{2,45}",descriptionName);

        String descriptionSurname="The name must be between 2 and 45 characters long";
        regexSurname=new RegexObject("^.{2,45}",descriptionSurname);

        String descriptionPhonenumber="phone number invalid(Examples: +375291234567)";
        regexPhonenumber=new RegexObject("^\\+\\d{1,4}\\d{2,3}\\d{3}\\d{2}\\d{2}$" ,descriptionPhonenumber);

        String descriptionEmail="email invalid";
        regexEmail=new RegexObject( "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",descriptionEmail);

        String descriptionBirthday="invalid birthday(Example: 11.02.2004)";
        regexBirthday=new RegexObject("^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[0-2])\\.(\\d{4})$",descriptionBirthday);

        String descriptionDes="The name must be between 1 and 100 characters long";
        regexDescription=new RegexObject( "^.{2,100}",descriptionDes);

        hashMap=new HashMap<>();
        hashMap.put("name",regexName);
        hashMap.put("surname",regexSurname);
        hashMap.put("email",regexEmail);
        hashMap.put("phonenumber",regexPhonenumber);
        hashMap.put("birthday",regexBirthday);
        hashMap.put("description",regexDescription);
    }

    public boolean check(Object object){
        Friend friend=(Friend)object;
        try {

            return regexName.check(friend.getName()) && regexSurname.check(friend.getSurname())
                    && regexPhonenumber.check(friend.getPhone_number()) && regexEmail.check(friend.getEmail());

        }catch(ClassCastException e){
            System.out.println("Class error exception");
        }catch(NullPointerException e){
            System.out.println("Null error exception");
        }

        return false;
    }

    public boolean smallCheck(Object object){
        Friend friend=(Friend)object;
        try {

            return regexName.check(friend.getName()) && regexSurname.check(friend.getSurname());

        }catch(ClassCastException e){
            System.out.println("Class error exception");
        }catch(NullPointerException e){
            System.out.println("Null error exception");
        }

        return false;
    }

    public boolean checkOne(String str,String type){
        RegexObject regexObject=hashMap.get(type);
        return regexObject.check(str);
    }

    public RegexObject getRegexName() {
        return regexName;
    }

    public RegexObject getRegexSurname() {
        return regexSurname;
    }

    public RegexObject getRegexPhonenumber() {
        return regexPhonenumber;
    }

    public RegexObject getRegexEmail() {
        return regexEmail;
    }

    public RegexObject getRegexBirthday() {
        return regexBirthday;
    }

    public RegexObject getRegexDescription() {
        return regexDescription;
    }

    public HashMap<String, RegexObject> getHashMap() {
        return hashMap;
    }

    public void setRegexName(RegexObject regexName) {
        this.regexName = regexName;
    }

    public void setRegexSurname(RegexObject regexSurname) {
        this.regexSurname = regexSurname;
    }

    public void setRegexPhonenumber(RegexObject regexPhonenumber) {
        this.regexPhonenumber = regexPhonenumber;
    }

    public void setRegexEmail(RegexObject regexEmail) {
        this.regexEmail = regexEmail;
    }

    public void setRegexBirthday(RegexObject regexBirthday) {
        this.regexBirthday = regexBirthday;
    }

    public void setHashMap(HashMap<String, RegexObject> hashMap) {
        this.hashMap = hashMap;
    }

    public void setRegexDescription(RegexObject regexDescription) {
        this.regexDescription = regexDescription;
    }
}
