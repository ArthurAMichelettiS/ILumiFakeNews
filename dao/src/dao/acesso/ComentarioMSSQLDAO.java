package dao.acesso;

import comum.Comentario;
import comum.Entidade;
import comum.Postagem;
import comum.Usuario;
import dao.basis.MSSQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ComentarioMSSQLDAO<E extends Entidade> extends MSSQLDAO {

    public ComentarioMSSQLDAO() {
        super(Usuario.class);
        setTabela("Comentario");
        setColunaLocalizaInt("IdPost");
        setColunaChaveId("IdCom");
        setColunaLocaliza("Conteudo");
    }

    @Override
    protected PreparedStatement CriaPreparedStatementInsere(Connection con, Entidade e) throws SQLException {
        String SQL = "insert into Comentario (IdPost, Conteudo, Data, IdUser) values (?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(SQL);
        Comentario p = (Comentario) e;
        stmt.setInt(1, (p.getIdPost()));
        stmt.setString(2, p.getConteudo());
        stmt.setDate(3, p.getData());
        stmt.setInt(4, p.getIdUser());

        return stmt;
    }

    @Override
    protected PreparedStatement CriaPreparedStatementAltera(Connection con, Entidade e) throws SQLException {
        String SQL = "update Comentario set conteudo = ? where IdCom = ?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        Comentario p = (Comentario) e;
        stmt.setString(1, p.getConteudo());
        stmt.setInt(2, p.getIdCom());

        return stmt;
    }

    @Override
    protected PreparedStatement CriaPreparedStatementApaga(Connection con, Entidade e) throws SQLException {
        String SQL = "delete from Comentario where IdCom = ?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        Comentario p = (Comentario) e;
        stmt.setInt(1, p.getIdCom());
        return stmt;
    }

    @Override
    protected E preencheEntidade(ResultSet rs) {
        Comentario entidade = new Comentario();
        try {
            entidade.setIdCom(rs.getInt("IdCom"));
            entidade.setIdPost(rs.getInt("IdPost"));
            entidade.setConteudo(rs.getString("Conteudo"));
            entidade.setData(rs.getDate("Data"));
            entidade.setIdUser(rs.getInt("IdUser"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (E) entidade;
    }

    @Override
    public void Apaga(Entidade e) throws SQLException {
        Comentario c = (Comentario) e;
        super.Apaga(c);
    }

    @Override
    public void Alter(Entidade e) throws SQLException{
        Comentario c = (Comentario) e;
        super.Alter(c);
    }
}
