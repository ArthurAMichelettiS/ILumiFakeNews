/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.basis;

import comum.Entidade;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author gabriell
 * @param <E>
 */
public abstract class DAO <E extends Entidade> {
    
    //DAO genérico para ser acessado pelo business com funções de localizar e listar do banco
    
    protected Class<E> entityClass;

    public DAO(Class<E> entityClass){
        this.entityClass = entityClass;
    }
    
    public abstract E seleciona(int id);
    public abstract E localiza(String codigo) throws SQLException;
    public abstract ArrayList<E> lista() throws SQLException;
    public abstract void Insere(E entidade) throws SQLException;
    
    protected E getInstanceOfE()
    {
        try
        {
            return entityClass.newInstance();
        }
        catch (IllegalAccessException | InstantiationException e)
        {
            // Oops, no default constructor
            throw new RuntimeException(e);
        }
    }
}
