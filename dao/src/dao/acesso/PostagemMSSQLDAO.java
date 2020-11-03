package dao.acesso;

import comum.Entidade;
import comum.Postagem;
import comum.Usuario;
import dao.basis.DAO;
import dao.basis.MSSQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostagemMSSQLDAO<E extends Entidade> extends MSSQLDAO {

    public PostagemMSSQLDAO() {
        super(Usuario.class);
        setTabela("Postagem");
        setColunaLocaliza("titulo");
        setColunaChaveId("IdPost");
    }

    @Override
    protected PreparedStatement CriaPreparedStatementInsere(Connection con, Entidade e) throws SQLException {
        String SQL = "insert into Postagem (titulo, conteudo) values (?,?)";
        PreparedStatement stmt = con.prepareStatement(SQL);
        Postagem p = (Postagem) e;
        stmt.setString(1, p.getTitulo());
        stmt.setString(2, p.getConteudo());
        return stmt;
    }

    @Override
    protected PreparedStatement CriaPreparedStatementAltera(Connection con, Entidade e) throws SQLException {
        String SQL = "update Postagem set titulo = ?, conteudo = ?, imagem = ? where idpost = ?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        Postagem p = (Postagem) e;
        stmt.setString(1, p.getTitulo());
        stmt.setString(2, p.getConteudo());
        stmt.setBytes(3, p.getImagem());
        stmt.setInt(4, p.getId());
        return stmt;
    }


    @Override
    protected E preencheEntidade(ResultSet rs) {
        Postagem entidade = new Postagem();
        try {
            entidade.setId(rs.getInt("Id"));
            entidade.setTitulo(rs.getString("titulo"));
            entidade.setConteudo(rs.getString("conteudo"));
            entidade.setImagem(rs.getBytes("imagem"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (E) entidade;
    }

    @Override
    public Entidade localiza(String codigo) throws SQLException {
        Postagem p = (Postagem) super.localiza(codigo);
        DAO d = new TagsMSSQLDAO<>();
        p.setTags(d.listaTodos());
        return p;
    }

}