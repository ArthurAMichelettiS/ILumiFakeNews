package comum;


import java.util.ArrayList;
import java.util.List;

public class Postagem extends Entidade {

    //TODO

    private String Titulo;

    private String Conteudo;

    private List<String> Tags;

    private byte[] Imagem;

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

    public List<String> getTags() { return Tags; }

    public void setTags(List<String> tags) { Tags = tags; }
}


