package telas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class Feed {

    @FXML
    private AnchorPane rootPane;

    public void FazLogin(ActionEvent actionEvent) {
        HelperTelas.getInstance().IrParaTela(rootPane, "Login.fxml");


        //test banco
        comum.Usuario d;
        try {
             d = (comum.Usuario)business.Acesso.listaDadosUsuario()[0];
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            d = null;
        }
        d.getLogin();

    }

    public void btnFazPostagem(ActionEvent actionEvent){
        HelperTelas.getInstance().IrParaTela(rootPane, "CriarPost.fxml");
    }

    public void btnVerPostagem(ActionEvent actionEvent){
        HelperTelas.getInstance().IrParaTela(rootPane, "VisualizaPost.fxml");
    }

    public void btnVerPerfil(ActionEvent actionEvent){
        HelperTelas.getInstance().IrParaTela(rootPane, "Perfil.fxml");
    }

}
