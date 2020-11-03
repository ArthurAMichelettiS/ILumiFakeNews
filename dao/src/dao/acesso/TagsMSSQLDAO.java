package dao.acesso;

import comum.Entidade;
import comum.Tag;
import dao.basis.MSSQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TagsMSSQLDAO  <E extends Entidade> extends MSSQLDAO {
    public TagsMSSQLDAO() {
        super(Tag.class);
        setTabela("Tags");
        setColunaLocaliza("descricao");
        setColunaChaveId("idTag");
    }
    
    @Override
    protected PreparedStatement CriaPreparedStatementAltera(Connection con, Entidade e) throws SQLException {
        String SQL = "update Tags set descricao = ? where idTag = ?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        Tag t = (Tag) e;
        stmt.setString(1, t.getTag());
        return stmt;
    }

    @Override
    protected PreparedStatement CriaPreparedStatementInsere(Connection con, Entidade e) throws SQLException {
        String SQL = "insert into Tags (idTag, descricao) values (?,?)";
        PreparedStatement stmt = con.prepareStatement(SQL);
        Tag t = (Tag) e;
        stmt.setString(1, t.getTag());
        return stmt;
    }


    @Override
    protected E preencheEntidade(ResultSet rs) {
        Tag entidade = new Tag();
        try {
            entidade.setId(rs.getInt("idTag"));
            entidade.setTag(rs.getString("descricao"));
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return (E) entidade;
    }

}
