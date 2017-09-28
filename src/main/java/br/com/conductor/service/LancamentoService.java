package br.com.conductor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.conductor.bean.Conta;
import br.com.conductor.bean.Lancamento;
import br.com.conductor.dto.ResponseLancamentoDTO;
import br.com.conductor.repository.ContaRepository;
import br.com.conductor.repository.LancamentoRepository;
import br.com.conductor.util.ResponseEnun;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository repositoryLancamento;
	
	@Autowired
	private ContaRepository repoConta;
	
	
	public List<ResponseLancamentoDTO> historicoMovimentacao(Long numeroConta) {
		 List<Lancamento> lancamentos = null;
		 List<ResponseLancamentoDTO> responseList = new ArrayList<>();
		 ResponseLancamentoDTO response = null;
		 
		try {
			Conta conta = repoConta.findByNumeroConta(numeroConta);
			if (conta != null) {
				lancamentos = repositoryLancamento.findByNumeroConta(numeroConta);
				if (!lancamentos.isEmpty()) {
					for (Lancamento lancamento : lancamentos) {
						ResponseLancamentoDTO responseLancamento = new ResponseLancamentoDTO();
						responseLancamento.setDataLancamento(lancamento.getDataLancamento());
						responseLancamento.setDescricao(lancamento.getDescricao());
						responseLancamento.setValor(lancamento.getValor());
						responseList.add(responseLancamento);
					}
				} else {
					response = new ResponseLancamentoDTO(ResponseEnun.SEM_LANCAMENTO.getCodigo(), 
							ResponseEnun.SEM_LANCAMENTO.getMensagem());
					responseList.add(response);
				}
			} else {
				response = new ResponseLancamentoDTO(ResponseEnun.CONTA_NAO_EXISTE.getCodigo(), 
						ResponseEnun.CONTA_NAO_EXISTE.getMensagem());
				responseList.add(response);
			}
		} catch (Exception e) {
			response = new ResponseLancamentoDTO(ResponseEnun.ERRO.getCodigo(), ResponseEnun.ERRO.getMensagem());
			responseList.add(response);
			return responseList;
		}
		return responseList;
	}

}
