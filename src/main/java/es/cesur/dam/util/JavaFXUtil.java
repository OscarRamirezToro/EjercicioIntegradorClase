package es.cesur.dam.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaFXUtil {

    private static final Logger logger = Logger.getLogger(JavaFXUtil.class.getName());
    private static Stage stage;

    public static void initStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public static <T> T setScene(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(JavaFXUtil.class.getResource("/com/paco/hibernatetableviewfx/" + fxml));
            Parent root = loader.load();
            T controller = loader.getController();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
            return controller;
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Error al cargar la vista: " + fxml, ex);
        }
        return null;
    }
}