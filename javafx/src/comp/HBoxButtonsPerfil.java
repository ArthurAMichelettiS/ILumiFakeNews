package comp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class HBoxButtonsPerfil extends HBox{

    private Button verPost = new Button();
    private Button editarPost = new Button();
    private Button deletarPost = new Button();



    HBoxButtonsPerfil(EventHandler<ActionEvent> metodoPost, EventHandler<ActionEvent> metodoApagar,EventHandler<ActionEvent> metodoEditar) {
        super();

        verPost.setText("Visualizar Post");
        verPost.setMaxWidth(Double.MAX_VALUE);
        verPost.setOnAction(metodoPost);
        HBox.setHgrow(verPost, Priority.ALWAYS);

        editarPost.setText("Editar Post");
        editarPost.setMaxWidth(Double.MAX_VALUE);
        editarPost.setOnAction(metodoEditar);
        HBox.setHgrow(editarPost, Priority.ALWAYS);

        deletarPost.setText("Deletar Post");
        deletarPost.setMaxWidth(Double.MAX_VALUE);
        deletarPost.setOnAction(metodoApagar);
        HBox.setHgrow(deletarPost, Priority.ALWAYS);

        this.getChildren().addAll(verPost,editarPost,deletarPost);
    }
}
