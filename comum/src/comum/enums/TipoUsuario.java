package comum.enums;

public enum TipoUsuario {
    MODERADOR(0),
    COMUM(1),
    PESQUISADOR(2);

    int idTipo;

    TipoUsuario(int id) {
        this.idTipo = id;
    }

    public int getIdTipoUsuario(){
        return  idTipo;
    }

    public static TipoUsuario getTipoUsuarioPorId(int id){
        switch (id){
            case 0:
                return TipoUsuario.MODERADOR;
            case 1:
                return TipoUsuario.COMUM;
            case 2:
                return TipoUsuario.PESQUISADOR;
            default:
                return null;
        }
    }
}
