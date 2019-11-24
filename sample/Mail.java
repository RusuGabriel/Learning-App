package sample;

import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class Mail extends HBox {
    Label sender;
    Label receive;
    Label subject;

    public Mail(String sender, String receive, String subject) {
        this.sender = new Label(sender);
        this.sender.setFont(new Font("Bell MT",24));
        this.subject = new Label(subject);
        this.subject.setPrefWidth(350);
        this.subject.setFont(new Font("Bell MT",24));
        this.receive = new Label(receive);
        this.receive.setFont(new Font("Bell MT",24));
        Separator separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);
        separator.setPrefWidth(70);
        Separator separator1 = new Separator();
        separator1.setOrientation(Orientation.VERTICAL);
        separator.setVisible(false);
        separator1.setVisible(false);
        separator1.setPrefWidth(300);
        Separator separator2 = new Separator();
        separator2.setOrientation(Orientation.VERTICAL);
        separator2.setPrefWidth(150);
        separator2.setVisible(false);
        this.getChildren().addAll(separator,this.sender, separator1,this.subject, separator2,this.receive);
    }

    public Mail(String[] args) {
        this(args[0], args[1], args[2]);
    }
}
