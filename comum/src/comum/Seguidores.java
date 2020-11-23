package comum;

public class Seguidores extends Entidade {
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdUserCon() {
        return idUserCon;
    }

    public void setIdUserCon(int idUserCon) {
        this.idUserCon = idUserCon;
    }

    private int idUser;
    private int idUserCon;
}
