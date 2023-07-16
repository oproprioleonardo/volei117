package com.magistrados;

import com.magistrados.config.DatabaseConfig;
import com.magistrados.config.DatabaseName;
import com.magistrados.graph.manager.ManagerFrame;
import com.magistrados.internal.database.HikariPostgresConnectionProvider;
import io.github.cdimascio.dotenv.Dotenv;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        final Dotenv dotenv = Dotenv.configure().load();
        final DatabaseConfig databaseConfig = new DatabaseConfig();
        databaseConfig.setDatabase(dotenv.get("DATABASE"));
        databaseConfig.setDbname(DatabaseName.valueOf(Integer.parseInt(dotenv.get("DB_ID"))));
        databaseConfig.setHost(dotenv.get("HOST"));
        databaseConfig.setPassword(dotenv.get("PASSWORD"));
        databaseConfig.setUser(dotenv.get("USER"));
        final HikariPostgresConnectionProvider provider = new HikariPostgresConnectionProvider(databaseConfig);

        // todo criar repositories e services

        ManagerFrame frame = new ManagerFrame();
        frame.setVisible(true);


    }
}