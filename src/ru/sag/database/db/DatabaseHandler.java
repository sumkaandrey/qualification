package ru.sag.database.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://"
                 +  dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }
    public void signUpUser(User user) {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USERS_USERNAME +
                " ," + Const.USERS_PASSWORD + ")" + "VALUES(?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, user.getUsername());
            prSt.setString(2, user.getPassword());

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ResultSet getUser(User user) {
        ResultSet resSet = null;

        String select =  "SELECT * FROM " + Const.USER_TABLE + " WHERE " +
                Const.USERS_USERNAME + "=? AND " + Const.USERS_PASSWORD + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getUsername());
            prSt.setString(2, user.getPassword());

           resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public void newCourse(Courses courses) {
        String insert = "INSERT INTO " + Const.COURSE_TABLE + "(" + Const.COURSES_SURNAME + " ," + Const.COURSES_NAME +
                " ," + Const.COURSES_PATRONYMIC + " ," + Const.COURSES_GENDER + " ," + Const.COURSES_ADDRESS
                + " ," + Const.COURSES_POST + " ," + Const.COURSES_TYPE +
                " ," + Const.COURSES_ORGNAME + " ," + Const.COURSES_DISCNAME + ")" + "VALUES(?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, courses.getSurname());
            prSt.setString(2, courses.getName());
            prSt.setString(3, courses.getPatronymic());
            prSt.setString(4, String.valueOf(courses.getGender_male()));
            prSt.setString(5, String.valueOf(courses.getGender_female()));
            prSt.setString(6, courses.getAddress());
            prSt.setString(7, String.valueOf(courses.getPost()));
            prSt.setString(8, String.valueOf(courses.getTypeOrg()));
            prSt.setString(9, String.valueOf(courses.getNameOrg()));
            prSt.setString(10, String.valueOf(courses.getNameDiscipline()));

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
