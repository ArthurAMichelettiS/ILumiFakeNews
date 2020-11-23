package comum;

import java.util.Date;

public class Denuncia extends Entidade{

    private int idDenuncia;
    private String Descricao;
    private int idPost;
    private int IdStatus;

    public int getIdDenuncia() {
        return idDenuncia;
    }

    public void setIdDenuncia(int idCom) {
        this.idDenuncia = idCom;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String conteudo) {
        this.Descricao = conteudo;
    }

    public int getIdStatus() {
        return IdStatus;
    }

    public void setIdStatus(int idPost) {
        this.IdStatus = idPost;
    }


}
