package sample;


import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;


import java.io.IOException;

public class Denuncias {

    public void irProProximo(){
        //altera o texto do Text Area para o próximo comentário denunciado
    }

    public void voltarModeradores(ActionEvent actionEvent) throws IOException {
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

}
