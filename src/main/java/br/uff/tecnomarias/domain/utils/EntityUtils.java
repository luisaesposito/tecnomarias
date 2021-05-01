package br.uff.tecnomarias.domain.utils;

import org.springframework.web.context.annotation.ApplicationScope;

import java.util.function.Consumer;

@ApplicationScope
public class EntityUtils {

    public static <T> void setIfNotNull(final Consumer<T> setter, final T value) {
        if (value != null)
            setter.accept(value);
    }
}
