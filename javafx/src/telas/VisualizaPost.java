package telas;

import business.Acesso;
import business.DadosDaSecao;
import comp.CustomControlCom;
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
    public ListView<CustomControlCom> pnCom;

    @FXML
    public Label lbNome;

    @FXML
    public Button btnEnviaEdit;

    @FXML
    public ImageView ivPost;

    @FXML
    protected void initialize() throws SQLException {
        if(Acesso.ehLogado()){
            Usuario user = DadosDaSecao.getInstance().getUsuarioLogado();
            ivUser.setImage(Acesso.bytesToImg(user.getImagem()));
            lbNome.setText(user.getNome());
        }
        //post especifico a ser visualizado
        btnEnviaEdit.setVisible(false);
        Postagem p = Acesso.obtemPost(HelperTelas.getInstance().getIdPostNavega());
        Comentario c = Acesso.obtemComentario(p.getId());
        if(Acesso.ehLogado()) {
            if (DadosDaSecao.getInstance().getUsuarioLogado().getId() == p.getIdUser()) {
                txtConteudo.setEditable(true);
                txtTitulo.setEditable(true);
                btnEnviaEdit.setVisible(true);
            }
        }
        if(p.getImagemBytes()!=null){
            ivPost.setPreserveRatio(true);
            ivPost.setImage(Acesso.bytesToImg(p.getImagemBytes()));
            txtConteudo.setPrefWidth(500);
        }
        txtTitulo.setText(p.getTitulo());
        txtConteudo.setText(p.getConteudo());
        criaListViewCom(Acesso.obtemListCom(p.getId()));
    }

    public void btnVoltarAction(ActionEvent actionEvent) {

        HelperTelas.getInstance().VoltarTela(rootPane);
    }

    public void btnComAction (ActionEvent actionEvent) throws SQLException {

        if(!Acesso.ehLogado()){
            HelperTelas.getInstance().IrParaTela(rootPane, "Login.fxml");
            return;
        }

        try {
            Acesso.validaCampoVazio(txtInsCom);
            Postagem p = Acesso.obtemPost(HelperTelas.getInstance().getIdPostNavega());
            Comentario c = new Comentario();
            c.setIdPost(p.getId());
            c.setConteudo(txtInsCom.getText());
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            c.setData(date);
            c.setIdUser(DadosDaSecao.getInstance().getUsuarioLogado().getId());
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
        boolean y = true;
        for (var u : com) {
            Comentario c = (Comentario) u;
            if (Acesso.ehLogado() == true) {
                if (DadosDaSecao.getInstance().getUsuarioLogado().getId() == c.getIdUser()){
                    y = true;
                }
                else{
                    y = false;}
            }
            else if(Acesso.ehLogado() == false){
                y = false;}
            list.add(new CustomControlCom(c, editarCom, excluirCom, y));
            }

        ObservableList<CustomControlCom> myObservableList = FXCollections.observableList(list);
        pnCom.setItems(myObservableList);
    }

    EventHandler<ActionEvent> editarCom = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent actionEvent){
            try {
                CustomControlCom c = (CustomControlCom) ((Button) actionEvent.getSource()).getParent().getParent();
                Comentario com = Acesso.obtemComentario(c.getIdCom());
                com.setConteudo(c.getText());
                Alert alert = new Alert(Alert.AlertType.WARNING, "Você realmente deseja editar este comentário?", ButtonType.OK, ButtonType.CANCEL);
                alert.showAndWait();
                if(alert.getResult()==ButtonType.OK) {
                    Acesso.alteraComentario(com);
                    Postagem p = Acesso.obtemPost(HelperTelas.getInstance().getIdPostNavega());
                    criaListViewCom(Acesso.obtemListCom(p.getId()));
                }
                if(alert.getResult()==ButtonType.CANCEL)
                {
                    alert.close();
                    Postagem p = Acesso.obtemPost(HelperTelas.getInstance().getIdPostNavega());
                    criaListViewCom(Acesso.obtemListCom(p.getId()));
                }
            }
            catch (SQLException erro) {
                new Alert(Alert.AlertType.ERROR, "Algo de errado ao salvar!").showAndWait();
            }
        }
    };

    EventHandler<ActionEvent> excluirCom = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent actionEvent){
            try {
                CustomControlCom c = (CustomControlCom) ((Button) actionEvent.getSource()).getParent().getParent();
                Comentario com = null;
                com = Acesso.obtemComentario(c.getIdCom());
                Alert alert = new Alert(Alert.AlertType.WARNING, "Você realmente deseja apagar este comentário?", ButtonType.OK, ButtonType.CANCEL);
                alert.showAndWait();
                if(alert.getResult()==ButtonType.OK) {
                    Acesso.apagaComentario(com);
                    Postagem p = Acesso.obtemPost(HelperTelas.getInstance().getIdPostNavega());
                    criaListViewCom(Acesso.obtemListCom(p.getId()));
                }
                if(alert.getResult()==ButtonType.CANCEL)
                {
                    alert.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    };

    public void enviaEdit(ActionEvent actionEvent) throws SQLException {
        Postagem p = Acesso.obtemPost(HelperTelas.getInstance().getIdPostNavega());
        p.setConteudo(txtConteudo.getText());
        p.setTitulo(txtConteudo.getText());
        Acesso.alteraPostagem(p);
        HelperTelas.getInstance().VoltarTela(rootPane);
    }
}
