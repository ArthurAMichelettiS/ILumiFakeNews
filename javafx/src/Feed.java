import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Feed {

    public void FazLogin(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void btnFazPostagem(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CriarPost.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Criar postagem");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void btnVerPostagem(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("VisualizaPost.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Postagem");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void btnVerPerfil(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Perfil.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Perfil");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
