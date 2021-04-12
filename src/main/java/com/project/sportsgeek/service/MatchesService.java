package com.project.sportsgeek.service;

import com.project.sportsgeek.exception.ResultException;
import com.project.sportsgeek.model.Matches;
import com.project.sportsgeek.repository.matchesrepo.MatchesRepository;
import com.project.sportsgeek.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MatchesService {
    @Autowired
    @Qualifier("matchesRepo")
    MatchesRepository matchesRepository;

    public Result<List<Matches>> findAllMatches() {
        List<Matches> matchesList = matchesRepository.findAllMatches();
        return new Result<>(200,"Matches Detail Retrieved Successfully",matchesList);
    }
    
    public Result<Matches> findMatchesById(int id) throws Exception {
        List<Matches> matchesList = matchesRepository.findMatchesById(id);
        if (matchesList.size() > 0) {
            return new Result<>(200,"Matches Detail Retrieved Successfully", matchesList.get(0));
        }
        else {
            throw new ResultException((new Result<>(404,"No Match's found,please try again","Match with id=('"+ id +"') not found")));
        }
    }
    public Result<Matches> addMatches(Matches matches) throws Exception {
        int id = matchesRepository.addMatch(matches);
        matches.setMatchId(id);
        if (id > 0) {
            return new Result<>(201,"Matches Added Successfully",matches);
        }
        throw new ResultException(new Result<>(400, "Error!, please try again!", new ArrayList<>(Arrays
                .asList(new Result.SportsGeekSystemError(matches.hashCode(), "unable to add the given gender")))));
    }

    public Result<List<Matches>> findAllMatchesByPreviousDateAndResultStatus() {
        List<Matches> matchesList = matchesRepository.findAllMatchesByPreviousDateAndResultStatus();
        return new Result<>(200,matchesList);
    }
    public Result<String> updateMatchWinningTeam(int matchId, int ResultStatus, int winningTeamId) throws Exception {
            if(matchesRepository.updateMatchWinningTeam(matchId,ResultStatus,winningTeamId)== true) {
                return new Result<>(201,"Matches Updated Successfully!!");
            }else {
                return new Result<>(400,"Failed to Update Match Result!!");
            }
    }
    public Result<String> updateMinimumBet(int matchId,int minBet) throws Exception {
        int result = matchesRepository.updateMinimumBet(matchId,minBet);
        if(result > 0) {
            return new Result<>(201, "Successfully Updated Minimum Bet for Match");
        }
        else {
            return new Result<>(500, "Internal Server error!, Unable to update the Minimum Bet");
        }
    }
}
