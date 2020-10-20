package dao.acesso;

import comum.*;
import comum.Entidade;
import comum.Usuario;
import dao.basis.MSSQLDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DadosUsuarioMSSQLDAO<E extends Entidade> extends MSSQLDAO
{
    public DadosUsuarioMSSQLDAO() {
        super(Usuario.class);
        setTabela("tbDadosUsuario");
    }

    public void InsereUsuarioMSSQLDAO(){

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
            Logger.getLogger(DadosUsuarioMSSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (E)entidade;
    }


    @Override
    public Entidade seleciona(int id) {
        // Não há retorno por id
        return null;
    }

    @Override
    public void Insere(Entidade entidade) throws SQLException {

    }

    //retorna o select para localizar um login
    @Override
    protected String getLocalizaCommand(){
        return "select * from tbDadosUsuario where nome = ?";
    }
}

