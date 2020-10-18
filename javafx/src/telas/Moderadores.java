package telas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class Moderadores {

    @FXML
    private AnchorPane rootPane;

    public void btnDenuncias() {
        HelperTelas.getInstance().IrParaTela(rootPane, "Denuncias.fxml");
    }
    public void btnValidacoes() {
        HelperTelas.getInstance().IrParaTela(rootPane, "ValidacoesDoc.fxml");
    }

    public void btnVoltarAction(ActionEvent actionEvent) {
        HelperTelas.getInstance().VoltarTela(rootPane);
    }
}
