package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;



public class LoginController implements Initializable {

    public static final String LOGIN_LOCATION = "Login.fxml";
    public static String USER_CNP;

    @FXML private TextField idCNPTextField;
    @FXML private PasswordField idParolaPassField;
    @FXML private Button loginBtn;
    @FXML private Button newAccBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Thread th = new Thread(()->{Database.getInstance();});
        th.start();
    }

    @FXML
    public void logIn(){
        int valid = 1;
        ArrayList<String> cnpList = Database.getCNPs();
        ArrayList<String> passList = Database.getPasswords();

        if(!cnpList.contains(idCNPTextField.getText())){
            idCNPTextField.clear();
            idCNPTextField.setPromptText("NUMELE SAU PAROLA NU EXISTA!");
            valid = 0;
        }
        if(!passList.contains(idParolaPassField.getText())){
            idParolaPassField.clear();
            idParolaPassField.setPromptText("NUMELE SAU PAROLA NU EXISTA!");
            valid = 0;
        }

        if(valid == 1){
            USER_CNP = idCNPTextField.getText();
            Main.getInstance().loadInterface(HomePageController.HOMEPAGE_LOCATION);
        }
    }
    @FXML
    public void signUp(){
        Main.getInstance().loadInterface(SignUpController.SIGNUP_LOCATION);
    }


}
