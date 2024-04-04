package com.example.demo;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseTest {
    public static void main(String[] args) {
        Connection connection = DatabaseConnection.getInstance().getConnection();

        try {
            connection.createStatement().execute("SELECT 1");
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}

