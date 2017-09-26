package br.com.conductor.despesasapp.exception;

import br.com.conductor.despesasapp.util.EnumRetorno;

public class ApplicationException extends Exception {

	private static final long serialVersionUID = 6044987582590920880L;
	
	private String errorCode;
	private String extraMsg;
	
	public ApplicationException(EnumRetorno operation) {
		setErrorCode(operation.getCodigo());
		setExtraMsg("");
	}
	
	public ApplicationException(EnumRetorno operation, String extraMsg) {
		setErrorCode(operation.getCodigo());
		setExtraMsg(extraMsg);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getExtraMsg() {
		return extraMsg;
	}

	public void setExtraMsg(String extraMsg) {
		this.extraMsg = extraMsg;
	}

}
