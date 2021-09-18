package br.uff.tecnomarias.domain.enums;

import java.util.List;
import java.util.stream.Collectors;

public enum Cargo {
    ESTAGIARIA, JUNIOR, PLENO, SENIOR, GERENTE;

    public static boolean isValid(String value) {
        List<Cargo> cargos = List.of(Cargo.values());
        return cargos.stream().map(Cargo::toString).collect(Collectors.toList()).contains(value);
    }
}
