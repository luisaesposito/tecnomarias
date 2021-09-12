package br.uff.tecnomarias.service.validacao;

import org.hibernate.validator.internal.constraintvalidators.hv.Mod11CheckValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import javax.validation.ConstraintValidatorContext;

public class CNPJValidatorTest {

    static CNPJValidator validator;

    @Mock
    ConstraintValidatorContext context;

    @Spy
    Mod11CheckValidator modValidator;

    @BeforeAll
    static void setup() {
        validator = new CNPJValidator();
    }

    @Test
    void cnpj_nulo_valido() {
        Assertions.assertTrue(validator.isValid(null, context));
    }

    @Test
    void cnpj_apenas_digitos_valido() {
        String cnpj = "10259354000109";
        boolean valid = validator.isValid(cnpj, context);
        Assertions.assertTrue(valid);
    }

    @Test
    void cnpj_apenas_digitos_invalido() {
        CharSequence cnpj = "10259354000209";
        Assertions.assertFalse(validator.isValid(cnpj, context));
    }

    @Test
    void cnpj_com_pontuacao_valido() {
        CharSequence cnpj = "10.259.354/0001-09";
        Assertions.assertTrue(validator.isValid(cnpj, context));
    }

    @Test
    void cnpj_com_pontuacao_invalido() {
        CharSequence cnpj = "10.259.354/0002-09";
        Assertions.assertFalse(validator.isValid(cnpj, context));
    }
}
