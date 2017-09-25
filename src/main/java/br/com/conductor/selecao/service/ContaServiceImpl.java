package br.com.conductor.selecao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.conductor.selecao.model.Conta;
import br.com.conductor.selecao.repository.ContaRepository;

@Service("contaService")
@Transactional
public class ContaServiceImpl implements ContaService {

	@Autowired
	private ContaRepository contaRepository;

	@Override
	public Conta pesquisarContaPorId(Long id) {
		return contaRepository.findOne(id);
	}

	@Override
	public void salvarConta(Conta conta) {
		contaRepository.save(conta);
	}

	@Override
	public void alterarConta(Conta conta) {
		salvarConta(conta);
	}

	@Override
	public List<Conta> listarContas() {
		return contaRepository.findAll();
	}

	@Override
	public boolean verificarContaExiste(Conta conta) {
		return contaRepository.findByBancoAndAgenciaAndConta(conta.getBanco(), conta.getAgencia(),
				conta.getConta()) != null;
	}

}
