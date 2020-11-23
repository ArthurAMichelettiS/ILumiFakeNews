package dao.acesso;

import comum.Denuncia;
import comum.Entidade;
import comum.Status;
import comum.Usuario;
import dao.basis.MSSQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusMSSQLDAO<E extends Entidade> extends MSSQLDAO {

    public StatusMSSQLDAO() {
        super(Usuario.class);
        setColunaChaveId("IdStatus");
        setColunaLocaliza("Descricao");
    }

    @Override
    protected PreparedStatement CriaPreparedStatementInsere(Connection con, Entidade e) throws SQLException {
        String SQL = "insert into Denuncia (IdStatus, Descricao) values (?,?)";
        PreparedStatement stmt = con.prepareStatement(SQL);
        Status p = (Status) e;
        stmt.setInt(1, (p.getIdStatus()));
        stmt.setString(2, p.getDescricao());

        return stmt;
    }

    @Override
    protected PreparedStatement CriaPreparedStatementAltera(Connection con, Entidade e) throws SQLException {
        String SQL = "update Status set Descricao = ? where IdStatus = ?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        Status p = (Status) e;
        stmt.setString(1, p.getDescricao());
        stmt.setInt(2, p.getIdStatus());

        return stmt;
    }

    @Override
    protected PreparedStatement CriaPreparedStatementApaga(Connection con, Entidade e) throws SQLException {
        String SQL = "delete from Status where IdStatus = ?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        Status p = (Status) e;
        stmt.setInt(1, p.getIdStatus());

        return stmt;
    }

    @Override
    protected E preencheEntidade(ResultSet rs) {
        Status entidade = new Status();
        try {
            entidade.setIdStatus(rs.getInt("IdDenuncia"));
            entidade.setDescricao(rs.getString("Descricao"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (E) entidade;
    }

    @Override
    public void Apaga(Entidade e) throws SQLException {

    }
}
