package br.com.conductor.selecao.util;


public class ApiErrorMessage {
	
	private String errorMessage;

    public ApiErrorMessage(String errorMessage){        
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
