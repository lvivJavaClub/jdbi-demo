package com.example.jdbiplayground.data;

import java.sql.Types;
import java.util.UUID;

import org.jdbi.v3.core.argument.AbstractArgumentFactory;
import org.jdbi.v3.core.argument.Argument;
import org.jdbi.v3.core.config.ConfigRegistry;

public class UuidArgumentFactory extends AbstractArgumentFactory<UUID> {

    public UuidArgumentFactory() {
        super(Types.VARCHAR);
    }

    @Override
    protected Argument build(UUID value, ConfigRegistry config) {
        return (position, statement, ctx) -> statement.setString(position, value.toString());
    }
}
