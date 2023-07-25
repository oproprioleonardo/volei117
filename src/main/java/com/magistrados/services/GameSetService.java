package com.magistrados.services;

import com.magistrados.api.repositories.GameSetRepository;
import com.magistrados.models.GameSet;

import java.util.List;
import java.util.Set;

public class GameSetService {

    private final GameSetRepository gameSetRepository;

    public GameSetService(GameSetRepository gameSetRepository) {
        this.gameSetRepository = gameSetRepository;
    }

    public GameSet buscarSet(Long id) {
        return this.gameSetRepository.findById(id);
    }

    public List<GameSet> buscarSets(Long idPartida) {
        return this.gameSetRepository.findAllSetsByMatchId(idPartida);
    }

    public void salvarSet(GameSet gameSet) {
        this.gameSetRepository.save(gameSet);
    }

    public void deletarSet(GameSet gameSet) {
        this.gameSetRepository.deleteById(gameSet.getId());
    }
}
