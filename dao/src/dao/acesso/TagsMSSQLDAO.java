package dao.acesso;

import comum.Entidade;
import comum.Postagem;
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
        String SQL = "insert into Tag (IdTag, Descricao) values (?,?)";
        PreparedStatement stmt = con.prepareStatement(SQL);
        Tag t = (Tag) e;
        stmt.setString(1, t.getTag());
        SQL = "insert into TagPostagem (IdTag, IdPostagem) values (?,?)";
        stmt = con.prepareStatement(SQL);
        Postagem p = (Postagem) e;
        stmt.setString(1,String.valueOf(p.getIdPost()));
        return stmt;
    }


    @Override
    protected E preencheEntidade(ResultSet rs) {
        Tag entidade = new Tag();
        Postagem entidadeP = new Postagem();
        try {
            entidade.setId(rs.getInt("idTag"));
            entidade.setTag(rs.getString("descricao"));
            entidadeP.setId(rs.getInt("idTag"));
            entidadeP.setIdPost(rs.getInt("idPost"));
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return (E) entidade;
    }

}
