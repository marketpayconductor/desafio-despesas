package br.com.conductor.desafiodespesas.componente;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;
import br.com.conductor.desafiodespesas.domain.conta.Conta;
import br.com.conductor.desafiodespesas.domain.movimentacao.Movimentacao;
import br.com.conductor.desafiodespesas.util.Constantes;

/**
 * 
 * @author luiz
 *
 */

@Component
public class ParserApi {

	
	public Movimentacao parserMovimentacao(int codigoOperacao, BigDecimal valor, String observacao) {
		Movimentacao movimentacao = new Movimentacao();
				
		if(codigoOperacao == Constantes.CODIGO_OPERACAO_RECEITA) {
			movimentacao.setTipoOperacao(Constantes.TIPO_OPERACAO_RECEITA);
			movimentacao.setCodigoDespesa(Constantes.CODIGO_OPERACAO_RECEITA);
		} else if (codigoOperacao == Constantes.CODIGO_OPERACAO_DESPESA) {
			movimentacao.setTipoOperacao(Constantes.TIPO_OPERACAO_DESPESA);
			movimentacao.setCodigoDespesa(Constantes.CODIGO_OPERACAO_DESPESA);
		} else {
			movimentacao.setTipoOperacao(Constantes.TIPO_OPERACAO_TRANSFERENCIA);
			movimentacao.setCodigoDespesa(Constantes.CODIGO_OPERACAO_TRANSFERENCIA);
		}
		movimentacao.setDataMovimentacao(new Date());
		movimentacao.setValor(valor);
		movimentacao.setObservacao(observacao);
		
		return movimentacao;
	}
	
	
	public Conta parserConta(String numeroConta, String cpfCliente, BigDecimal saldo) {
		Conta conta = new Conta();
		conta.setCpf(cpfCliente);
		conta.setNumeroConta(numeroConta);
		conta.setSaldoConta(saldo);
		List<Movimentacao> listaMovimentacao = new ArrayList<>();
		conta.setListaDeMovimentacoes(listaMovimentacao);
		
		return conta;
		
	}
}
