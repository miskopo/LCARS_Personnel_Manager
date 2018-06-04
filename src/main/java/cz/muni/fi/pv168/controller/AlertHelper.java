package cz.muni.fi.pv168.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.stage.Window;

import java.awt.*;
import java.io.IOException;
import java.util.Optional;

public class AlertHelper {
    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public static boolean showAlertWithConfirmation(Window owner, String title, String
            message) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        Optional<ButtonType> option = alert.showAndWait();
        return ButtonType.OK.equals(option.get());
    }

}
