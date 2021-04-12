package com.project.sportsgeek.repository.matchesrepo;


import com.project.sportsgeek.model.Matches;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository(value = "matchesRepo")
public interface MatchesRepository {

    public List<Matches> findAllMatches();
    public List<Matches> findMatchesById(int id) throws Exception;
    public List<Matches> findAllMatchesByTournament(int id) throws Exception;
    public List<Matches> findAllMatchesByVenue(int id) throws Exception;
    public List<Matches> findAllMatchesByTeam(int id) throws Exception;
    public List<Matches> findAllMatchesByPreviousDateAndResultStatus();
    public List<Matches> findAllMatchesByMinimumBet(int minBet) throws Exception;
    public List<Matches> findAllMatchesByWinningTeam(int winningTeamId) throws Exception;
    public List<Matches> findAllMatchesBetweenDate(Date date) throws Exception;
    public int addMatch(Matches matches) throws Exception;
    public boolean updateMatch(int id, Matches matches) throws Exception;
    public boolean updateMatchWinningTeam(int matchId, int ResultStatus, int winningTeamId) throws Exception;
    public int updateMatchVenue(int id,int venueId) throws Exception;
    public int updateResultStatus(int id, boolean status) throws Exception;
    public int updateMinimumBet(int matchId, int minBet) throws Exception;
    public int updateMatchScheduleDate(int id, Date date) throws Exception;
    public int deleteMatches(int id) throws Exception;
}
