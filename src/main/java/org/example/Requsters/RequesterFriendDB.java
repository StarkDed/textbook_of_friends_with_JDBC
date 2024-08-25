package org.example.Requsters;

import org.example.Objects.Friend;
import org.example.Print.PrintFriend;

import java.sql.*;

//clas for request friend from db
public class RequesterFriendDB extends RequesterObjectDB {

    public RequesterFriendDB(String URL, String USERNAME, String PASSWORD, String nameTable) throws SQLException{
        super(URL, USERNAME, PASSWORD,nameTable);
    }

    public RequesterFriendDB(Connection connection,String nameTable){
        super(connection,nameTable);
    }

    public boolean insert(Object object){ // method for add object friend to db
        String insertStr="insert into "+getNameTable()+"(name,surname,gender,phonenumber,email,birthday,description) values(?,?,?,?,?,?,?)";
        try(PreparedStatement preparedStatement=getConnection().prepareStatement(insertStr)){
            Friend friend=(Friend)object;
            preparedStatement.setString(1,friend.getName());
            preparedStatement.setString(2,friend.getSurname());
            preparedStatement.setString(3,friend.getGender().toString());
            preparedStatement.setString(4,friend.getPhone_number());
            preparedStatement.setString(5,friend.getEmail());
            preparedStatement.setDate(6,friend.getBirthday());
            preparedStatement.setString(7,friend.getDescription());
            preparedStatement.execute();
            return true;
        }catch(SQLException e){
            System.out.println("pre Statet insert error");

        }catch(ClassCastException e){
            System.out.println("class castException");
        }

        return false;
    }


    public boolean outputFriends(String str){ //method for request friends from db
        if(str==null){
            str="select * from "+getNameTable();
        }
        try(Statement statement=getConnection().createStatement()){
            return PrintFriend.outputResult( statement.executeQuery(str));
        }catch(SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteFriend(int id){ // method for delete object friend from db
        String deleteStr="delete from "+getNameTable()+" where id=?";
        try(PreparedStatement preparedStatement=getConnection().prepareStatement(deleteStr)){
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            System.out.println("Friend's deleted");
            return true;
        }catch(SQLException e) {
            System.out.println("pre Statet delete error");
        }

        return false;
    }

    public boolean changeFriend(int id,String type,String str){ //method for change object friend in db
        String updateStr="update "+getNameTable()+" set "+type+"=? where id=?";
        try(PreparedStatement preparedStatement=getConnection().prepareStatement(updateStr)){
            preparedStatement.setString(1,str);
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
            return true;
        }catch(SQLException e){
            System.out.println("pre Statet update error");

        }

        return false;
    }

}
