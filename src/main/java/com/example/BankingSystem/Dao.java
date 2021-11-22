package com.example.BankingSystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dao {
    private String jdbcUrl="jdbc:mysql://localhost:3306/bank";
    private String jdbcUsername="hendry";
    private  String jdbcPassword="hendry56!!";

    private static final String InsertUser="INSERT INTO users" +"(name,email,country) VALUES" + "(?,?,?);";
    private static final String SELECT_USER_BY_ID="select id,name,email,country from users where id=?";
    private static final String SELECT_All_Users="select * from users";
    private static final String  DELETE_USER="delete from users where id=?";
    private static final String Update_User="update users set name=?,email=?,country=? where id=?;";


    protected Connection getConnection(){
        Connection connection=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection(jdbcUrl,jdbcPassword,jdbcUsername);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public  void insertUser(User user) throws SQLException {
        try (Connection connection=getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(InsertUser);){
                preparedStatement.setString(1,user.getName());
                preparedStatement.setString(2,user.getEmail());
                preparedStatement.setString(3,user.getCountry());
                preparedStatement.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated = false;
        try (Connection connection=getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(Update_User);){
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(1,user.getEmail());
            preparedStatement.setString(1,user.getCountry());

            rowUpdated=preparedStatement.executeUpdate()>0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowUpdated;

    }
    public List<User> selectAllUsers(){
        List<User> users=new ArrayList<>();
        try (Connection connection=getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(SELECT_All_Users);){
                System.out.println(preparedStatement);
                ResultSet rs=preparedStatement.executeQuery();

                while (rs.next()){
                    String name=rs.getString("name");
                    String email=rs.getString("email");
                    String country=rs.getString("country");
                    users.add(new User(name,email,country));
                }
        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }
    public  boolean deleteUser() throws SQLException {
        boolean rowDeleted = false;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER);){
                preparedStatement.setString(1, "name");
                rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

}
