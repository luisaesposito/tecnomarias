package br.uff.tecnomarias.rest.resource;

import br.uff.tecnomarias.rest.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class ConstraintViolationHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TransactionSystemException.class)
    protected ResponseEntity<ErrorResponse> handleConstraintViolationException(TransactionSystemException ex, ServletWebRequest request) {
        ErrorResponse response;
        String errors;
        Throwable cause = ex.getRootCause();
        if (cause instanceof ConstraintViolationException) {
            Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) cause).getConstraintViolations();
            errors = constraintViolations.stream().map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("; "));

        } else {
            errors = ex.getMessage();
        }
        response = new ErrorResponse.Builder()
                .message("Erros de validação: " + errors)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .error(cause != null ? cause.getClass().getName() : "Bad Request")
                .path(request.getRequest().getRequestURI())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
