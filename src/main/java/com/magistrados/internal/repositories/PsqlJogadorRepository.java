package com.magistrados.internal.repositories;

import com.magistrados.api.database.ConnectionProvider;
import com.magistrados.api.repositories.JogadorRepository;
import com.magistrados.models.Jogador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PsqlJogadorRepository implements JogadorRepository {

    private final ConnectionProvider connectionProvider;

    public PsqlJogadorRepository(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public void create(Jogador object) {
        try (final Connection con = this.connectionProvider.getConnection()) {
            final String sql = "INSERT INTO volei_jogadores(id_time, nome, numero, bloqueios, defesas, saques, pontos_feitos) VALUES (?,?,?,?,?,?,?)";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setLong(1, object.getTimeId());
            st.setString(2, object.getNome());
            st.setInt(3, object.getNumeroJogador());
            st.setInt(4, object.getQuantidadeBloqueios());
            st.setInt(5, object.getQuantidadeDefesas());
            st.setInt(6, object.getQuantidadeSaques());
            st.setInt(7, object.getQuantidadePontos());
            int affectedRows = st.executeUpdate();

            if (affectedRows == 0)
                throw new SQLException("Creating 'jogador' failed, no rows affected.");
            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next())
                    object.setId(generatedKeys.getLong(1));
                else
                    throw new SQLException("Creating 'jogador' failed, no ID obtained.");
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Jogador findById(Long object) {
        final Jogador jogador = new Jogador();
        try (final Connection con = this.connectionProvider.getConnection()) {
            final String sql = "SELECT * FROM volei_jogadores WHERE id = ?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setLong(1, object);
            final ResultSet rs = st.executeQuery();
            if (rs.next()) {
                jogador.setId(object);
                jogador.setNome(rs.getString("nome"));
                jogador.setTimeId(rs.getLong("id_time"));
                jogador.setNumeroJogador(rs.getInt("numero"));
                jogador.setQuantidadeBloqueios(rs.getInt("bloqueios"));
                jogador.setQuantidadeDefesas(rs.getInt("defesas"));
                jogador.setQuantidadePontos(rs.getInt("pontos_feitos"));
                jogador.setQuantidadeSaques(rs.getInt("saques"));
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jogador;
    }

    @Override
    public void update(Jogador object) {
        try (final Connection con = this.connectionProvider.getConnection()) {
            final String sql = "UPDATE volei_jogadores SET id_time = ?, nome = ?, numero = ?, bloqueios = ?, defesas = ?, saques = ?, pontos_feitos = ? WHERE id = ?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setLong(1, object.getTimeId());
            st.setString(2, object.getNome());
            st.setInt(3, object.getNumeroJogador());
            st.setInt(4, object.getQuantidadeBloqueios());
            st.setInt(5, object.getQuantidadeDefesas());
            st.setInt(6, object.getQuantidadeSaques());
            st.setInt(7, object.getQuantidadePontos());
            st.setLong(8, object.getId());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Long object) {
        try (final Connection con = this.connectionProvider.getConnection()) {
            final String sql = "DELETE FROM volei_jogadores WHERE id = ?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setLong(1, object);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Jogador object) {
        if (!object.isCreated()) this.create(object);
        else this.update(object);
    }

    @Override
    public Jogador findByName(String name) {
        final Jogador jogador = new Jogador();
        try (final Connection con = this.connectionProvider.getConnection()) {
            final String sql = "SELECT * FROM volei_jogadores WHERE name = ?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, name);
            final ResultSet rs = st.executeQuery();
            if (rs.next()) {
                jogador.setId(rs.getLong("id"));
                jogador.setNome(rs.getString("nome"));
                jogador.setTimeId(rs.getLong("id_time"));
                jogador.setNumeroJogador(rs.getInt("numero"));
                jogador.setQuantidadeBloqueios(rs.getInt("bloqueios"));
                jogador.setQuantidadeDefesas(rs.getInt("defesas"));
                jogador.setQuantidadePontos(rs.getInt("pontos_feitos"));
                jogador.setQuantidadeSaques(rs.getInt("saques"));
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jogador;
    }

    @Override
    public Jogador findByNumber(Long timeId, Integer number) {
        final Jogador jogador = new Jogador();
        try (final Connection con = this.connectionProvider.getConnection()) {
            final String sql = "SELECT * FROM volei_jogadores WHERE id_time = ? AND numero = ?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setLong(1, timeId);
            st.setInt(2, number);
            final ResultSet rs = st.executeQuery();
            if (rs.next()) {
                jogador.setId(rs.getLong("id"));
                jogador.setNome(rs.getString("nome"));
                jogador.setTimeId(rs.getLong("id_time"));
                jogador.setNumeroJogador(rs.getInt("numero"));
                jogador.setQuantidadeBloqueios(rs.getInt("bloqueios"));
                jogador.setQuantidadeDefesas(rs.getInt("defesas"));
                jogador.setQuantidadePontos(rs.getInt("pontos_feitos"));
                jogador.setQuantidadeSaques(rs.getInt("saques"));
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jogador;
    }
}
