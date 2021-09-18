package br.uff.tecnomarias.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@ResponseBody
public class PessoaInvalidaException extends RuntimeException {

    public PessoaInvalidaException(String message) {
        super(message);
    }

}
