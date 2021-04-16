package com.project.sportsgeek.repository.betonplayerrepo;

import com.project.sportsgeek.model.BetOnPlayer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "betOnPlayerRepo")
public interface BetOnPlayerRepository {
    public List<BetOnPlayer> findAllBetOnPlayer();
    public List<BetOnPlayer> findBetOnPlayerByBetPlayerId(int betPlayerId) throws Exception;
    public List<BetOnPlayer> findBetOnPlayerByUserId(int userId) throws Exception;
    public List<BetOnPlayer> findAllBetOnPlayerByMatchId(int matchId) throws Exception;
    public int addBetOnPlayer(BetOnPlayer player) throws Exception;
    public int updateBetOnPlayer(int userId,BetOnPlayer player) throws Exception;
    public int updateTotalGamePoints(int userId, int points) throws Exception;
    public int deleteBetOnPlayer(int betPlayerId) throws Exception;
}
