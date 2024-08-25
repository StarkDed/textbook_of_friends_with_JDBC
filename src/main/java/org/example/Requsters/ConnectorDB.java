package org.example.Requsters;

import java.sql.*;

//class where you can connect to the DB

public class ConnectorDB {

    private Connection connection;

    public ConnectorDB(String URL, String USERNAME, String PASSWORD){
        try{
            connection =DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }catch(SQLException e){
            System.out.println("Error in connection");;
        }
    }

    public ConnectorDB(Connection connection){
        this.connection=connection;
    }

    public Connection getConnection(){
        return connection;
    }



}
