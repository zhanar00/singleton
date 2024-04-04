package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection extends configs{
    private String URL = "jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbName;

    private static DatabaseConnection instance;
    private final Connection connection;

    private DatabaseConnection(){
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(URL, dbUser, dbPassword);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized DatabaseConnection getInstance(){
        if(instance == null) instance = new DatabaseConnection();
        return instance;
    }

    public Connection getConnection(){
        return connection;
    }

    private Properties loadConfiguration() {
        Properties props = new Properties();
        String configFilePath = System.getProperty("user.home") + File.separator + "database_config.properties";
        try (FileInputStream fis = new FileInputStream(configFilePath)) {
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load database configuration", e);
        }
        return props;
    }
}

