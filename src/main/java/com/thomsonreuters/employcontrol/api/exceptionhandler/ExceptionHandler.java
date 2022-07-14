package com.thomsonreuters.employcontrol.api.exceptionhandler;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
    return handleExceptionInternal(
        ex, new Erro(messageUser, messageDevelope), headers, HttpStatus.BAD_REQUEST, request);
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
