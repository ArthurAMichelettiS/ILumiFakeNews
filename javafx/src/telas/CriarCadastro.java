package telas;

import business.Acesso;
import static business.ValidacoesJavafxMascara.mascaraEmail;
import comum.Usuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;



public class CriarCadastro {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtSenha;

    @FXML
    private TextField txtSenhaConf;

    @FXML
    private TextField txtEmail;

    @FXML
    private DatePicker dpNiver;

    @FXML
    private ComboBox cbGen;

    @FXML
    private ComboBox cbPais;

    @FXML
    private ImageView ivProfile;

    private byte[] imgBytes;

    @FXML
    private void initialize() {
        String[] countryCodes = java.util.Locale.getISOCountries();

        for (String countryCode : countryCodes) {
            Locale locale = new Locale("", countryCode);
            String name = locale.getDisplayCountry();
            cbPais.getItems().add(name);
        }
        cbGen.getItems().addAll("Masculino", "Feminino", "Prefiro Não Dizer");
        mascaraEmail(txtEmail);
    }

    public void cadastraUser(ActionEvent actionEvent) {

        Usuario d = new Usuario();

        try {
            Acesso.validaNovaSenha(txtSenha.getText().trim(), txtSenhaConf.getText().trim());
            Acesso.validaDataNasc(dpNiver.getValue());

            d.setEmail(txtEmail.getText());
            d.setNome(txtNome.getText());
            d.setSenha(txtSenha.getText());
            d.setNascimento(dpNiver.getValue());
            d.setPais(cbPais.getValue().toString());
            d.setGenero(cbGen.getValue().toString());
            d.setIdTipoDeUsuario(0);
            d.setImagem(imgBytes);

            Acesso.enviaDadosUsuario(d);
            HelperTelas.getInstance().VoltarTela(rootPane);
        } catch (SQLException erro) {
            new Alert(Alert.AlertType.ERROR, "Algo de errado ao salvar!").showAndWait();
        } catch (Exception erro) {
            new Alert(Alert.AlertType.ERROR, "Há valores incorretos!").showAndWait();
        }
    }

    public void voltaLogin(ActionEvent actionEvent) throws IOException {

        HelperTelas.getInstance().VoltarTela(rootPane);
    }

    public void ckValidacoes(ActionEvent actionEvent) throws IOException {
        final CheckBox source = (CheckBox) actionEvent.getSource();

        if (source.isSelected()) {
            Parent root = FXMLLoader.load(getClass().getResource("ArquivosParaValidacoes.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Validações");
            primaryStage.setScene(new Scene(root));

            final Node src = (Node) actionEvent.getSource();
            final Stage stage = (Stage) src.getScene().getWindow();
            primaryStage.initOwner(stage);
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.showAndWait();
            //HelperTelas.getInstance().IrParaTela(rootPane, "ArquivosParaValidacoes.fxml");
        }
    }

    public void selecionaImagem(ActionEvent actionEvent) {
        try {
            Stage primaryStage = new Stage();
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            Image image = new Image(selectedFile.toURI().toString());
            ivProfile.setImage(image);
            imgBytes = Acesso.imgToBytes(selectedFile);
        } catch (Exception ex) {
            //nothing
        }
    }
}



