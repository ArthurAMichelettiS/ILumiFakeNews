package telas;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ArquivosParaValidacoes {
    public ImageView imageDocCFoto;
    public ImageView imageComprovantePesquisador;

    @FXML
    private AnchorPane rootPane;

    public void carregaDocFoto() throws IOException {
        JFileChooser chooseFile = new JFileChooser();
        JPanel test = new JPanel();
        chooseFile.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = chooseFile.showOpenDialog(test);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooseFile.getSelectedFile();
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageDocCFoto.setImage(image);
        }

    }

    public void carregaComprovante() throws IOException {
        JFileChooser chooseFile = new JFileChooser();
        JPanel test = new JPanel();
        chooseFile.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = chooseFile.showOpenDialog(test);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooseFile.getSelectedFile();
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageComprovantePesquisador.setImage(image);
        }
    }

    public void voltar(ActionEvent actionEvent) throws IOException {
        //HelperTelas.getInstance().VoltarTela(rootPane);

        final Node source = (Node) actionEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
