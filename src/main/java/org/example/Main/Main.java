package org.example.Main;

import org.example.Requsters.RequesterUserDB;
import org.example.WorkWithFiles.ReaderInfoForCon;

import java.io.IOException;
import java.sql.SQLException;


public class Main {

    private static final String nameTable="users";

    public static void main(String[] args) {
        try {
            String[] arr= ReaderInfoForCon.readFileForConnection();
            RequesterUserDB requesterUserDB = new RequesterUserDB(arr[0], arr[1], arr[2], nameTable);

            RequestApplication requestApplication = new RequestApplication(
                    requesterUserDB
            );

            requesterUserDB.checkTable();
            requestApplication.start();

        }catch(SQLException e){
            System.out.println("error connection to DB");
        }catch(IOException e) {
            System.out.println("File not found");
        }catch(IndexOutOfBoundsException e){
            System.out.println("File don't include connection");
        }

    }


}