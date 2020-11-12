package comp;

import helper.HelperTelas;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class CustomControlPost extends VBox {
    private TextField textField = new TextField();
    private Label label = new Label();
    private HBoxButtonsPost btnsInteragir;

    public CustomControlPost(String titulo, String conteudo, int idPost,
             EventHandler<ActionEvent> metodoPerfil, EventHandler<ActionEvent> metodoPostagem) {
        super();

        HelperTelas.getInstance().setIdPostNavega(idPost);

        btnsInteragir = new HBoxButtonsPost(metodoPerfil, metodoPostagem);

        label.setText(titulo);
        label.setMaxHeight(Double.MAX_VALUE);
        HBox.setHgrow(label, Priority.ALWAYS);

        textField.setText(conteudo);
        textField.setMaxHeight(Double.MAX_VALUE);
        HBox.setHgrow(textField, Priority.ALWAYS);

        HBox.setHgrow(btnsInteragir, Priority.ALWAYS);
        this.getChildren().addAll(label, textField, btnsInteragir);
    }

    public String getText() {
        return textProperty().get();
    }

    public void setText(String value) {
        textProperty().set(value);
    }

    public StringProperty textProperty() {
        return textField.textProperty();
    }

}