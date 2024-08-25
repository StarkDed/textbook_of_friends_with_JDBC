package org.example.Main;

import org.example.Requsters.RequesterUserDB;

import java.util.Scanner;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/textbookofusers";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String nameTable="users";

    private static Scanner sc=new Scanner(System.in);

    public static void main(String[] args) {
        RequesterUserDB requesterUserDB=new RequesterUserDB(URL,USERNAME,PASSWORD,nameTable);
        if(requesterUserDB.getConnection()!=null){
            RequestApplication requestApplication=new RequestApplication(
                    requesterUserDB
            );

            requesterUserDB.checkTable();
            requestApplication.start();
        }

    }


}