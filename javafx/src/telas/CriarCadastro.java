package telas;

import business.Acesso;
import comum.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class CriarCadastro{

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

    @FXML
    private void initialize() {
        cbPais.getItems().addAll("Afeganistão",
                "África do Sul",
                "Albânia",
                "Alemanha",
                "Andorra",
                "Angola",
                "Antiga e Barbuda",
                "Arábia Saudita",
                "Argélia",
                "Argentina",
                "Arménia",
                "Austrália",
                "Áustria",
                "Azerbaijão",
                "Bahamas",
                "Bangladexe",
                "Barbados",
                "Barém",
                "Bélgica",
                "Belize",
                "Benim",
                "Bielorrússia",
                "Bolívia",
                "Bósnia e Herzegovina",
                "Botsuana",
                "Brasil",
                "Brunei",
                "Bulgária",
                "Burquina Faso",
                "Burúndi",
                "Butão",
                "Cabo Verde",
                "Camarões",
                "Camboja",
                "Canadá",
                "Catar",
                "Cazaquistão",
                "Chade",
                "Chile",
                "China",
                "Chipre",
                "Colômbia",
                "Comores",
                "Congo-Brazzaville",
                "Coreia do Norte",
                "Coreia do Sul",
                "Cosovo",
                "Costa do Marfim",
                "Costa Rica",
                "Croácia",
                "Cuaite",
                "Cuba",
                "Dinamarca",
                "Dominica",
                "Egito",
                "Emirados Árabes Unidos",
                "Equador",
                "Eritreia",
                "Eslováquia",
                "Eslovénia",
                "Espanha",
                "Estado da Palestina",
                "Estados Unidos",
                "Estónia",
                "Etiópia",
                "Fiji",
                "Filipinas",
                "Finlândia",
                "França",
                "Gabão",
                "Gâmbia",
                "Gana",
                "Geórgia",
                "Granada",
                "Grécia",
                "Guatemala",
                "Guiana",
                "Guiné",
                "Guiné Equatorial",
                "Guiné-Bissau",
                "Haiti",
                "Honduras",
                "Hungria",
                "Iémen",
                "Ilhas Marechal",
                "Índia",
                "Indonésia",
                "Irão",
                "Iraque",
                "Irlanda",
                "Islândia",
                "Israel",
                "Itália",
                "Jamaica",
                "Japão",
                "Jibuti",
                "Jordânia",
                "Laus",
                "Lesoto",
                "Letónia",
                "Líbano",
                "Libéria",
                "Líbia",
                "Listenstaine",
                "Lituânia",
                "Luxemburgo",
                "Macedónia",
                "Madagáscar",
                "Malásia",
                "Maláui",
                "Maldivas",
                "Mali",
                "Malta",
                "Marrocos",
                "Maurícia",
                "Mauritânia",
                "México",
                "Mianmar",
                "Micronésia",
                "Moçambique",
                "Moldávia",
                "Mónaco",
                "Mongólia",
                "Montenegro",
                "Namíbia",
                "Nauru",
                "Nepal",
                "Nicarágua",
                "Níger",
                "Nigéria",
                "Noruega",
                "Nova Zelândia",
                "Omã",
                "Países Baixos",
                "Palau",
                "Panamá",
                "Papua Nova Guiné",
                "Paquistão",
                "Paraguai",
                "Peru",
                "Polónia",
                "Portugal",
                "Quénia",
                "Quirguistão",
                "Quiribáti",
                "Reino Unido",
                "República Centro-Africana",
                "República Checa",
                "República Democrática do Congo",
                "República Dominicana",
                "Roménia",
                "Ruanda",
                "Rússia",
                "Salomão",
                "Salvador",
                "Samoa",
                "Santa Lúcia",
                "São Cristóvão e Neves",
                "São Marinho",
                "São Tomé e Príncipe",
                "São Vicente e Granadinas",
                "Seicheles",
                "Senegal",
                "Serra Leoa",
                "Sérvia",
                "Singapura",
                "Síria",
                "Somália",
                "Sri Lanca",
                "Suazilândia",
                "Sudão",
                "Sudão do Sul",
                "Suécia",
                "Suíça",
                "Suriname",
                "Tailândia",
                "Taiuã",
                "Tajiquistão",
                "Tanzânia",
                "Timor-Leste",
                "Togo",
                "Tonga",
                "Trindade e Tobago",
                "Tunísia",
                "Turcomenistão",
                "Turquia",
                "Tuvalu",
                "Ucrânia",
                "Uganda",
                "Uruguai",
                "Usbequistão",
                "Vanuatu",
                "Vaticano",
                "Venezuela",
                "Vietname",
                "Zâmbia",
                "Zimbábue"
                );
        cbGen.getItems().addAll("Masculino","Feminino","Prefiro Não Dizer");
    }

    public void cadastraUser(ActionEvent actionEvent) {

        Usuario d = new Usuario();

        try{
            d.setEmail(txtEmail.getText());
            d.setNome(txtNome.getText());
            d.setSenha(txtSenha.getText());
            d.setNascimento(dpNiver.getValue());
            d.setPais(cbPais.getValue().toString());
            d.setGenero(cbGen.getValue().toString());

            Acesso.enviaDadosUsuario(d);
            HelperTelas.getInstance().VoltarTela(rootPane);
        }
        catch (SQLException erro){
            new Alert(Alert.AlertType.ERROR, "Algo de errado ao salvar!").showAndWait();
        }
        catch (Exception erro){
            new Alert(Alert.AlertType.ERROR, "Há valores incorretos!").showAndWait();
        }
    }

    public void voltaLogin(ActionEvent actionEvent) throws IOException {

        HelperTelas.getInstance().VoltarTela(rootPane);
    }

    public void ckValidacoes(ActionEvent actionEvent) throws IOException {
        final CheckBox source = (CheckBox) actionEvent.getSource();

        if(source.isSelected()){
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
        Stage primaryStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        Image image = new Image(selectedFile.toURI().toString());
        ivProfile.setImage(image);
        }

    }



