package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {
    public void abreCadastro(ActionEvent actionEvent) throws IOException {

            Parent root = FXMLLoader.load(getClass().getResource("CriarCadastro.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Criar Cadastro");
            primaryStage.setScene(new Scene(root, 790.0, 517.0));
            primaryStage.setMaximized(true);
            primaryStage.show();
            final Node source = (Node) actionEvent.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }

