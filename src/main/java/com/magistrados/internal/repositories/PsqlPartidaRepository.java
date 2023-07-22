package com.magistrados.internal.repositories;

import com.magistrados.api.database.ConnectionProvider;
import com.magistrados.api.repositories.PartidaRepository;
import com.magistrados.models.Partida;

import java.sql.*;

public class PsqlPartidaRepository implements PartidaRepository {

    private final ConnectionProvider connectionProvider;

    public PsqlPartidaRepository(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public void create(Partida object) {
        try (final Connection con = this.connectionProvider.getConnection()) {
            final String sql = "INSERT INTO volei_partidas(id_time_a, id_time_b, local, qntd_sets, data_hora, vencedor) VALUES (?,?,?,?,?,?) RETURNING id";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setLong(1, object.getIdTimeA());
            st.setLong(2, object.getIdTimeB());
            st.setString(3, object.getLocal());
            st.setInt(4, object.getQuantidadeSets());
            st.setTimestamp(5, Timestamp.valueOf(object.getDateTime()));
            st.setString(6, object.getVencedor());

            try (ResultSet generatedKeys = st.executeQuery()) {
                if (generatedKeys.next())
                    object.setId(generatedKeys.getLong(1));
                else
                    throw new SQLException("Creating 'partida' failed, no ID obtained.");
            }

            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Partida findById(Long object) {
        final Partida partida = new Partida();
        try (final Connection con = this.connectionProvider.getConnection()) {
            final String sql = "SELECT * FROM volei_partidas WHERE id = ?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setLong(1, object);
            final ResultSet rs = st.executeQuery();
            if (rs.next()) {
                partida.setId(rs.getLong("id"));
                partida.setLocal(rs.getString("local"));
                partida.setQuantidadeSets(rs.getInt("qntd_sets"));
                partida.setIdTimeA(rs.getLong("id_time_a"));
                partida.setIdTimeB(rs.getLong("id_time_b"));
                partida.setVencedor(rs.getString("vencedor"));
                partida.setDateTime(rs.getTimestamp("data_hora").toLocalDateTime());
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return partida;
    }

    @Override
    public void update(Partida object) {
        try (final Connection con = this.connectionProvider.getConnection()) {
            final String sql = "UPDATE volei_partidas SET id_time_a = ?, id_time_b = ?, local = ?, qntd_sets = ?, data_hora = ?, vencedor = ? WHERE id = ?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setLong(1, object.getIdTimeA());
            st.setLong(2, object.getIdTimeB());
            st.setString(3, object.getLocal());
            st.setInt(4, object.getQuantidadeSets());
            st.setTimestamp(5, Timestamp.valueOf(object.getDateTime()));
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
            final String sql = "DELETE FROM volei_partidas WHERE id = ?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setLong(1, object);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Partida object) {
        if (object.isCreated()) this.update(object);
        else this.create(object);
    }
}
