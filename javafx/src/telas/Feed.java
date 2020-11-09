package telas;

import business.Acesso;
import business.DefinicoesPadrao;
import comum.Postagem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;

public class Feed {

    @FXML
    private AnchorPane rootPane;

    @FXML
    public Button btnFazLogin;

    @FXML
    public Button btnFazLogoff;

    @FXML
    public Button btnFazPost;

    @FXML
    public Button btnFazPostCientifico;

    @FXML
    public Button btnModerar;

    @FXML
    public ImageView ivUser;

    @FXML
    public TextField txtTitulo;

    @FXML
    public TextArea txtTexto;

    @FXML
    private void initialize() throws SQLException {
        //Postagem d = Acesso.obtemPost(04);
        btnFazPostCientifico.setVisible(Acesso.ehPesquisadorLogado());
        btnFazPost.setVisible(!Acesso.ehModeradorLogado());
        btnFazLogin.setVisible(!Acesso.ehLogado());
        btnFazLogoff.setVisible(Acesso.ehLogado());
        btnModerar.setVisible(Acesso.ehModeradorLogado());
        if(Acesso.ehLogado()){
            ivUser.setImage(Acesso.bytesToImg(DefinicoesPadrao.getInstance().getUsuarioLogado().getImagem()));
        }
        //txtTitulo.setText(d.getTitulo());
        //txtTexto.setText(d.getConteudo());
    }

    public void btnOnActionModerar(ActionEvent actionEvent) {
        HelperTelas.getInstance().IrParaTela(rootPane, "Moderadores.fxml");
    }

    public void FazLogin(ActionEvent actionEvent) {
        HelperTelas.getInstance().IrParaTela(rootPane, "Login.fxml");
    }

    public void btnFazPostagem(ActionEvent actionEvent){
        if(Acesso.ehLogado()){
            HelperTelas.getInstance().IrParaTela(rootPane, "CriarPost.fxml");
        }
        else{
            HelperTelas.getInstance().IrParaTela(rootPane, "Login.fxml");
        }

    }

    public void btnVerPostagem(ActionEvent actionEvent){
        HelperTelas.getInstance().IrParaTela(rootPane, "VisualizaPost.fxml");
    }

    public void btnFazPostCientifico(ActionEvent actionEvent){
        HelperTelas.getInstance().IrParaTela(rootPane, "CriarPostCientifico.fxml");
    }

    public void btnVerPerfil(ActionEvent actionEvent){
        HelperTelas.getInstance().IrParaTela(rootPane, "Perfil.fxml");
    }

    public void btnFazLogoff(ActionEvent actionEvent) {
        DefinicoesPadrao.getInstance().DeslogarUsuario();
        HelperTelas.getInstance().IrParaTela(rootPane, "Login.fxml");
    }
}
