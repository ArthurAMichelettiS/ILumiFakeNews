import java.io.IOException;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class CustomControl extends VBox {
    @FXML private TextField textField;

    public CustomControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "Feed.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public String getText() {
        return textProperty().get();
    }

    public void setText(String value) {
        textProperty().set(value);
    }

    public StringProperty textProperty() {
        return textField.textProperty();
    }

    // dentro do region:

    @FXML
    protected void getTitle() {
        //puxar titulo do banco
        //exibir titulo em label header
    }

    @FXML
    protected void getPost() {
        //puxar texto do post do banco
        //exibir titulo em label comum
    }

    @FXML
    protected void getTags() {
        //puxar tags do post do banco
        //exibir tags em label bright
    }

    @FXML
    protected void getAnexos() {
        //puxar anexo do post do banco
        //exibir anexos no fim do post com um border hbox
    }

    @FXML
    protected void getProximoPost() {
        //ao terminar o post carrega o proximo e exibi na tela
    }

}