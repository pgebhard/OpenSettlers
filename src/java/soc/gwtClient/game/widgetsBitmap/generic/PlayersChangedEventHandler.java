package soc.gwtClient.game.widgetsBitmap.generic;

import com.google.gwt.event.shared.EventHandler;

public interface PlayersChangedEventHandler extends EventHandler
{
    public void onPlayersChanged(PlayersChangedEvent event);
}
