package telas;

import business.Acesso;
import business.DefinicoesPadrao;
import comp.CustomControlCom;
import comp.CustomControlPost;
import comp.HBoxButtonsCom;
import comp.HboxUsuario;
import comum.Comentario;
import comum.Postagem;
import comum.Usuario;
import helper.HelperTelas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class VisualizaPost {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private ImageView ivUser;

    @FXML
    private TextField txtTitulo;

    @FXML
    private TextArea txtConteudo;

    @FXML
    private TextField txtInsCom;

    @FXML
    private TextArea txtCom;

    @FXML
    public ListView<CustomControlCom> pnCom;

    @FXML
    private void initialize() throws SQLException {
        if(Acesso.ehLogado()){
            Usuario user = DefinicoesPadrao.getInstance().getUsuarioLogado();
            ivUser.setImage(Acesso.bytesToImg(user.getImagem()));
        }

        //post especifico a ser visualizado
        Postagem p = Acesso.obtemPost(HelperTelas.getInstance().getIdPostNavega());
        Comentario c = Acesso.obtemComentario(p.getId());

        txtTitulo.setText(p.getTitulo());
        txtConteudo.setText(p.getConteudo());
        criaListViewCom(Acesso.obtemListCom(p.getId()));

    }

    public void btnVoltarAction(ActionEvent actionEvent) {

        HelperTelas.getInstance().VoltarTela(rootPane);
    }

    public void btnComAction (ActionEvent actionEvent) throws SQLException {

        try {
            Acesso.validaCampoVazio(txtInsCom);
            Postagem p = Acesso.obtemPost(HelperTelas.getInstance().getIdPostNavega());
            Comentario c = new Comentario();
            c.setIdPost(p.getId());
            c.setConteudo(txtInsCom.getText());
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            c.setData(date);
            c.setIdUser(DefinicoesPadrao.getInstance().getUsuarioLogado().getId());
            Acesso.enviaComentario(c);
            HelperTelas.getInstance().VoltarTela(rootPane);
        } catch (SQLException erro) {
            new Alert(Alert.AlertType.ERROR, "Algo de errado ao salvar!").showAndWait();
        } catch (Exception erro) {
            new Alert(Alert.AlertType.ERROR, erro.getMessage()).showAndWait();
        }
    }

    public void criaListViewCom(ArrayList com) {

        List<CustomControlCom> list = new ArrayList<CustomControlCom>();

        for (var u : com) {
            Comentario c = (Comentario) u;
            list.add(new CustomControlCom(c, editarCom, excluirCom));
        }

        ObservableList<CustomControlCom> myObservableList = FXCollections.observableList(list);
        pnCom.setItems(myObservableList);
    }

    EventHandler<ActionEvent> editarCom = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent actionEvent){
            HBoxButtonsCom c = (HBoxButtonsCom) ((Button) actionEvent.getSource()).getParent();
        }
    };

    EventHandler<ActionEvent> excluirCom = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent actionEvent){
            HBoxButtonsCom c = (HBoxButtonsCom) ((Button) actionEvent.getSource()).getParent();
            //if()
                //Acesso.excluirCom;
        }
    };
}
