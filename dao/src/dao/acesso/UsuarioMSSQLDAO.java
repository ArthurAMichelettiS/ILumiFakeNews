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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabriell
 * @param <E>
 */
public class UsuarioMSSQLDAO<E extends Entidade> extends MSSQLDAO {
    public UsuarioMSSQLDAO() {
        super(Usuario.class);
        setTabela("usuario");
    }

    //atribui os campos de login e senha de uma tabela em um usuário
    @Override
    protected E preencheEntidade(ResultSet rs) {
        Usuario entidade = new Usuario();
        try {
            entidade.setEmail(rs.getString("Email"));
            entidade.setSenha(rs.getString("Senha"));
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioMSSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (E)entidade;
    }
    
    
    @Override
    public Entidade seleciona(int id) {
        // Não há retorno por id
        return null;
    }

    //retorna o select para localizar um login
    @Override
    protected String getLocalizaCommand(){
        return "select * from usuario where email = ?";
    }

    @Override
    public void Insere(Entidade entidade) throws SQLException {
        Usuario e = (Usuario) entidade;
        try (Connection conexao = DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA)) {
            System.out.println("Banco conectado!");
            // ? => binding
            String SQL = setNovoUsuarioCommand();
            try (PreparedStatement stmt = conexao.prepareStatement(SQL)) {
                stmt.setString(1, e.getEmail());
                stmt.setString(2, e.getSenha());
                stmt.setString(3, e.getPais());
                stmt.setString(4, e.getNome());
                stmt.setString(5, e.getGenero());
                stmt.setString(6, e.getNascimento().toString());
                System.out.println(stmt);
                stmt.executeQuery();

            }
        }
        catch (SQLException ea)
        {
            System.out.println(ea);
        }
    }






}
