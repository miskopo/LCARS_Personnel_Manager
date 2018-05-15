package cz.muni.fi.pv168.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;


public class GUIController {
    @FXML
    private Button exitButton;

    @FXML
    private Label starDateLabel;

    


    @FXML
    void exit(ActionEvent event) {
        Window window = exitButton.getScene().getWindow();
        if (AlertHelper.showAlertWithConfirmation(window, "Exit confirmation", "Are you sure you want to exit " +
                "application?")) {
            Stage stage = (Stage) window;
            stage.close();
        }
    }

}
