package soc.common.views.behaviour.gameWidget.beforeSend;

import soc.common.actions.gameAction.standard.BuildTown;
import soc.common.board.pieces.Town;
import soc.common.game.player.GamePlayer;
import soc.common.views.behaviour.gameBoard.BuildTownBehaviour;
import soc.common.views.behaviour.gameWidget.GameBehaviour;
import soc.common.views.behaviour.gameWidget.GameBehaviourCallback;
import soc.common.views.widgetsInterface.main.GameWidget;

public class BuildTownGameBehaviour implements GameBehaviour,
        GameBehaviourCallback, TradeFirst
{
    private BuildTown buildTown;
    private BuildTownBehaviour buildTownGameBoardBehaviour;
    private GameWidget gameWidget;
    private Town town = new Town();

    public BuildTownGameBehaviour(GameWidget gameWidget, BuildTown buildTown)
    {
        super();
        this.buildTown = buildTown;
        this.gameWidget = gameWidget;
        buildTownGameBoardBehaviour = new BuildTownBehaviour(buildTown, this);
    }

    @Override
    public void finish()
    {

    }

    @Override
    public void start(GameWidget gameWidget)
    {
        GamePlayer player = gameWidget.getPlayingPlayer();
        if (gameWidget.getGame().getCurrentPhase().isPlayTurns())
        {
            if (player.getResources().hasAtLeast(town.getCost()))
            {
                gameWidget.getBoardVisualWidget().getBoardVisual()
                        .setBehaviour(buildTownGameBoardBehaviour);
            }
            else
            {
                gameWidget.getBankTradeDialog().setPieceToTradeFor(town, this);
            }
        }
        else
        {
            gameWidget.getBoardVisualWidget().getBoardVisual().setBehaviour(
                    buildTownGameBoardBehaviour);
        }
    }

    @Override
    public void cancel()
    {

    }

    @Override
    public void done()
    {
        gameWidget.sendAction(buildTown);
        buildTownGameBoardBehaviour.setNeutral(gameWidget
                .getBoardVisualWidget().getBoardVisual());
    }

    @Override
    public void onCancelTrade()
    {
        buildTownGameBoardBehaviour.setNeutral(gameWidget
                .getBoardVisualWidget().getBoardVisual());
    }

    @Override
    public void onTraded()
    {
        gameWidget.getBoardVisualWidget().getBoardVisual().setBehaviour(
                buildTownGameBoardBehaviour);
    }
}