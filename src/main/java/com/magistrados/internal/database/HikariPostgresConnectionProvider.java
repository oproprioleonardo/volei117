package com.magistrados.internal.database;

import com.magistrados.api.database.ConnectionProvider;
import com.magistrados.config.DatabaseConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariPostgresConnectionProvider implements ConnectionProvider {
    private final HikariDataSource dataSource;

    public HikariPostgresConnectionProvider(DatabaseConfig databaseConfig) {
        final HikariConfig config = new HikariConfig();

        config.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
        config.setJdbcUrl("jdbc:postgresql://" + databaseConfig.getHost() + "/" + databaseConfig.getDatabase());
        config.setUsername(databaseConfig.getUser());
        config.setPassword(databaseConfig.getPassword());
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        this.dataSource = new HikariDataSource(config);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }
}
