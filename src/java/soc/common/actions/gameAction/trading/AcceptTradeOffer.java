package soc.common.actions.gameAction.trading;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.actions.gameAction.GameAction;
import soc.common.core.Core;
import soc.common.game.Game;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.PlayTurnsGamePhase;
import soc.common.game.gamePhase.turnPhase.TradingTurnPhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.game.player.GamePlayer;
import soc.common.game.trading.TradeResponse;
import soc.common.ui.Graphics;
import soc.common.ui.Icon;
import soc.common.ui.ToolTip;
import soc.common.ui.meta.Meta;
import soc.gwtClient.game.behaviour.gameBoard.received.ReceiveGameBehaviour;
import soc.gwtClient.game.behaviour.gameWidget.GameBehaviour;
import soc.gwtClient.game.widgetsInterface.actions.ActionWidget;

public class AcceptTradeOffer extends AbstractGameAction implements
        TradeResponse
{
    private static final long serialVersionUID = -1794721147505851423L;
    private static Meta meta = new Meta()
    {

        @Override
        public Icon icon()
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public Graphics graphics()
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String getName()
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String getLocalizedName()
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String getDescription()
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public ToolTip createToolTip()
        {
            // TODO Auto-generated method stub
            return null;
        }
    };

    private TradeOffer originatingOffer;

    @Override
    public TradeOffer getOriginatingOffer()
    {
        return originatingOffer;
    }

    public TradeResponse setOriginatingOffer(TradeOffer originatingOffer)
    {
        this.originatingOffer = originatingOffer;
        return this;
    }

    @Override
    public String getToDoMessage()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.actions.gameAction.AbstractGameAction#perform(soc.common.game
     * .Game)
     */
    @Override
    public void perform(Game game)
    {
        game.addTradeResponse(this);

        // TODO: fix message

        super.perform(game);
    }

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return turnPhase instanceof TradingTurnPhase;
    }

    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        return gamePhase instanceof PlayTurnsGamePhase;
    }

    @Override
    public void setTradeResources(TradePlayer tradePlayer)
    {
        tradePlayer.getOfferedResources().addList(
                originatingOffer.getOfferedResources());
        tradePlayer.getRequestedResources().addList(
                originatingOffer.getRequestedResources());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.actions.gameAction.AbstractGameAction#isExpectedQueueType(
     * soc.common.actions.gameAction.GameAction)
     */
    @Override
    public boolean isExpectedQueueType(GameAction actualAction)
    {
        return actualAction instanceof TradeResponse;
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }

    @Override
    public ActionWidget createActionWidget(GamePlayer player)
    {
        return null;
    }

    @Override
    public GameBehaviour getNextActionBehaviour()
    {
        return null;
    }

    @Override
    public ReceiveGameBehaviour getOpponentReceiveBehaviour()
    {
        return Core.get().getClientFactory().getBehaviourFactory()
                .getOpponentReceiveBehaviourFactory()
                .createAcceptOfferBehaviour(this);

    }

    @Override
    public ReceiveGameBehaviour getReceiveBehaviour()
    {
        return Core.get().getClientFactory().getBehaviourFactory()
                .getReceiveBehaviourFactory().createAcceptOfferBehaviour(this);
    }

    @Override
    public GameBehaviour getSendBehaviour()
    {
        // TODO Auto-generated method stub
        return null;
    }
}
