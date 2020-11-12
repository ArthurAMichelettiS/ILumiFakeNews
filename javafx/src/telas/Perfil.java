package telas;

import business.DefinicoesPadrao;
import business.Acesso;
import comum.Usuario;
import helper.HelperTelas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.sql.SQLException;


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
    private void initialize() throws SQLException {
        Usuario user;
        boolean ehEditavel = false;
        if(HelperTelas.getInstance().getIdPerfilNavega()==-1){
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

}
