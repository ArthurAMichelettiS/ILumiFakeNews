package comum;


import java.util.ArrayList;
import java.util.List;

public class Postagem extends Entidade {



    private String Titulo;

    private String Conteudo;

    private byte[] Imagem;

    private Anexo imagem;

    public void setImagem(Anexo imagem) {
        this.imagem = imagem;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        List<Tag> tgs = new ArrayList<>();
        for (String tag:
             tags) {
            Tag t = new Tag();
            t.setTag(tag);
            tgs.add(t);
        }
        this.tags = tgs;
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

    public byte[] getImagem() { return Imagem; }

    public void setImagem(byte[] imagem) { Imagem = imagem; }

}


