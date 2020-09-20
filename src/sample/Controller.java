package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller {
    public void abreCriarInfográfico()throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("CriarInfográfico.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Criar Infográfico");
        primaryStage.setScene(new Scene(root, 790.0, 517.0));
        primaryStage.show();
    }
}

