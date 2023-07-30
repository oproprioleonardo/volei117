package com.magistrados.internal.repositories;

import com.magistrados.api.database.ConnectionProvider;
import com.magistrados.api.repositories.GameSetRepository;
import com.magistrados.models.GameSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class PsqlGameSetRepository implements GameSetRepository {

    private final ConnectionProvider connectionProvider;

    public PsqlGameSetRepository(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public void create(GameSet object) {
        try (final Connection con = this.connectionProvider.getConnection()) {
            final PreparedStatement st = con.prepareStatement("insert into volei_sets (id_partida, ordem, pontos_a, pontos_b, " +
                    "finalizado, vencedor)" +
                    " values (?,?,?,?,?,?) RETURNING id");

            st.setLong(1, object.getPartida().getId());
            st.setInt(2, object.getOrdem());
            st.setInt(3, object.getPontosTimeA());
            st.setInt(4, object.getPontosTimeB());
            st.setBoolean(5, object.isFinalizado());
            st.setString(6, object.getVencedor());

            try (ResultSet generatedKeys = st.executeQuery()) {
                if (generatedKeys.next())
                    object.setId(generatedKeys.getLong(1));
                else
                    throw new SQLException("Creating 'set' failed, no ID obtained.");
            }

            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public GameSet findById(Long object) {
        final GameSet gameSet = new GameSet();
        try (final Connection con = this.connectionProvider.getConnection()) {
            final PreparedStatement st = con.prepareStatement("select * from volei_sets where id=?");
            st.setLong(1, object);
            final ResultSet rs;
            rs = st.executeQuery();
            if (rs.next()) {
                gameSet.setId(rs.getLong("id"));
                gameSet.setIdPartida(rs.getLong("id_partida"));
                gameSet.setOrdem(rs.getInt("ordem"));
                gameSet.setPontosTimeA(rs.getInt("pontos_a"));
                gameSet.setPontosTimeB(rs.getInt("pontos_b"));
                gameSet.setFinalizado(rs.getBoolean("finalizado"));
                gameSet.setVencedor(rs.getString("vencedor"));
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gameSet;
    }

    @Override
    public void update(GameSet object) {
        try (final Connection con = this.connectionProvider.getConnection()) {
            final PreparedStatement st = con.prepareStatement("UPDATE volei_sets SET id_partida = ?, ordem = ?, pontos_a = ?, pontos_b = ?, finalizado = ?, vencedor = ? WHERE id = ?");
            st.setLong(1, object.getPartida().getId());
            st.setInt(2, object.getOrdem());
            st.setInt(3, object.getPontosTimeA());
            st.setInt(4, object.getPontosTimeB());
            st.setBoolean(5, object.isFinalizado());
            st.setString(6, object.getVencedor());
            st.setLong(7, object.getId());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Long object) {
        try (final Connection con = this.connectionProvider.getConnection()) {
            final PreparedStatement st = con.prepareStatement("DELETE FROM volei_sets WHERE id = ?");
            st.setLong(1, object);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(GameSet object) {
        if (object.isCreated()) this.update(object);
        else this.create(object);
    }

    @Override
    public List<GameSet> findAllSetsByMatchId(Long matchId) {
        final List<GameSet> gameSets = new ArrayList<>();
        try (final Connection con = this.connectionProvider.getConnection()) {
            final PreparedStatement st = con.prepareStatement("select * from volei_sets where id_partida=?");
            st.setLong(1, matchId);
            final ResultSet rs;
            rs = st.executeQuery();
            while (rs.next()) {
                final GameSet gameSet = new GameSet();
                gameSet.setId(rs.getLong("id"));
                gameSet.setIdPartida(rs.getLong("id_partida"));
                gameSet.setOrdem(rs.getInt("ordem"));
                gameSet.setPontosTimeA(rs.getInt("pontos_a"));
                gameSet.setPontosTimeB(rs.getInt("pontos_b"));
                gameSet.setFinalizado(rs.getBoolean("finalizado"));
                gameSet.setVencedor(rs.getString("vencedor"));
                gameSets.add(gameSet);
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gameSets;
    }
}
