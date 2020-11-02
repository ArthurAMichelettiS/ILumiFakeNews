package telas;

import business.Acesso;
import comum.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    private ImageView ivProfile;

    public void btnVoltarAction(ActionEvent actionEvent) {
        HelperTelas.getInstance().VoltarTela(rootPane);
    }

    public void editarCadastro (ActionEvent actionEvent) {

        Usuario user = new Usuario();


        try {

            user.setEmail(txtEmail.getText());
            user.setSenha(txtSenha.getText());
            user.setIdTipoDeUsuario(2);

            Acesso.enviaDadosUsuario(user);
            HelperTelas.getInstance().VoltarTela(rootPane);

        } catch (SQLException erro) {
            new Alert(Alert.AlertType.ERROR, "Algo de errado ao salvar!").showAndWait();
        } catch (Exception erro) {
            new Alert(Alert.AlertType.ERROR, "Há valores incorretos!").showAndWait();
        }
    }

}
