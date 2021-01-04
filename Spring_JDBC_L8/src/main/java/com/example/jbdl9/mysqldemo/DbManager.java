package com.example.jbdl9.mysqldemo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DbManager {

    private static Connection connection;

    // Java Database Connectivity
    public static void getDBConnection() {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/userDb", "root", "");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public DbManager(){
        try {
            createTable();
            System.out.println("User table has been created");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void createTable() throws SQLException {

        getDBConnection();

        Statement statement = connection.createStatement();

        statement.execute("create table if not exists user(userId int primary key auto_increment, " +
                "name varchar(30), age int, country varchar(20), timeOfInsertion date)");

    }

    public static void insertUser(User user) throws SQLException{

        getDBConnection();

//        Statement statement = connection.createStatement("insert into user(null, " + user.getName() + ", " + user.getAge() + ", " + user.getCountry() + ", " + new Dateuser.getTimeOfInsertion().getTime());

        PreparedStatement statement = connection.prepareStatement("insert into user" +
                "(userId, name, age, country, timeOfInsertion) values (null, ?, ?, ?, ?)");

        statement.setString(1, user.getCountry());
        statement.setInt(2, user.getAge());
        statement.setString(3, user.getName());
        if(user.getTimeOfInsertion() == null){
            statement.setDate(4, new Date(System.currentTimeMillis()));
        }else{
            statement.setDate(4, new Date(user.getTimeOfInsertion().getTime()));
        }

        int rows_affected = statement.executeUpdate();

        if(rows_affected > 0){
            System.out.println("User " + user + " has been inserted successfully");
        }else{

        }
    }

    public static List<User> getUsers() throws SQLException{

        List<User> userList = new ArrayList<>();
        getDBConnection();

        String sql = "select * from user";

        Statement statement = connection.createStatement();

        ResultSet result = statement.executeQuery(sql);

        while(result.next()){

            int age = result.getInt("age");
            int userId = result.getInt(1);
            String name = result.getString(2);
            String country = result.getString(4);
            Date date = result.getDate(5);
            java.util.Date time = null;
            if(date != null){
                time = new java.util.Date(result.getDate(5).getTime());
            }
//            java.util.Date time = new java.util.Date(result.getDate(5).getTime());

            User user = new User(userId, name, age, country, time);
            userList.add(user);
        }

        return userList;

    }

    public static List<User> getUser(int id) throws SQLException{

        List<User> userList = new ArrayList<>();

        getDBConnection();

        String sql = "select * from user where userId = " + id;

        Statement statement = connection.createStatement();

        ResultSet result = statement.executeQuery(sql);

        while(result.next()){

            int age = result.getInt("age");
            int userId = result.getInt(1);
            String name = result.getString(2);
            String country = result.getString(4);
            java.util.Date time = new java.util.Date(result.getDate(5).getTime());

            User user = new User(userId, name, age, country, time);
            userList.add(user);

        }

        return userList;

    }
}
