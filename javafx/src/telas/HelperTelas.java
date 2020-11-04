package telas;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Stack;

public class HelperTelas {

    private static HelperTelas instance;

    private HelperTelas(){
        telasAcessadas = new Stack<String>();
    }

    public static HelperTelas getInstance(){
        if(instance == null){
            instance = new HelperTelas();
        }
        return instance;
    }

    public int getIdPostNavega() {
        return idPostNavega;
    }

    public void setIdPostNavega(int idPostNavega) {
        this.idPostNavega = idPostNavega;
    }

    private int idPostNavega;

    public void setTelaInicial(String tela){
        telaAtual = tela;
    }

    private Stack<String> telasAcessadas;

    private String telaAtual;

    public void VoltarTela(AnchorPane painelOrigem){

        if(telasAcessadas.isEmpty())
            return;

        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource(telasAcessadas.peek()));
            painelOrigem.getStylesheets().clear();
            painelOrigem.getChildren().setAll(pane);
            telaAtual = telasAcessadas.pop();
        }
        catch (Exception erro){
            //log
        }

    }

    public void IrParaTela(AnchorPane painelOrigem, String destino){

        try{
            //if(!destino.substring(destino.length() - 4).equals(".fxml"))
              //  destino = destino + ".fxml";
            URL s = getClass().getResource(destino);
            AnchorPane pane = FXMLLoader.load(s);
            painelOrigem.getChildren().setAll(pane);
            telasAcessadas.add(telaAtual);
            telaAtual = destino;
        }
        catch (Exception erro){
            //log
            //System.out.printf(erro.getMessage());
        }
    }
}
