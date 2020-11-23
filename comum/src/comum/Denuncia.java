package comum;

import java.util.Date;

public class Denuncia extends Entidade{

    private int idDenuncia;
    private String Descricao;
    private int idPost;
    private String StatusDenuncia;

    public int getIdDenuncia() {
        return idDenuncia;
    }

    public void setIdDenuncia(int idDenuncia) {
        this.idDenuncia = idDenuncia;
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

    public String getStatusDenuncia() {
        return StatusDenuncia;
    }

    public void setStatusDenuncia(String idPost) {
        this.StatusDenuncia = idPost;
    }


}
