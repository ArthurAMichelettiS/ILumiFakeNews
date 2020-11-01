package telas;

import business.Acesso;
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
    public Button btnModerar;

    @FXML
    private void initialize() {
        btnFazLogin.setVisible(!Acesso.ehLogado());
        btnModerar.setVisible(Acesso.ehModeradorLogado());
    }

    public void btnOnActionModerar(ActionEvent actionEvent) {
        HelperTelas.getInstance().IrParaTela(rootPane, "Moderadores.fxml");
    }

    public void FazLogin(ActionEvent actionEvent) {
        HelperTelas.getInstance().IrParaTela(rootPane, "Login.fxml");
    }

    public void btnFazPostagem(ActionEvent actionEvent){
        HelperTelas.getInstance().IrParaTela(rootPane, "CriarPost.fxml");
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
}
