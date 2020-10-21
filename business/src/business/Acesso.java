
package business;

import business.Log.ControleAuditoria;
import comum.Usuario;
import dao.acesso.UsuarioMSSQLDAO;

import java.sql.SQLException;


public class Acesso {

    public static boolean validaLogin(String email, String senha) throws SQLException {
        UsuarioMSSQLDAO dao = new UsuarioMSSQLDAO();
        Usuario encontrado = (Usuario) dao.localiza(email);
        return senha.equals(encontrado.getSenha());
    }

    public static Object[] listaDadosUsuario() throws SQLException {
        UsuarioMSSQLDAO dados = new UsuarioMSSQLDAO();
        return dados.lista().toArray();
    }

    public static void enviaDadosUsuario(Usuario d) throws SQLException{
        UsuarioMSSQLDAO dados = new UsuarioMSSQLDAO();
        dados.Insere(d);
        ControleAuditoria.getInstance().AddAuditoria("Usuario salvo: " + d.getEmail());
    }
}
