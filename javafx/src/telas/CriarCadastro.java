package telas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class CriarCadastro {

    @FXML
    private AnchorPane rootPane;

    public void cadastraUser(ActionEvent actionEvent) throws IOException {

        //salvar cadastro

        HelperTelas.getInstance().VoltarTela(rootPane);
    }

    public void voltaLogin(ActionEvent actionEvent) throws IOException {

        HelperTelas.getInstance().VoltarTela(rootPane);
    }

    public void ckValidacoes(ActionEvent actionEvent) throws IOException {
        final CheckBox source = (CheckBox) actionEvent.getSource();

        if(source.isSelected()){
            Parent root = FXMLLoader.load(getClass().getResource("ArquivosParaValidacoes.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Validações");
            primaryStage.setScene(new Scene(root));

            final Node src = (Node) actionEvent.getSource();
            final Stage stage = (Stage) src.getScene().getWindow();
            primaryStage.initOwner(stage);
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.showAndWait();
            //HelperTelas.getInstance().IrParaTela(rootPane, "ArquivosParaValidacoes.fxml");
        }
    }
}


