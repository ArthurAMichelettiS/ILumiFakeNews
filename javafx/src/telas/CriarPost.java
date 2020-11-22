package telas;

import business.Acesso;
import business.DefinicoesPadrao;
import comum.Postagem;
import helper.HelperTelas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.regex.Pattern;

public class CriarPost {

    @FXML
    private TextField txtTituloPergunta;

    @FXML
    private TextArea txtConteudo;

    @FXML
    private TextArea txtTags;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private void initialize() throws SQLException {
        final int max = 1000;
        txtConteudo.setTextFormatter(new TextFormatter<String>(change -> change.getControlNewText().length() <= max ? change : null));
    }


    public void salvarPostagem(ActionEvent actionEvent){


        //salva post e stuff

        try{
            Postagem p = new Postagem();
            p.setTitulo(txtTituloPergunta.getText());
            p.setConteudo(txtConteudo.getText());
            p.setStringTags(Arrays.asList(txtTags.getText().split(Pattern.quote(" "))));
            p.setIdUser(DefinicoesPadrao.getInstance().getUsuarioLogado().getId());
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
