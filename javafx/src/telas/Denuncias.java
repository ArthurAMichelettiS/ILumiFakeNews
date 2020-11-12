package telas;

import helper.HelperTelas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class Denuncias {

    @FXML
    private AnchorPane rootPane;

    public void irProProximo(){
        //altera o texto do Text Area para o próximo comentário denunciado
    }

    public void voltarModeradores(ActionEvent actionEvent){

        HelperTelas.getInstance().VoltarTela(rootPane);
    }

}
