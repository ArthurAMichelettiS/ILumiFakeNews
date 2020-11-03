package dao.enums;

import dao.acesso.AuditoriaMSSQLDAO;
import dao.acesso.PostagemMSSQLDAO;
import dao.acesso.UsuarioMSSQLDAO;
import dao.basis.DAO;

public enum EntidadeDAO {
    USUARIO(getUsuarioDao()),
    POSTAGEM(getPostagemDao()),
    AUDITORIA(getAuditoriaDao());


    DAO entidadeDAO;

    EntidadeDAO(DAO dao){
        entidadeDAO = dao;
    }

    public DAO getEntidadeDAO() {
        return entidadeDAO;
    }

    static private DAO getUsuarioDao(){
        return new UsuarioMSSQLDAO<>();
    }

    static private DAO getPostagemDao(){
        return new PostagemMSSQLDAO();
    }

    static private DAO getAuditoriaDao(){
        return new AuditoriaMSSQLDAO();
    }

}

