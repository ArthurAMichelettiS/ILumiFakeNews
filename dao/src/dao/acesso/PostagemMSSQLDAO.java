package dao.acesso;

import comum.Entidade;
import comum.Postagem;
import comum.Usuario;
import dao.basis.DAO;
import dao.basis.MSSQLDAO;

import java.sql.*;
import java.util.ArrayList;

public class PostagemMSSQLDAO<E extends Entidade> extends MSSQLDAO {

    public PostagemMSSQLDAO() {
        super(Usuario.class);
        setTabela("Postagem");
        setColunaLocaliza("Titulo");
        setColunaChaveId("IdPost");
        setColunaLocalizaInt("IdUser");
    }

    @Override
    public void Insere(Entidade entidade) throws SQLException {
        Postagem p = (Postagem) entidade;

        super.Insere(entidade);

        /*TagsMSSQLDAO daoTag = new TagsMSSQLDAO();
        for (Tag tg:
                p.getTags()) {
            tg.setIdPost(p.getIdPost());
            daoTag.Insere(tg);
        }*/
    }

    @Override
    public void Alter(Entidade entidade) throws SQLException {
        super.Alter(entidade);
        Postagem p = (Postagem) entidade;

        /*TagsMSSQLDAO daoTag = new TagsMSSQLDAO();
        for (Tag tg:
                p.getTags()) {
            daoTag.Alter(tg);
        }*/
    }

    @Override
    protected PreparedStatement CriaPreparedStatementInsere(Connection con, Entidade e) throws SQLException {
        String SQL = "insert into Postagem (titulo, conteudo, IdUser) values (?,?,?)";
        PreparedStatement stmt = con.prepareStatement(SQL);
        Postagem p = (Postagem) e;
        stmt.setString(1, p.getTitulo());
        stmt.setString(2, p.getConteudo());
        stmt.setInt(3, p.getIdUser());

        return stmt;
    }

    @Override
    protected PreparedStatement CriaPreparedStatementAltera(Connection con, Entidade e) throws SQLException {
        String SQL = "update Postagem set titulo = ?, conteudo = ? where IdPost = ?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        Postagem p = (Postagem) e;
        stmt.setString(1, p.getTitulo());
        stmt.setString(2, p.getConteudo());
        stmt.setInt(3, p.getId());

        return stmt;
    }

    @Override
    protected PreparedStatement CriaPreparedStatementApaga(Connection con, Entidade e) throws SQLException {
        String SQL = "delete from Postagem where titulo = ?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        Postagem p = (Postagem) e;
        stmt.setString(1, p.getTitulo());
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

    @Override
    public ArrayList listaFiltro(String filtro) throws SQLException {
        ArrayList<E> entidades = new ArrayList<E>();

        try (Connection conexao = DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA)) {

            try (PreparedStatement stmt = CriaPreparedStatementListaFiltro(conexao, filtro)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()){
                        E entidade = preencheEntidade(rs);
                        entidades.add(entidade);
                    }
                }
            }
        }
        return entidades;
    }

    private PreparedStatement CriaPreparedStatementListaFiltro(Connection con, String filtro) throws SQLException {

        String SQL = "select * from " + tabela + "  where " + colunaLocaliza + " like ?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        stmt.setString(1, "%"+filtro+"%");
        return stmt;
    }


}