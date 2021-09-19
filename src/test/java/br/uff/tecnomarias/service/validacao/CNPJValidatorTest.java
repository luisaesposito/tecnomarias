package br.uff.tecnomarias.service.validacao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class CNPJValidatorTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"07755311000100", "07.755.311/0001-00"})
    void cnpjNuloValido(String cnpj) {
        Set<ConstraintViolation<MockEmpresa>> violations = validator.validate(new MockEmpresa(cnpj));
        Assertions.assertTrue(violations.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"91509901000160", "07755311000200", "07755311000102", "91.509.901/0001-60", "10.259.354/0002-09", "07.755.311/0001-02"})
    void cnpjSemPontuacaoCheck1E2FailInvalido(String cnpj) {
        Set<ConstraintViolation<MockEmpresa>> violations = validator.validate(new MockEmpresa(cnpj));
        List<String> msgs = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(1, violations.size());
        Assertions.assertEquals("CNPJ inválido", msgs.get(0));
    }

    private static class MockEmpresa {
        @CNPJ
        private String cnpj;

        public MockEmpresa(String cnpj) {
            this.cnpj = cnpj;
        }
    }
}

