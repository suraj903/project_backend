package com.project.sportsgeek.repository.playerrepo;

import com.project.sportsgeek.model.Player;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "playerRepo")
public class PlayerRepoImpl implements PlayerRepository {
    @Override
    public List<Player> findAllPlayers() {
        return null;
    }

    @Override
    public List<Player> findPlayerByPlayerId(int id) throws Exception {
        return null;
    }

    @Override
    public List<Player> findPlayerByPlayerType(int id) throws Exception {
        return null;
    }

    @Override
    public List<Player> findPlayerByTeamId(int id) throws Exception {
        return null;
    }

    @Override
    public int addPlayer(Player player) throws Exception {
        return 0;
    }

    @Override
    public boolean updatePlayer(int id, Player player) throws Exception {
        return false;
    }

    @Override
    public int updateProfilePicture(int id, String profilePicture) throws Exception {
        return 0;
    }

    @Override
    public int updatePlayerType(int id, int playerTypeId) throws Exception {
        return 0;
    }

    @Override
    public int deletePlayer(int id) throws Exception {
        return 0;
    }
}
