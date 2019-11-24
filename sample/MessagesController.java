package sample;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MessagesController implements Initializable {
    public static final String MESSAGES = "Messages.fxml";

    private static ArrayList<Mail> inbox = new ArrayList<>();
    @FXML
    private VBox displayArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void goToDashboard(Event e) {
        Main.load(DashboardController.DASHBOARD);
    }

    public void goToInbox(MouseEvent mouseEvent) {
        if (!inbox.isEmpty())
            displayArea.getChildren().removeAll(inbox);
        inbox = new ArrayList<>();
        Database.loadMailsIn(inbox, Database.getInstance().search(LoginController.USER_CNP), "IDUserR");
        displayArea.getChildren().addAll(inbox);

    }

    public void goToSentMessages(MouseEvent mouseEvent) {
        if (!inbox.isEmpty())
            displayArea.getChildren().removeAll(inbox);
        inbox = new ArrayList<>();
        Database.loadMailsIn(inbox, Database.getInstance().search(LoginController.USER_CNP), "IDUserS");
        displayArea.getChildren().addAll(inbox);
    }
}
