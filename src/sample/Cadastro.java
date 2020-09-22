package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Cadastro {
    public void cadastraUser(ActionEvent actionEvent) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Login");
            primaryStage.setScene(new Scene(root, 790.0, 517.0));
            primaryStage.show();
        }
    }

