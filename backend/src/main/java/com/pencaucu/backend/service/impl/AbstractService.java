package com.pencaucu.backend.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.pencaucu.backend.BackendApplication;

public abstract class AbstractService {

    protected Connection con;

    @Value("${spring.datasource.connectionString}")
    protected String connectionString;
    @Value("${spring.datasource.databaseName}")
    protected String databaseName;
    @Value("${spring.datasource.databaseUsername}")
    protected String databaseUser;
    @Value("${spring.datasource.databasePassword}")
    protected String databasePassword;

    protected final Logger logger = LoggerFactory.getLogger(BackendApplication.class);

    protected void createConection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.con = DriverManager.getConnection(

                connectionString + databaseName, databaseUser, databasePassword);
    }
}
