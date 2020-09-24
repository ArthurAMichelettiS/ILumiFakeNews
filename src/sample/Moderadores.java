package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Moderadores {
    public void btnDenuncias() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Denuncias.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Denúncias");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    public void btnValidacoes() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ValidacoesDoc.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Validação de documentos");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void voltar(ActionEvent actionEvent) throws IOException {
        final Node source = (Node) actionEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
