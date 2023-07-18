package com.magistrados.services;

import com.magistrados.api.repositories.JogadorRepository;
import com.magistrados.exceptions.EntityNotFoundException;
import com.magistrados.models.Jogador;
import com.magistrados.models.create.CreatePlayer;
import com.magistrados.models.edit.EditPlayer;
import com.magistrados.models.remove.RemovePlayer;

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
        jogador.setNumeroJogador(createPlayer.getNumero());
        jogador.setTimeId(createPlayer.getIdTime());
        jogador.setQuantidadePontos(createPlayer.getPontos());
        jogador.setQuantidadeDefesas(createPlayer.getDefesas());
        jogador.setQuantidadeSaques(createPlayer.getSaques());
        jogador.setQuantidadeBloqueios(createPlayer.getBloqueios());
        jogador.setNome(createPlayer.getNome());

        this.jogadorRepository.save(jogador);
        return jogador;
    }

    public void editarJogador(Long id, EditPlayer editPlayer) {
        final Jogador jogador = this.buscarJogador(id);

        jogador.setTimeId(editPlayer.getIdTime());
        jogador.setNome(editPlayer.getNome());
        jogador.setNumeroJogador(editPlayer.getNumero());
        jogador.setQuantidadePontos(editPlayer.getPontos());
        jogador.setQuantidadeDefesas(editPlayer.getDefesas());
        jogador.setQuantidadeBloqueios(editPlayer.getBloqueios());
        jogador.setQuantidadeSaques(editPlayer.getSaques());

        this.jogadorRepository.save(jogador);
    }

    public void deletarJogador(RemovePlayer removePlayer) {
        if (!removePlayer.id().isBlank()) {
            final Jogador jogador = this.buscarJogador(removePlayer.getId());
            this.jogadorRepository.deleteById(jogador.getId());
            return;
        }
        if (!removePlayer.nome().isBlank()) {
            final Jogador jogador = this.buscarJogador(removePlayer.getNome());
            this.jogadorRepository.deleteById(jogador.getId());
            return;
        }
        final Jogador jogador = this.buscarJogadorPorNumero(removePlayer.getIdTime(), removePlayer.getNumero());
        this.jogadorRepository.deleteById(jogador.getId());
    }

    public void salvarJogador(Jogador jogador) {
        this.jogadorRepository.save(jogador);
        jogador.getMatchPlayerStats().forEach(matchPlayerStatsService::saveMatchPlayerStats);
    }

    public Jogador buscarJogador(Long id) {
        if (id == null) return null;
        final Jogador jogador = this.jogadorRepository.findById(id);
        if (jogador.isCreated()) {
            throw new EntityNotFoundException(id.toString(), "jogador");
        }
        jogador.setMatchPlayerStats(this.matchPlayerStatsService.findByPlayerId(jogador.getId()));
        return jogador;
    }

    public Jogador buscarJogador(String nome) {
        if (nome == null) return null;
        final Jogador jogador = this.jogadorRepository.findByName(nome);
        if (jogador.isCreated()) {
            throw new EntityNotFoundException(nome, "jogador");
        }
        jogador.setMatchPlayerStats(this.matchPlayerStatsService.findByPlayerId(jogador.getId()));
        return jogador;
    }

    public Jogador buscarJogadorPorNumero(Long timeId, Integer numero){
        final Jogador jogador = this.jogadorRepository.findByNumber(timeId, numero);
        if(jogador.isCreated())
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
