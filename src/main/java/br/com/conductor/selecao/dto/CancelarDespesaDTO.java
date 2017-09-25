package br.com.conductor.selecao.dto;

public class CancelarDespesaDTO {
	
	private long usuarioCancelamento;

	public CancelarDespesaDTO() {
		super();
	}

	public CancelarDespesaDTO(long usuarioCancelamento) {
		super();
		this.usuarioCancelamento = usuarioCancelamento;
	}

	public long getUsuarioCancelamento() {
		return usuarioCancelamento;
	}

	public void setUsuarioCancelamento(long usuarioCancelamento) {
		this.usuarioCancelamento = usuarioCancelamento;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CancelarReceitaDTO [usuarioCancelamento=");
		builder.append(usuarioCancelamento);
		builder.append("]");
		return builder.toString();
	}	
}
