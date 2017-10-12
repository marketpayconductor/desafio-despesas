package br.pro.fagnerlima.desafiodespesas.presentation.transferobjectassembler;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import br.pro.fagnerlima.desafiodespesas.domain.model.conta.entity.Conta;
import br.pro.fagnerlima.desafiodespesas.domain.model.usuario.entity.Usuario;
import br.pro.fagnerlima.desafiodespesas.domain.model.usuario.valueobject.InformacaoPessoal;
import br.pro.fagnerlima.desafiodespesas.presentation.datatransferobject.ContaTO;
import br.pro.fagnerlima.desafiodespesas.presentation.datatransferobject.UsuarioTO;

@RunWith(SpringRunner.class)
public class ContaAssemblerTest {

    @Test
    public void obterContaTO() {
        Usuario usuario = new Usuario("test@email.com", "p@ssw0rd",
                new InformacaoPessoal("Test", "12345678901", LocalDate.now()), true);
        Conta conta = new Conta(usuario);
        ContaTO contaTO = (new ContaAssembler()).getData(conta);

        assertThat(contaTO, instanceOf(ContaTO.class));
        assertEquals(contaTO.getId(), conta.getId());
        assertEquals(contaTO.getUsuario(), conta.getUsuario().getInformacaoPessoal().getNome());
        assertEquals(contaTO.getSaldo(), conta.getSaldo());
        assertEquals(contaTO.isAtivo(), conta.isAtivo());
    }

    @Test(expected = NullPointerException.class)
    public void lancarNullPointerException() {
        assertThat((new ContaAssembler()).getData(null), instanceOf(UsuarioTO.class));
    }
}
