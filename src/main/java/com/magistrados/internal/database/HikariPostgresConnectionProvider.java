package com.magistrados.internal.database;

import com.magistrados.api.database.ConnectionProvider;
import com.magistrados.api.database.config.DatabaseConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariPostgresConnectionProvider implements ConnectionProvider {
    private final HikariDataSource dataSource;

    public HikariPostgresConnectionProvider(DatabaseConfig databaseConfig) {
        final HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:postgresql://" + databaseConfig.getHost() + "/" + databaseConfig.getDatabase());
        config.setUsername(databaseConfig.getUser());
        config.setPassword(databaseConfig.getPassword());

        config.setMaximumPoolSize(10); // Número máximo de conexões no pool
        config.setMinimumIdle(2);      // Número mínimo de conexões inativas no pool
        config.setIdleTimeout(30000);  // Tempo máximo em milissegundos para uma conexão ficar inativa no pool

        this.dataSource = new HikariDataSource(config);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }
}
