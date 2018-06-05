package cz.muni.fi.pv168.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

import cz.muni.fi.pv168.common.Rank;
import cz.muni.fi.pv168.common.StarDateUtils;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

import javax.xml.crypto.Data;


public class GUIController {
    @FXML private Button exitButton;
    @FXML private Label starDateLabel;
    @FXML private Label systemInformation;
    @FXML private Pane viewPane;
    ResourceBundle resourceBundle = ResourceBundle.getBundle("cz.muni.fi.pv168.view.bundles.lcars", Locale.getDefault());

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
        Image globe = new Image("/globe.png");
        ImageView imageView = new ImageView(globe);
        imageView.setLayoutX(viewPane.getLayoutX() +  globe.getWidth());
        imageView.setLayoutY(globe.getHeight() - 5);

        Label welcomeLabel = new Label();
        welcomeLabel.setText("Welcome " + System.getProperty("user.name").toUpperCase());
        welcomeLabel.setId("welcomeLabel");
        welcomeLabel.setLayoutX(viewPane.getLayoutX() - 150);
        welcomeLabel.setLayoutY(viewPane.getLayoutY());

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
        }
        // add created elements to view pane
        viewPane.getChildren().add(imageView);
        viewPane.getChildren().add(welcomeLabel);
        viewPane.getChildren().add(dbStatusLabel);
    }


    @FXML
    private void renderNewCrewman(){
        viewPane.getChildren().clear();


    }

    @FXML
    private void renderListAllCrewmen(){
        viewPane.getChildren().clear();
        TableView tableView = new TableView();
        tableView.setEditable(true);
                TableColumn name = new TableColumn(resourceBundle.getString("crewmanName"));
        TableColumn rank= new TableColumn(resourceBundle.getString("crewmanRank"));
        // TODO: Resolve if possible
        tableView.getColumns().addAll(name, rank);
        ObservableList<ObservableList> dataToShow = FXCollections.observableArrayList();
        try(Connection connection = DatabaseController.createConnection()){
            String SQLStatement = "SELECT * FROM CREWMAN";
            ResultSet resultSet = connection.createStatement().executeQuery(SQLStatement);
            while(resultSet.next()){
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=resultSet.getMetaData().getColumnCount(); ++i){
                    //Iterate Column
                    row.add(resultSet.getString(i));
                }
                dataToShow.add(row);
            }
        } catch (SQLException e) {
            // TODO:
        }
        tableView.setItems(dataToShow);


        viewPane.getChildren().add(tableView);
    }

    @FXML
    private void exit(ActionEvent event) {
        Window window = exitButton.getScene().getWindow();
        if (AlertHelper.showAlertWithConfirmation(window, resourceBundle.getString("exitConfirmation"),
                resourceBundle.getString("confirmationDialog"))) {
            Stage stage = (Stage) window;
            stage.close();
        }
    }

}
