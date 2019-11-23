package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class QuizTextController implements Initializable {

    public static final String QUIZ_TEXT_LOCATION = "QuizText.fxml";

    @FXML private Label idLabelQuizText;
    @FXML private Button backBtn;
    @FXML private Button nextBtn;
    @FXML private TextField idAnswerTextField;
    @FXML private Button verifyBtn;
    @FXML private Button showAnswerBtn;


    public void initialize(URL url, ResourceBundle rb){
        idLabelQuizText.setWrapText(true);
        displayQuiz();
    }

    public void goBack(){
        Main.getInstance().loadInterface(PrezentareController.PREZENTARE_LOCATION);
    }
    public void displayQuiz(){
        idLabelQuizText.setText(PrezentareController.intrebari.get(PrezentareController.index));
    }
    public void goNext(){
        PrezentareController.index++;
        Main.getInstance().loadInterface(PrezentareController.PREZENTARE_LOCATION);
    }

    @FXML
    public void verifyAns(){
        if(idAnswerTextField.getText().equalsIgnoreCase(PrezentareController.raspunsuri.get(PrezentareController.index))){
            idAnswerTextField.clear();
            idAnswerTextField.setPromptText("Raspuns Corect! Felicitari!");
        }else{
            idAnswerTextField.clear();
            idAnswerTextField.setPromptText("Raspuns gresit, mai incearca!");
        }
    }

    @FXML
    public void showAns(){
        idAnswerTextField.clear();
        idAnswerTextField.setPromptText(PrezentareController.raspunsuri.get(PrezentareController.index));
    }
}
