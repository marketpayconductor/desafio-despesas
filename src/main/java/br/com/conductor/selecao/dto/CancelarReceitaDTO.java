package br.com.conductor.selecao.dto;

public class CancelarReceitaDTO {
	
	private long usuarioCancelamento;

	public CancelarReceitaDTO() {
		super();
	}

	public CancelarReceitaDTO(long usuarioCancelamento) {
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
