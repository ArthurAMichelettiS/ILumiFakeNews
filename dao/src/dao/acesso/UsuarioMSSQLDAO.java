/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.acesso;

import comum.Entidade;
import comum.Usuario;
import dao.basis.MSSQLDAO;

import java.sql.*;
import java.util.ArrayList;

public class UsuarioMSSQLDAO<E extends Entidade> extends MSSQLDAO {
    public UsuarioMSSQLDAO() {
        super(Usuario.class);
        setTabela("Usuario");
        setColunaLocaliza("Email");
        setColunaChaveId("IdUser");
        setColunaLocalizaInt("IdTipoDeUsuário");
    }

    @Override
    protected PreparedStatement CriaPreparedStatementInsere(Connection con, Entidade e) throws SQLException {
        String SQL = "insert into Usuario (Email, Senha, Pais, " +
                "Nome, Genero, DataNasc, IdTipoDeUsuário, Imagem, ComprovantePesq, DocFoto) " +
                "values (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(SQL);

        Usuario u = (Usuario) e;
        stmt.setString(1, u.getEmail());
        stmt.setString(2, u.getSenha());
        stmt.setString(3, u.getPais());
        stmt.setString(4, u.getNome());
        stmt.setString(5, u.getGenero());
        String s = u.getNascimento().toString().replace("-","/");
        stmt.setString(6, s);
        stmt.setInt(7, u.getIdTipoDeUsuario());
        stmt.setBytes(8, u.getImagem());
        stmt.setBytes(9, u.getComprovantePesquisadorByte());
        stmt.setBytes(10,u.getDocFotoByte());

        return stmt;
    }

    @Override
    protected PreparedStatement CriaPreparedStatementAltera(Connection con, Entidade e) throws SQLException {
        String SQL = "update Usuario set Email = ?, Senha = ?, Bio = ?, IdTipoDeUsuário = ? where IdUser = ?";
        PreparedStatement stmt = con.prepareStatement(SQL);

        Usuario u = (Usuario) e;
        stmt.setString(1, u.getEmail());
        stmt.setString(2, u.getSenha());
        stmt.setString(3, u.getBio());
        stmt.setInt(4, u.getIdTipoDeUsuario());
        stmt.setInt(5, u.getId());

        return stmt;
    }

    //atribui os campos de login e senha de uma tabela em um usuário
    @Override
    protected E preencheEntidade(ResultSet rs) {
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
        return (E)entidade;
    }

    private PreparedStatement CriaPreparedStatementListaFiltro(Connection con, String filtro) throws SQLException {
        String SQL = "select * from " + tabela + "  where [Nome] like ?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        stmt.setString(1, "%"+filtro+"%");
        return stmt;
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


    //CriaPreparedStatementListaSeguindo
    /*
    @Override
    protected PreparedStatement CriaPreparedStatementFiltraPorInt(Connection con, int idUser) throws SQLException {
        String SQL = "select u.* from Seguidores s inner join Usuario u on u.idUser = s.idUserCon where s.idUser = ?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        stmt.setInt(1, idUser);
        return stmt;
    }
    */


}
