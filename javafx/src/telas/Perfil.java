package telas;

import business.DefinicoesPadrao;
import business.Acesso;
import comp.CustomControlPerfil;
import comp.CustomControlPost;
import comum.Postagem;
import comum.Usuario;
import helper.HelperTelas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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

        if(user.getImagem() != null && user.getImagem().length!=0){
            ivProfile.setImage(Acesso.bytesToImg(user.getImagem()));
        }

        criaListViewPostagemUser(Acesso.obtemListPostsPorUser(user.getId()));
    }

    public void btnVoltarAction(ActionEvent actionEvent) {
        HelperTelas.getInstance().VoltarTela(rootPane);
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

    EventHandler<ActionEvent> onActionVerPostagem = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent actionEvent){
            CustomControlPerfil c = (CustomControlPerfil) ((Button) actionEvent.getSource()).getParent().getParent();
            HelperTelas.getInstance().setIdPostNavega(c.getIdPostNavega());
            HelperTelas.getInstance().IrParaTela(rootPane, "VisualizaPost.fxml");
        }
    };

    EventHandler<ActionEvent> onActionVerPerfil = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent actionEvent){
            CustomControlPerfil c = (CustomControlPerfil) ((Button) actionEvent.getSource()).getParent().getParent();
            HelperTelas.getInstance().setIdPerfilNavega(c.getIdPerfilNavega());
            HelperTelas.getInstance().IrParaTela(rootPane, "Perfil.fxml");
        }
    };

    public void criaListViewPostagemUser (ArrayList posts) throws SQLException {

        List<CustomControlPerfil> list = new ArrayList<CustomControlPerfil>();

        for (var p : posts) {

            if(true) {
                Postagem post = (Postagem) p;
                list.add(new CustomControlPerfil(post, onActionVerPerfil, onActionVerPostagem));
            }
        }

        ObservableList<CustomControlPerfil> myObservableList = FXCollections.observableList(list);
        pnPostsUser.setItems(myObservableList);
    }

}