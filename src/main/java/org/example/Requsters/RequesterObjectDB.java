package org.example.Requsters;

import java.sql.Connection;
import java.sql.SQLException;

//abstract class which includes connecting and requesting

public abstract class RequesterObjectDB extends ConnectorDB {

    private String nameTable;

    public RequesterObjectDB(String URL, String USERNAME, String PASSWORD, String nameTable) throws SQLException {
        super(URL, USERNAME, PASSWORD);
        this.nameTable=nameTable;
    }

    public RequesterObjectDB(Connection connection,String nameTable){
        super(connection);
        this.nameTable=nameTable;
    }

    public abstract boolean insert(Object object);  // method for add object to db

    public String getNameTable() {
        return nameTable;
    }

    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    }
}
