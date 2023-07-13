package com.magistrados;

import com.magistrados.config.DatabaseConfig;
import com.magistrados.config.DatabaseName;
import com.magistrados.internal.database.HikariPostgresConnectionProvider;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        final DatabaseConfig databaseConfig = new DatabaseConfig();
        databaseConfig.setDatabase(System.getenv("database"));
        databaseConfig.setDbname(DatabaseName.valueOf(System.getenv("db_id")));
        databaseConfig.setHost(System.getenv("host"));
        databaseConfig.setPassword(System.getenv("password"));
        databaseConfig.setUser(System.getenv("user"));
        final HikariPostgresConnectionProvider provider = new HikariPostgresConnectionProvider(databaseConfig);
        // todo criar repositories e services


    }
}