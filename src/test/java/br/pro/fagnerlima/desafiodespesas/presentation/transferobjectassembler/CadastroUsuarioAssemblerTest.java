package br.pro.fagnerlima.desafiodespesas.presentation.transferobjectassembler;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import br.pro.fagnerlima.desafiodespesas.domain.model.usuario.entity.Usuario;
import br.pro.fagnerlima.desafiodespesas.domain.model.usuario.valueobject.InformacaoPessoal;
import br.pro.fagnerlima.desafiodespesas.presentation.datatransferobject.CadastroUsuarioTO;

@RunWith(SpringRunner.class)
public class CadastroUsuarioAssemblerTest {

    @Test
    public void obterUsuario() {
        CadastroUsuarioTO cadastroUsuarioTO = new CadastroUsuarioTO("test@email.com", "p@ssw0rd",
                new InformacaoPessoal("Test", "12345678901", LocalDate.parse("1991-01-01")));
        Usuario usuario = (new CadastroUsuarioAssembler()).getEntity(cadastroUsuarioTO);

        assertThat(usuario, instanceOf(Usuario.class));
        assertEquals(usuario.getEmail(), cadastroUsuarioTO.getEmail());
        assertEquals(usuario.getSenha(), cadastroUsuarioTO.getSenha());
        assertEquals(usuario.getInformacaoPessoal(), cadastroUsuarioTO.getInformacaoPessoal());
    }

    @Test(expected = NullPointerException.class)
    public void lancarNullPointerException() {
        assertThat((new CadastroUsuarioAssembler()).getEntity(null), instanceOf(Usuario.class));
    }
}
