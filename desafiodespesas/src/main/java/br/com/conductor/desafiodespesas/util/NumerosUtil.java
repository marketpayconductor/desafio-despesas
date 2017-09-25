package br.com.conductor.desafiodespesas.util;

public class NumerosUtil {
	
	public String formatarCpf(String cpf) {
		String cpfAuxiliar = String.format("xxx.xxx.xxx-xx", cpf);
		return cpfAuxiliar;		
	}

}
