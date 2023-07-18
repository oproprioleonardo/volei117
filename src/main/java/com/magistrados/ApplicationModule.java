package com.magistrados;

import com.magistrados.api.database.ConnectionProvider;
import com.magistrados.api.repositories.*;
import com.magistrados.api.database.config.DatabaseConfig;
import com.magistrados.api.database.config.DatabaseName;
import com.magistrados.graph.screens.menuinicial.MenuInicial;
import com.magistrados.internal.database.HikariMysqlConnectionProvider;
import com.magistrados.internal.database.HikariPostgresConnectionProvider;
import com.magistrados.internal.repositories.*;
import com.magistrados.services.*;
import io.github.cdimascio.dotenv.Dotenv;

import javax.swing.*;

public class ApplicationModule {


    public ApplicationModule() {
        final Dotenv dotenv = this.providesDotenv();
        final DatabaseConfig config = this.providesDatabaseConfig(dotenv);
        final ConnectionProvider connectionProvider = this.providesConnectionProvider(config);
        final TimeRepository timeRepository = this.providesTimeRepository(connectionProvider);
        final JogadorRepository jogadorRepository = this.providesJogadorRepository(connectionProvider);
        final MatchPlayerStatsRepository matchPlayerStatsRepository = this.providesPlayerStatsRepository(connectionProvider);
        final PartidaRepository partidaRepository = this.providesPartidaRepository(connectionProvider);
        final GameSetRepository gameSetRepository = this.providesGameSetRepository(connectionProvider);
        final MatchPlayerStatsService statsService = new MatchPlayerStatsService(matchPlayerStatsRepository);
        final JogadorService jogadorService = new JogadorService(jogadorRepository, statsService);
        final TimeService timeService = new TimeService(timeRepository, jogadorService);
        final GameSetService gameSetService = new GameSetService(gameSetRepository);
        final PartidaService partidaService = new PartidaService(partidaRepository, gameSetService, timeService);


        final JFrame menuInicial = this.providesMenuInicial(jogadorService);
        menuInicial.setVisible(true);
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

    private MatchPlayerStatsRepository providesPlayerStatsRepository(ConnectionProvider connectionProvider) {
        return new PsqlMatchPlayerStatsRepository(connectionProvider);
    }

    private PartidaRepository providesPartidaRepository(ConnectionProvider connectionProvider) {
        return new PsqlPartidaRepository(connectionProvider);
    }

    private GameSetRepository providesGameSetRepository(ConnectionProvider connectionProvider) {
        return new PsqlGameSetRepository(connectionProvider);
    }

    private JFrame providesMenuInicial(JogadorService jogadorService) {
        return new MenuInicial(jogadorService);
    }
}
