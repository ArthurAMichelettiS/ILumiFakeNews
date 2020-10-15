



package business;

import comum.Usuario;
import dao.acesso.UsuarioMSSQLDAO;

import java.sql.SQLException;


public class Acesso {

    public static boolean validaLogin(Usuario u) throws SQLException {
        UsuarioMSSQLDAO dao = new UsuarioMSSQLDAO();
        Usuario encontrado = (Usuario) dao.localiza(u.getLogin());
        return true;
    }

    public static Object[] listaDadosUsuario() throws SQLException {
        UsuarioMSSQLDAO dados = new UsuarioMSSQLDAO();
        return dados.lista().toArray();
    }
}
