package dao.acesso;

import comum.Comentario;
import comum.Denuncia;
import comum.Entidade;
import comum.Usuario;
import dao.basis.MSSQLDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DenunciaMSSQLDAO<E extends Entidade> extends MSSQLDAO {

    public DenunciaMSSQLDAO() {
        super(Usuario.class);
        setTabela("Denuncia");
        setColunaLocalizaInt("IdStatus");
        setColunaLocalizaInt("IdPost");
        setColunaChaveId("IdDenuncia");
        setColunaLocaliza("Descricao");
    }

    @Override
    protected PreparedStatement CriaPreparedStatementInsere(Connection con, Entidade e) throws SQLException {
        String SQL = "insert into Denuncia (IdDenuncia, Denuncia, IdPost, IdStatus) values (?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(SQL);
        Denuncia p = (Denuncia) e;
        stmt.setInt(1, (p.getIdDenuncia()));
        stmt.setInt(2, p.getIdPost());
        stmt.setString(3, p.getDescricao());
        stmt.setInt(4, p.getIdStatus());

        return stmt;
    }

    @Override
    protected PreparedStatement CriaPreparedStatementAltera(Connection con, Entidade e) throws SQLException {
        String SQL = "update Denuncia set Descricao = ? where IdDenuncia = ?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        Denuncia p = (Denuncia) e;
        stmt.setString(1, p.getDescricao());
        stmt.setInt(2, p.getIdDenuncia());

        return stmt;
    }

    @Override
    protected PreparedStatement CriaPreparedStatementApaga(Connection con, Entidade e) throws SQLException {
        String SQL = "delete from Denuncia where IdDenuncia = ?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        Denuncia p = (Denuncia) e;
        stmt.setInt(1, p.getIdDenuncia());

        return stmt;
    }

    @Override
    protected E preencheEntidade(ResultSet rs) {
        Denuncia entidade = new Denuncia();
        try {
            entidade.setIdDenuncia(rs.getInt("IdDenuncia"));
            entidade.setIdPost(rs.getInt("IdPost"));
            entidade.setDescricao(rs.getString("Descricao"));
            entidade.setIdStatus(rs.getInt("IdStatus"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (E) entidade;
    }

    @Override
    public void Apaga(Entidade e) throws SQLException {

    }
}
