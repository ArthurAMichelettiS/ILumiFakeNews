package business.Testes;

import business.DadosDaSecao;
import comum.Usuario;
import comum.enums.TipoUsuario;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SecaoTest {

    @Test
    public void secaoDeUser() {
        Usuario u = new Usuario();
        u.setId(3);
        u.setIdTipoDeUsuario(0);
        DadosDaSecao.getInstance().setUsuarioLogado(u);
        assertEquals(TipoUsuario.COMUM, DadosDaSecao.getInstance().getTipoUsuario());
        DadosDaSecao.getInstance().DeslogarUsuario();
        assertNull(DadosDaSecao.getInstance().getTipoUsuario());
    }

}
