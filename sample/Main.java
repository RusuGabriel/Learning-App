package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static Parent parent;
    private static Stage stage;
    private static Main instance;
    public static final String MAIN_LOCATION = "Login.fxml";

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        instance = this;
        load(MAIN_LOCATION);
    }

    public static void load(String resource) {
        try {
            parent = FXMLLoader.load(instance.getClass().getResource(resource));
            Scene scene = new Scene(parent);
            stage.setTitle("Learning App");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println();
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
        }
    }

    public void loadInterface(String path) {
        try {
            parent = FXMLLoader.load(getClass().getResource(path));
            Scene sc = new Scene(parent);
            stage.setScene(sc);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error while loading the resource(Main)");
        }
    }

    public static void main(String[] args) {
        Thread connect = new Thread(() -> {
            Database.getInstance();
        });
        connect.start();
        launch(args);
    }
}
