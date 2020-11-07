/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.acesso;

import comum.Entidade;
import comum.Usuario;
import dao.basis.MSSQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UsuarioMSSQLDAO<E extends Entidade> extends MSSQLDAO {
    public UsuarioMSSQLDAO() {
        super(Usuario.class);
        setTabela("Usuario");
        setColunaLocaliza("Email");
        setColunaChaveId("IdUser");
    }

    @Override
    protected PreparedStatement CriaPreparedStatementInsere(Connection con, Entidade e) throws SQLException {
        String SQL = "insert into Usuario (Email, Senha, Pais, " +
                "Nome, Genero, DataNasc, IdTipoDeUsuário, Imagem) values (?,?,?,?,?,?,?,?)";
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

        return stmt;
    }

    @Override
    protected PreparedStatement CriaPreparedStatementAltera(Connection con, Entidade e) throws SQLException {
        String SQL = "update Usuario set Email = ?, Senha = ?, Bio = ? where IdUser = ?";
        PreparedStatement stmt = con.prepareStatement(SQL);

        Usuario u = (Usuario) e;
        stmt.setString(1, u.getEmail());
        stmt.setString(2, u.getSenha());
        stmt.setString(3, u.getBio());
        stmt.setInt(4, u.getId());

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
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (E)entidade;
    }

    @Override
    public Entidade localiza(String codigo) throws SQLException {
        return super.localiza(codigo);
    }
}
