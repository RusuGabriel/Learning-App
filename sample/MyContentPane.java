package sample;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class MyContentPane extends HBox {
    String name;
    String capitol;
    Label lbl;

    public MyContentPane(String numarCapitol, String nume) {
        name = nume;
        capitol = "Capitolul: " + numarCapitol;
        lbl = new Label(capitol+" - "+nume);
        lbl.setFont(new Font("Bell MT",24));
        Separator separator2 = new Separator();
        separator2.setOrientation(Orientation.VERTICAL);
        separator2.setPrefWidth(150);
        getChildren().addAll(separator2,lbl);
    }
}
