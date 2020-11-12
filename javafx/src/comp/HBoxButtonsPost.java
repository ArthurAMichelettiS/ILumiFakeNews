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
    private Button reportar = new Button();
    private Button curtir = new Button();
    private Button dislike = new Button();

    HBoxButtonsPost(EventHandler<ActionEvent> metodoPerfil, EventHandler<ActionEvent> metodoPostagem) {
        super();

        verPerfil.setText("Ver Perfil");
        verPerfil.setMaxWidth(Double.MAX_VALUE);
        verPerfil.setOnAction(metodoPerfil);
        HBox.setHgrow(verPerfil, Priority.ALWAYS);

        seguirPerfil.setText("Seguir Perfil");
        seguirPerfil.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(seguirPerfil, Priority.ALWAYS);

        comentarios.setText("Comentários");
        comentarios.setMaxWidth(Double.MAX_VALUE);
        comentarios.setOnAction(metodoPostagem);
        HBox.setHgrow(comentarios, Priority.ALWAYS);

        reportar.setText("Reportar");
        reportar.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(reportar, Priority.ALWAYS);

        curtir.setText("☆");
        curtir.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(curtir, Priority.ALWAYS);

        dislike.setText("\uD83D\uDC4E");
        dislike.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(dislike, Priority.ALWAYS);

        this.getChildren().addAll(verPerfil, seguirPerfil, comentarios, reportar, curtir, dislike);
    }
}
