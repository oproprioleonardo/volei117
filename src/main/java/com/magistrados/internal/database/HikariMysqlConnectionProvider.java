package com.magistrados.internal.database;

import com.magistrados.api.database.ConnectionProvider;
import com.magistrados.api.config.DatabaseConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariMysqlConnectionProvider implements ConnectionProvider {

    private final HikariDataSource dataSource;

    public HikariMysqlConnectionProvider(DatabaseConfig databaseConfig) {
        final HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:mysql://" + databaseConfig.getHost() + "/" + databaseConfig.getDatabase());
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
