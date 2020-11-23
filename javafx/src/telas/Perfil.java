package telas;

import business.DefinicoesPadrao;
import business.Acesso;
import comp.CustomControlPerfil;
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


public class Perfil {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField txtSenha;

    @FXML
    private TextField txtSenhaConf;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtBio;

    @FXML
    private ImageView ivProfile;

    @FXML
    private Button btnEditarPerfil;

    @FXML
    public ListView<CustomControlPerfil> pnPostsUser;

    @FXML
    public Label lbNome;

    @FXML
    private void initialize() throws SQLException {
        Usuario user;
        boolean ehEditavel = false;
        if(HelperTelas.getInstance().getIdPerfilNavega() == -1){
             user = DefinicoesPadrao.getInstance().getUsuarioLogado();
             ehEditavel=true;
        }
        else{
            user = Acesso.localizaUsuarioPorId(HelperTelas.getInstance().getIdPerfilNavega());
        }

        txtBio.setEditable(ehEditavel);
        txtSenha.setEditable(ehEditavel);
        txtSenhaConf.setEditable(ehEditavel);
        txtEmail.setEditable(ehEditavel);
        btnEditarPerfil.setVisible(ehEditavel);

        txtEmail.setText(user.getEmail());
        txtBio.setText(user.getBio());
        txtSenha.setText(user.getSenha());
        txtSenhaConf.setText(user.getSenha());
        lbNome.setText(user.getNome());

        if(user.getImagem() != null && user.getImagem().length!=0){
            ivProfile.setImage(Acesso.bytesToImg(user.getImagem()));
        }

        criaListViewPostagemUser(Acesso.obtemListPostsPorUser(user.getId()));
    }

    public void btnVoltarAction(ActionEvent actionEvent) {
        HelperTelas.getInstance().IrParaTela(rootPane, "Feed.fxml");
    }

    public void editarCadastro (ActionEvent actionEvent) {

        Usuario user = DefinicoesPadrao.getInstance().getUsuarioLogado();
        try {
            user.setBio(txtBio.getText());
            user.setEmail(txtEmail.getText());
            user.setSenha(txtSenha.getText());
            Acesso.alterarDadosUsuario(user);
            HelperTelas.getInstance().VoltarTela(rootPane);

        } catch (SQLException erro) {
            new Alert(Alert.AlertType.ERROR, "Algo de errado ao salvar!").showAndWait();
        } catch (Exception erro) {
            new Alert(Alert.AlertType.ERROR, "Há valores incorretos!").showAndWait();
        }
    }

    EventHandler<ActionEvent> metodoEditar = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent actionEvent){
            CustomControlPerfil c = (CustomControlPerfil) ((Button) actionEvent.getSource()).getParent().getParent();
            HelperTelas.getInstance().setIdPostNavega(c.getIdPostNavega());
            HelperTelas.getInstance().IrParaTela(rootPane, "VisualizaPost.fxml");
        }
    };

    EventHandler<ActionEvent> metodoApagar = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent actionEvent) {
            CustomControlPerfil c = (CustomControlPerfil) ((Button) actionEvent.getSource()).getParent().getParent();
            try {
                Postagem p = Acesso.obtemPost(c.getIdPostNavega());
                Alert alert = new Alert(Alert.AlertType.WARNING, "Você realmente deseja apagar este comentário?", ButtonType.OK, ButtonType.CANCEL);
                alert.showAndWait();
                if(alert.getResult()==ButtonType.OK) {
                    Acesso.apagaPostagem(p);
                    criaListViewPostagemUser(Acesso.obtemListPostsPorUser(DefinicoesPadrao.getInstance().getUsuarioLogado().getId()));
                }
                if(alert.getResult()==ButtonType.CANCEL)
                {
                    alert.close();
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    };

    EventHandler<ActionEvent> metodoPost = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent actionEvent){
            CustomControlPerfil c = (CustomControlPerfil) ((Button) actionEvent.getSource()).getParent().getParent();
            HelperTelas.getInstance().setIdPostNavega(c.getIdPostNavega());
            HelperTelas.getInstance().IrParaTela(rootPane, "VisualizaPost.fxml");

        }
    };

    public void criaListViewPostagemUser (ArrayList posts) throws SQLException {

        List<CustomControlPerfil> list = new ArrayList<CustomControlPerfil>();

        for (var p : posts) {

            if(true) {
                Postagem post = (Postagem) p;
                list.add(new CustomControlPerfil(post,metodoPost,metodoApagar,metodoEditar));
            }
        }

        ObservableList<CustomControlPerfil> myObservableList = FXCollections.observableList(list);
        pnPostsUser.setItems(myObservableList);
    }

}