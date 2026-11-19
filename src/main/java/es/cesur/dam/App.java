package es.cesur.dam;

import es.cesur.dam.util.HibernateUtil;
import es.cesur.dam.util.JavaFXUtil;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        JavaFXUtil.initStage(stage);
        stage.setTitle("Gestión con Hibernate y JavaFX");
        JavaFXUtil.setScene("primary.fxml");
    }

    @Override
    public void stop() {
        HibernateUtil.close();
    }

    public static void main(String[] args) {
        launch();
    }
}