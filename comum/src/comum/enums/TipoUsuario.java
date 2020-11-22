package comum.enums;

public enum TipoUsuario {
    COMUM(0),
    PESQUISADOR(1),
    MODERADOR(2),
    PROBATIO(3);

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
                return TipoUsuario.COMUM;
            case 1:
                return TipoUsuario.PESQUISADOR;
            case 2:
                return TipoUsuario.MODERADOR;
            case 3:
                return TipoUsuario.PROBATIO;
            default:
                return null;
        }
    }
}
