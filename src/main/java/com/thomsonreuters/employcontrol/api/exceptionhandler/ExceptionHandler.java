package com.thomsonreuters.employcontrol.api.exceptionhandler;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

  private final MessageSource messageSource;

  public ExceptionHandler(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
      HttpMessageNotReadableException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {

    String messageUser =
        messageSource.getMessage("message.invalid", null, LocaleContextHolder.getLocale());
    String messageDevelope = ex.getCause().toString();
    List<Erro> erros = Arrays.asList(new Erro(messageUser, messageDevelope));
    return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    List<Erro> erros = createListErros(ex.getBindingResult());
    return handleExceptionInternal(ex, erros, headers, status, request);
  }

  private List<Erro> createListErros(BindingResult bindingResul) {
    List<Erro> erros = new ArrayList<>();
    for (FieldError fieldError : bindingResul.getFieldErrors()) {
      String messageUser = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
      String messageDevelope = fieldError.toString();
      erros.add(new Erro(messageUser, messageDevelope));
    }
    return erros;
  }

  @org.springframework.web.bind.annotation.ExceptionHandler({EmptyResultDataAccessException.class})
  public ResponseEntity<Object> handleEmptyResultDataAccessException(
      EmptyResultDataAccessException ex, WebRequest request) {
    String messageUser =
        messageSource.getMessage("resource.notfound", null, LocaleContextHolder.getLocale());
    String messageDevelope = ex.toString();
    List<Erro> erros = Arrays.asList(new Erro(messageUser, messageDevelope));
    return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  public static class Erro {
    private String messageUser;
    private String messageDevelope;

    public Erro(String messageUser, String messageDevelope) {
      this.messageUser = messageUser;
      this.messageDevelope = messageDevelope;
    }

    public String getMessageUser() {
      return messageUser;
    }

    public String getMessageDevelope() {
      return messageDevelope;
    }
  }
}
