package telas;

import business.Acesso;
import comum.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class CriarCadastro {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtSenha;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtCPF;

    @FXML
    private DatePicker dpNiver;

    @FXML
    private CheckBox chRes;

    @FXML
    private ComboBox cbGen;

    @FXML
    private ComboBox cbPais;

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

    public void cadastraUser(ActionEvent actionEvent) throws IOException, SQLException {
        //salvar cadastro
        Usuario d = new Usuario();

        d.setEmail(txtEmail.getText());
        d.setSenha(txtSenha.getText());
        d.setNascimento(dpNiver.getValue());
        d.setCPF(txtCPF.getText());
        d.setPais(cbPais.getValue().toString());
        d.setGenero(cbGen.getValue().toString());

        Acesso.enviaDadosUsuario(d);

        HelperTelas.getInstance().VoltarTela(rootPane);
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
}


