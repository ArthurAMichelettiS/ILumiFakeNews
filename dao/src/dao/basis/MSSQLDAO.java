/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.basis;

import comum.Entidade;

import java.sql.*;
import java.util.ArrayList;


public abstract class MSSQLDAO <E extends Entidade> extends DAO {
    protected final String STRING_CONEXAO = "jdbc:sqlserver://sql5080.site4now.net";
    protected final String USUARIO = "DB_A688E3_IlumiDB_admin";
    protected final String SENHA = "IlumiFake01";
    protected String tabela;
    protected String colunaLocaliza;

    public MSSQLDAO(Class entityClass) {
        super(entityClass);
    }

    protected void setTabela(String value){
        tabela = value;
    }

    protected void setColunaLocaliza(String value){
        colunaLocaliza = value;
    }

    @Override
    public E localizaPorId(int id) {
        // Não há retorno por idA
        return null;
    }

    @Override
    public E localiza (String codigo) throws SQLException {
        E entidade = null;
        try (Connection conexao = DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA )) {

            String SQL = getLocalizaCommand();

            try (PreparedStatement stmt = conexao.prepareStatement(SQL)) {

                stmt.setString(1, codigo);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.first()){
                        entidade = preencheEntidade(rs);
                    }
                }
            }
        }        
        return entidade;
    }

    protected String getLocalizaCommand(){
        return "select * from " + tabela + "  where " + colunaLocaliza +" = ?";
    }

    protected String getListaCommand() {
        return "select * from " + tabela;
    }

    protected abstract String setInsertCommand() ;

    protected abstract E preencheEntidade(ResultSet rs);

    protected abstract void preencheStatement(E entidade, PreparedStatement stmt) throws SQLException;


    @Override
    public void Insere(Entidade entidade) throws SQLException {
        try (Connection conexao = DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA)) {

            String SQL = setInsertCommand();
            try (PreparedStatement stmt = conexao.prepareStatement(SQL)) {

                preencheStatement((E)entidade, stmt);

                stmt.executeUpdate();
            }
        }
        catch (SQLException ea)
        {
            System.out.println(ea.getMessage());
        }
    }

    @Override
    public ArrayList<E> lista() throws SQLException {
        ArrayList<E> entidades = new ArrayList<E>();

        try (Connection conexao = DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA)) {

            String SQL = getListaCommand();
            try (PreparedStatement stmt = conexao.prepareStatement(SQL)) {
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
}
