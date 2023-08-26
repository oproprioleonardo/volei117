package com.magistrados.api.repositories;

import com.magistrados.api.repositories.base.Repository;
import com.magistrados.models.Jogador;

import java.util.Set;

public interface JogadorRepository extends Repository<Long, Jogador> {

    Jogador findByName(String name);
    Jogador findByNumber(Long timeId, Integer number);
    Set<Jogador> findAllByTeamId(Long timeId);

}
