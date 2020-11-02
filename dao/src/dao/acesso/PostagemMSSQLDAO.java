package dao.acesso;

import comum.Entidade;
import comum.Postagem;
import comum.Usuario;
import dao.basis.MSSQLDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostagemMSSQLDAO<E extends Entidade> extends MSSQLDAO {

    public PostagemMSSQLDAO() {
        super(Usuario.class);
        setTabela("Postagem");
        setColunaLocaliza("titulo");
    }

    @Override
    protected String setInsertCommand() {
        return "insert into Postagem (titulo, conteudo, imagem) values (?,?,?)";
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
    protected String getLocalizaCommand() {
        return "select * from Postagem where idpost = ?";
    }

    @Override
    protected void preencheStatementInsert(Entidade entidade, PreparedStatement stmt) throws SQLException {
        Postagem p = (Postagem) entidade;
        stmt.setString(1, p.getTitulo());
        stmt.setString(2, p.getConteudo());
        stmt.setBytes(3, p.getImagem());
    }

    @Override
    protected void preencheStatementAlter(Entidade entidade, PreparedStatement stmt) throws SQLException {
        Postagem p = (Postagem) entidade;
        stmt.setString(1, p.getTitulo());
        stmt.setString(2, p.getConteudo());
        stmt.setBytes(3, p.getImagem());
    }

    @Override
    protected void preencheStatementSelect(String e, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, e);
    }

    @Override
    protected String setAlterCommand() {
        return "update Postagem set titulo = ?, conteudo = ?, imagem = ? where idpost = ?";
    }


    @Override
    public Entidade localizaPorId(int id) {
        return null;
    }

    @Override
    public void Insere(Entidade entidade) throws SQLException {

    }
}

