package org.example.Main;

import org.example.Objects.Friend;
import org.example.Objects.Gender;
import org.example.Objects.User;
import org.example.Requsters.RequesterFriendDB;
import org.example.Requsters.RequesterUserDB;
import org.example.Verification.VerificationFriend;
import org.example.Verification.VerificationUser;

import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

public class RequestApplication {
    private final Scanner sc=new Scanner(System.in);
    private final RequesterUserDB requesterUserDB;
    private VerificationUser verificationUser=new VerificationUser();
    private RequesterFriendDB requesterFriendDB;
    private VerificationFriend verificationFriend=new VerificationFriend();

    public RequestApplication(RequesterUserDB requesterUserDB){
        this.requesterUserDB = requesterUserDB;
    }

    public void start(){
        int command;

        while(true){
            try {
                System.out.print("""
                        1 - Input
                        2 - registration
                        3 - exit
                        Enter command:
                        """);
                command = sc.nextInt();
                switch (command) {
                    case 1 -> entry();
                    case 2 -> registerUser();
                    case 3 -> {
                        //метод close()
                        try{
                            requesterUserDB.getConnection().close();
                        }catch(SQLException e){
                            e.printStackTrace();
                        }

                        return;
                    }
                    default -> System.out.println("Wrong command");
                }
                System.out.println();

            }catch(InputMismatchException e){
                System.out.println("Mistake.Enter a number\n");
                sc.next();
            }
        }
    }

    private void registerUser(){


        sc.nextLine();
        System.out.print("Enter name:");
        String name=sc.nextLine().trim();
        System.out.print("Enter surname:");
        String surname=sc.nextLine().trim();
        System.out.print("Enter login:");
        String login=sc.nextLine().trim();
        System.out.print("Enter password:");
        String password=sc.nextLine();
        System.out.print("Repeat password:");
        String passwordTemp=sc.nextLine();
        User user=new User(login,password,name,surname);

        if(verificationUser.check(user)) {
            if (passwordTemp.equals(password)) {
                if(requesterUserDB.insert(user))
                    System.out.println("Registration was successful");
            } else
                System.out.println("Passwords don't match");
        }
    }

    private void entry(){

        sc.nextLine();

        System.out.println("Enter login:");
        String login=sc.nextLine().trim();
        System.out.println("Enter password:");
        String password=sc.nextLine();

        User user=(User)requesterUserDB.selectObject(new User(login,password));
        if(user!=null){
            System.out.println("You are logged in");
            commandsOfUser(user);
        }
        else
            System.out.println("The user was not found");
    }

    private void commandsOfUser(User user){
        int command;
        requesterFriendDB=new RequesterFriendDB(requesterUserDB.getConnection(),user.getLogin());
        try{
            while(true){
                System.out.print("""
                    1 - output friend(s)
                    2 - input new friend
                    3 - change friend
                    4 - delete friend
                    5 - back
                    Enter command:
                    """);
                command=sc.nextInt();
                switch(command){
                    case 1 -> outputFriend();
                    case 2 -> inputFriend(); //complete
                    case 3 -> changeFriend();
                    case 4 -> deleteFriend();
                    case 5 -> {
                        return;
                    }
                    default -> System.out.println("Wrong command");
                }
            }
        }catch(InputMismatchException e) {
            System.out.println("Mistake.Enter a number\n");
            sc.next();
        }

    }

    //1 - output friend(s)

    private void outputFriend(){
        int command;
        System.out.print("""
                1 - output every friend
                2 - output by choice
                Enter command:
                """);
        try{
            command=sc.nextInt();
            switch(command){
                case 1 ->requesterFriendDB.outputFriends(null);
                case 2 ->outputByChoice();
                default ->System.out.println("Wrong command");
            }
        }catch(InputMismatchException e) {
            System.out.println("Mistake.Enter a number\n");
            sc.next();
        }
    }

    private void outputByChoice(){
        StringBuilder sb=new StringBuilder("select * from ").append(requesterFriendDB.getNameTable());

        includeType("name",sb,true);
        includeType("surname",sb,false);

        requesterFriendDB.outputFriends(sb.toString());
    }

    private void includeType(String str,StringBuilder sb,boolean first){
        int command;
        try{
            System.out.printf("include %s ?\n",str);
            System.out.println("""
                    1 - Yes
                    2 - No
                    Enter command:
                    """);
            command=sc.nextInt();
            switch (command) {
                case 1 -> {
                    String newStr = sc.nextLine();
                    if (first) {
                        sb.append(" where ").append(str).append("='").append(newStr).append("'");
                    } else {
                        if (sb.indexOf(" where ") != -1 || sb.indexOf(" and ") != -1) {
                            sb.append(" and ").append(str).append("='").append(newStr).append("'");
                        } else {
                            sb.append(" where ").append(str).append("='").append(newStr).append("'");
                        }
                    }
                }
                case 2 -> { }
                default -> System.out.println("Wrong command");
            }
        }catch(InputMismatchException e) {
            System.out.println("Mistake.Enter a number\n");
            sc.next();
        }

    }

