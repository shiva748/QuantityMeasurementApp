package com.quantitymeasurement.app.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection{
    private static DatabaseConnection instance;
    private Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/quantitymeasurement?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Panditji@23";

    private DatabaseConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database Driver not found", e);
        }
    }

    public static DatabaseConnection getInstance() {
        try{
            if (instance == null || instance.getConnection().isClosed()) {
                instance = new DatabaseConnection();
            }
            return instance;
        }catch(SQLException e){
            System.err.println("Database Connection Failed! Check output console");
            System.err.println(e.getMessage());
            System.exit(-1);
            return null;
        }
    }

    public Connection getConnection() {
        return connection;
    }
}