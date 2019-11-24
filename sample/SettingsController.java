package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    public static final String SETTINGS_LOCATION = "Settings.fxml";
    public static int nrBonuri;
    public static int nrBilete;
    @FXML private Label idLabelNumeS;
    @FXML private Button schPassBtn;
    @FXML private Button afisPassBtn;
    @FXML private TextField idTextFieldpwd;
    @FXML private RadioButton checkBoxBon;
    @FXML private RadioButton checkBoxBilet;
    @FXML private Label labelPctRamase;
    @FXML private Label idLabelCumparaturi;
    @FXML private ImageView idBackBtnSet;
    @FXML private Button chitantaBtn;
    @FXML private Label idLabelDC;
    @FXML private Label idLabelDC2;
    @FXML private Label idLabelDD;
    @FXML private Label idLabelDD2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nrBilete = 0;
        nrBonuri = 0;
        labelPctRamase.setText("Puncte ramase: " + Database.getPuncte(LoginController.USER_CNP));
        ToggleGroup group = new ToggleGroup();
        group.getToggles().add(checkBoxBilet);
        group.getToggles().add(checkBoxBon);
        idLabelNumeS.setText(Database.getNume(LoginController.USER_CNP));
    }

    @FXML
    public void showPwd() throws SQLException {
        System.out.println(LoginController.USER_CNP);
        idTextFieldpwd.setText(Database.getPwd(LoginController.USER_CNP));

    }
    public void changePwd(){
            Database.changePwd(idTextFieldpwd.getText(),LoginController.USER_CNP);
    }

    public void buyStuff(){

        int puncte = Database.getPuncte(LoginController.USER_CNP);
        if(checkBoxBilet.isSelected() && (puncte >= 20)){
            nrBilete++;
            Database.updatePuncte(LoginController.USER_CNP, 20);
            labelPctRamase.setText("Puncte ramase: " + Database.getPuncte(LoginController.USER_CNP));
            idLabelCumparaturi.setText("");
            idLabelCumparaturi.setText("Ai cumparat cu succes un bilet de transport in comun!");
        }else
            if(checkBoxBon.isSelected() && (puncte >= 30)){
                nrBonuri++;
                Database.updatePuncte(LoginController.USER_CNP,30);
                labelPctRamase.setText("Puncte ramase: " + Database.getPuncte(LoginController.USER_CNP));
                idLabelCumparaturi.setText("");
                idLabelCumparaturi.setText("Ai cumparat cu succes un bon de masa!");
            }else{
                idLabelCumparaturi.setWrapText(true);
                idLabelCumparaturi.setText("");
                idLabelCumparaturi.setText("Nu ai suficiente puncte! Completeaza cursurile pentru a obtine puncte.");
            }
        }

        public void goBackToDashboard(MouseEvent e){
            Main.getInstance().loadInterface(HomePageController.HOMEPAGE_LOCATION);
        }

    public static String generateUniqueCod(int nrBonuri,int nrBilete,String cnp){
        //CNP + NrBonuri + Separator + NrBilete
        final int nrSalting = 48;
        String result = "";
        String subCnp;
        int index = 1;
        char cc = (char)((int)cnp.charAt(0) + 64);
        char c;
        char[] arr = new char[7];
        arr[0] = cc;
        for(int i = 1;i < cnp.length();i+=2){
            subCnp = cnp.substring(i,i+2);
            c = (char)(Integer.parseInt(subCnp) + nrSalting);
            arr[index] = c;
            index++;
        }
        result = String.copyValueOf(arr);
        result = result + Integer.toString(nrBonuri) + String.format("%c",cc) + nrBilete;
        return result;
    }

    public static Decodificare decodeCnp(String codedCnp){
        Decodificare d = new Decodificare();
        final int nrSalting = 48;
        String cnp = "";
        // Character ch =(char) Character.getNumericValue(codedCnp.charAt(0)-64);
        cnp = cnp + Character.getNumericValue(codedCnp.charAt(0)-64);
        String add = "";
        for(int i = 1;i < 7;i++){
            if((int)(codedCnp.charAt(i)) - nrSalting < 9){
                add = "0" + Integer.toString((int)(codedCnp.charAt(i)) - nrSalting);
            }else{
                add = Integer.toString((int)(codedCnp.charAt(i)) - nrSalting);
            }
            cnp = cnp + add;
        }
        String nrs = codedCnp.substring(7,codedCnp.length());
        int finalIndex  = 0;
        String nrBilete = "";
        String nrBonuri = "";
        for(int i = 0; i < nrs.length();i++){
            if(nrs.charAt(i) == codedCnp.charAt(0)){
                finalIndex = i+1;
                break;
            }
            nrBonuri = nrBonuri + nrs.charAt(i);
            finalIndex = i+1;
        }
        nrBilete = nrs.substring(finalIndex,nrs.length());
        d.setCnp(cnp);
        d.setNrBilete(nrBilete);
        d.setNrBonuri(nrBonuri);

        return d;
    }

        public void elibereazaChitanta(){
            String codif = generateUniqueCod(nrBonuri,nrBilete, LoginController.USER_CNP);
            idLabelDC2.setText(codif);

            Decodificare decodif = decodeCnp(codif);
            String decode = "CNP: " + decodif.getCnp() + "\nNr Bilete: " + decodif.getNrBilete() +"\nNr Bonuri :" + decodif.getNrBonuri();
            idLabelDD2.setWrapText(true);
            idLabelDD2.setText(decode);

        }
}
