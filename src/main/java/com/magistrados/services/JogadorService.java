package com.magistrados.services;

import com.magistrados.api.repositories.JogadorRepository;
import com.magistrados.exceptions.EntityNotFoundException;
import com.magistrados.models.Jogador;
import com.magistrados.models.create.CreatePlayer;
import com.magistrados.models.edit.EditPlayer;
import com.magistrados.models.find.FindPlayer;
import com.magistrados.models.remove.RemovePlayer;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.HashSet;
import java.util.Set;

public class JogadorService {

    private final JogadorRepository jogadorRepository;
    private final MatchPlayerStatsService matchPlayerStatsService;

    public JogadorService(JogadorRepository jogadorRepository, MatchPlayerStatsService matchPlayerStatsService) {
        this.jogadorRepository = jogadorRepository;
        this.matchPlayerStatsService = matchPlayerStatsService;
    }

    public Jogador criarJogador(CreatePlayer createPlayer) {
        final Jogador jogador = new Jogador();
        jogador.setNome(createPlayer.getNome());
        jogador.setNumeroJogador(createPlayer.getNumero());
        jogador.setTimeId(createPlayer.getIdTime());
        jogador.setQuantidadePontos(createPlayer.getPontos());
        jogador.setQuantidadeDefesas(createPlayer.getDefesas());
        jogador.setQuantidadeSaques(createPlayer.getSaques());
        jogador.setQuantidadeBloqueios(createPlayer.getBloqueios());
        jogador.setPartidasJogadas(createPlayer.getPartidasJogadas());

        this.jogadorRepository.save(jogador);
        return jogador;
    }

    public Jogador editarJogador(Long id, EditPlayer editPlayer) {
        final Jogador jogador = this.buscarJogador(id);

        jogador.setTimeId(editPlayer.getIdTime());
        jogador.setNome(editPlayer.getNome());
        jogador.setNumeroJogador(editPlayer.getNumero());
        jogador.setQuantidadePontos(editPlayer.getPontos());
        jogador.setQuantidadeDefesas(editPlayer.getDefesas());
        jogador.setQuantidadeBloqueios(editPlayer.getBloqueios());
        jogador.setQuantidadeSaques(editPlayer.getSaques());
        jogador.setPartidasJogadas(editPlayer.getPartidasJogadas());

        this.jogadorRepository.save(jogador);
        return jogador;
    }

    public Jogador buscarJogador(FindPlayer findPlayer) {
        Jogador jogador = this.buscarJogador(findPlayer.nome());
        if (jogador == null) {
            if (NumberUtils.isCreatable(findPlayer.id())) {
                jogador = this.buscarJogador(findPlayer.getId());
            } else if (NumberUtils.isCreatable(findPlayer.numero()) && NumberUtils.isCreatable(findPlayer.id_time())) {
                jogador = this.buscarJogadorPorNumero(findPlayer.getIdTime(), findPlayer.getNumero());
            }
        }
        return jogador;
    }

    public void removerJogador(RemovePlayer removePlayer) {
        final Jogador jogador = this.buscarJogador(new FindPlayer(removePlayer.id(), removePlayer.id_time(), removePlayer.numero(), ""));

        if (jogador != null) {
            jogador.getMatchPlayerStats().forEach(this.matchPlayerStatsService::deleteMatchPlayerStats);
            jogador.setMatchPlayerStats(new HashSet<>());
            this.jogadorRepository.deleteById(jogador.getId());
        }
    }


    public void salvarJogador(Jogador jogador) {
        this.jogadorRepository.save(jogador);
        jogador.getMatchPlayerStats().forEach(matchPlayerStatsService::saveMatchPlayerStats);
    }


    public Jogador buscarJogador(Long id) {
        if (id == null) return null;
        final Jogador jogador = this.jogadorRepository.findById(id);
        if (!jogador.isCreated()) {
            throw new EntityNotFoundException(id.toString(), "jogador");
        }
        jogador.setMatchPlayerStats(this.matchPlayerStatsService.findByPlayerId(jogador.getId()));
        return jogador;
    }

    public Jogador buscarJogador(String nome) {
        if (nome == null || nome.isBlank()) return null;
        final Jogador jogador = this.jogadorRepository.findByName(nome);
        if (!jogador.isCreated()) {
            throw new EntityNotFoundException(nome, "jogador");
        }
        jogador.setMatchPlayerStats(this.matchPlayerStatsService.findByPlayerId(jogador.getId()));
        return jogador;
    }

    public Jogador buscarJogadorPorNumero(Long timeId, Integer numero) {
        final Jogador jogador = this.jogadorRepository.findByNumber(timeId, numero);
        if (!jogador.isCreated())
            throw new EntityNotFoundException(
                    jogador.getTimeId().toString() + ", " + jogador.getNumeroJogador().toString(),
                    "jogador"
            );
        return jogador;
    }

    public Set<Jogador> buscarJogadores(Long idTime) {
        Set<Jogador> allByTeamId = this.jogadorRepository.findAllByTeamId(idTime);
        allByTeamId.forEach(jogador -> jogador.setMatchPlayerStats(this.matchPlayerStatsService.findByPlayerId(jogador.getId())));
        return allByTeamId;
    }

}
