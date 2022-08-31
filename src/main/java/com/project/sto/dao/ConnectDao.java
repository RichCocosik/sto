package com.project.sto.dao;

import com.project.sto.service.resourcesPath.Path;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectDao {

    public Connection getConnection() {
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get(Path.DATABASE_PROPERTY_PATH))) {
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");

        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

