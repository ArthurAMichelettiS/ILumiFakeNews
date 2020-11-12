package telas;

import business.Acesso;
import business.DefinicoesPadrao;
import comum.Usuario;
import helper.HelperTelas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField txtSenha;

    @FXML
    private TextField txtEmail;


    public void abreCadastro(ActionEvent actionEvent) throws IOException {
        HelperTelas.getInstance().IrParaTela(rootPane, "CriarCadastro.fxml");
    }

    public void btnVoltarAction(ActionEvent actionEvent) {
        HelperTelas.getInstance().VoltarTela(rootPane);
    }

    public void fazLogin(ActionEvent actionEvent) throws IOException {

        //loga user
        try{
            Usuario logando = Acesso.validaLogin(txtEmail.getText(), txtSenha.getText());
            if(logando != null){
                DefinicoesPadrao.getInstance().setUsuarioLogado(logando);
                HelperTelas.getInstance().VoltarTela(rootPane);
            }
            else{
                new Alert(Alert.AlertType.ERROR, "Login Invalido!").showAndWait();
            }
        }
        catch (SQLException erro) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, erro);
        }

    }
}

