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
    protected String colunaChaveId;



    public MSSQLDAO(Class entityClass) {
        super(entityClass);
    }

    protected void setTabela(String value){
        tabela = value;
    }

    protected void setColunaLocaliza(String value){
        colunaLocaliza = value;
    }

    public void setColunaChaveId(String colunaChaveId) {
        this.colunaChaveId = colunaChaveId;
    }

    @Override
    public E localizaPorId(int id) throws SQLException {
        return localiza(Integer.toString(id));
    }

    protected PreparedStatement CriaPreparedStatementLocaliza(Connection con, String codigo) throws SQLException {
        String SQL = "select * from " + tabela + "  where " + colunaLocaliza +" = ?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        stmt.setString(1, codigo);
        return stmt;
    }

    abstract protected PreparedStatement CriaPreparedStatementInsere(Connection con, Entidade e) throws SQLException;

    abstract protected PreparedStatement CriaPreparedStatementAltera(Connection con, Entidade e) throws SQLException;

    protected PreparedStatement CriaPreparedStatementListagem(Connection con) throws SQLException{
        String SQL = "select * from " + tabela;
        PreparedStatement stmt = con.prepareStatement(SQL);
        return stmt;
    }

    @Override
    public E localiza (String codigo) throws SQLException {
        E entidade = null;
        try (Connection conexao = DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA )) {

            try (PreparedStatement stmt = CriaPreparedStatementLocaliza(conexao, codigo);) {

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()){
                        entidade = preencheEntidade(rs);
                    }
                }
            }
        }        
        return entidade;
    }

    @Override
    public void Insere(Entidade entidade) throws SQLException {
        try (Connection conexao = DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA)) {

            try (PreparedStatement stmt = CriaPreparedStatementInsere(conexao, entidade)) {
                stmt.executeUpdate();
            }
        }
        catch (SQLException ea)
        {
            System.out.println(ea.getMessage());
        }
    }

    @Override
    public void Alter(Entidade entidade) throws SQLException {
        try (Connection conexao = DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA)) {

            try (PreparedStatement stmt = CriaPreparedStatementAltera(conexao, entidade)) {
                stmt.executeUpdate();
            }
        }
        catch (SQLException ea)
        {
            System.out.println(ea.getMessage());
        }
    }

    protected abstract E preencheEntidade(ResultSet rs);

    @Override
    public ArrayList listaFiltro(String filtro) throws SQLException {
        ArrayList<E> entidades = new ArrayList<E>();

        try (Connection conexao = DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA)) {

            try (PreparedStatement stmt = CriaPreparedStatementLocaliza(conexao, filtro)) {
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

    @Override
    public ArrayList<E> listaTodos() throws SQLException {
        ArrayList<E> entidades = new ArrayList<E>();

        try (Connection conexao = DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA)) {

            try (PreparedStatement stmt = CriaPreparedStatementListagem(conexao)) {
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
