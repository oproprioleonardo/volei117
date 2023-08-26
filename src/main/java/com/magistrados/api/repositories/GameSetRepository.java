package com.magistrados.api.repositories;

import com.magistrados.api.repositories.base.Repository;
import com.magistrados.models.GameSet;

import java.util.List;

public interface GameSetRepository extends Repository<Long, GameSet> {

    List<GameSet> findAllSetsByMatchId(Long matchId);

}
