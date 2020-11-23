package comum;

public class Foto extends Entidade{
    private int idPost;

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public byte[] getAnexo() {
        return Anexo;
    }

    public void setAnexo(byte[] anexo) {
        Anexo = anexo;
    }

    private byte[] Anexo;
}
