package business;

import comum.Postagem;
import comum.Status;
import comum.Usuario;
import comum.enums.TipoUsuario;
import jdk.jshell.Snippet;

import java.util.ArrayList;
import java.util.List;

public class DefinicoesPadrao {

    private DefinicoesPadrao(){
        usuarioLogado = null;
        tipoUsuario = null;
        tagsExistentes = new ArrayList<String>();
        //fazer: puxa do banco as tags
    }

    public int getCriterioPesquisaPost() {
        return CriterioPesquisaPost;
    }

    public void setCriterioPesquisaPost(int criterioPesquisaPost) {
        CriterioPesquisaPost = criterioPesquisaPost;
    }

    //0 -> Titulo, 1 -> Conteudo, 2 -> Usuarios
    private int CriterioPesquisaPost;



    public List<String> getTagsExistentes() {
        return tagsExistentes;
    }

    private List<String> tagsExistentes;

    private static DefinicoesPadrao instance;

    public static DefinicoesPadrao getInstance(){
        if(instance == null){
           instance = new DefinicoesPadrao();
        }
        return instance;
    }

    private TipoUsuario tipoUsuario;

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    private Usuario usuarioLogado;

    public Usuario getUsuarioLogado(){
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
        this.tipoUsuario = TipoUsuario.getTipoUsuarioPorId(usuarioLogado.getIdTipoDeUsuario());
    }

    public void DeslogarUsuario(){
        this.usuarioLogado = null;
        this.tipoUsuario = null;
    }

    private Postagem postagem;
    public Postagem getPostagem() {return postagem;}

    public void setIdPostagem(int postagem){
        this.postagem.setId(postagem);
    }

    private Status status;
    public Status getStatus() {return  status;}

    public void setStatus(String status){
        this.status.setDescricao(status);
    }
}