    // 2 - input new friend

    private void inputFriend(){
        int command;
        System.out.print("""
                1 - input all information about friend
                2 - input necessary information
                Enter command:
                """);
        try{
            command=sc.nextInt();
            switch(command){
                case 1->inputAllInformation();
                case 2->inputNecessaryInfo();
                default ->System.out.println("Wrong command");
            }
        }catch(InputMismatchException e) {
            System.out.println("Mistake.Enter a number\n");
            sc.next();
        }
    }

    public void inputAllInformation(){
        sc.nextLine();

        System.out.print("Enter name:");
        String name=sc.nextLine().trim();

        System.out.print("Enter surname:");
        String surname=sc.nextLine().trim();

        Gender gender=getGenderFromUser();

        sc.nextLine();

        System.out.print("Enter phonenumber(Examples: +375291234567):");
        String phoneNumber=sc.nextLine().trim();

        System.out.print("Enter email:");
        String email=sc.nextLine().trim();

        System.out.print("Enter birthday(Example: 11.02.2004):");
        String dateStr=sc.nextLine().trim();

        Date date =checkDate(dateStr);

        if(date!=null){
            Friend friend=new Friend(name,surname,gender,phoneNumber,email,date);

            if(verificationFriend.check(friend) && requesterFriendDB.insert(friend))
                System.out.println("Input friend was successfully");
        }

    }

    private Date checkDate(String str){
        if(verificationFriend.getRegexBirthday().check(str)){
            String[] strArr=str.split("\\.");

            return new Date(Integer.parseInt(strArr[2])-1900,Integer.parseInt(strArr[1])-1,Integer.parseInt(strArr[0]));
        }

        return null;
    }

    public void inputNecessaryInfo(){
        sc.nextLine();

        System.out.print("Enter name:");
        String name=sc.nextLine().trim();
        System.out.print("Enter surname:");
        String surname=sc.nextLine().trim();

        Gender gender=getGenderFromUser();

        Friend friend=new Friend(name,surname,gender);

        if(verificationFriend.smallCheck(friend) && requesterFriendDB.insert(friend)){
            System.out.println("Input friend was successfully");
        }

    }

    // 3 - change friend

    private void changeFriend(){
        int id;
        System.out.println("Choose friend's id for update:");
        if(requesterFriendDB.outputFriends(null)){
            while(true) {
                try {
                    id = sc.nextInt();
                    sc.nextLine();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Mistake.Enter a number\n");
                    sc.next();
                }
            }



            System.out.println("""
                Choose what info change:
                1 - name
                2 - surname
                3 - gender
                4 - phonenumber
                5 - email
                6 - birthday
                7 - description
                """);
            int command;
            String type=null;

            while (type==null) {
                try {
                    command = sc.nextInt();
                    sc.nextLine();
                    switch (command) {
                        case 1 -> type = "name";
                        case 2 -> type = "surname";
                        case 3 -> type = "gender";
                        case 4 -> type = "phonenumber";
                        case 5 -> type = "email";
                        case 6 -> type = "birthday";
                        case 7 -> type = "description";
                        default -> System.out.println("Wrong command");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Mistake.Enter a number\n");
                    sc.next();
                }
            }

            String str;

            if(type.equals("gender")){
                str=getGenderFromUser().toString();
            }else{
                System.out.println("Enter:");
                str=sc.nextLine();
            }


            if(!type.equals("gender")){
                requesterFriendDB.changeFriend(id,type,str);
            }else{
                if(verificationFriend.checkOne(str,type)){
                    requesterFriendDB.changeFriend(id,type,str);
                }
            }
        }


    }

    //4 - delete friend

    private void deleteFriend(){
        int id;

        System.out.println("Choose friend's id for delete:");
        if(requesterFriendDB.outputFriends(null)){
            try{
                System.out.print("Enter id:");
                id=sc.nextInt();
                requesterFriendDB.deleteFriend(id);
            }catch(InputMismatchException e) {
                System.out.println("Mistake.Enter a number\n");
                sc.next();
            }
        }

    }

    //other staff

    private Gender getGenderFromUser(){
        int command;
        while (true) {

            try {
                System.out.print("""
                        1 - male
                        2 - female
                        3 - none
                        Enter gender:
                        """);
                command = sc.nextInt();
                switch (command) {
                    case 1 -> {
                        return Gender.MALE;
                    }
                    case 2 ->{
                        return Gender.FEMALE;
                    }
                    case 3 -> {
                        return Gender.NONE;
                    }
                    default -> System.out.println("Wrong command");
                }
            } catch (InputMismatchException e) {
                System.out.println("Mistake.Enter a number\n");
                sc.next();
            }
        }
    }
}
