package dao.acesso;

import comum.Entidade;
import comum.Postagem;
import comum.Usuario;
import dao.basis.MSSQLDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostagemMSSQLDAO<E extends Entidade> extends MSSQLDAO
{
    //TODO

    public PostagemMSSQLDAO() {
        super(Usuario.class);
        setTabela("Postagem");
        setColunaLocaliza("titulo");
    }

    @Override
    protected E preencheEntidade(ResultSet rs) {
        Postagem entidade = new Postagem();
        try {
            entidade.setTitulo(rs.getString("titulo"));
            entidade.setConteudo(rs.getString("conteudo"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (E)entidade;
    }

    @Override
    protected void preencheStatement(Entidade entidade, PreparedStatement stmt) throws SQLException {

    }


    @Override
    public Entidade localizaPorId(int id) {
        return null;
    }

    @Override
    public void Insere(Entidade entidade) throws SQLException {

    }



    @Override
    protected String setInsertCommand() {
        return null;
    }
}

