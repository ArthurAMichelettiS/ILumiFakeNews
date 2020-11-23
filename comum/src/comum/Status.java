package comum;

public class Status extends Entidade{

    private int idStatus;
    private String Descricao;


    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String conteudo) {
        this.Descricao = conteudo;
    }

}
