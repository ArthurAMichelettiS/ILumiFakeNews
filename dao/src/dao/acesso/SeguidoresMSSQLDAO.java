package dao.acesso;

import comum.Entidade;
import comum.Seguidores;
import comum.Usuario;
import dao.basis.MSSQLDAO;

import java.sql.*;

public class SeguidoresMSSQLDAO<E extends Entidade> extends MSSQLDAO {
    public SeguidoresMSSQLDAO() {
        super(Usuario.class);
        setTabela("Seguidores");
        setColunaLocaliza("");
        setColunaChaveId("Id");
        setColunaLocalizaInt("idUser");
    }

    @Override
    protected PreparedStatement CriaPreparedStatementInsere(Connection con, Entidade e) throws SQLException {
        String SQL = "insert into Seguidores (idUser, idUserCon) values (?,?)";
        PreparedStatement stmt = con.prepareStatement(SQL);

        Seguidores u = (Seguidores) e;
        stmt.setInt(1, u.getIdUser());
        stmt.setInt(2, u.getIdUserCon());

        return stmt;
    }

    @Override
    protected PreparedStatement CriaPreparedStatementAltera(Connection con, Entidade e) throws SQLException {
        String SQL = "update Seguidores set idUser = ?, idUserCon = ? where Id = ?";
        PreparedStatement stmt = con.prepareStatement(SQL);

        Seguidores u = (Seguidores) e;
        stmt.setInt(1, u.getIdUser());
        stmt.setInt(2, u.getIdUserCon());
        stmt.setInt(3, u.getId());

        return stmt;
    }

    protected PreparedStatement CriaPreparedStatementApaga(Connection con, Entidade e) throws SQLException {
        String SQL = "delete from "+tabela+" where idUser = ? and idUserCon = ?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        Seguidores u = (Seguidores) e;
        stmt.setInt(1, u.getIdUser());
        stmt.setInt(2, u.getIdUserCon());
        return stmt;
    }

    //atribui os campos de login e senha de uma tabela em um usuário
    @Override
    protected Usuario preencheEntidade(ResultSet rs) {
        Usuario entidade = new Usuario();
        try {
            entidade.setId(rs.getInt("IdUser"));
            entidade.setEmail(rs.getString("Email"));
            entidade.setSenha(rs.getString("Senha"));
            entidade.setPais(rs.getString("Pais"));
            entidade.setNome(rs.getString("Nome"));
            entidade.setIdTipoDeUsuario(rs.getInt("IdTipoDeUsuário"));
            entidade.setGenero(rs.getString("Genero"));
            entidade.setBio(rs.getString("Bio"));
            entidade.setImagem(rs.getBytes("Imagem"));
            entidade.setComprovantePesquisadorByte(rs.getBytes("ComprovantePesq"));
            entidade.setDocFotoByte(rs.getBytes("DocFoto"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return entidade;
    }

    @Override
    protected PreparedStatement CriaPreparedStatementFiltraPorInt(Connection con, int id) throws SQLException {
        String SQL =  "select u.* from Seguidores s inner join Usuario u on u.idUser = s.idUserCon where s.idUser = ?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        stmt.setInt(1, id);
        return stmt;
    }

    public boolean LocalizaSeguidor(int idUser, int idLogado) throws SQLException {
        try (Connection conexao = DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA )) {

            try (PreparedStatement stmt = CriaPreparedStatementBuscaSeguidor(conexao, idUser, idLogado);) {

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    protected PreparedStatement CriaPreparedStatementBuscaSeguidor(Connection con, int id, int idLogado) throws SQLException {
        String SQL =  "select * from Seguidores where idUser = ? and idUserCon = ?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        stmt.setInt(1, idLogado);
        stmt.setInt(2, id);
        return stmt;
    }

    /*private PreparedStatement CriaPreparedStatementListaFiltro(Connection con, String filtro) throws SQLException {
        String SQL = "select * from " + tabela + "  where [Nome] like ?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        stmt.setString(1, "%"+filtro+"%");
        return stmt;
    }*/

    /*@Override
    public ArrayList listaFiltroInt(String filtro) throws SQLException {
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
    }*/
}
