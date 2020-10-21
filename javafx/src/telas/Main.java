package telas;

import business.Log.ControleAuditoria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Inicio protótipo");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        ControleAuditoria.getInstance().iniciaThread();
    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}
