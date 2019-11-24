package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    public static final String SIGNUP_LOCATION = "Signup.fxml";

    @FXML private Button backBtnS;
    @FXML private Button submitBtn;
    @FXML private RadioButton idCheckBoxDa;
    @FXML private RadioButton idCheckBoxNu;
    @FXML private TextField idNumeS;
    @FXML private TextField idCNPS;
    @FXML private PasswordField idPassS;
    @FXML private Label idErrCNP;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup group = new ToggleGroup();
        group.getToggles().add(idCheckBoxDa);
        group.getToggles().add(idCheckBoxNu);
    }

    @FXML
    public void goBackL(){
        Main.getInstance().loadInterface(LoginController.LOGIN_LOCATION);
    }
    // user validation
    boolean alreadyExists(String iCNP){
        ArrayList<String> cnpList;
        cnpList = Database.getCNPs();

        if(cnpList.contains(iCNP)){
            return true;
        }
        return false;
    }

    @FXML
    public void register(){
        int valid = 1;
        User u = new User();
        u.setNumeUser(idNumeS.getText());
        u.setCNP(idCNPS.getText());
        u.setParola(idPassS.getText());
        if(idCheckBoxDa.isSelected())
            u.setEsteMentor("1");
        else
            u.setEsteMentor("0");
        if(idNumeS.getText().equalsIgnoreCase("")){
            idNumeS.clear();;
            idNumeS.setPromptText("Te rugam sa introduci numele!");
            valid = 0;
        }

        if(idPassS.getText().equalsIgnoreCase("")){
            idPassS.clear();
            idPassS.setPromptText("Te rugam sa introduci parola!");
            valid = 0;
        }

        if(idCNPS.getText().equalsIgnoreCase("")){
            idCNPS.clear();
            idCNPS.setPromptText("TE RUGAM SA INTRODUCI CNP-UL!");
            valid = 0;
        }
        if (!u.checkCnp()) {
            idErrCNP.setText("CNP-UL INTRODUS ESTE INVALID!");
            valid = 0;
        }
        if(alreadyExists(u.getCNP())){
            idErrCNP.setText("CNP-UL INTRODUS DEJA EXISTA!");
            valid = 0;
        }
        if(valid == 1) {
                Database.newUser(u);
                Main.getInstance().loadInterface(LoginController.LOGIN_LOCATION);
            }
    }

}
