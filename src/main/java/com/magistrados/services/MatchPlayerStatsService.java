package com.magistrados.services;

import com.magistrados.api.repositories.MatchPlayerStatsRepository;
import com.magistrados.models.MatchPlayerStats;

import java.util.Set;

public class MatchPlayerStatsService {
    private final MatchPlayerStatsRepository matchPlayerStatsRepository;

    public MatchPlayerStatsService(MatchPlayerStatsRepository matchPlayerStatsRepository){
        this.matchPlayerStatsRepository = matchPlayerStatsRepository;
    }

    public void createMatchPlayerStats(MatchPlayerStats matchPlayerStats){
        this.matchPlayerStatsRepository.create(matchPlayerStats);
    }

    public void saveMatchPlayerStats(MatchPlayerStats matchPlayerStats){
        this.matchPlayerStatsRepository.save(matchPlayerStats);
    }

    public void deleteMatchPlayerStats(MatchPlayerStats matchPlayerStats){
        this.deleteMatchPlayerStats(matchPlayerStats.getId());
    }

    public void deleteMatchPlayerStats(Long id){
        this.matchPlayerStatsRepository.deleteById(id);
    }

    public MatchPlayerStats findByPlayerIdAndMatchId(Long playerId, Long matchId){
        return this.matchPlayerStatsRepository.findByPlayerIdAndMatchId(playerId, matchId);
    }

    public Set<MatchPlayerStats> findByPlayerId(Long playerId){
        return this.matchPlayerStatsRepository.findByPlayerId(playerId);
    }
}
