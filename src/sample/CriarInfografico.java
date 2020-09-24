package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CriarInfografico {

    public ImageView imageBox;

    public void carregaImagem() throws IOException {
        JFileChooser chooseFile = new JFileChooser();
        JPanel test = new JPanel();
        chooseFile.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = chooseFile.showOpenDialog(test);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooseFile.getSelectedFile();
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageBox.setImage(image);
        }
    }

    public void voltarPostagem(ActionEvent actionEvent) throws IOException {
         /*Parent root = FXMLLoader.load(getClass().getResource("CriarPost.fxml"));
                Stage primaryStage = new Stage();
                primaryStage.setTitle("CriarPost");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();*/

        final Node source = (Node) actionEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void salvarInfografico(ActionEvent actionEvent) throws IOException {
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
