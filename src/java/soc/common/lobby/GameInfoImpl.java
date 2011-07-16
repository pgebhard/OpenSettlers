package soc.common.lobby;

import soc.common.game.settings.GameSettings;
import soc.common.game.statuses.GameStatus;
import soc.common.server.entities.User;
import soc.common.server.entities.UserList;

/*
 * Lightweight pojo to describe a game superficially
 */
public class GameInfoImpl implements GameInfo
{
    private static final long serialVersionUID = -1030159652269927649L;
    private int id;
    private String name;
    private User host;
    private UserList users = new UserList();
    private String boardId;
    private transient LobbyLog lobbyLog = new LobbyLog();
    private GameSettings gameSettings;
    private GameStatus gameStatus;

    public GameInfoImpl()
    {
    }

    public GameInfoImpl(String name, User host, String boardId)
    {
        super();
        this.name = name;
        this.host = host;
        this.boardId = boardId;
    }

    @Override
    public int getID()
    {
        return id;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public User getHost()
    {
        return host;
    }

    @Override
    public UserList getUsers()
    {
        return users;
    }

    @Override
    public String getBoardId()
    {
        return boardId;
    }

    @Override
    public GameStatus getGameStatus()
    {
        return gameStatus;
    }

    @Override
    public GameSettings getSettings()
    {
        return gameSettings;
    }

    @Override
    public GameInfo setSettings(GameSettings settings)
    {
        this.gameSettings = settings;
        return this;
    }

    @Override
    public GameInfo setGameStatus(GameStatus newGameStatus)
    {
        this.gameStatus = newGameStatus;
        return this;
    }

    @Override
    public LobbyLog getLobbyLog()
    {
        return lobbyLog;
    }
}