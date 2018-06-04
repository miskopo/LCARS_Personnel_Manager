package cz.muni.fi.pv168.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import cz.muni.fi.pv168.common.StarDateUtils;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;


public class GUIController {
    @FXML private Button exitButton;
    @FXML private Label starDateLabel;
    @FXML private Label systemInformation;
    @FXML private Pane viewPane;
    private Image globe;

    @FXML
    private void initialize() {
        starDateLabel.textProperty().setValue("STARDATE: " + StarDateUtils.dateToStarDate(LocalDate.now()));
        systemInformation.textProperty().setValue("Available memory: " + Runtime.getRuntime().freeMemory()/1_000_000 +
                "/" +
                Runtime.getRuntime().totalMemory() / 1_000_000 + " MB");
        renderHome();
    }


    @FXML
    private void renderHome() {
        // clean up the pane
        viewPane.getChildren().clear();
        globe = new Image("/globe.png");
        ImageView imageView = new ImageView(globe);
        imageView.setLayoutX(viewPane.getLayoutX() +  globe.getWidth());
        imageView.setLayoutY(globe.getHeight() - 5);
        viewPane.getChildren().add(imageView);

        Label welcomeLabel = new Label();
        welcomeLabel.setText("Welcome " + System.getProperty("user.name").toUpperCase());
        welcomeLabel.setId("welcomeLabel");
        welcomeLabel.setLayoutX(viewPane.getLayoutX() - 150);
        welcomeLabel.setLayoutY(viewPane.getLayoutY());
        viewPane.getChildren().add(welcomeLabel);

        // add DB status label
        Label dbStatusLabel = new Label();
        dbStatusLabel.setId("dbStatusLabel");
        dbStatusLabel.setLayoutX(viewPane.getLayoutX() - 150);
        dbStatusLabel.setLayoutY(viewPane.getLayoutY() + 100);
        try(Connection connection = DatabaseController.createConnection()){
            dbStatusLabel.setText("DB status: Online");
        } catch (SQLException e) {
            Label offlineLabel = new Label();
            offlineLabel.setId("dbStatusLabelOff");
            offlineLabel.setText("OFFLINE");
            dbStatusLabel.setText("DB status: ");
            offlineLabel.setLayoutX(dbStatusLabel.getLayoutX() + 125);
            offlineLabel.setLayoutY(dbStatusLabel.getLayoutY());
            viewPane.getChildren().add(offlineLabel);
//            FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), offlineLabel);
//            fadeTransition.setFromValue(1.0);
//            fadeTransition.setToValue(0.1);
//            fadeTransition.setDuration(Duration.INDEFINITE);
//            fadeTransition.setAutoReverse(true);
//            fadeTransition.play();
        }
        viewPane.getChildren().add(dbStatusLabel);
    }


    @FXML
    private void renderNewCrewman(){
        viewPane.getChildren().clear();

    }


    @FXML
    private void exit(ActionEvent event) {
        Window window = exitButton.getScene().getWindow();
        if (AlertHelper.showAlertWithConfirmation(window, "Exit confirmation", "Are you sure you want to exit " +
                " this application?")) {
            Stage stage = (Stage) window;
            stage.close();
        }
    }

}
