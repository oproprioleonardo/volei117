package com.magistrados.internal.repositories;

import com.magistrados.api.database.ConnectionProvider;
import com.magistrados.api.repositories.SetRepository;
import com.magistrados.models.Set;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PsqlSetRepository implements SetRepository {

    private final ConnectionProvider connectionProvider;

    public PsqlSetRepository(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public void create(Set object) {
        try (final Connection con = this.connectionProvider.getConnection()) {
            final PreparedStatement st = con.prepareStatement("insert into volei_sets (id_partida, ordem, pontos_a, pontos_b, " +
                    "finalizado, vencedor)" +
                    " values (?,?,?,?,?,?)");
            st.setLong(1, object.getPartida().getId());
            st.setInt(2, object.getOrdem());
            st.setInt(3, object.getPontosTimeA());
            st.setInt(4, object.getPontosTimeB());
            st.setBoolean(5, object.isFinalizado());
            st.setString(6, object.getVencedor());
            int affectedRows = st.executeUpdate();

            if (affectedRows == 0)
                throw new SQLException("Creating 'set' failed, no rows affected.");
            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next())
                    object.setId(generatedKeys.getLong(1));
                else
                    throw new SQLException("Creating 'set' failed, no ID obtained.");
            }

            st.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set findById(Long object) {
        final Set set = new Set();
        try (final Connection con = this.connectionProvider.getConnection()) {
            final PreparedStatement st = con.prepareStatement("select * from volei_sets where id=?");
            st.setLong(1, object);
            final ResultSet rs;
            rs = st.executeQuery();
            if (rs.next()) {
                set.setId(rs.getLong("id"));
                set.setIdPartida(rs.getLong("id_partida"));
                set.setOrdem(rs.getInt("ordem"));
                set.setPontosTimeA(rs.getInt("pontos_a"));
                set.setPontosTimeB(rs.getInt("pontos_b"));
                set.setFinalizado(rs.getBoolean("finalizado"));
                set.setVencedor(rs.getString("vencedor"));
            }
            st.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return set;
    }

    @Override
    public void update(Set object) {
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long object) {
        try (final Connection con = this.connectionProvider.getConnection()) {
            final PreparedStatement st = con.prepareStatement("DELETE FROM volei_sets WHERE id=?");
            st.setLong(1, object);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Set object) {
        if (object.isCreated()) this.update(object);
        else this.create(object);
    }
}
