package cz.muni.fi.pv168.controller;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Michal Polovka
 */
public class DatabaseController {

//    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private File databaseFile = new File("../model/Database.db");
    private final String DB_URL = "jdbc:sqlite:" + databaseFile.getAbsolutePath();

    public void createConnection() throws SQLException {
        Connection conn;
        Statement stmt;
            //STEP 2: Register JDBC driver
//            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM CREWMAN";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(rs.getString("Name"));

    }
}





