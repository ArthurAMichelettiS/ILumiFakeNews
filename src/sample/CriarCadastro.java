package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class CriarCadastro {
    public void cadastraUser(ActionEvent actionEvent) throws IOException {
            /*Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Login");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();*/

            //salvar cadastro

            final Node source = (Node) actionEvent.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }

        public void voltaLogin(ActionEvent actionEvent) throws IOException {
                /*Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Login");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();*/

                final Node source = (Node) actionEvent.getSource();
                final Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
        }
}


