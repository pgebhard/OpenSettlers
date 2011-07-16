package soc.common.views.behaviour.gameWidget.received;

import soc.common.actions.gameAction.standard.ClaimVictory;
import soc.common.views.widgetsInterface.main.GameWidget;

public class GameOverGameBehaviour implements ReceiveGameBehaviour
{
    ClaimVictory claimVictory;
    GameWidget gameWidget;

    public GameOverGameBehaviour(GameWidget gameWidget, ClaimVictory claimVictory)
    {
        super();
        this.claimVictory = claimVictory;
        this.gameWidget = gameWidget;
    }

    public ClaimVictory getClaimVictory()
    {
        return claimVictory;
    }

    @Override
    public void finish()
    {

    }

    @Override
    public void start(GameWidget gameWidget)
    {
        gameWidget.getGameOverDialog().update(this);
    }

    @Override
    public boolean endsManually()
    {
        return true;
    }

}