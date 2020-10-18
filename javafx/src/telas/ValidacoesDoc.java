package telas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class ValidacoesDoc {


    @FXML
    private AnchorPane rootPane;


    public void btnVoltarAction(ActionEvent actionEvent) {
        HelperTelas.getInstance().VoltarTela(rootPane);
    }
}
