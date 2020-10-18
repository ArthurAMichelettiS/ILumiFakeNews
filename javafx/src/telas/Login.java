package telas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Login {

    @FXML
    private AnchorPane rootPane;


    public void abreCadastro(ActionEvent actionEvent) throws IOException {
        HelperTelas.getInstance().IrParaTela(rootPane, "CriarCadastro.fxml");
    }

    public void btnVoltarAction(ActionEvent actionEvent) {
        HelperTelas.getInstance().VoltarTela(rootPane);
    }

    public void fazLogin(ActionEvent actionEvent) throws IOException {

        //loga user

        HelperTelas.getInstance().VoltarTela(rootPane);
    }
}

