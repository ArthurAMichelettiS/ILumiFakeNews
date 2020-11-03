package telas;

import business.Acesso;
import business.DefinicoesPadrao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

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
    private void initialize() {
        btnFazPostCientifico.setVisible(Acesso.ehPesquisadorLogado());
        btnFazPost.setVisible(!Acesso.ehModeradorLogado());
        btnFazLogin.setVisible(!Acesso.ehLogado());
        btnFazLogoff.setVisible(Acesso.ehLogado());
        btnModerar.setVisible(Acesso.ehModeradorLogado());
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
