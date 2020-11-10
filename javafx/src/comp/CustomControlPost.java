package comp;

import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class CustomControlPost extends VBox {
    private TextField textField = new TextField();
    private Label label = new Label();
    private HBoxButtonsPost btnsInteragir = new HBoxButtonsPost();

    public static class HBoxButtonsPost extends HBox {
        Button verPerfil = new Button();
        Button seguirPerfil = new Button();
        Button comentarios = new Button();
        Button reportar = new Button();
        Button curtir = new Button();
        Button dislike = new Button();

        HBoxButtonsPost() {
            super();

            verPerfil.setText("Ver Perfil");
            verPerfil.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(verPerfil, Priority.ALWAYS);

            seguirPerfil.setText("Seguir Perfil");
            seguirPerfil.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(seguirPerfil, Priority.ALWAYS);

            comentarios.setText("Comentários");
            comentarios.setMaxWidth(Double.MAX_VALUE);
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

    public CustomControlPost(String titulo, String conteudo) {
        super();

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