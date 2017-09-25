package br.com.conductor.despesasapp.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class ApiErrors {
	private List<String> errors;

	public ApiErrors() {
	}

	public ApiErrors(Errors errs) {
		this.errors = new ArrayList<>();
		for (ObjectError obj : errs.getAllErrors()) {
			this.errors.add(obj.getDefaultMessage());
		}
	}

	public ApiErrors(List<String> errors) {
		this.errors = errors;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}