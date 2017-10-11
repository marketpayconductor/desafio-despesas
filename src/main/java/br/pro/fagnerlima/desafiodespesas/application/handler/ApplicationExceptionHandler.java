package br.pro.fagnerlima.desafiodespesas.application.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.pro.fagnerlima.desafiodespesas.presentation.datatransferobject.ResponseTO;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMessage = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
        List<String> errors = Arrays.asList(errorMessage);
        ResponseTO<Object> responseTO = new ResponseTO<>(errors);

        return handleExceptionInternal(exception, responseTO, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = createErrorsList(exception.getBindingResult());
        ResponseTO<Object> responseTO = new ResponseTO<>(errors);

        return handleExceptionInternal(exception, responseTO, headers, status, request);
    }

    @ExceptionHandler({ EmptyResultDataAccessException.class })
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException exception,
            WebRequest request) {
        List<String> errors = Arrays.asList(exception.getMessage());
        ResponseTO<Object> responseTO = new ResponseTO<>(errors);

        return handleExceptionInternal(exception, responseTO, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException exception, WebRequest request) {
        List<String> errors = Arrays
                .asList(messageSource.getMessage("recurso.acesso-negado", null, LocaleContextHolder.getLocale()));
        ResponseTO<Object> responseTO = new ResponseTO<>(errors);

        return handleExceptionInternal(exception, responseTO, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    private List<String> createErrorsList(BindingResult bindingResult) {
        List<String> errors = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String errorMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            errors.add(errorMessage);
        }

        return errors;
    }
}
