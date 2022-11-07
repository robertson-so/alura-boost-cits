package br.com.alura.comex.exception;

import java.util.Collections;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class UnauthorizedHandler extends ResponseEntityExceptionHandler {

  @ResponseBody
  @ExceptionHandler(UnauthorizedException.class)
  public ResponseEntity<Object> handlerBusinessException(UnauthorizedException ex,
      WebRequest request) {

    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

    return handleExceptionInternal(
        ex,
        null,
        headers,
        HttpStatus.UNAUTHORIZED,
        request);
  }
}
