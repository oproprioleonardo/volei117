package com.magistrados;

import com.magistrados.api.database.ConnectionProvider;
import com.magistrados.config.DatabaseConfig;
import com.magistrados.config.DatabaseName;
import com.magistrados.graph.manager.ManagerFrame;
import com.magistrados.internal.database.HikariMysqlConnectionProvider;
import com.magistrados.internal.database.HikariPostgresConnectionProvider;
import io.github.cdimascio.dotenv.Dotenv;

import javax.swing.*;

public class ApplicationModule {


    public ApplicationModule() {
        final Dotenv dotenv = this.providesDotenv();
        final DatabaseConfig config = this.providesDatabaseConfig(dotenv);
        final ConnectionProvider connectionProvider = this.providesConnectionProvider(config);


        final JFrame managerFrame = this.providesManagerFrame();
        managerFrame.setVisible(true);
    }

    private Dotenv providesDotenv() {
        return Dotenv.configure().load();
    }

    private DatabaseConfig providesDatabaseConfig(Dotenv dotenv) {
        final DatabaseConfig databaseConfig = new DatabaseConfig();
        databaseConfig.setDatabase(dotenv.get("DATABASE"));
        databaseConfig.setDbname(DatabaseName.valueOf(Integer.parseInt(dotenv.get("DB_ID"))));
        databaseConfig.setHost(dotenv.get("HOST"));
        databaseConfig.setPassword(dotenv.get("PASSWORD"));
        databaseConfig.setUser(dotenv.get("USER"));
        return databaseConfig;
    }

    private ConnectionProvider providesConnectionProvider(DatabaseConfig config) {
        return config.getDbname() == DatabaseName.POSTGRES ?
                new HikariPostgresConnectionProvider(config) :
                new HikariMysqlConnectionProvider(config);
    }

    private JFrame providesManagerFrame() {
        return new ManagerFrame();
    }
}
