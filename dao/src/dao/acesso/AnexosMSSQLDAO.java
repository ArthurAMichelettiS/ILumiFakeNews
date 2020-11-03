package dao.acesso;

import comum.Anexo;
import comum.Entidade;
import dao.basis.MSSQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnexosMSSQLDAO <E extends Entidade> extends MSSQLDAO {
    public AnexosMSSQLDAO() {
        super(Anexo.class);
        setTabela("Anexos");
        setColunaLocaliza("IdPost");
        setColunaChaveId("IdAnexo");
    }

    @Override
    protected PreparedStatement CriaPreparedStatementInsere(Connection con, Entidade e) throws SQLException {
        String SQL ="insert into Anexos (Anexo,IdPost) values (?,?)";
        PreparedStatement stmt = con.prepareStatement(SQL);
        Anexo u = new Anexo();
        stmt.setInt(1, u.getIdPost());
        stmt.setBytes(2, u.getAnexo());
        return stmt;
    }

    @Override
    protected PreparedStatement CriaPreparedStatementAltera(Connection con, Entidade e) throws SQLException {
        String SQL = "update Anexos set Anexo = ? where IdPost = ?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        Anexo a = (Anexo) e;
        stmt.setInt(1, a.getIdPost());
        stmt.setBytes(2, a.getAnexo());
        return stmt;
    }

    @Override
    protected E preencheEntidade(ResultSet rs) {
        Anexo entidade = new Anexo();
        try {
            entidade.setIdPost(rs.getInt("IdPost"));
            entidade.setAnexo(rs.getBytes("Anexo"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (E)entidade;
    }

}
