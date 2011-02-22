package soc.gwtClient.game.behaviour.gameWidget.beforeSend;

import soc.common.actions.gameAction.standard.BuyDevelopmentCard;
import soc.common.game.developmentCards.DevelopmentCard;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.behaviour.gameWidget.GameBehaviour;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

public class BuyDevelopmentCardGameBehaviour implements GameBehaviour,
        TradeFirst
{
    private BuyDevelopmentCard buyDev;
    private GameWidget gameWidget;

    public BuyDevelopmentCardGameBehaviour(GameWidget gameWidget,
            BuyDevelopmentCard buyDev)
    {
        super();
        this.gameWidget = gameWidget;
        this.buyDev = buyDev;
    }

    @Override
    public void finish()
    {
    }

    @Override
    public void start(GameWidget gameWidget)
    {
        GamePlayer player = gameWidget.getPlayingPlayer();
        if (player.getResources().hasAtLeast(DevelopmentCard.getCost()))
        {
            gameWidget.sendAction(buyDev);
        }
        else
        {
            gameWidget.getBankTradeUI().setDevcardTrade(this);
        }
    }

    @Override
    public void onCancelTrade()
    {
    }

    @Override
    public void onTraded()
    {
        gameWidget.sendAction(buyDev);
    }
}