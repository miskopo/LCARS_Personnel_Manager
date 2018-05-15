package cz.muni.fi.pv168.view;
import cz.muni.fi.pv168.controller.GUIController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sun.plugin.javascript.navig.Anchor;

public class GUI extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Starfleet personnel manager");
        Parent root = FXMLLoader.load(getClass().getResource("lcars.fxml"));
        Scene scene = new Scene(root, 1920 , 1080);   // 19:10
        Font.loadFont(GUI.class.getResource("/Swiss 911 Ultra Compressed.ttf").toExternalForm(), -1);
        scene.getStylesheets().add(getClass().getResource("lcars.css").toExternalForm());
        primaryStage.setScene(scene);

        primaryStage.setResizable(true);


        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
