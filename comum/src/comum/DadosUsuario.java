package comum;

public class DadosUsuario extends Entidade{

    private String Nome;

    private String Sobrenome;

    private int Numteste;


    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getSobrenome() {
        return Sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        Sobrenome = sobrenome;
    }

    public int getNumteste() {
        return Numteste;
    }

    public void setNumteste(int numteste) {
        Numteste = numteste;
    }
}


