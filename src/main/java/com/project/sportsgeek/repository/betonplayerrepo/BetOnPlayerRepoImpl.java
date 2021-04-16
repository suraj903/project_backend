package com.project.sportsgeek.repository.betonplayerrepo;

import com.project.sportsgeek.model.BetOnPlayer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "betOnPlayerRepoImpl")
public class BetOnPlayerRepoImpl implements BetOnPlayerRepository{

    @Override
    public List<BetOnPlayer> findAllBetOnPlayer() {
        return null;
    }

    @Override
    public List<BetOnPlayer> findBetOnPlayerByBetPlayerId(int betPlayerId) throws Exception {
        return null;
    }

    @Override
    public List<BetOnPlayer> findBetOnPlayerByUserId(int userId) throws Exception {
        return null;
    }

    @Override
    public List<BetOnPlayer> findAllBetOnPlayerByMatchId(int matchId) throws Exception {
        return null;
    }

    @Override
    public int addBetOnPlayer(BetOnPlayer player) throws Exception {
        return 0;
    }

    @Override
    public int updateBetOnPlayer(int userId, BetOnPlayer player) throws Exception {
        return 0;
    }

    @Override
    public int updateTotalGamePoints(int userId, int points) throws Exception {
        return 0;
    }

    @Override
    public int deleteBetOnPlayer(int betPlayerId) throws Exception {
        return 0;
    }
}
