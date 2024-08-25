package org.example.Verification;

import org.example.Objects.User;

//class which checks all type of object user for validation
public class VerificationUser implements Verification {

    private RegexObject regexName;
    private RegexObject regexSurname;
    private RegexObject regexLogin;
    private RegexObject regexPassword;

    public VerificationUser(){
        String nameDescription="The name must be between 2 and 45 characters long";
        regexName=new RegexObject("^.{2,45}",nameDescription);

        String surnameDescription="The surname must be between 2 and 45 characters long";
        regexSurname=new RegexObject("^.{2,45}",surnameDescription);

        String loginDescription="The login must be between 2 and 45 characters long";
        regexLogin=new RegexObject("^.{2,45}",loginDescription);

        String passwordDescription="The password must contains at least 1 large letter, 1 small letter and either a special character or a number" +
                "\n The password must consist of at least 8 characters.";
        regexPassword=new RegexObject("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9@#$%]).{8,64}",passwordDescription);
    }

    public boolean check(Object object){
        User user=(User)object;
        try {

            return regexName.check(user.getName()) && regexSurname.check(user.getSurname())
                    && regexLogin.check(user.getLogin()) && regexPassword.check(user.getPassword());

        }catch(ClassCastException e){
            System.out.println("Class error exception");
        }catch(NullPointerException e){
            System.out.println("Null error exception");
        }

        return false;
    }

    public RegexObject getRegexName() {
        return regexName;
    }

    public RegexObject getRegexSurname() {
        return regexSurname;
    }

    public RegexObject getRegexLogin() {
        return regexLogin;
    }

    public RegexObject getRegexPassword() {
        return regexPassword;
    }

    public void setRegexName(RegexObject regexName) {
        this.regexName = regexName;
    }

    public void setRegexSurname(RegexObject regexSurname) {
        this.regexSurname = regexSurname;
    }

    public void setRegexLogin(RegexObject regexLogin) {
        this.regexLogin = regexLogin;
    }

    public void setRegexPassword(RegexObject regexPassword) {
        this.regexPassword = regexPassword;
    }
}

















/*public void putInMap(String str,int number){
        hashMap.put(str,number);
    }*/

    /*private boolean checkPassword(String password){
        String pat = ;
        if (password.matches(pat)) {
            return true;
        }
        else {
            System.out.println("Пароль должен содержать как минимум 1 большую букву, 1 маленькую букву и либо спец. символ либо цифру и состоять из не менее 8 символов.");
            return false;
        }
    }*/
