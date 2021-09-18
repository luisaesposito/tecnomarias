package br.uff.tecnomarias.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@ResponseBody
public class PessoaJuridicaInvalidaException extends RuntimeException {

    public PessoaJuridicaInvalidaException(String message) {
        super(message);
    }

}
