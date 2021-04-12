package com.project.sportsgeek.repository.playerrepo;

import com.project.sportsgeek.model.Player;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "playerRepo")
public interface PlayerRepository {

    public List<Player> findAllPlayers();
    public List<Player> findPlayerByPlayerId(int id) throws Exception;
    public List<Player> findPlayerByPlayerType(int id) throws Exception;
    public List<Player> findPlayerByTeamId(int id) throws Exception;
    public int addPlayer(Player player) throws Exception;
    public boolean updatePlayer(int id, Player player) throws Exception;
    public int updateProfilePicture(int id, String profilePicture) throws Exception;
    public int updatePlayerType(int id,int playerTypeId) throws Exception;
    public int deletePlayer(int id) throws Exception;
}
