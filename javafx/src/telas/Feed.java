package telas;

import business.Acesso;
import business.DefinicoesPadrao;
import comp.CustomControlPost;
import comum.Postagem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public ListView<CustomControlPost> pnPosts;

    @FXML
    private void initialize() throws SQLException {
        Postagem d = Acesso.obtemPost(04);
        btnFazPostCientifico.setVisible(Acesso.ehPesquisadorLogado());
        btnFazPost.setVisible(!Acesso.ehModeradorLogado());
        btnFazLogin.setVisible(!Acesso.ehLogado());
        btnFazLogoff.setVisible(Acesso.ehLogado());
        btnModerar.setVisible(Acesso.ehModeradorLogado());
        if(Acesso.ehLogado()){
            ivUser.setImage(Acesso.bytesToImg(DefinicoesPadrao.getInstance().getUsuarioLogado().getImagem()));
        }
        txtTitulo.setText(d.getTitulo());
        txtTexto.setText(d.getConteudo());

        criaListViewPostagem();
    }

    public void criaListViewPostagem() {

        List<CustomControlPost> list = new ArrayList<CustomControlPost>();
        for (int i = 0; i < 5; i++) {
            list.add(new CustomControlPost("Título", "Descrição"));
        }

        ObservableList<CustomControlPost> myObservableList = FXCollections.observableList(list);
        pnPosts.setItems(myObservableList);
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

    public void btnVerPerfil(ActionEvent actionEvent)
    {
        if(Acesso.ehLogado()) {
            HelperTelas.getInstance().IrParaTela(rootPane, "Perfil.fxml");
        }
        else
            new Alert(Alert.AlertType.ERROR, "Antes de efetuar está ação, favor logar!!!").showAndWait();
    }

    public void btnFazLogoff(ActionEvent actionEvent) {
        DefinicoesPadrao.getInstance().DeslogarUsuario();
        HelperTelas.getInstance().IrParaTela(rootPane, "Login.fxml");
    }
}
