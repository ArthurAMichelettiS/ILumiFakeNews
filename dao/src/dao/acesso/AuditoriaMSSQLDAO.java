package dao.acesso;

import comum.Auditoria;
import comum.Entidade;
import dao.basis.MSSQLDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuditoriaMSSQLDAO <E extends Entidade> extends MSSQLDAO {
    public AuditoriaMSSQLDAO() {
        super(Auditoria.class);
        setTabela("Auditoria");
        setColunaLocaliza("descricao");
    }

    @Override
    protected String setInsertCommand() { return "insert into "+ tabela +
            " (descricao, IdTipo) values (?,?)";}

    @Override
    protected E preencheEntidade(ResultSet rs) {
        Auditoria entidade = new Auditoria();
        try {
            entidade.setIdTipo(rs.getInt("IdTipo"));
            entidade.setDescricao(rs.getString("descricao"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (E)entidade;
    }

    @Override
    protected void preencheStatementInsert(Entidade entidade, PreparedStatement stmt) throws SQLException {
        Auditoria e = (Auditoria) entidade;

        stmt.setString(1, e.getDescricao());
        stmt.setInt(2, e.getIdTipo());
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


    @Override
    public Entidade localizaPorId(int id) {
        return null;
    }

}
