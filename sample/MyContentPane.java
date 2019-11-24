package sample;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MyContentPane extends HBox {
    String name;
    String capitol;
    Label lbl;
    public static String searchValue;
    public MyContentPane(String numarCapitol, String nume) {
        name = nume;
        capitol = "Capitolul: " + numarCapitol;
        searchValue = nume;
        lbl = new Label(capitol+" - "+nume);
        lbl.setFont(new Font("Bell MT",24));
        lbl.setId(searchValue);
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                System.out.println("Hello World");
                if(((Control)e.getSource()).getId().contains("Intoducere in HTML&CSS"))
                    searchValue = "Intoducere in HTML&CSS";
                else if(((Control)e.getSource()).getId().contains("Crearea paragrafelor"))
                    searchValue = "Crearea paragrafelor";
                else if(((Control)e.getSource()).getId().contains("Crearea unui header"))
                    searchValue = "Crearea unui header";

                Main.load(PrezentareController.PREZENTARE_LOCATION);
            }
        };
//Adding event Filter
        lbl.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
        Separator separator2 = new Separator();
        separator2.setOrientation(Orientation.VERTICAL);
        separator2.setPrefWidth(150);
        //TODO: Implement mouse handler to go to question
        getChildren().addAll(separator2,lbl);
    }
}
