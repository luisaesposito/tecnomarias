package br.uff.tecnomarias.service.validacao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CNPJValidatorTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void cnpj_nulo_valido() {
        Set<ConstraintViolation<MockEmpresa>> violations = validator.validate(new MockEmpresa(null));
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    void cnpj_sem_pontuacao_valido() {
        Set<ConstraintViolation<MockEmpresa>> violations = validator.validate(new MockEmpresa("07755311000100"));
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    void cnpj_com_pontuacao_valido() {
        Set<ConstraintViolation<MockEmpresa>> violations = validator.validate(new MockEmpresa("07.755.311/0001-00"));
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    void cnpj_sem_pontuacao_check_1_2_fail_invalido() {
        Set<ConstraintViolation<MockEmpresa>> violations = validator.validate(new MockEmpresa("91509901000160"));
        List<String> msgs = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(1, violations.size());
        Assertions.assertEquals("CNPJ inválido", msgs.get(0));
    }

    @Test
    void cnpj_sem_pontuacao_check_1_fail_invalido() {
        Set<ConstraintViolation<MockEmpresa>> violations = validator.validate(new MockEmpresa("07755311000200"));
        List<String> msgs = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(1, violations.size());
        Assertions.assertEquals("CNPJ inválido", msgs.get(0));
    }

    @Test
    void cnpj_sem_pontuacao_check_2_fail_invalido() {
        Set<ConstraintViolation<MockEmpresa>> violations = validator.validate(new MockEmpresa("07755311000102"));
        List<String> msgs = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(1, violations.size());
        Assertions.assertEquals("CNPJ inválido", msgs.get(0));
    }

    @Test
    void cnpj_com_pontuacao_check_1_2_fail_invalido() {
        Set<ConstraintViolation<MockEmpresa>> violations = validator.validate(new MockEmpresa("91.509.901/0001-60"));
        List<String> msgs = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(1, violations.size());
        Assertions.assertEquals("CNPJ inválido", msgs.get(0));
    }

    @Test
    void cnpj_com_pontuacao_check_1_fail_invalido() {
        Set<ConstraintViolation<MockEmpresa>> violations = validator.validate(new MockEmpresa("10.259.354/0002-09"));
        List<String> msgs = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(1, violations.size());
        Assertions.assertEquals("CNPJ inválido", msgs.get(0));
    }

    @Test
    void cnpj_com_pontuacao_check_2_fail_invalido() {
        Set<ConstraintViolation<MockEmpresa>> violations = validator.validate(new MockEmpresa("07.755.311/0001-02"));
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

