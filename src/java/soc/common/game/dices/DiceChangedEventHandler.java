package soc.common.game.dices;

import com.google.gwt.event.shared.EventHandler;

public interface DiceChangedEventHandler extends EventHandler
{
    public void onDiceChanged(DiceChangedEvent event);
}
