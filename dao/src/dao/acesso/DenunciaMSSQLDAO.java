package dao.acesso;

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
        setTabela("Denuncias");
        setColunaLocalizaInt("IdPost");
        setColunaChaveId("IdDenuncia");
        setColunaLocaliza("Descricao");
    }

    @Override
    protected PreparedStatement CriaPreparedStatementInsere(Connection con, Entidade e) throws SQLException {
        String SQL = "insert into Denuncias (Descricao, IdPost, StatusDenuncia) values (?,?,?)";
        PreparedStatement stmt = con.prepareStatement(SQL);
        Denuncia p = (Denuncia) e;
        stmt.setString(1, p.getDescricao());
        stmt.setInt(2, p.getIdPost());
        stmt.setString(3, p.getStatusDenuncia());

        return stmt;
    }

    @Override
    protected PreparedStatement CriaPreparedStatementAltera(Connection con, Entidade e) throws SQLException {
        String SQL = "update Denuncias set Descricao = ? where IdDenuncia = ?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        Denuncia p = (Denuncia) e;
        stmt.setString(1, p.getDescricao());
        stmt.setInt(2, p.getId());

        return stmt;
    }

    @Override
    protected E preencheEntidade(ResultSet rs) {
        Denuncia entidade = new Denuncia();
        try {
            entidade.setId(rs.getInt("IdDenuncia"));
            entidade.setIdPost(rs.getInt("IdPost"));
            entidade.setDescricao(rs.getString("Descricao"));
            entidade.setStatusDenuncia(rs.getString("StatusDenuncia"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (E) entidade;
    }

    @Override
    public void Apaga(Entidade e) throws SQLException {

    }
}
