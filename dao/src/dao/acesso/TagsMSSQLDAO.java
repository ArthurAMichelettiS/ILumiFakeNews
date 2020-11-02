package dao.acesso;

import comum.Entidade;
import dao.basis.MSSQLDAO;

import javax.swing.text.html.HTML;
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

        return null;
    }

    @Override
    protected void preencheStatementInsert(Entidade entidade, PreparedStatement stmt) throws SQLException {

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
