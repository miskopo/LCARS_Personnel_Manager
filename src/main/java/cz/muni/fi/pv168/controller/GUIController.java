package cz.muni.fi.pv168.controller;

import java.time.LocalDate;

import cz.muni.fi.pv168.common.StarDateUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import javafx.stage.Window;


public class GUIController {
    @FXML private Button exitButton;
    @FXML private Label starDateLabel;
    @FXML private Label systemInformation;
    @FXML private Pane viewPane;
    @FXML private Pane sideViewDecoration;

    @FXML
    private void initialize() {
        starDateLabel.textProperty().setValue("STARDATE: " + StarDateUtils.dateToStarDate(LocalDate.now()));
        systemInformation.textProperty().setValue("Available memory: " + Runtime.getRuntime().freeMemory()/1_000_000 +
                "/" +
                Runtime.getRuntime().totalMemory() / 1_000_000);
        renderHome();
    }


    @FXML
    private void renderHome() {
        Image image = new Image("/globe.png");
        ImageView imageView = new ImageView(image);
        imageView.setX(viewPane.getWidth() - (viewPane.getWidth() - sideViewDecoration.getLayoutX()) - image.getWidth());
        imageView.setLayoutY(viewPane.getLayoutY() + viewPane.getHeight() + image.getHeight() / 2);
        viewPane.getChildren().add(imageView);

    }


    @FXML
    private void exit(ActionEvent event) {
        Window window = exitButton.getScene().getWindow();
        if (AlertHelper.showAlertWithConfirmation(window, "Exit confirmation", "Are you sure you want to exit " +
                "application?")) {
            Stage stage = (Stage) window;
            stage.close();
        }
    }

}
