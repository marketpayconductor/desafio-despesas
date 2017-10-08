package com.jarddel.desafio.api.application.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jarddel.desafio.api.application.service.exception.ContaInexistenteException;
import com.jarddel.desafio.api.application.service.exception.InformacaoNaoEncontradaException;
import com.jarddel.desafio.api.application.service.exception.UsuarioJaCadastradoException;
import com.jarddel.desafio.api.domain.exception.SaldoInsuficienteException;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    protected MessageSource messageSource;

    @ExceptionHandler({ InformacaoNaoEncontradaException.class })
    public ResponseEntity<Object> handleInformacaoNaoEncontradaException(InformacaoNaoEncontradaException ex,
            WebRequest request) {
        return handleException(ex, HttpStatus.NOT_FOUND, request, "recurso.informacao-nao-encontrada");
    }

    @ExceptionHandler({ SaldoInsuficienteException.class })
    public ResponseEntity<Object> handleSaldoInsuficienteException(SaldoInsuficienteException ex, WebRequest request) {
        return handleException(ex, HttpStatus.BAD_REQUEST, request, "recurso.lancamento.saldo-insuficiente");
    }

    @ExceptionHandler({ ContaInexistenteException.class })
    public ResponseEntity<Object> handleContaInexistenteException(ContaInexistenteException ex, WebRequest request) {
        return handleException(ex, HttpStatus.BAD_REQUEST, request, "recurso.conta.inexistente");
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> erros = obterListaErros(ex.getBindingResult());
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ UsuarioJaCadastradoException.class })
    public ResponseEntity<Object> handleUsuarioJaCadastradoException(UsuarioJaCadastradoException ex,
            WebRequest request) {
        return handleException(ex, HttpStatus.BAD_REQUEST, request, "recurso.usuario.login-cadastrado");
    }

    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
            WebRequest request) {
        return handleException(ex, HttpStatus.BAD_REQUEST, request, "recurso.operacao-invalida");
    }

    protected ResponseEntity<Object> handleException(Exception ex, HttpStatus httpStatus, WebRequest request,
            String sourceMessage) {
        String mensagem = messageSource.getMessage(sourceMessage, null, LocaleContextHolder.getLocale());
        return handleExceptionInternal(ex, Arrays.asList(mensagem), new HttpHeaders(), httpStatus, request);
    }

    protected List<String> obterListaErros(BindingResult bindingResult) {
        List<String> erros = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            erros.add(messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()));
        }

        return erros;
    }
}
