/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.acesso;

import comum.Entidade;
import comum.Usuario;
import dao.basis.MSSQLDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UsuarioMSSQLDAO<E extends Entidade> extends MSSQLDAO {
    public UsuarioMSSQLDAO() {
        super(Usuario.class);
        setTabela("Usuario");
        setColunaLocaliza("Email");
    }

    @Override
    protected String setInsertCommand() { return "insert into Usuario (Email, Senha, Pais, " +
            "Nome, Genero, DataNasc, IdTipoDeUsuário) values (?,?,?,?,?,?,?)";}

    @Override
    protected String getLocalizaCommand() {
        return "select * from Usuario where Email = ?";
    }

    @Override
    protected String setAlterCommand() {
        return "update Usuario set Email = ?, Senha = ?, Bio = ? where Email = ?";
    }

    //atribui os campos de login e senha de uma tabela em um usuário
    @Override
    protected E preencheEntidade(ResultSet rs) {
        Usuario entidade = new Usuario();
        try {
            entidade.setEmail(rs.getString("Email"));
            entidade.setSenha(rs.getString("Senha"));
            entidade.setPais(rs.getString("Pais"));
            entidade.setNome(rs.getString("Nome"));
            entidade.setIdTipoDeUsuario(rs.getInt("IdTipoDeUsuário"));
            entidade.setGenero(rs.getString("Genero"));
            entidade.setBio(rs.getString("Bio"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (E)entidade;
    }
    
    
    @Override
    public Entidade localizaPorId(int id) {
        return null;
    }

    @Override
    public void Alter(Entidade entidade) throws SQLException {

    }

    @Override
    protected void preencheStatementSelect(String e, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, e);
    }

    @Override
    protected void preencheStatementInsert(Entidade entidade, PreparedStatement stmt) throws SQLException {
        Usuario u = (Usuario) entidade;
        stmt.setString(1, u.getEmail());
        stmt.setString(2, u.getSenha());
        stmt.setString(3, u.getPais());
        stmt.setString(4, u.getNome());
        stmt.setString(5, u.getGenero());
        String s = u.getNascimento().toString().replace("-","/");
        stmt.setString(6, s);
        stmt.setInt(7, u.getIdTipoDeUsuario());
    }

    @Override
    protected void preencheStatementAlter(Entidade entidade, PreparedStatement stmt) throws SQLException {
        Usuario u = (Usuario) entidade;
        stmt.setString(1, u.getEmail());
        stmt.setString(2, u.getSenha());
        stmt.setString(3, u.getBio());
        stmt.setString(4, u.getEmail());
    }

}
