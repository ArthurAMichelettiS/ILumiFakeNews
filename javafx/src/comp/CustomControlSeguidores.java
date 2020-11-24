package comp;

import business.Acesso;
import comum.Usuario;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class CustomControlSeguidores extends HBox {
    private Label labelNome = new Label();
    private ImageView ivUser = new ImageView();
    private Button verPerfil = new Button();
    private Button seguirPerfil = new Button();
    private int idPerfilNavega;

    public int getIdPerfilNavega() {
        return idPerfilNavega;
    }

    public void setIdPerfilNavega(int idPerfilNavega) {
        this.idPerfilNavega = idPerfilNavega;
    }

    public CustomControlSeguidores(Usuario u, EventHandler<ActionEvent> metodoPerfil) {
        super();
        setIdPerfilNavega(u.getId());
        if(u.getImagem()!=null){
            ivUser.setImage(Acesso.bytesToImg(u.getImagem()));
        }
        ivUser.setPreserveRatio(true);
        ivUser.setFitWidth(50);
        HBox.setHgrow(ivUser, Priority.ALWAYS);

        labelNome.setText(u.getNome());
        labelNome.setMaxWidth(70);
        HBox.setHgrow(labelNome, Priority.ALWAYS);

        verPerfil.setText("Perfil");
        verPerfil.setMaxWidth(100);
        verPerfil.setOnAction(metodoPerfil);
        HBox.setHgrow(verPerfil, Priority.ALWAYS);

        this.getChildren().addAll(ivUser, labelNome, verPerfil);
    }
}
