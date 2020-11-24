package telas;

import business.Acesso;
import business.DefinicoesPadrao;
import comum.Postagem;
import helper.HelperTelas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.regex.Pattern;

public class CriarPostCientifico {

    @FXML
    private TextField txtTitulo;

    @FXML
    private TextArea txtConteudo;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextArea txtTags;

    public byte[] person_image;

    @FXML
    public ImageView ivAnexo;

    public void salvarPostagem(ActionEvent actionEvent) {

        //salva post e stuff

        try {
            Postagem pc = new Postagem();
            pc.setTitulo(txtTitulo.getText());
            pc.setConteudo(txtConteudo.getText());
            pc.setStringTags(Arrays.asList(txtTags.getText().split(Pattern.quote(" "))));
            pc.setImagem(person_image);
            pc.setIdTipoPost(1);
            pc.setIdUser(DefinicoesPadrao.getInstance().getUsuarioLogado().getId());
            Acesso.enviaPostCientifico(pc);
            HelperTelas.getInstance().VoltarTela(rootPane);
        } catch (SQLException erro) {
            new Alert(Alert.AlertType.ERROR, "Algo de errado ao salvar!").showAndWait();
        } catch (Exception erro) {
            new Alert(Alert.AlertType.ERROR, "Há valores incorretos!").showAndWait();
        }

    }

    public void anexar(ActionEvent actionEvent) throws IOException {

        Stage primaryStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        Image image = new Image(selectedFile.toURI().toString());
        ivAnexo.setImage(image);

        person_image = Acesso.imgToBytes(selectedFile);

    }

    public void voltarFeed(ActionEvent actionEvent) {
        HelperTelas.getInstance().VoltarTela(rootPane);
    }
}