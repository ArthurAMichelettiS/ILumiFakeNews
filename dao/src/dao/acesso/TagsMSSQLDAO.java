package dao.acesso;

import comum.Entidade;
import comum.Tag;
import dao.basis.MSSQLDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TagsMSSQLDAO  <E extends Entidade> extends MSSQLDAO {
    public TagsMSSQLDAO(Class entityClass) {
        super(entityClass);
    }

    @Override
    protected String setInsertCommand() {
        return "insert into Tags (idTag, descricao) values (?,?)";
    }

    @Override
    protected E preencheEntidade(ResultSet rs) {
        Tag entidade = new Tag();
        try {
            entidade.setId(rs.getInt("idtag"));
            entidade.setTag(rs.getString("descricao"));
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return (E) entidade;
    }

    @Override
    protected void preencheStatementInsert(Entidade entidade, PreparedStatement stmt) throws SQLException {
        Tag t = (Tag) entidade;
        stmt.setString(1, t.getTag());
    }

    @Override
    protected void preencheStatementAlter(Entidade entidade, PreparedStatement stmt) throws SQLException {
        Tag t = (Tag) entidade;
        stmt.setString(1, t.getTag());
    }

    @Override
    protected void preencheStatementSelect(String e, PreparedStatement stmt) throws SQLException {

    }

    @Override
    protected String setAlterCommand() {
        return "update Tags set descricao = ? where idpost = ?";
    }

    @Override
    protected String getLocalizaCommand() {
        return "select * from Tags where idtag = ?";
    }
}
