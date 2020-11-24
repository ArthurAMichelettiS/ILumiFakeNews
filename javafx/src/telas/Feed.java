package telas;

import business.Acesso;
import business.DefinicoesPadrao;
import comp.CustomControlPost;
import comp.CustomControlSeguidores;
import comp.HboxUsuario;
import comum.Postagem;
import comum.Usuario;
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
    public Label LbNome;

    @FXML
    public ListView pnPosts;

    @FXML
    public ListView pnPostsParaVc;

    @FXML
    public ListView lvSeguindo;

    @FXML
    public TextField txtPesquisa;

    @FXML
    public ComboBox cbxPesquisa;

    @FXML
    public TabPane tbPane;

    //public TableView tb;

    public void AtivaBotoesCorrespondentes(){
        btnFazPostCientifico.setVisible(Acesso.ehPesquisadorLogado());
        btnFazPost.setVisible(!Acesso.ehModeradorLogado());
        btnFazLogin.setVisible(!Acesso.ehLogado());
        btnFazLogoff.setVisible(Acesso.ehLogado());
        btnModerar.setVisible(Acesso.ehModeradorLogado());
        btnVerMeuPerfil.setVisible(Acesso.ehLogado());
    }

    @FXML
    private void initialize() throws SQLException {
        try{
            AtivaBotoesCorrespondentes();
            if(Acesso.ehLogado()){
                LbNome.setText(DefinicoesPadrao.getInstance().getUsuarioLogado().getNome());
                criaListViewSeguindo(Acesso.obtemSeguindo(DefinicoesPadrao.getInstance().getUsuarioLogado().getId()));
            }
            if(Acesso.ehLogado()){
                byte[] img = DefinicoesPadrao.getInstance().getUsuarioLogado().getImagem();
                if(img != null && img.length!=0){
                    ivUser.setImage(Acesso.bytesToImg(img));
                }
            }
            ArrayList p = Acesso.obtemListPosts();
            criaListViewPostagem(p, pnPosts);
            criaListViewPostagem(p, pnPostsParaVc);
            cbxPesquisa.getItems().add("Postagens");
            cbxPesquisa.getItems().add("Usuários");
            cbxPesquisa.getSelectionModel().select(0);
        } catch (SQLException erro) {
            new Alert(Alert.AlertType.ERROR, "Existe um problema com a sua conexão a internet!\nverifique-a e tente novamente.", ButtonType.OK, ButtonType.CANCEL).showAndWait();
            System.exit(0);
        }
    }

    public void criaListViewPostagem(ArrayList posts, ListView lv) throws SQLException {

        List<CustomControlPost> list = new ArrayList<CustomControlPost>();

        for (var p : posts) {
            Postagem post = (Postagem)p;
            list.add(new CustomControlPost(post, onActionVerPerfil, onActionVerPostagem, onActionDenunciar, onActionSeguir));
        }

        ObservableList<CustomControlPost> myObservableList = FXCollections.observableList(list);
        lv.setItems(myObservableList);
    }


    EventHandler<ActionEvent> onActionSeguir = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent actionEvent){
            if(!Acesso.ehLogado()){
                HelperTelas.getInstance().IrParaTela(rootPane, "Login.fxml");
            }

            Button btnSegue = (Button) actionEvent.getSource();
            CustomControlPost c = (CustomControlPost) btnSegue.getParent().getParent();
            try {
                if(Acesso.logadoEstaSeguindo(c.getIdPerfilNavega())){
                    Acesso.apagaSeguindo(c.getIdPerfilNavega());
                    btnSegue.setText("Seguir Perfil");
                }
                else{
                    Acesso.insereSeguindo(c.getIdPerfilNavega());
                    btnSegue.setText("Deixar de Seguir Perfil");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    };

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

    EventHandler<ActionEvent> onActionVerPerfilUser = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent actionEvent){
            HboxUsuario c = (HboxUsuario) ((Button) actionEvent.getSource()).getParent();
            HelperTelas.getInstance().setIdPerfilNavega(c.getIdPerfilNavega());
            HelperTelas.getInstance().IrParaTela(rootPane, "Perfil.fxml");
        }
    };

    EventHandler<ActionEvent> onActionDenunciar = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent actionEvent){
            if(!Acesso.ehLogado()){
                HelperTelas.getInstance().IrParaTela(rootPane, "Login.fxml");
            }
            CustomControlPost c = (CustomControlPost) ((Button) actionEvent.getSource()).getParent().getParent();
            DefinicoesPadrao.getInstance().setIdPostagem(c.getIdPostNavega());
            HelperTelas.getInstance().IrParaTela(rootPane,"Denuncias.fxml");
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
        tbPane.getSelectionModel().select(1);
        switch (cbxPesquisa.getSelectionModel().getSelectedIndex()){
            case 0:
                criaListViewPostagem(Acesso.obtemPostsFiltro(txtPesquisa.getText()), pnPosts);
                break;
            case 1:
                criaListViewUsuario(Acesso.obtemUsuariosFiltro(txtPesquisa.getText()));
                break;
            default:
                break;
        }
    }

    public void criaListViewUsuario(ArrayList users) {

        List<HboxUsuario> list = new ArrayList<HboxUsuario>();

        for (var u : users) {
            Usuario user = (Usuario) u;
            list.add(new HboxUsuario(user, onActionVerPerfilUser, onActionSeguir));
        }

        ObservableList<HboxUsuario> myObservableList = FXCollections.observableList(list);
        pnPosts.setItems(myObservableList);
    }

    public void criaListViewSeguindo(ArrayList users) {

        List<CustomControlSeguidores> list = new ArrayList<CustomControlSeguidores>();

        for (var u : users) {
            Usuario user = (Usuario) u;
            list.add(new CustomControlSeguidores(user, onActionVerPerfilUser, onActionSeguir));
        }

        ObservableList<CustomControlSeguidores> myObservableList = FXCollections.observableList(list);
        lvSeguindo.setItems(myObservableList);
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
