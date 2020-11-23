package comum;


import java.util.ArrayList;
import java.util.List;

public class Postagem extends Entidade {

    private String Titulo;

    private String Conteudo;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    private int idUser;

    private Foto imagem = new Foto();

    public void setImagem(byte[] imagem) {
        this.imagem.setAnexo(imagem);
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setStringTags(List<String> tags) {
        List<Tag> tgs = new ArrayList<>();
        for (String tag:
             tags) {
            Tag t = new Tag();
            t.setTag(tag);
            tgs.add(t);
        }
        this.tags = tgs;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    private List<Tag> tags;

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getConteudo() {
        return Conteudo;
    }

    public void setConteudo(String conteudo) {
        Conteudo = conteudo;
    }

    public byte[] getImagemBytes() { return imagem.getAnexo(); }

    public Foto getImagem() {
        imagem.setIdPost(getId());
        return imagem;
    }

}


