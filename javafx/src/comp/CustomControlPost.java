package comp;

import business.Acesso;
import comum.Postagem;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

public class CustomControlPost extends VBox {
    private TextField textField = new TextField();
    private Label label = new Label();
    private HBoxButtonsPost btnsInteragir;

    private int idPerfilNavega;

    private int idPostNavega;

    public CustomControlPost(Postagem post,
             EventHandler<ActionEvent> metodoPerfil, EventHandler<ActionEvent> metodoPostagem, EventHandler<ActionEvent> metodoDenunciar, EventHandler<ActionEvent> metodoSeguir) throws SQLException {
        super();

        setIdPerfilNavega(post.getIdUser());
        setIdPostNavega(post.getId());

        if(post.getIdTipoPost()==1){
            label.setStyle("-fx-text-fill: #FFD692; ");
            textField.setStyle("-fx-text-fill: #FFD692;");
        }

        btnsInteragir = new HBoxButtonsPost(Acesso.logadoEstaSeguindo(getIdPerfilNavega()), metodoPerfil, metodoPostagem, metodoDenunciar, metodoSeguir);

        label.setText(post.getTitulo());
        label.setMaxHeight(Double.MAX_VALUE);
        HBox.setHgrow(label, Priority.ALWAYS);

        textField.setText(post.getConteudo());
        textField.setMaxHeight(Double.MAX_VALUE);
        HBox.setHgrow(textField, Priority.ALWAYS);


        HBox.setHgrow(btnsInteragir, Priority.ALWAYS);
        this.getChildren().addAll(label, textField, btnsInteragir);
    }


    public int getIdPerfilNavega() {
        return idPerfilNavega;
    }

    public void setIdPerfilNavega(int idPerfilNavega) {
        this.idPerfilNavega = idPerfilNavega;
    }

    public int getIdPostNavega() {
        return idPostNavega;
    }

    public void setIdPostNavega(int idPostNavega) {
        this.idPostNavega = idPostNavega;
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