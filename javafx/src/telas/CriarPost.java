package telas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class CriarPost {

    @FXML
    private AnchorPane rootPane;

    public void salvarPostagem(ActionEvent actionEvent){


        //salva post e stuff

        HelperTelas.getInstance().VoltarTela(rootPane);
    }

    public void voltarFeed(ActionEvent actionEvent){

        HelperTelas.getInstance().VoltarTela(rootPane);
    }

}
