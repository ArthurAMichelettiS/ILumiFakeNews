package dao.enums;

import dao.acesso.*;
import dao.basis.DAO;

public enum
EntidadeDAO {
    USUARIO(getUsuarioDao()),
    POSTAGEM(getPostagemDao()),
    AUDITORIA(getAuditoriaDao()),
    COMENTARIO(getComentarioDAO()),
    DENUNCIA(getDenunciaDAO());


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

    static private DAO getComentarioDAO(){
        return new ComentarioMSSQLDAO();
    }

    static private DAO getDenunciaDAO(){
        return new DenunciaMSSQLDAO();
    }


}

