package org.example.Requsters;

import org.example.Objects.User;

import java.sql.*;

//class for add

public class RequesterUserDB extends RequesterObjectDB {

    public RequesterUserDB(String URL, String USERNAME, String PASSWORD, String nameTable) throws SQLException{
        super(URL, USERNAME, PASSWORD,nameTable);
    }

    public RequesterUserDB(Connection connection,String nameTable){
        super(connection,nameTable);
    }

    public boolean insert(Object object){ // add object user to DB
        String str="insert into "+getNameTable()+"(login,password,name,surname) values(?,?,?,?)";


        try(Statement statement=getConnection().createStatement()){

            User user=(User)object;

            String insertStr= "insert into " +getNameTable() +
                              "(login,password,name,surname) " +
                              "values('" + user.getLogin() + "'," +
                              "'" + user.getPassword() + "'," +
                              "'" + user.getName() + "'," +
                              "'" +user.getSurname() + "')";

            String createStr= "CREATE TABLE `"+user.getLogin()+"` (\n" +
                              "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                              "  `name` VARCHAR(45) NOT NULL,\n" +
                              "  `surname` VARCHAR(45) NOT NULL,\n" +
                              "  `gender` ENUM('male','female','none') NOT NULL,\n" +
                              "  `phonenumber` VARCHAR(20) NULL,\n" +
                              "  `email` VARCHAR(256) NULL,\n" +
                              "  `birthday` DATE NULL,\n" +
                              "  `description` VARCHAR(100) NULL,\n" +
                              "  PRIMARY KEY (`id`));";


            statement.addBatch(insertStr);
            statement.addBatch(createStr);
            statement.executeBatch();

            return true;


        }catch(SQLIntegrityConstraintViolationException e){
            System.out.println("Login already exists");

        }catch(SQLException e){
            System.out.println("user sql insert error");

        }catch(ClassCastException e){
            System.out.println("class castException");
        }

        return false;
    }


    public Object selectObject(Object object){ // method to find object User and return him
        String str="select * from "+getNameTable()+" where login=? and password=?";
        try(PreparedStatement preparedStatement=getConnection().prepareStatement(str)) {
            User user = (User) object;
            preparedStatement.setString(1,user.getLogin());
            preparedStatement.setString(2,user.getPassword());

            return returnObject(preparedStatement.executeQuery());

        }
        catch(SQLException e){
            System.out.println("select object error");
            e.printStackTrace();
        }catch(ClassCastException e){
            System.out.println("class castException");
        }

        return null;
    }

    public Object returnObject(ResultSet resultSet) throws SQLException{ // method which return User
        User user=new User();

        if(resultSet.next()){
            user.setLogin(resultSet.getString(1));
            user.setPassword(resultSet.getString(2));
            user.setName(resultSet.getString(3));
            user.setSurname(resultSet.getString(4));
            return user;
        }

        return null;
    }

    public void checkTable(){  // method for create table users if this doesn't exist
        String str= """
                CREATE TABLE IF NOT EXISTS users (
                    login VARCHAR(45) NOT NULL,
                    password VARCHAR(45) NOT NULL,
                    name VARCHAR(45) NOT NULL,
                    surname VARCHAR(45) NOT NULL,
                    PRIMARY KEY (login)
                );
                """;
        try(Statement statement=getConnection().createStatement()){
            statement.execute(str);
        }catch(SQLException e){
            e.printStackTrace();
        }

    }
}
