package telas;

import business.Acesso;
import business.DefinicoesPadrao;
import comum.Postagem;
import comum.Usuario;
import helper.HelperTelas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;

public class VisualizaPost {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private ImageView ivUser;

    @FXML
    private TextField txtTitulo;

    @FXML
    private TextArea txtConteudo;

    @FXML
    private TextField txtInsCom;

    @FXML
    private Button btnCom;

    @FXML
    private void initialize() throws SQLException {
        if(Acesso.ehLogado()){
            Usuario user = DefinicoesPadrao.getInstance().getUsuarioLogado();
            ivUser.setImage(Acesso.bytesToImg(user.getImagem()));
        }

        //post especifico a ser visualizado
        Postagem p = Acesso.obtemPost(HelperTelas.getInstance().getIdPostNavega());
        txtTitulo.setText(p.getTitulo());
        txtConteudo.setText(p.getConteudo());

    }

    public void btnVoltarAction(ActionEvent actionEvent) {

        HelperTelas.getInstance().VoltarTela(rootPane);
    }

    public void btnComAction (ActionEvent actionEvent) throws SQLException {
        Postagem p = Acesso.obtemPost(HelperTelas.getInstance().getIdPostNavega());
    }


}
