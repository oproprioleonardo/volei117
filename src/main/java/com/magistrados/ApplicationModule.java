package com.magistrados;

import com.magistrados.api.database.ConnectionProvider;
import com.magistrados.api.repositories.GameSetRepository;
import com.magistrados.api.repositories.JogadorRepository;
import com.magistrados.api.repositories.PartidaRepository;
import com.magistrados.api.repositories.TimeRepository;
import com.magistrados.config.DatabaseConfig;
import com.magistrados.config.DatabaseName;
import com.magistrados.graph.manager.ManagerFrame;
import com.magistrados.internal.database.HikariMysqlConnectionProvider;
import com.magistrados.internal.database.HikariPostgresConnectionProvider;
import com.magistrados.internal.repositories.PsqlGameSetRepository;
import com.magistrados.internal.repositories.PsqlJogadorRepository;
import com.magistrados.internal.repositories.PsqlPartidaRepository;
import com.magistrados.internal.repositories.PsqlTimeRepository;
import com.magistrados.services.GameSetService;
import com.magistrados.services.JogadorService;
import com.magistrados.services.PartidaService;
import com.magistrados.services.TimeService;
import io.github.cdimascio.dotenv.Dotenv;

import javax.swing.*;

public class ApplicationModule {
    //teste


    public ApplicationModule() {
        final Dotenv dotenv = this.providesDotenv();
        final DatabaseConfig config = this.providesDatabaseConfig(dotenv);
        final ConnectionProvider connectionProvider = this.providesConnectionProvider(config);
        final TimeRepository timeRepository = this.providesTimeRepository(connectionProvider);
        final JogadorRepository jogadorRepository = this.providesJogadorRepository(connectionProvider);
        final PartidaRepository partidaRepository = this.providesPartidaRepository(connectionProvider);
        final GameSetRepository gameSetRepository = this.providesGameSetRepository(connectionProvider);
        final JogadorService jogadorService = new JogadorService(jogadorRepository);
        final TimeService timeService = new TimeService(timeRepository, jogadorService);
        final GameSetService gameSetService = new GameSetService(gameSetRepository);
        final PartidaService partidaService = new PartidaService(partidaRepository, gameSetService, timeService);

        


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

    private TimeRepository providesTimeRepository(ConnectionProvider connectionProvider) {
        return new PsqlTimeRepository(connectionProvider);
    }

    private JogadorRepository providesJogadorRepository(ConnectionProvider connectionProvider) {
        return new PsqlJogadorRepository(connectionProvider);
    }

    private PartidaRepository providesPartidaRepository(ConnectionProvider connectionProvider) {
        return new PsqlPartidaRepository(connectionProvider);
    }

    private GameSetRepository providesGameSetRepository(ConnectionProvider connectionProvider) {
        return new PsqlGameSetRepository(connectionProvider);
    }

    private JFrame providesManagerFrame() {
        return new ManagerFrame();
    }
}
