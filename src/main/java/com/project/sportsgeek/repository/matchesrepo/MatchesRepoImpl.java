package com.project.sportsgeek.repository.matchesrepo;

import com.project.sportsgeek.mapper.MatchesRowMapper;
import com.project.sportsgeek.mapper.TournamentRowMapper;
import com.project.sportsgeek.model.Matches;
import com.project.sportsgeek.query.QueryGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository(value = "matchesRepo")
public class MatchesRepoImpl implements MatchesRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private QueryGenerator<Matches> queryGenerator = new QueryGenerator<Matches>();
    private SimpleJdbcCall simpleJdbcCall;
    @Override
    public List<Matches> findAllMatches() {
        String tournament_sql = "SELECT * from Tournament WHERE active = true";
        int tournamentid = jdbcTemplate.query(tournament_sql,new TournamentRowMapper()).get(0).getTournamentId();
       String sql = "SELECT MatchId , StartDatetime, Team1,Team2, t1.Name as team1long, t1.ShortName as team1short, " +
               "t1.TeamLogo as team1logo, t2.Name as team2long, t2.ShortName as team2short, t2.TeamLogo as team2logo, v.Name as venue, MinimumBet, WinnerTeamId, ResultStatus, TournamentId  " +
               "FROM Matches as m INNER JOIN Venue as v on m.VenueId=v.VenueId left JOIN Team as t1 on m.Team1=t1.TeamId left JOIN Team as t2 on m.Team2=t2.TeamId " +
               "where TournamentId=" + tournamentid + " and StartDatetime > CURRENT_TIMESTAMP order by StartDatetime";
       return jdbcTemplate.query(sql,new MatchesRowMapper());
    }

    @Override
    public List<Matches> findAllMatchesByTournament(int id) throws Exception {
        return null;
    }

    @Override
    public List<Matches> findAllMatchesByVenue(int id) throws Exception {
        return null;
    }

    @Override
    public List<Matches> findAllMatchesByTeam(int id) throws Exception {
        return null;
    }

    @Override
    public List<Matches> findAllMatchesByPreviousDateAndResultStatus() {
        String tournament_sql = "SELECT * from Tournament WHERE active = true";
        int tournamentid = jdbcTemplate.query(tournament_sql,new TournamentRowMapper()).get(0).getTournamentId();
        String sql = "SELECT MatchId , StartDatetime, Team1,Team2, t1.Name as team1long, t1.ShortName as team1short, " +
                "t1.TeamLogo as team1logo, t2.Name as team2long, t2.ShortName as team2short, t2.TeamLogo as team2logo, v.Name as venue, MinimumBet, WinnerTeamId, ResultStatus, TournamentId  " +
                "FROM Matches as m INNER JOIN Venue as v on m.VenueId=v.VenueId left JOIN Team as t1 on m.Team1=t1.TeamId left JOIN Team as t2 on m.Team2=t2.TeamId " +
                "where TournamentId=" + tournamentid + " and StartDatetime < CURRENT_TIMESTAMP and m.ResultStatus IS NULL order by StartDatetime";
        return jdbcTemplate.query(sql,new MatchesRowMapper());
    }

    @Override
    public List<Matches> findAllMatchesByMinimumBet(int minBet) throws Exception {
        return null;
    }

    @Override
    public List<Matches> findAllMatchesByWinningTeam(int winningTeamId) throws Exception {
        return null;
    }

    @Override
    public List<Matches> findAllMatchesBetweenDate(Date date) throws Exception {
        return null;
    }

    @Override
    public int addMatch(Matches matches) throws Exception {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(queryGenerator.generatePreparedStatementInsertQuery("Matches", matches),
                new BeanPropertySqlParameterSource(matches), holder);
        return holder.getKey().intValue();
    }

    @Override
    public boolean updateMatch(int id, Matches matches) throws Exception {
        return false;
    }

    @Override
    public boolean updateMatchWinningTeam(int matchId, int ResultStatus, int winningTeamId) throws Exception {
        try
        {
            SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("vMatchId", matchId)
                    .addValue("vResultStatus",ResultStatus)
                    .addValue("vWinnerTeamId",winningTeamId);

            simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withProcedureName("setMatchResult");
            simpleJdbcCall.execute(in);
            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public int updateMatchVenue(int id, int venueId) throws Exception {
        return 0;
    }

    @Override
    public int updateResultStatus(int id, boolean status) throws Exception {
        return 0;
    }

    @Override
    public int updateMinimumBet(int matchId, int minBet) throws Exception {
       String sql = "UPDATE Matches SET MinimumBet="+minBet+" WHERE MatchId="+matchId;
       return jdbcTemplate.update(sql);
    }

    @Override
    public int updateMatchScheduleDate(int id, Date date) throws Exception {
        return 0;
    }

    @Override
    public int deleteMatches(int id) throws Exception {
        return 0;
    }

	@Override
	public List<Matches> findMatchesById(int id) throws Exception {
		String tournament_sql = "SELECT * from Tournament WHERE active = true";
        int tournamentid = jdbcTemplate.query(tournament_sql,new TournamentRowMapper()).get(0).getTournamentId();
       String sql = "SELECT MatchId , StartDatetime, Team1,Team2, t1.Name as team1long, t1.ShortName as team1short, " +
               "t1.TeamLogo as team1logo, t2.Name as team2long, t2.ShortName as team2short, t2.TeamLogo as team2logo, v.Name as venue, MinimumBet, WinnerTeamId, ResultStatus, TournamentId  " +
               "FROM Matches as m INNER JOIN Venue as v on m.VenueId=v.VenueId left JOIN Team as t1 on m.Team1=t1.TeamId left JOIN Team as t2 on m.Team2=t2.TeamId " +
               "where TournamentId="+ tournamentid + " AND MatchId=" + id;
       return jdbcTemplate.query(sql,new MatchesRowMapper());
	}
}
