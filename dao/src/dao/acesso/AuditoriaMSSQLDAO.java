package dao.acesso;

import comum.Auditoria;
import comum.Entidade;
import comum.Usuario;
import dao.basis.MSSQLDAO;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuditoriaMSSQLDAO <E extends Entidade> extends MSSQLDAO {
    public AuditoriaMSSQLDAO() {
        super(Usuario.class);
        setTabela("Auditoria");
    }

    @Override
    protected String setInsertCommand() { return "insert into "+ tabela +" (descricao, " +
            "DataNasc) values ?,?";}

    //atribui os campos de login e senha de uma tabela em um usuário
    @Override
    protected E preencheEntidade(ResultSet rs) {
        Auditoria entidade = new Auditoria();
        try {
            entidade.setIdTipo(rs.getInt("IdTipo"));
            entidade.setDescricao(rs.getString("descricao"));
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioMSSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        return "select * from " + tabela + " where id = ?";
    }

    @Override
    public void Insere(Entidade entidade) throws SQLException {
        Auditoria e = (Auditoria) entidade;
        try (Connection conexao = DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA)) {
            System.out.println("Banco conectado!");
            // ? => binding
            String SQL = setInsertCommand();
            try (PreparedStatement stmt = conexao.prepareStatement(SQL)) {
                stmt.setString(1, e.getDescricao());
                stmt.setInt(2, e.getIdTipo());

                stmt.executeQuery();
            }
        }
        catch (SQLException ea)
        {
            System.out.println(ea.getMessage());
        }
    }


}
