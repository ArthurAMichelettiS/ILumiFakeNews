
package business;

import business.Log.ControleAuditoria;
import comum.Postagem;
import comum.Usuario;
import comum.enums.TipoUsuario;
import dao.basis.DAO;
import dao.enums.EntidadeDAO;

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

    public static Object[] listaDadosUsuario() throws SQLException {
        DAO dados = EntidadeDAO.USUARIO.getEntidadeDAO();
        return dados.lista().toArray();
    }


    public static void enviaDadosUsuario(Usuario u) throws SQLException{
        DAO dao = EntidadeDAO.USUARIO.getEntidadeDAO();
        dao.Insere(u);
        ControleAuditoria.getInstance().AddAuditoria("Usuario salvo: " + u.getEmail());
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
    
}
