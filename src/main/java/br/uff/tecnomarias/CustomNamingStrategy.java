package br.uff.tecnomarias;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;


public class CustomNamingStrategy
        extends PhysicalNamingStrategyStandardImpl {

    public static final CustomNamingStrategy INSTANCE =
            new CustomNamingStrategy();

    public static final String CAMEL_CASE_REGEX = "([a-z])([A-Z])";

    public static final String SNAKE_CASE_PATTERN = "$1_$2";

    @Override
    public Identifier toPhysicalCatalogName(
            Identifier identifier,
            JdbcEnvironment context) {
        return formatIdentifier(identifier);
    }

    @Override
    public Identifier toPhysicalSchemaName(
            Identifier identifier,
            JdbcEnvironment context) {
        return formatIdentifier(identifier);
    }

    @Override
    public Identifier toPhysicalTableName(
            Identifier identifier,
            JdbcEnvironment context) {
        return formatIdentifier(identifier);
    }

    @Override
    public Identifier toPhysicalSequenceName(
            Identifier identifier,
            JdbcEnvironment context) {
        return formatIdentifier(identifier);
    }

    @Override
    public Identifier toPhysicalColumnName(
            Identifier identifier,
            JdbcEnvironment context) {
        return formatIdentifier(identifier);
    }

    private Identifier formatIdentifier(
            Identifier identifier) {
        if (identifier != null) {
            String name = identifier.getText();

            String formattedName = name
                    .replaceAll(
                            CAMEL_CASE_REGEX,
                            SNAKE_CASE_PATTERN)
                    .toLowerCase();

            return !formattedName.equals(name) ?
                    Identifier.toIdentifier(
                            formattedName,
                            identifier.isQuoted()
                    ) :
                    identifier;
        } else {
            return null;
        }

    }
}