package soc.common.lobby;

import java.io.Serializable;
import java.util.List;

import soc.common.server.entities.UserList;

public interface LoginResponse extends Serializable
{
    public UserList getLoggedInUsers();

    public List<GameInfo> getLobbyGames();
}
