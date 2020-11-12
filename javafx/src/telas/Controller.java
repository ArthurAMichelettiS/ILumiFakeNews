package telas;

import helper.HelperTelas;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    public void abreFeed(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Feed.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Feed");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        HelperTelas.getInstance().setTelaInicial("Feed.fxml");
    }

    public void abreModera(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Moderadores.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Moderadores");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        HelperTelas.getInstance().setTelaInicial("Moderadores.fxml");
    }

    /* fecha esta janela
    final Node source = (Node) actionEvent.getSource();
    final Stage stage = (Stage) source.getScene().getWindow();
    stage.close();*/


    //load e show
    /*Parent root = FXMLLoader.load(getClass().getResource("ArquivosParaValidacoes.fxml"));
    Stage primaryStage = new Stage();
    primaryStage.setTitle("Validações");
    primaryStage.setScene(new Scene(root));
    primaryStage.show();*/


}

