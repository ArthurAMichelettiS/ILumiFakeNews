package comum;

public class Status extends Entidade{

    private int idStatus;
    private String Descricao;


    public int getIdDenuncia() {
        return idStatus;
    }

    public void setIdDenuncia(int idCom) {
        this.idStatus = idCom;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String conteudo) {
        this.Descricao = conteudo;
    }

}
