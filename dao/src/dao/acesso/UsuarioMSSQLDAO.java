/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.acesso;

import comum.Entidade;
import comum.Usuario;
import dao.basis.MSSQLDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
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
            entidade.setLogin(rs.getString("E-mail"));
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
}
