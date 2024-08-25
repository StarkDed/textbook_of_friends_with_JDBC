package org.example.Print;

import org.example.Objects.Friend;
import org.example.Objects.Gender;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

//class for output friend
public class PrintFriend {

    public static boolean outputResult(ResultSet resultSet) throws SQLException {
        Friend friend;
        int id;
        String name,surname,phonenumber,email,description;
        Gender gender;
        Date date;

        if (resultSet.next()) {
            do {
                // Получаем данные из ResultSet
                id=resultSet.getInt(1);
                name=resultSet.getString(2);
                surname=resultSet.getString(3);
                gender=Gender.valueOf(resultSet.getString(4).toUpperCase());
                phonenumber=resultSet.getString(5);
                email=resultSet.getString(6);
                date=resultSet.getDate(7);
                description=resultSet.getString(8);
                friend=new Friend(name,surname,gender,phonenumber,email,date);
                friend.setId(id);friend.setDescription(description);
                System.out.println(friend);
            } while (resultSet.next());
            return true;
        } else {
            System.out.println("No friends.");
            return false;
        }
    }
}
