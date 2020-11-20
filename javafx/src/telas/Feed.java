package telas;

import business.Acesso;
import business.DefinicoesPadrao;
import comp.CustomControlPost;
import comum.Postagem;
import helper.HelperTelas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    public Button btnVerMeuPerfil;

    @FXML
    public ImageView ivUser;

    @FXML
    public TextField txtTitulo;

    @FXML
    public TextArea txtTexto;

    @FXML
    public Label LbNome;

    @FXML
    public ListView<CustomControlPost> pnPosts;

    @FXML
    public TextField txtPesquisa;

    @FXML
    public ComboBox cbxPesquisa;

    //public TableView tb;

    @FXML
    private void initialize() throws SQLException {
        Postagem d = Acesso.obtemPost(04);
        btnFazPostCientifico.setVisible(Acesso.ehPesquisadorLogado());
        btnFazPost.setVisible(!Acesso.ehModeradorLogado());
        btnFazLogin.setVisible(!Acesso.ehLogado());
        btnFazLogoff.setVisible(Acesso.ehLogado());
        btnModerar.setVisible(Acesso.ehModeradorLogado());
        btnVerMeuPerfil.setVisible(Acesso.ehLogado());
        if(Acesso.ehLogado()){
            LbNome.setText(DefinicoesPadrao.getInstance().getUsuarioLogado().getNome());
        }
        if(Acesso.ehLogado()){
            byte[] img = DefinicoesPadrao.getInstance().getUsuarioLogado().getImagem();
            if(img != null && img.length!=0){
                ivUser.setImage(Acesso.bytesToImg(img));
            }
        }
        txtTitulo.setText(d.getTitulo());
        txtTexto.setText(d.getConteudo());

        criaListViewPostagem(Acesso.obtemListPosts());
        cbxPesquisa.getItems().add("Título");
        cbxPesquisa.getItems().add("Conteúdo");
        cbxPesquisa.getItems().add("Usuários");
        cbxPesquisa.getSelectionModel().select(0);
    }

    public void criaListViewPostagem(ArrayList posts) throws SQLException {

        List<CustomControlPost> list = new ArrayList<CustomControlPost>();

        for (var p : posts) {
            Postagem post = (Postagem)p;
            list.add(new CustomControlPost(post, onActionVerPerfil, onActionVerPostagem));
        }

        ObservableList<CustomControlPost> myObservableList = FXCollections.observableList(list);
        pnPosts.setItems(myObservableList);
    }



    EventHandler<ActionEvent> onActionVerPostagem = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent actionEvent){
            CustomControlPost c = (CustomControlPost) ((Button) actionEvent.getSource()).getParent().getParent();
            HelperTelas.getInstance().setIdPostNavega(c.getIdPostNavega());
            HelperTelas.getInstance().IrParaTela(rootPane, "VisualizaPost.fxml");
        }
    };

    EventHandler<ActionEvent> onActionVerPerfil = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent actionEvent){
            CustomControlPost c = (CustomControlPost) ((Button) actionEvent.getSource()).getParent().getParent();
            HelperTelas.getInstance().setIdPerfilNavega(c.getIdPerfilNavega());
            HelperTelas.getInstance().IrParaTela(rootPane, "Perfil.fxml");
        }
    };


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


    public void btnFazLogoff(ActionEvent actionEvent) {
        DefinicoesPadrao.getInstance().DeslogarUsuario();
        HelperTelas.getInstance().IrParaTela(rootPane, "Login.fxml");
    }

    public void PesquisaPosts(ActionEvent actionEvent) throws SQLException {
        criaListViewPostagem(Acesso.obtemPostsFiltro(txtPesquisa.getText()));
    }

    public void btnVerPostagem(ActionEvent actionEvent){
        HelperTelas.getInstance().IrParaTela(rootPane, "VisualizaPost.fxml");
    }

    public void btnVerPerfil(ActionEvent actionEvent)
    {
        if(Acesso.ehLogado()) {
            HelperTelas.getInstance().IrParaTela(rootPane, "Perfil.fxml");
        }
        else
            new Alert(Alert.AlertType.ERROR, "Antes de efetuar está ação, favor logar!!!").showAndWait();
    }

    public void btnFazPostCientifico(ActionEvent actionEvent) {
        HelperTelas.getInstance().IrParaTela(rootPane, "CriarPostCientifico.fxml");
    }

    public void verMeuPerfil(ActionEvent actionEvent) {
        HelperTelas.getInstance().setIdPerfilNavega(-1);
        HelperTelas.getInstance().IrParaTela(rootPane, "Perfil.fxml");
    }
}
