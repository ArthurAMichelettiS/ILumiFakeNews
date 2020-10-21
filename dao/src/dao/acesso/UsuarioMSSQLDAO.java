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
            "Nome, Genero, DataNasc) values (?,?,?,?,?,?)";}


    //atribui os campos de login e senha de uma tabela em um usuário
    @Override
    protected E preencheEntidade(ResultSet rs) {
        Usuario entidade = new Usuario();
        try {
            entidade.setEmail(rs.getString("Email"));
            entidade.setSenha(rs.getString("Senha"));
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
    protected void preencheStatement(Entidade entidade, PreparedStatement stmt) throws SQLException {
        Usuario u = (Usuario) entidade;
        stmt.setString(1, u.getEmail());
        stmt.setString(2, u.getSenha());
        stmt.setString(3, u.getPais());
        stmt.setString(4, u.getNome());
        stmt.setString(5, u.getGenero());
        String s = u.getNascimento().toString().replace("-","/");
        stmt.setString(6, s);
    }

}
