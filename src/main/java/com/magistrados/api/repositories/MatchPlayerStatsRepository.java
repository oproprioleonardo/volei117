package com.magistrados.api.repositories;

import com.magistrados.api.repositories.base.Repository;
import com.magistrados.models.MatchPlayerStats;

import java.util.Set;

public interface MatchPlayerStatsRepository extends Repository<Long, MatchPlayerStats> {

    Set<MatchPlayerStats> findByPlayerId(Long playerId);

    MatchPlayerStats findByPlayerIdAndMatchId(Long playerId, Long matchId);

}
