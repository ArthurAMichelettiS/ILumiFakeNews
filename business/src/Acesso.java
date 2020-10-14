import comum.Usuario;
import dao.acesso.DadosUsuarioMySQLDAO;
import dao.acesso.UsuarioMySQLDAO;

import java.sql.SQLException;

public class Acesso {

    public static boolean validaLogin(Usuario u) throws SQLException {
        UsuarioMySQLDAO dao = new UsuarioMySQLDAO();
        Usuario encontrado = (Usuario) dao.localiza(u.getLogin());
        return true;
    }

    public static Object[] listaDadosUsuario() throws SQLException {
        DadosUsuarioMySQLDAO dados = new DadosUsuarioMySQLDAO();
        return dados.lista().toArray();
    }
}
