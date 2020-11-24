package business;

import comum.Postagem;
import comum.Usuario;
import comum.enums.TipoUsuario;

public class DadosDaSecao {

    private DadosDaSecao(){
        usuarioLogado = null;
        tipoUsuario = null;
    }

    private static DadosDaSecao instance;

    public static DadosDaSecao getInstance(){
        if(instance == null){
           instance = new DadosDaSecao();
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

    private Postagem postagem = new Postagem();
    public Postagem getPostagem() {return postagem;}

    public void setIdPostagem(int postagem){
        this.postagem.setId(postagem);
    }


}
