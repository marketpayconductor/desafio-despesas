package br.com.conductor.despesasapp.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.conductor.despesasapp.exception.ApplicationException;
import br.com.conductor.despesasapp.util.ApiErrors;

@ControllerAdvice
public class ExceptionHandlingController {
	
	@Autowired private MessageSource bundle;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        
        String errorMessage = bundle.getMessage(result.getFieldError().getDefaultMessage().replace("{", "").replace("}", ""), null, LocaleContextHolder.getLocale());
        
        List<String> errors = new ArrayList<>();
		errors.add(errorMessage);
        return new ResponseEntity(new ApiErrors(errors), HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<?> processValidationError(Exception ex) {
		ex.printStackTrace();
        return new ResponseEntity(new ApiErrors(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(ApplicationException.class)
    public ResponseEntity<?> processValidationError(ApplicationException ex) {
		ex.printStackTrace();
		String errorMessage = bundle.getMessage(ex.getErrorCode(), null, LocaleContextHolder.getLocale());
		
		List<String> errors = new ArrayList<>();
		errors.add(errorMessage);
        return new ResponseEntity(new ApiErrors(errors), HttpStatus.BAD_REQUEST);
    }
}
