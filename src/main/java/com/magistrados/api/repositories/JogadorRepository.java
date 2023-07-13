package com.magistrados.api.repositories;

import com.magistrados.api.repositories.base.CrudRepository;
import com.magistrados.models.Jogador;

public interface JogadorRepository extends CrudRepository<Long, Jogador> {

    Jogador findByName(String name);
    Jogador findByNumber(Long timeId, Integer number);

}
