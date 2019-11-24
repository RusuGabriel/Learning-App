package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    public static final String HOMEPAGE_LOCATION = "HomePage.fxml";
    @FXML private ImageView idCursuri;
    @FXML private ImageView idSetari;
    @FXML private ImageView idContact;
    @FXML private Label idLabelNume;
    @FXML private Label idLabelPuncte;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idLabelPuncte.setText(Integer.toString(Database.getPuncte(LoginController.USER_CNP)));
        idLabelNume.setText(Database.getNume(LoginController.USER_CNP));
    }

    @FXML
    public void goToDashboard(MouseEvent e){
        Main.getInstance().loadInterface(DashboardController.DASHBOARD_LOCATION);
    }
    @FXML
    public void goToSettings(MouseEvent e){
        Main.getInstance().loadInterface(SettingsController.SETTINGS_LOCATION);
    }

    @FXML
    public void goToInfo(MouseEvent e){
         Main.getInstance().loadInterface(InfoController.INFO_LOCATION);

    }

}
