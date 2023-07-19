package com.magistrados.internal.repositories;

import com.magistrados.api.database.ConnectionProvider;
import com.magistrados.api.repositories.MatchPlayerStatsRepository;
import com.magistrados.models.MatchPlayerStats;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class PsqlMatchPlayerStatsRepository implements MatchPlayerStatsRepository {
    private final ConnectionProvider connectionProvider;

    public PsqlMatchPlayerStatsRepository(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public void create(MatchPlayerStats object) {
        try (final Connection con = this.connectionProvider.getConnection()) {
            final PreparedStatement st = con.prepareStatement("insert into volei_match_player_stats (id_jogador,id_partida," +
                    "bloqueios,saques,defesas,pontos_feitos)" +
                    " values (?,?,?,?,?,?) RETURNING id");
            st.setLong(1, object.getPlayerId());
            st.setLong(2, object.getPartidaId());
            st.setInt(3, object.getQuantidadeBloqueios());
            st.setInt(4, object.getQuantidadeSaques());
            st.setInt(5, object.getQuantidadeDefesas());
            st.setInt(6, object.getQuantidadePontos());

            try (ResultSet generatedKeys = st.executeQuery()) {
                if (generatedKeys.next())
                    object.setId(generatedKeys.getLong(1));
                else
                    throw new SQLException("Creating 'match player stats' failed, no ID obtained.");
            }

            st.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MatchPlayerStats findById(Long id) {
        final MatchPlayerStats jogadorStats = new MatchPlayerStats();
        try (final Connection con = this.connectionProvider.getConnection()) {
            final String sql = "SELECT * FROM volei_match_player_stats WHERE id = ?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setLong(1, id);
            final ResultSet rs = st.executeQuery();
            while (rs.next()) {
                jogadorStats.setId(id);
                jogadorStats.setPartidaId(rs.getLong("id_partida"));
                jogadorStats.setPlayerId(rs.getLong("id_jogador"));
                jogadorStats.setQuantidadeBloqueios(rs.getInt("bloqueios"));
                jogadorStats.setQuantidadeDefesas(rs.getInt("defesas"));
                jogadorStats.setQuantidadePontos(rs.getInt("pontos_feitos"));
                jogadorStats.setQuantidadeSaques(rs.getInt("saques"));
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jogadorStats;
    }

    @Override
    public Set<MatchPlayerStats> findByPlayerId(Long playerId) {
        final Set<MatchPlayerStats> statsSet = new HashSet<>();
        try (final Connection con = this.connectionProvider.getConnection()) {
            final String sql = "SELECT * FROM volei_match_player_stats WHERE id_jogador = ?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setLong(1, playerId);
            final ResultSet rs = st.executeQuery();
            while (rs.next()) {
                final MatchPlayerStats jogadorStats = new MatchPlayerStats();
                jogadorStats.setPlayerId(playerId);
                jogadorStats.setPartidaId(rs.getLong("id_partida"));
                jogadorStats.setQuantidadeBloqueios(rs.getInt("bloqueios"));
                jogadorStats.setQuantidadeDefesas(rs.getInt("defesas"));
                jogadorStats.setQuantidadePontos(rs.getInt("pontos_feitos"));
                jogadorStats.setQuantidadeSaques(rs.getInt("saques"));
                statsSet.add(jogadorStats);
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statsSet;
    }

    @Override
    public MatchPlayerStats findByPlayerIdAndMatchId(Long playerId, Long matchId) {
        final MatchPlayerStats jogadorStats = new MatchPlayerStats();
        try (final Connection con = this.connectionProvider.getConnection()) {
            final String sql = "SELECT * FROM volei_match_player_stats WHERE id_jogador = ? && id_partida = ?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setLong(1, playerId);
            st.setLong(2, matchId);
            final ResultSet rs = st.executeQuery();
            while (rs.next()) {
                jogadorStats.setId(rs.getLong("id"));
                jogadorStats.setPartidaId(rs.getLong("id_partida"));
                jogadorStats.setPlayerId(rs.getLong("id_jogador"));
                jogadorStats.setQuantidadeBloqueios(rs.getInt("bloqueios"));
                jogadorStats.setQuantidadeDefesas(rs.getInt("defesas"));
                jogadorStats.setQuantidadePontos(rs.getInt("pontos_feitos"));
                jogadorStats.setQuantidadeSaques(rs.getInt("saques"));
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jogadorStats;
    }

    @Override
    public void update(MatchPlayerStats object) {
        try (final Connection con = this.connectionProvider.getConnection()) {
            final String sql = "UPDATE volei_match_player_stats SET " +
                    "id_jogador = ?, id_partida = ?, " +
                    "bloqueios = ?, defesas = ?, saques = ?, pontos_feitos = ? WHERE id = ?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setLong(1, object.getPlayerId());
            st.setLong(2, object.getPartidaId());
            st.setInt(3, object.getQuantidadeBloqueios());
            st.setInt(4, object.getQuantidadeDefesas());
            st.setInt(5, object.getQuantidadeSaques());
            st.setInt(6, object.getQuantidadePontos());
            st.setLong(7, object.getId());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Long id) {
        try (final Connection con = this.connectionProvider.getConnection()) {
            final PreparedStatement st = con.prepareStatement("delete from volei_match_player_stats" +
                    " where id=?");
            st.setLong(1, id);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(MatchPlayerStats object) {
        if (!object.isCreated()) this.create(object);
        else this.update(object);
    }
}
