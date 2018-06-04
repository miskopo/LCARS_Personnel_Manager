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

    private static File databaseFile = new File("src/main/java/cz/muni/fi/pv168/model/Database.db");
    private final static String DB_URL = "jdbc:sqlite:" + databaseFile.getAbsolutePath();

    public static Connection createConnection() throws SQLException {
            // TODO: logging
            return DriverManager.getConnection(DB_URL);
}
}





