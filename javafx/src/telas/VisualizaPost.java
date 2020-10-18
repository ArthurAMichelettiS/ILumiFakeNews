package telas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class VisualizaPost {

    @FXML
    private AnchorPane rootPane;

    public void btnVoltarAction(ActionEvent actionEvent) throws IOException {

        HelperTelas.getInstance().VoltarTela(rootPane);
    }
}
