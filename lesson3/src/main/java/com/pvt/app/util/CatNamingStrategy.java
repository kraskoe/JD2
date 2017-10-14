package com.pvt.app.util;

import org.apache.maven.shared.utils.StringUtils;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

/**
 * Created by Yauheni Krasko on 14.10.2017.
 */
public class CatNamingStrategy implements PhysicalNamingStrategy {
    @Override
    public Identifier toPhysicalCatalogName(Identifier identifier, JdbcEnvironment jdbcEnv) {
        return identifier;
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier identifier, JdbcEnvironment jdbcEnv) {
        return convertColumn(identifier);
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier identifier, JdbcEnvironment jdbcEnv) {
        return identifier;
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier identifier, JdbcEnvironment jdbcEnv) {
        return identifier;
    }

    @Override
    public Identifier toPhysicalTableName(Identifier identifier, JdbcEnvironment jdbcEnv) {
        return convertTable(identifier);
    }

    private Identifier convertTable(Identifier identifier) {
        if (identifier == null || StringUtils.isBlank(identifier.getText())) {
            return identifier;
        }

        String newName = "T_" + identifier.getText().toUpperCase();
        return Identifier.toIdentifier(newName);
    }

    private Identifier convertColumn(Identifier identifier) {
        if (identifier == null || StringUtils.isBlank(identifier.getText())) {
            return identifier;
        }

        String newName = "F_" + identifier.getText().toUpperCase();
        return Identifier.toIdentifier(newName);
    }

//    public String classToTableName(String className) {
//        return "T_" + super.classToTableName(className).toUpperCase();
//    }
//    public String propertyToColumnName(String propertyName) {
//        return "T_" + super.propertyToColumnName(propertyName).toUpperCase();
//    }
//    public String columnName(String columnName) {
//        return columnName;
//    }
//    public String tableName(String tableName) {
//        return tableName;
//    }
}
