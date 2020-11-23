package telas;

import business.Acesso;
import business.DefinicoesPadrao;
import comum.Denuncia;
import helper.HelperTelas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;

public class Denuncias {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextArea txtAreaDenuncias;
    @FXML
    private void initialize() throws SQLException {
        final int max = 1000;
        txtAreaDenuncias.setTextFormatter(new TextFormatter<String>(change -> change.getControlNewText().length() <= max ? change : null));
    }
    public void salvarDenuncia(ActionEvent actionEvent){

        try{

            Denuncia d = new Denuncia();
            d.setDescricao(txtAreaDenuncias.getText());
            d.setStatusDenuncia("Não Avaliado");
            d.setIdPost(DefinicoesPadrao.getInstance().getPostagem().getId());
            Acesso.enviaDenuncia(d);
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
