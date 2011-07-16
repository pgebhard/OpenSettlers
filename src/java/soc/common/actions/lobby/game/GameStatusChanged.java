package soc.common.actions.lobby.game;

import soc.common.actions.ValidateResult;
import soc.common.actions.lobby.AbstractLobbyAction;
import soc.common.game.statuses.GameStatus;
import soc.common.lobby.GameInfo;
import soc.common.lobby.Lobby;

/*
 * Represents a status change in a game
 */
public class GameStatusChanged extends AbstractLobbyAction
{
    private static final long serialVersionUID = -1626089108739910240L;
    private GameStatus newStatus;
    private int gameId;

    /** @return the newStatus */
    public GameStatus getNewStatus()
    {
        return newStatus;
    }

    @Override
    public String getMessage()
    {
        // TODO fix message
        return null;
    }

    @Override
    public void perform(Lobby lobby)
    {
        // Grab associated gameInfo instance
        GameInfo gameInfo = lobby.getGames().findById(gameId);

        // Set new status on it
        if (gameInfo != null)
            gameInfo.setGameStatus(newStatus);
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.actions.lobby.AbstractLobbyAction#isValid(soc.common.lobby.Lobby,
     * soc.common.actions.ValidateResult)
     */
    @Override
    public ValidateResult isValid(Lobby lobby, ValidateResult result)
    {
        ValidateResult newResult = super.isValid(lobby, result);

        if (!newResult.isValid())
            return newResult;

        return valid;
    }

}