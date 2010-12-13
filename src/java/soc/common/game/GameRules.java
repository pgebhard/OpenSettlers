package soc.common.game;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.SimpleEventBus;

import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.board.resources.Resource;
import soc.common.game.dices.IDice;
import soc.common.game.logs.ActionQueueChangedEvent;

public class GameRules implements IGameRules
{
    private List<TurnAction> possibleActions = new ArrayList<TurnAction>();
    private List<Resource> playableResources = new ArrayList<Resource>();
    private List<StockItem> stockPieces = new ArrayList<StockItem>();
    private int bankAmountPerResource = 19;
    private boolean enableLargestArmy;
    
    // State of last rolled dice
    private IDice diceType;
        
    @Override
    public TurnAction createPlaceRobberPirateAction()
    {
        return null;
    }

    /**
     * @return the possibleActions
     */
    public List<TurnAction> getPossibleActions()
    {
        return possibleActions;
    }

    /**
     * @return the playableResources
     */
    public List<Resource> getPlayableResources()
    {
        return playableResources;
    }

    /**
     * @return the stockPieces
     */
    public List<StockItem> getStockPieces()
    {
        return stockPieces;
    }

    /**
     * @return the bankAmountPerResource
     */
    public int getBankAmountPerResource()
    {
        return bankAmountPerResource;
    }

    /**
     * @return the enableLargestArmy
     */
    public boolean getEnableLargestArmy()
    {
        return enableLargestArmy;
    }
    /**
     * @param enableLargestArmy the enableLargestArmy to set
     */
    public GameRules setEnableLargestArmy(boolean enableLargestArmy)
    {
        this.enableLargestArmy = enableLargestArmy;
    
        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }
    @Override
    public IDice getDiceType()
    {
        return diceType;
    }
    @Override
    public GameRules setDiceType(IDice diceType)
    {
        this.diceType=diceType;
        
        return this;
    }
}