package cz.muni.fi.pv168.controller;

import cz.muni.fi.pv168.common.StarDateUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.annotation.Resources;
import java.net.URL;
import java.time.LocalDate;


public class GUIController {
    @FXML
    private Button exitButton;

    @FXML
    private Label starDateLabel;

    @FXML
    void setCurrentStarDate(ActionEvent event){
        starDateLabel.textProperty().setValue("STARDATE: " + StarDateUtils.dateToStarDate(LocalDate.now()));
    }

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