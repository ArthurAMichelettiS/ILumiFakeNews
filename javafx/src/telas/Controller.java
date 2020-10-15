package telas;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    public void abreCriarInfografico()throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("CriarInfografico.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Criar Infográfico");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void abreFeed(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Feed.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Feed");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void abreModera(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Moderadores.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Moderadores");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}

