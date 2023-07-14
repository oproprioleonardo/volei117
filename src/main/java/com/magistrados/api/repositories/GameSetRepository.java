package com.magistrados.api.repositories;

import com.magistrados.api.repositories.base.CrudRepository;
import com.magistrados.models.GameSet;

import java.util.Set;

public interface GameSetRepository extends CrudRepository<Long, GameSet> {

    Set<GameSet> findAllSetsByMatchId(Long matchId);

}
