package comp;

import comum.Comentario;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class CustomControlCom extends HBox {
    private TextField textField = new TextField();
    HBoxButtonsCom hBoxButtonsCom;
    private int idCom;

    public CustomControlCom(Comentario com,
                            EventHandler<ActionEvent> editarCom, EventHandler<ActionEvent> excluirCom, boolean y) {
        super();

        setIdCom(com.getIdCom());
        hBoxButtonsCom = new HBoxButtonsCom(editarCom, excluirCom, y);

        textField.setText(com.getConteudo());
        textField.setMaxHeight(Double.MAX_VALUE);
        HBox.setHgrow(textField, Priority.ALWAYS);
        this.getChildren().addAll(textField, hBoxButtonsCom);

    }

    public String getText() {
        return textProperty().get();
    }

    public void setText(String value) {
        textProperty().set(value);
    }

    public int getIdCom() {
        return idCom;
    }

    public void setIdCom(int idCom) {
        this.idCom = idCom;
    }

    public StringProperty textProperty() {
        return textField.textProperty();
    }
}
