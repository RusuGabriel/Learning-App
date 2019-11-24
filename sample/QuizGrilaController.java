package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class QuizGrilaController implements Initializable {
    public static final String QUIZGRILA_LOCATION = "QuizGrila.fxml";
    private static ArrayList<String> intrebari = new ArrayList<>();
    private static ArrayList<String> raspunsuri = new ArrayList<>();
    public static ArrayList<ArrayList<String>> all = new ArrayList<ArrayList<String>>();
    public static ArrayList<Integer> points = new ArrayList<>();

    public static String raspunsCorect;

    @FXML private Label idLabelQuizGrila;
    @FXML private RadioButton varBtn1;
    @FXML private RadioButton varBtn2;
    @FXML private RadioButton varBtn3;
    @FXML private RadioButton varBtn4;
    @FXML private Button idnextQuizGBtn;
    static int index = 0;
    ToggleGroup group = new ToggleGroup();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        idLabelQuizGrila.setWrapText(true);
        Database.getQuestions(intrebari);
        Database.getRaspunsuri(raspunsuri);
        idLabelQuizGrila.setText(intrebari.get(index));
        varBtn1.setText(all.get(index).get(0));
        varBtn2.setText(all.get(index).get(1));
        varBtn3.setText(all.get(index).get(2));
        varBtn4.setText(all.get(index).get(3));
        group.getToggles().add(varBtn1);
        group.getToggles().add(varBtn2);
        group.getToggles().add(varBtn3);
        group.getToggles().add(varBtn4);

    }

    @FXML
    public void goNextQuizG(){
        try {
            if(((RadioButton)group.getSelectedToggle()).getText().equals(raspunsuri.get(index))) {
                Database.crestePuncte(LoginController.USER_CNP, points.get(index));
                //DashboardController.pointsWon.setText(Integer.parseInt(DashboardController.pointsWon.getText())+points.get(index)+"");
            }
            idLabelQuizGrila.setText(intrebari.get(++index));
            varBtn1.setText(all.get(index).get(0));
            varBtn2.setText(all.get(index).get(1));
            varBtn3.setText(all.get(index).get(2));
            varBtn4.setText(all.get(index).get(3));
        }catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            if (index == intrebari.size()) {
                index = 0;
                Main.load(DashboardController.DASHBOARD);
            }
        }
    }
}
