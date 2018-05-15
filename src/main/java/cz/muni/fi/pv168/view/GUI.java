package cz.muni.fi.pv168.view;
import cz.muni.fi.pv168.common.StarDateUtils;
import cz.muni.fi.pv168.controller.GUIController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUI extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        GUIController guiController = new GUIController();
        primaryStage.setTitle("Starfleet personnel manager");
        Parent root = FXMLLoader.load(getClass().getResource("lcars.fxml"));
        Scene scene = new Scene(root, 1920 , 1080);   // 19:10
        Font.loadFont(GUI.class.getResource("/Swiss 911 Ultra Compressed.ttf").toExternalForm(), -1);
        scene.getStylesheets().add(getClass().getResource("/cz/muni/fi/pv168/view/lcars.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);

        // TODO: set stardate
        StarDateUtils starDateUtils = new StarDateUtils(StarDateUtils.getCurrentStarDate());
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
