package telas;

import business.Acesso;
import business.DefinicoesPadrao;
import comum.Denuncia;
import comum.Postagem;
import comum.Status;
import helper.HelperTelas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Denuncias {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextArea txtDescricao;
    @FXML
    private void initialize() throws SQLException {
        final int max = 1000;
        txtDescricao.setTextFormatter(new TextFormatter<String>(change -> change.getControlNewText().length() <= max ? change : null));
    }
    public void salvarDenuncia(ActionEvent actionEvent){

        try{
            Denuncia d = new Denuncia();
            d.setDescricao(txtDescricao.getText());
            d.setIdStatus(DefinicoesPadrao.getInstance().getStatus().getId());
            d.setIdPost(DefinicoesPadrao.getInstance().getPostagem().getId());
            Acesso.enviaDenuncia(d);
            HelperTelas.getInstance().VoltarTela(rootPane);

            Status s = new Status();
            s.setDescricao("Não avaliado");
            Acesso.enviaStatus(s);
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
