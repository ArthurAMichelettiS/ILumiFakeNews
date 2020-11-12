package telas;

import business.Log.ControleAuditoria;
import helper.HelperTelas;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("Feed.fxml"));
        primaryStage.setTitle("Inicio protótipo");
        primaryStage.setScene(new Scene(root));
        ControleAuditoria.getInstance().iniciaThread();
        HelperTelas.getInstance().setTelaInicial("Feed.fxml");
        primaryStage.show();
    }

    public static void main(String[] args) {
        try{
            Application.launch(args);
        }
        finally {
            ControleAuditoria.getInstance().pararThread();
        }
    }
}
