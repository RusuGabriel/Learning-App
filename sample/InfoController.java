package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class InfoController implements Initializable {
        public static final String INFO_LOCATION = "Info.fxml";

        @FXML private ImageView idImageInfo;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void goBackToHome(){
        Main.getInstance().loadInterface(HomePageController.HOMEPAGE_LOCATION);
    }
}
