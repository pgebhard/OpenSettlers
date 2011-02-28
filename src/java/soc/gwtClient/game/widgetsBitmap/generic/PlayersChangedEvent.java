package soc.gwtClient.game.widgetsBitmap.generic;

import soc.common.game.player.GamePlayer;

import com.google.gwt.event.shared.GwtEvent;

public class PlayersChangedEvent extends GwtEvent<PlayersChangedEventHandler>
{
    public static Type<PlayersChangedEventHandler> TYPE = new Type<PlayersChangedEventHandler>();
    private GamePlayer playerAdded;
    private GamePlayer playerRemoved;

    public PlayersChangedEvent(GamePlayer playerAdded, GamePlayer playerRemoved)
    {
        super();
        this.playerAdded = playerAdded;
        this.playerRemoved = playerRemoved;
    }

    /**
     * @return the playerAdded
     */
    public GamePlayer getPlayerAdded()
    {
        return playerAdded;
    }

    /**
     * @return the playerRemoved
     */
    public GamePlayer getPlayerRemoved()
    {
        return playerRemoved;
    }

    @Override
    protected void dispatch(PlayersChangedEventHandler handler)
    {
        handler.onPlayersChanged(this);
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<PlayersChangedEventHandler> getAssociatedType()
    {
        return TYPE;
    }

}
