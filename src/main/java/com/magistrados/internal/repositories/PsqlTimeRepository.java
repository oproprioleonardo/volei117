package com.magistrados.internal.repositories;

import com.magistrados.api.database.ConnectionProvider;
import com.magistrados.api.repositories.TimeRepository;
import com.magistrados.models.Time;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PsqlTimeRepository implements TimeRepository {
    private final ConnectionProvider connectionProvider;

    public PsqlTimeRepository(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }


    @Override
    public void create(Time time) {
        try (final Connection con = this.connectionProvider.getConnection()) {
            final PreparedStatement st = con.prepareStatement("insert into volei_times (nome,vitorias,derrotas)" +
                    " values (?,?,?) RETURNING id");
            st.setString(1, time.getNomeTime());
            st.setInt(2, time.getVitorias());
            st.setInt(3, time.getDerrotas());

            try (ResultSet generatedKeys = st.executeQuery()) {
                if (generatedKeys.next())
                    time.setId(generatedKeys.getLong(1));
                else
                    throw new SQLException("Creating 'time' failed, no ID obtained.");
            }

            st.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Time findById(Long timeId) {
        ResultSet rs;
        Time time = new Time();
        try (final Connection con = this.connectionProvider.getConnection()) {
            final PreparedStatement st = con.prepareStatement("select * from volei_times where id=?");
            st.setLong(1, timeId);
            rs = st.executeQuery();
            if (rs.next()) {
                time.setId(rs.getLong("id"));
                time.setNomeTime(rs.getString("nome"));
                time.setVitorias(rs.getInt("vitorias"));
                time.setDerrotas(rs.getInt("derrotas"));
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return time;
    }

    @Override
    public void update(Time time) {
        try (final Connection con = this.connectionProvider.getConnection()) {
            final PreparedStatement st = con.prepareStatement("update volei_times " +
                    "set nome = ?, vitorias = ?, derrotas = ? where id = ?");
            st.setString(1, time.getNomeTime());
            st.setInt(2, time.getVitorias());
            st.setInt(3, time.getDerrotas());
            st.setLong(4, time.getId());
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long timeId) {
        try (final Connection con = this.connectionProvider.getConnection()) {
            final PreparedStatement st = con.prepareStatement("delete from volei_times where id=?");
            st.setLong(1, timeId);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Time time) {
        if (!time.isCreated()) this.create(time);
        else this.update(time);
    }

    @Override
    public Time findByName(String timeNome) {
        ResultSet rs;
        Time time = new Time();
        try (final Connection con = this.connectionProvider.getConnection()) {
            final PreparedStatement st = con.prepareStatement("select * from volei_times where nome ILIKE ? ");
            st.setString(1, timeNome + "%");
            rs = st.executeQuery();
            if (rs.next()) {
                time.setId(rs.getLong("id"));
                time.setNomeTime(rs.getString("nome"));
                time.setVitorias(rs.getInt("vitorias"));
                time.setDerrotas(rs.getInt("derrotas"));
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return time;
    }
}
