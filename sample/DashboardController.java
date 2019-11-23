package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    Label nameLbl;
    @FXML
    Label pointsLbl;
    @FXML
    Label idLbl;
    @FXML
    VBox courseArea;

    ArrayList<Chapters> chapters;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void goToMessages(MouseEvent mouseEvent) {
        System.out.println("Am plecat!!");
    }

    public void goToCourses(MouseEvent mouseEvent) {
        System.out.println("Mergi la crsuri");
    }

    public void goToContact(MouseEvent mouseEvent) {
        System.out.println("Mergi la Contacte");
    }

    public void goToSettings(MouseEvent mouseEvent) {
        System.out.println("Mergi la Setari");
    }

    public void goToMyCourse(MouseEvent mouseEvent) {
        chapters = new ArrayList<>();
        Database.loadChapters(chapters);
    }
}
