package telas;

import business.Acesso;
import business.DefinicoesPadrao;
import comum.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;

public class VisualizaPost {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private ImageView ivUser;


    @FXML
    private void initialize() throws SQLException {
        Usuario user = DefinicoesPadrao.getInstance().getUsuarioLogado();
        ivUser.setImage(Acesso.bytesToImg(user.getImagem()));
        //Postagem p = Acesso.obtemPost(HelperTelas.getInstance().getIdPostNavega());

    }

    public void btnVoltarAction(ActionEvent actionEvent) {

        HelperTelas.getInstance().VoltarTela(rootPane);
    }
}
