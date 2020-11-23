package telas;

import business.Acesso;
import comum.Denuncia;
import comum.Postagem;
import helper.HelperTelas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;
import java.util.List;

public class ValidacoesDenuncia {

    @FXML
    TextArea txtDenunciaValidar;

    @FXML
    TextArea txtPostDenunciado;

    @FXML
    AnchorPane rootPane;

    @FXML
    CheckBox cbxAprovadosValidaDoc;

    @FXML
    protected void initialize() throws SQLException {
        listaDenuncias = Acesso.obtemDenuncias();

        Postagem p =  Acesso.obtemPost(listaDenuncias.get(position).getIdPost());
        txtPostDenunciado.setText(p.getConteudo());
        txtDenunciaValidar.setText(listaDenuncias.get(position).getDescricao());
    }

    public void btnVoltarAction(ActionEvent actionEvent) {
        HelperTelas.getInstance().VoltarTela(rootPane);
    }

    List<Denuncia> listaDenuncias;
    int position = 0;

    public void alteraTexto() throws SQLException {

        Postagem p = new Postagem();

        if (cbxAprovadosValidaDoc.isSelected()) {
            p.setId(listaDenuncias.get(position).getIdPost());
           Acesso.apagaPostagem(p);
        }

        if (position < listaDenuncias.size() - 1) {
            position += 1;
        }

        p =  Acesso.obtemPost(listaDenuncias.get(position).getIdPost());
        txtPostDenunciado.setText(p.getConteudo());
        txtDenunciaValidar.setText(listaDenuncias.get(position).getDescricao());
    }
}
