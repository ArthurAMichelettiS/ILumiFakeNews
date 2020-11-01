package telas;

import business.Acesso;
import comum.Postagem;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class CriarPostCientifico {


    @FXML
    private TextField txtTitulo;

    @FXML
    private TextArea txtConteudo;

    @FXML
    private AnchorPane rootPane;

    public ImageView selectedImage;

    public void salvarPostagem(ActionEvent actionEvent) {

        //salva post e stuff

        try {
            Postagem pc = new Postagem();
            pc.setTitulo(txtTitulo.getText());
            pc.setConteudo(txtConteudo.getText());
            Acesso.enviaPostCientifico(pc);
            HelperTelas.getInstance().VoltarTela(rootPane);
        } catch (SQLException erro) {
            new Alert(Alert.AlertType.ERROR, "Algo de errado ao salvar!").showAndWait();
        } catch (Exception erro) {
            new Alert(Alert.AlertType.ERROR, "Há valores incorretos!").showAndWait();
        }

    }

    public void anexar(ActionEvent actionEvent) throws IOException {

        JFileChooser chooseFile = new JFileChooser();
        JPanel test = new JPanel();
        chooseFile.setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop"));
        int result = chooseFile.showOpenDialog(test);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooseFile.getSelectedFile();
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            selectedImage.setImage(image);
        }
    }

    public void voltarFeed(ActionEvent actionEvent) {

        HelperTelas.getInstance().VoltarTela(rootPane);
    }
}
