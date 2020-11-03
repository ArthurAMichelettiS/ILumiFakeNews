package dao.acesso;

import comum.Anexo;
import comum.Entidade;
import comum.Usuario;
import dao.basis.MSSQLDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnexosMSSQLDAO <E extends Entidade> extends MSSQLDAO {
    public AnexosMSSQLDAO() {
        super(Anexo.class);
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
            entidade.setIdPost(rs.getInt("IdPost"));
            entidade.setAnexo(rs.getBytes("Anexo"));
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
        Anexo a = (Anexo) entidade;
        stmt.setInt(1, a.getIdPost());
        stmt.setBytes(2, a.getAnexo());
    }

    @Override
    protected String setAlterCommand() {
        return "update Anexos set Anexo = ? where IdPost = ?";
    }

    @Override
    protected String getLocalizaCommand() {
        return "select * from Anexos where IdPost = ?";
    }

    @Override
    protected void preencheStatementSelect(String e, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, e);
    }


}
