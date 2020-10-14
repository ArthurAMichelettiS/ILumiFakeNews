import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class CriarPost {

    public void salvarPostagem(ActionEvent actionEvent) throws IOException {
        /*Parent root = FXMLLoader.load(getClass().getResource("CriarPost.fxml"));
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Criar Post");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();*/

        //salva infografico, poe no post

        final Node source = (Node) actionEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void voltarFeed(ActionEvent actionEvent) throws IOException {
        /*Parent root = FXMLLoader.load(getClass().getResource("CriarPost.fxml"));
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Criar Post");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();*/

        //salva infografico, poe no post

        final Node source = (Node) actionEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void fazerInfografico(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CriarInfografico.fxml"));
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Criar infográfico");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();

        //salva infografico, poe no post

        /*final Node source = (Node) actionEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();*/
    }
}
