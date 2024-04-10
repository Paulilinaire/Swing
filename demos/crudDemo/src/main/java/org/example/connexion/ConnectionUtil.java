package org.example.connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public static Connection getConnection(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String database = "cruddbswing";
            String url = "jdbc:mysql://localhost:3306/";
            Connection connection = DriverManager.getConnection(url + database , "root" ,"1234");
            return connection;
        } catch (ClassNotFoundException | SQLException e){
            throw  new RuntimeException(e);
        }
    }
}

