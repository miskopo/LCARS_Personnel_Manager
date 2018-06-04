package cz.muni.fi.pv168;

import cz.muni.fi.pv168.controller.DatabaseController;

import java.sql.Connection;
import java.sql.SQLException;

import static javafx.application.Application.launch;

/**
 * @author Michal Polovka
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        Connection connection = DatabaseController.createConnection();
        if (connection == null) {
            System.out.println("DB error");
        }
    }
}
