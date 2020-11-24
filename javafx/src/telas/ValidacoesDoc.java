package telas;

import business.Acesso;
import comum.Usuario;
import helper.HelperTelas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;
import java.util.List;

public class ValidacoesDoc {

    List<Usuario> listaUsuariosDocs;
    int position = 0;

    @FXML
    private void initialize() throws SQLException {
        listaUsuariosDocs = Acesso.obtemUsuarioTipo3();
        if(listaUsuariosDocs.size()==0){
            new Alert(Alert.AlertType.ERROR, "Nenhum Documento a Validar").showAndWait();
            HelperTelas.getInstance().VoltarTela(rootPane);
        }
        else{
            imageDocCFoto.setImage(Acesso.bytesToImg(listaUsuariosDocs.get(position).getDocFotoByte()));
            imageComprovante.setImage(Acesso.bytesToImg(listaUsuariosDocs.get(position).getComprovantePesquisadorByte()));
        }
    }

    @FXML
    private AnchorPane rootPane;

    @FXML
    private ImageView imageDocCFoto;

    @FXML
    private ImageView imageComprovante;

    @FXML
    private CheckBox cbxAprovadosValidaDoc;

    public void btnVoltarAction(ActionEvent actionEvent) {
        HelperTelas.getInstance().VoltarTela(rootPane);
    }

    public void alteraImagem() throws SQLException {

        if (cbxAprovadosValidaDoc.isSelected()) {
        listaUsuariosDocs.get(position).setIdTipoDeUsuario(1);
        Acesso.alterarDadosUsuario(listaUsuariosDocs.get(position));
        }
        else {
            listaUsuariosDocs.get(position).setIdTipoDeUsuario(0);
            Acesso.alterarDadosUsuario(listaUsuariosDocs.get(position));
        }

        if (position < listaUsuariosDocs.size() - 1) {
            position += 1;
        }

        imageDocCFoto.setImage(Acesso.bytesToImg(listaUsuariosDocs.get(position).getDocFotoByte()));
        imageComprovante.setImage(Acesso.bytesToImg(listaUsuariosDocs.get(position).getComprovantePesquisadorByte()));

    }
}
