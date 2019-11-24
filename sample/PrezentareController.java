package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PrezentareController implements Initializable {

    public static final String PREZENTARE_LOCATION = "Prezentare.fxml";

    @FXML private Label idLabelTitlu;
    @FXML private Label idLabelContinut;
    @FXML private Button askBtn;
    @FXML private Button quizBtn;
    @FXML private TextField questionTextField;
    public static ArrayList<String> prezentari;
    public static ArrayList<String> intrebari;
    public static ArrayList<String> titluri;
    public static ArrayList<String> raspunsuri;
    public static int index = 0;

    static{

    }

    public void initialize(URL url, ResourceBundle rb){
        raspunsuri = new ArrayList<String>();
        prezentari = new ArrayList<String>();
        intrebari = new ArrayList<String>();
        titluri = new ArrayList<String>();
        Database.loadPrezentari(prezentari);

        intrebari.add("Fișierul care creează o pagină web nu este altceva decât ______. ");
        intrebari.add("intrebare 2: ");
        intrebari.add("intrebare 3: ");

        titluri.add("titlu 1");
        titluri.add("titlu 2");
        titluri.add("titlu 3");

        raspunsuri.add("Raspuns 1");
        raspunsuri.add("Raspuns 2");
        raspunsuri.add("Raspuns 3");

        idLabelContinut.setWrapText(true);
        idLabelTitlu.setWrapText(true);
        displayInfo();

    }

    @FXML
    public void goToQuiz(){
        Main.getInstance().loadInterface(QuizTextController.QUIZ_TEXT_LOCATION);
    }

    public void displayInfo(){
        idLabelTitlu.setText(titluri.get(index));
        idLabelContinut.setText(prezentari.get(index));
    }

    public void displayMentorMSG(){
        System.out.println(questionTextField.getText());
        questionTextField.clear();
        questionTextField.setPromptText("Mesajul tau a fost trimis cu succes!");
        //TODO
    }

    public void sendQuestion(){
        displayMentorMSG();
    }



}
