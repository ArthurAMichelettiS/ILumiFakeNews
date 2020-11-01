package business;

import comum.Usuario;
import comum.enums.TipoUsuario;

import java.util.ArrayList;
import java.util.List;

public class DefinicoesPadrao {

    private DefinicoesPadrao(){
        usuarioLogado = null;
        tipoUsuario = null;
        tagsExistentes = new ArrayList<String>();
        //fazer: puxa do banco as tags
    }


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
}
