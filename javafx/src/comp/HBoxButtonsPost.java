package comp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class HBoxButtonsPost extends HBox{

    private Button verPerfil = new Button();
    private Button seguirPerfil = new Button();
    private Button comentarios = new Button();
    private Button denunciar = new Button();

    HBoxButtonsPost(Boolean jaSegue, EventHandler<ActionEvent> metodoPerfil, EventHandler<ActionEvent> metodoPostagem, EventHandler<ActionEvent> metodoDenunciar, EventHandler<ActionEvent> metodoSeguir) {
        super();

        verPerfil.setText("Ver Perfil");
        verPerfil.setMaxWidth(Double.MAX_VALUE);
        verPerfil.setOnAction(metodoPerfil);
        HBox.setHgrow(verPerfil, Priority.ALWAYS);

        seguirPerfil.setText(jaSegue? "Deixar de Seguir Perfil":"Seguir Perfil");
        seguirPerfil.setMaxWidth(Double.MAX_VALUE);
        seguirPerfil.setOnAction(metodoSeguir);
        HBox.setHgrow(seguirPerfil, Priority.ALWAYS);

        comentarios.setText("Postagem e Comentários");
        comentarios.setMaxWidth(Double.MAX_VALUE);
        comentarios.setOnAction(metodoPostagem);
        HBox.setHgrow(comentarios, Priority.ALWAYS);

        denunciar.setText("Denunciar");
        denunciar.setMaxWidth(Double.MAX_VALUE);
        denunciar.setOnAction(metodoDenunciar);
        HBox.setHgrow(denunciar, Priority.ALWAYS);

        this.getChildren().addAll(verPerfil, seguirPerfil, comentarios, denunciar);
    }
}
