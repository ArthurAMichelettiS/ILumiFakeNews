package comum;

import java.util.Date;

public class Comentario extends Entidade{



    private int idCom;
    private int idPost;
    private String conteudo;
    private Date data;

    public int getIdCom() {
        return idCom;
    }

    public void setIdCom(int idCom) {
        this.idCom = idCom;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public java.sql.Date getData() {
        return (java.sql.Date) data;
    }

    public void setData(java.sql.Date data) {
        this.data = data;
    }



}
