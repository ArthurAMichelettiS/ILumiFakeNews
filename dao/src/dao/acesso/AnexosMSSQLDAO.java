package dao.acesso;

import comum.Anexo;
import comum.Entidade;
import dao.basis.MSSQLDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnexosMSSQLDAO  <E extends Entidade> extends MSSQLDAO {
    public AnexosMSSQLDAO(Class entityClass) {
        super(entityClass);
        setTabela("Anexos");
        setColunaLocaliza("IdPost");
    }

    @Override
    protected String setInsertCommand() {
        return "insert into Anexos (Anexo,IdPost) values (?,?)";
    }

    @Override
    protected E preencheEntidade(ResultSet rs) {
        Anexo entidade = new Anexo();
        try {
            entidade.setIdPost(rs.getInt("IdUser"));
            entidade.setAnexo(rs.getBytes("Email"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (E)entidade;
    }

    @Override
    protected void preencheStatementInsert(Entidade entidade, PreparedStatement stmt) throws SQLException {
        Anexo u = new Anexo();
        stmt.setInt(1, u.getIdPost());
        stmt.setBytes(2, u.getAnexo());
    }

    @Override
    protected void preencheStatementAlter(Entidade entidade, PreparedStatement stmt) throws SQLException {

    }

    @Override
    protected void preencheStatementSelect(String e, PreparedStatement stmt) throws SQLException {

    }

    @Override
    protected String setAlterCommand() {
        return null;
    }
}
