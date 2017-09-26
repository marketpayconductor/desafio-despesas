package br.com.conductor.despesasapp.api.controller.validation;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.conductor.despesasapp.api.dto.request.CadastroMovimentoRequestDTO;
import br.com.conductor.despesasapp.api.dto.response.CadastroMovimentacaoResponseDTO;
import br.com.conductor.despesasapp.util.EnumRetorno;

@Service
@Component
public class ContaControllerValidation {
	
	public CadastroMovimentacaoResponseDTO validateCadastrarMovimentacao(CadastroMovimentoRequestDTO request) {
		CadastroMovimentacaoResponseDTO response = new CadastroMovimentacaoResponseDTO();
		if (!validarValorNegativo(request)) {
			response.setRetornoOperacao(EnumRetorno.VALOR_MOVIMENTACAO_NEGATIVO.parse());
			return response;
		}
		//TODO demais validações
		return response;
	}

	private boolean validarValorNegativo(CadastroMovimentoRequestDTO request) {
		boolean isAllRight = true;
		if(request.getValor().compareTo(BigDecimal.ZERO) == -1) {
			isAllRight = false;
		}	
		return isAllRight;
	}

}
