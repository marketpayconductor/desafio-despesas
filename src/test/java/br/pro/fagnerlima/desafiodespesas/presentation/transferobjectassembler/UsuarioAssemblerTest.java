package br.pro.fagnerlima.desafiodespesas.presentation.transferobjectassembler;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import br.pro.fagnerlima.desafiodespesas.domain.model.usuario.entity.Usuario;
import br.pro.fagnerlima.desafiodespesas.domain.model.usuario.valueobject.InformacaoPessoal;
import br.pro.fagnerlima.desafiodespesas.presentation.datatransferobject.UsuarioTO;

@RunWith(SpringRunner.class)
public class UsuarioAssemblerTest {

    @Test
    public void obterUsuarioTO() {
        Usuario usuario = new Usuario("test@email.com", "p@ssw0rd",
                new InformacaoPessoal("Test", "12345678901", LocalDate.parse("1990-01-01")), true);
        UsuarioTO usuarioTO = (new UsuarioAssembler()).getData(usuario);

        assertThat(usuarioTO, instanceOf(UsuarioTO.class));
        assertEquals(usuarioTO.getEmail(), usuario.getEmail());
        assertEquals(usuarioTO.getInformacaoPessoal(), usuario.getInformacaoPessoal());
        assertEquals(usuarioTO.isAtivo(), usuario.isAtivo());
    }

    @Test(expected = NullPointerException.class)
    public void lancarNullPointerException() {
        assertThat((new UsuarioAssembler()).getData(null), instanceOf(UsuarioTO.class));
    }
}
