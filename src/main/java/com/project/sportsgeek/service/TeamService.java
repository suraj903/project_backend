package com.project.sportsgeek.service;

import com.project.sportsgeek.exception.ResultException;
import com.project.sportsgeek.model.Team;
import com.project.sportsgeek.repository.teamrepo.TeamRepository;
import com.project.sportsgeek.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TeamService {

    @Autowired
    @Qualifier("teamRepo")
    TeamRepository teamRepository;

    public Result<List<Team>> findAllTeam() {
        List<Team> teamList = teamRepository.findAllTeam();
        return new Result<>(200,"Team Details Retrieved Successfully",teamList);
    }

    public Result<Team> findTeamById(int id) throws Exception {
        List<Team> teamList = teamRepository.findTeamById(id);
        if (teamList.size() > 0) {
            return new Result<>(200,"Team Details Retrieved Successfully", teamList.get(0));
        }
        else {
            throw new ResultException((new Result<>(404,"No team's found,please try again","Team with id=('"+ id +"') not found")));
        }
    }

    public Result<Team> addTeam(Team team) throws Exception {
        int id = teamRepository.addTeam(team);
        team.setTeamId(id);
        if (id > 0) {
            return new Result<>(201,"Team Added Successfully",team);
        }
        throw new ResultException(new Result<>(400, "Error!, please try again!", new ArrayList<>(Arrays
                .asList(new Result.SportsGeekSystemError(team.hashCode(), "unable to add the given team")))));
    }
    public Result<Team> updateTeam(int id, Team team) throws Exception {
        if (teamRepository.updateTeam(id,team)) {
            return new Result<>(201,"Team Updated Successfully",team);
        }
        throw new ResultException(new Result<>(400, "Unable to update the given team details! Please try again!", new ArrayList<>(Arrays
                .asList(new Result.SportsGeekSystemError(team.hashCode(), "given teamId('"+id+"') does not exists")))));
    }
    public Result<Integer> deleteTeam(int id) throws Exception{
        int data = teamRepository.deleteTeam(id);
        if (data > 0) {
            return new Result<>(200,"Team Deleted Successfully",data);
        }
        else {
            throw new ResultException((new Result<>(404,"No Team's found to delete,please try again","Teams with id=('"+ id +"') not found")));
        }
    }
}
