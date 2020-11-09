package dao.acesso;

import comum.Entidade;
import comum.Postagem;
import comum.Tag;
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
        setColunaLocaliza("IdPost");
        setColunaChaveId("IdPost");
    }

    @Override
    public void Insere(Entidade entidade) throws SQLException {
        Postagem p = (Postagem) entidade;

        super.Insere(entidade);

        AnexosMSSQLDAO daoAnexo = new AnexosMSSQLDAO();
        daoAnexo.Insere(p.getImagem());

        TagsMSSQLDAO daoTag = new TagsMSSQLDAO();
        for (Tag tg:
                p.getTags()) {
            tg.setIdPost(p.getIdPost());
            daoTag.Insere(tg);
        }
    }

    @Override
    public void Alter(Entidade entidade) throws SQLException {
        super.Alter(entidade);

        Postagem p = (Postagem) entidade;
        AnexosMSSQLDAO daoAnexo = new AnexosMSSQLDAO();
        daoAnexo.Alter(p.getImagem());

        /*TagsMSSQLDAO daoTag = new TagsMSSQLDAO();
        for (Tag tg:
                p.getTags()) {
            daoTag.Alter(tg);
        }*/
    }

    @Override
    protected PreparedStatement CriaPreparedStatementInsere(Connection con, Entidade e) throws SQLException {
        String SQL = "insert into Postagem (titulo, conteudo, IdUser) values (?,?, ?)";
        PreparedStatement stmt = con.prepareStatement(SQL);
        Postagem p = (Postagem) e;
        stmt.setString(1, p.getTitulo());
        stmt.setString(2, p.getConteudo());
        stmt.setInt(3, p.getIdUser());

        return stmt;
    }

    @Override
    protected PreparedStatement CriaPreparedStatementAltera(Connection con, Entidade e) throws SQLException {
        String SQL = "update Postagem set titulo = ?, conteudo = ?, where idpost = ?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        Postagem p = (Postagem) e;
        stmt.setString(1, p.getTitulo());
        stmt.setString(2, p.getConteudo());
        stmt.setInt(3, p.getId());

        return stmt;
    }


    @Override
    protected E preencheEntidade(ResultSet rs) {
        Postagem entidade = new Postagem();
        try {
            entidade.setId(rs.getInt("IdPost"));
            entidade.setTitulo(rs.getString("titulo"));
            entidade.setConteudo(rs.getString("conteudo"));
            entidade.setImagem(rs.getBytes("imagem"));
            entidade.setIdUser(rs.getInt("idUser"));
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