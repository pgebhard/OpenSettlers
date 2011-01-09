package soc.gwtClient.game.widgets;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.HostStartsGame;
import soc.common.actions.gameAction.turnActions.EndTurn;
import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.game.GamePhaseChangedEvent;
import soc.common.game.GamePhaseChangedEventHandler;
import soc.common.game.TurnChangedEvent;
import soc.common.game.TurnChangedEventHandler;
import soc.common.game.gamePhase.DetermineFirstPlayerGamePhase;
import soc.common.game.gamePhase.InitialPlacementGamePhase;
import soc.common.game.gamePhase.PlayTurnsGamePhase;
import soc.common.game.player.GamePlayerImpl;
import soc.common.server.HotSeatServer;
import soc.common.server.data.Player;
import soc.gwtClient.game.CenterWidget;
import soc.gwtClient.game.abstractWidgets.AbstractGamePanel;
import soc.gwtClient.game.abstractWidgets.factories.GameWidgetFactory;
import soc.gwtClient.game.behaviour.GameBehaviour;
import soc.gwtClient.game.widgets.bitmap.BoardLayoutPanel;
import soc.gwtClient.visuals.behaviour.BehaviourDoneEvent;
import soc.gwtClient.visuals.behaviour.BehaviourDoneEventHandler;
import soc.gwtClient.visuals.behaviour.gameBoard.DisabledMap;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TabLayoutPanel;

public class HotSeatGamePanel extends AbstractGamePanel implements
        CenterWidget, TurnChangedEventHandler, GamePhaseChangedEventHandler,
        BehaviourDoneEventHandler
{
    DockLayoutPanel rootPanel = new DockLayoutPanel(Unit.EM);
    DockLayoutPanel playersBankChatPanel = new DockLayoutPanel(Unit.EM);
    DockLayoutPanel boardActionResourcesPanel = new DockLayoutPanel(Unit.EM);
    TabLayoutPanel chatHistoryDebugPanel = new TabLayoutPanel(20.0, Unit.PX);
    BoardLayoutPanel boardVisualPanel;

    public HotSeatGamePanel()
    {
        super();

        server = new HotSeatServer(this);

        HostStartsGame start = new HostStartsGame();
        start.setPlayer(new GamePlayerImpl().setUser(new Player().setName(
                "Henk").setId(1)));

        server.sendAction(start);
    }

    @Override
    protected void initialize()
    {
        super.initialize();
        boardVisualPanel = new BoardLayoutPanel(gameBoardVisual);

        createChatHistoryDebugPanel();

        boardActionResourcesPanel.addEast(handCards.asWidget(), 15);
        boardActionResourcesPanel.addEast(buildPallette.asWidget(), 10);
        boardActionResourcesPanel.addSouth(statusPanel.asWidget(), 4);
        boardActionResourcesPanel.add(boardVisualPanel);

        playersBankChatPanel.addNorth(playersWidget.asWidget(), 20);
        playersBankChatPanel.addNorth(bankStockPanel.asWidget(), 5);
        playersBankChatPanel.add(chatHistoryDebugPanel);

        rootPanel.addWest(playersBankChatPanel, 20);
        rootPanel.add(boardActionResourcesPanel);

        game.addTurnchangedeventHandler(this);
        game.addGamePhaseChangedEventHandler(this);
        gameBoardVisual.addBehaviourDoneEventHandler(this);
        gameBoardVisual.setBehaviour(new DisabledMap());
    }

    private void createChatHistoryDebugPanel()
    {
        chatHistoryDebugPanel.add(chatPanel, "chat");
        chatHistoryDebugPanel.add(historyWidget, "history");
        chatHistoryDebugPanel.add(debugPanel, "debug");
        chatHistoryDebugPanel.add(gameQueuePanel, "queue");
    }

    @Override
    public Panel getRootWidget()
    {
        return rootPanel;
    }

    @Override
    public void receive(GameAction gameAction)
    {
        // Only initialize the rest of the ui when host started the game
        if (gameAction instanceof HostStartsGame)
        {
            HostStartsGame hostStartsGame = (HostStartsGame) gameAction;
            this.game = hostStartsGame.getGame();
            this.player = game.getPlayers().get(0);
            initialize();
        }

        // Hotseat, so when the turn changes, the current player is set to the
        // new player on turn
        if (gameAction instanceof EndTurn)
        {
            player = game.getCurrentTurn().getPlayer();
        }

        super.receive(gameAction);

        if (game.getCurrentPhase() instanceof DetermineFirstPlayerGamePhase)
        {
            updateBehaviour();
        }
        if (game.getCurrentPhase() instanceof InitialPlacementGamePhase)
        {
            updateBehaviour();
        }
        if (game.getCurrentPhase() instanceof PlayTurnsGamePhase)
        {
            receivedActionBehaviour(gameAction);
        }
    }

    @Override
    public void onTurnChanged(TurnChangedEvent event)
    {
        player = event.getNewTurn().getPlayer();
    }

    @Override
    public void onGamePhaseChanged(GamePhaseChangedEvent event)
    {
        if (!(event.getNewPhase() instanceof PlayTurnsGamePhase))
        {
            updateBehaviour();
        }
    }

    private void receivedActionBehaviour(GameAction action)
    {
        if (action instanceof TurnAction)
        {
            GameBehaviour newBehaviour = gameBehaviourFactory
                    .createBehaviour((TurnAction) action);
            if (newBehaviour != null)
            {
                setNewGameBehaviour(newBehaviour);
            }
        }
    }

    private void updateBehaviour()
    {
        GameAction next = game.getActionsQueue().peekAction();
        if (next != null && next instanceof TurnAction)
        {
            GameBehaviour newBehaviour = gameBehaviourFactory
                    .createBehaviour((TurnAction) next);
            if (newBehaviour != null)
            {
                setNewGameBehaviour(newBehaviour);
            }
        }
    }

    @Override
    public void onBehaviourDone(BehaviourDoneEvent event)
    {
        GameAction action = gameBoardVisual.getBehaviour().getGameAction();
        action.setPlayer(player);
        server.sendAction(action);
    }

    @Override
    public void doneBehaviour(GameBehaviour behaviour)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public GameWidgetFactory createGameWidgetFactory()
    {
        return new GameBitmapWidgetFactory(this);
    }
}
