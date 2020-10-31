package telas;

import business.Acesso;
import comum.Postagem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class CriarPost {


    @FXML
    private TextField txtTituloPergunta;

    @FXML
    private TextArea txtConteudo;

    @FXML
    private AnchorPane rootPane;

    public void salvarPostagem(ActionEvent actionEvent){

        //salva post e stuff

        try{
            Postagem p = new Postagem();
            p.setTitulo(txtTituloPergunta.getText());
            p.setConteudo(txtConteudo.getText());
            Acesso.enviaPost(p);
            HelperTelas.getInstance().VoltarTela(rootPane);
        }
        catch (SQLException erro){
            new Alert(Alert.AlertType.ERROR, "Algo de errado ao salvar!").showAndWait();
        }
        catch (Exception erro){
            new Alert(Alert.AlertType.ERROR, "Há valores incorretos!").showAndWait();
        }
    }

    public void voltarFeed(ActionEvent actionEvent){

        HelperTelas.getInstance().VoltarTela(rootPane);
    }
}
