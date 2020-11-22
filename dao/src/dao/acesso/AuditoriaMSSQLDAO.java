package dao.acesso;

import comum.Auditoria;
import comum.Entidade;
import dao.basis.MSSQLDAO;

import java.sql.Connection;
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
    protected PreparedStatement CriaPreparedStatementAltera(Connection con, Entidade e) throws SQLException {
        //sem alterar auditoria
        return null;
    }

    @Override
    protected PreparedStatement CriaPreparedStatementApaga(Connection con, Entidade entidade) throws SQLException {
        return null;
    }


    @Override
    protected PreparedStatement CriaPreparedStatementInsere(Connection con, Entidade e) throws SQLException {
        String SQL = "insert into "+ tabela + " (descricao, IdTipo) values (?,?)";
        PreparedStatement stmt = con.prepareStatement(SQL);
        Auditoria a = (Auditoria) e;
        stmt.setString(1, a.getDescricao());
        stmt.setInt(2, a.getIdTipo());
        return stmt;
    }

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
    public Entidade localizaPorId(int id) {
        return null;
    }

}
