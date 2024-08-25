package org.example.Verification;

//class for check validation of string
public class RegexObject {

    private String regexStr;

    private String description;

    public RegexObject(String regexStr, String description) {
        this.regexStr = regexStr;
        this.description = description;
    }

    public boolean check(String str){
        if(!str.matches(regexStr)){
            System.out.println(description);
            return false;
        }

        return true;
    }

    public String getRegexStr() {
        return regexStr;
    }

    public String getDescription() {
        return description;
    }

    public void setRegexStr(String regexStr) {
        this.regexStr = regexStr;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
