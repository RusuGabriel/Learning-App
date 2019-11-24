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
    public static final String DASHBOARD = "DashBoard.fxml";
    public static final String DASHBOARD_LOCATION = "DashBoard.fxml";

    @FXML
    Label nameLbl;
    @FXML
    Label pointsLbl;
    @FXML
    Label idLbl;
    @FXML
    VBox courseArea;
    @FXML
    public static Label pointsWon;

    ArrayList<MyContentPane> chaptersPane = new ArrayList<>();
    ArrayList<Chapters> chapters;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void goToMessages(MouseEvent mouseEvent) {
        Main.load(MessagesController.MESSAGES);
    }

    public void goToCourses(MouseEvent mouseEvent) {
        Main.load(DASHBOARD);
    }

    public void goToContact(MouseEvent mouseEvent) {
        Main.load(InfoController.INFO_LOCATION);
    }

    public void goToSettings(MouseEvent mouseEvent) {
        Main.load(SettingsController.SETTINGS_LOCATION);
    }

    public void goToMyCourse(MouseEvent mouseEvent) {
        chapters = new ArrayList<>();
        Database.loadChapters(chapters, 1);
        if (courseArea.getChildren().size() == 3) {
            if(chaptersPane.isEmpty())
            for (Chapters ch : chapters)
                chaptersPane.add(new MyContentPane(ch.getNumarCapitol(), ch.getNumeCapitol()));
            courseArea.getChildren().addAll(chaptersPane);
        }else
            courseArea.getChildren().removeAll(chaptersPane);
    }

    public void goBack(MouseEvent mouseEvent) {
        Main.load(HomePageController.HOMEPAGE_LOCATION);
    }
}
