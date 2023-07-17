package com.magistrados.api.repositories;

import com.magistrados.api.repositories.base.CrudRepository;
import com.magistrados.models.MatchPlayerStats;

import java.util.Set;

public interface MatchPlayerStatsRepository extends CrudRepository<Long, MatchPlayerStats> {

    Set<MatchPlayerStats> findByPlayerId(Long playerId);

    MatchPlayerStats findByPlayerIdAndMatchId(Long playerId, Long matchId);

}
