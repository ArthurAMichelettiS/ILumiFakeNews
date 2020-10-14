package dao.acesso;

import comum.*;
import comum.Entidade;
import comum.Usuario;
import dao.basis.MySQLDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DadosUsuarioMySQLDAO <E extends Entidade> extends basis.MySQLDAO
{
    public DadosUsuarioMySQLDAO() {
        super(Usuario.class);
        setTabela("tbDadosUsuario");
    }

    //atribui os campos de login e senha de uma tabela em um usuário
    @Override
    protected E preencheEntidade(ResultSet rs) {
        DadosUsuario entidade = new DadosUsuario();
        try {
            entidade.setNome(rs.getString("nome"));
            entidade.setSobrenome(rs.getString("sobrenome"));
            entidade.setNumteste(rs.getInt("Numero de testes"));
        } catch (SQLException ex) {
            Logger.getLogger(dao.acesso.DadosUsuarioMySQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (E)entidade;
    }


    @Override
    public Entidade seleciona(int id) {
        // Não há retorno por id
        return null;
    }

    //retorna o select para localizar um login
    @Override
    protected String getLocalizaCommand(){
        return "select * from tbDadosUsuario where nome = ?";
    }
}

