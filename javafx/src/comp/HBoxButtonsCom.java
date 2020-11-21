package comp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class HBoxButtonsCom extends HBox {
    private TextField textField = new TextField();
    private Button btnEditarCom = new Button();
    private Button btnExcluirCom = new Button();

    HBoxButtonsCom(EventHandler<ActionEvent> editarCom, EventHandler<ActionEvent> excluirCom) {
        super();

        textField.setMaxHeight(Double.MAX_VALUE);
        HBox.setHgrow(textField, Priority.ALWAYS);

        btnEditarCom.setText("Editar Comentário");
        btnEditarCom.setMaxWidth(Double.MAX_VALUE);
        btnEditarCom.setOnAction(editarCom);
        HBox.setHgrow(btnEditarCom, Priority.ALWAYS);

        btnExcluirCom.setText("Excluir Comentário");
        btnExcluirCom.setMaxWidth(Double.MAX_VALUE);
        btnEditarCom.setOnAction(editarCom);
        HBox.setHgrow(btnExcluirCom, Priority.ALWAYS);

        this.getChildren().addAll(textField,btnEditarCom,btnExcluirCom);
    }
}
