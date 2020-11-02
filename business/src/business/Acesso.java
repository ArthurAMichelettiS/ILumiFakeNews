
package business;

import business.Log.ControleAuditoria;
import comum.Postagem;
import comum.Usuario;
import comum.enums.TipoUsuario;
import dao.basis.DAO;
import dao.enums.EntidadeDAO;
import javafx.scene.control.Alert;

import java.io.*;
import java.sql.SQLException;


public class Acesso {

    public static Usuario validaLogin(String email, String senha) throws SQLException {

        DAO dao = EntidadeDAO.USUARIO.getEntidadeDAO();

        Usuario encontrado = (Usuario) dao.localiza(email);
        if(encontrado == null || !senha.equals(encontrado.getSenha())){
            return null;
        }
        return encontrado;
    }

    public static void validaNovaSenha (String senha, String senhaconf) throws Exception {
       try{
        if (senha != senhaconf || senha == null)
        {
            throw new Exception();
        }
    } catch (Exception erro) {
        new Alert(Alert.AlertType.ERROR, "Há valores incorretos!").showAndWait();
    }
    }

    public static Object[] listaDadosUsuario() throws SQLException {
        DAO dados = EntidadeDAO.USUARIO.getEntidadeDAO();
        return dados.lista().toArray();
    }


    public static void enviaDadosUsuario(Usuario u) throws SQLException{
        DAO dao = EntidadeDAO.USUARIO.getEntidadeDAO();
        dao.Insere(u);
        ControleAuditoria.getInstance().AddAuditoria("Usuario salvo: " + u.getEmail());
    }

    public static void alterarDadosUsuario(Usuario u)  throws SQLException{
        DAO dao = EntidadeDAO.USUARIO.getEntidadeDAO();
        dao.Alter(u);
    }


    public static void enviaPost (Postagem p) throws SQLException{
        DAO dao = EntidadeDAO.POSTAGEM.getEntidadeDAO();
        dao.Insere(p);
    }

    public static void enviaPostCientifico (Postagem pc) throws SQLException {
        DAO dao = EntidadeDAO.POSTAGEM.getEntidadeDAO();
        dao.Insere(pc);
    }
    
    public static boolean ehModeradorLogado(){
        return DefinicoesPadrao.getInstance().getTipoUsuario() == TipoUsuario.MODERADOR;
    }
    public static boolean ehComumLogado(){
        return DefinicoesPadrao.getInstance().getTipoUsuario() == TipoUsuario.COMUM;
    }
    public static boolean ehPesquisadorLogado(){
        return DefinicoesPadrao.getInstance().getTipoUsuario() == TipoUsuario.PESQUISADOR;
    }
    public static boolean ehLogado(){
        return DefinicoesPadrao.getInstance().getTipoUsuario() != null;
    }

    public static byte[] imgToBytes(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10241];
        for (int readNum; (readNum = fis.read(buf)) != -1; ) {
            bos.write(buf, 0, readNum);
        }
        return bos.toByteArray();
    }

}
