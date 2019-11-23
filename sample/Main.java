package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("DashBoard.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Learning App");
            primaryStage.setScene(scene);
            Thread connect = new Thread(()->{Database.getInstance();});
            connect.start();
            primaryStage.show();
        }catch (Exception e)
        {
            System.out.println();
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
