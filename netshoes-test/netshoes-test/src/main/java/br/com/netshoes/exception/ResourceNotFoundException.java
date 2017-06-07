package br.com.netshoes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Recurso não encontrado!")
public class ResourceNotFoundException extends RuntimeException {

}
