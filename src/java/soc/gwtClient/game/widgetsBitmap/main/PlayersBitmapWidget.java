package soc.gwtClient.game.widgetsBitmap.main;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.Point2D;
import soc.gwtClient.game.widgetsAbstract.playerInfo.AbstractPlayersWidget;
import soc.gwtClient.game.widgetsBitmap.playerInfo.PlayerBitmapWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsInterface.playerInfo.PlayerInfoWidget;

import com.google.gwt.user.client.ui.Widget;

public class PlayersBitmapWidget extends AbstractPlayersWidget
{
    public PlayersBitmapWidget(GameWidget gameWidget)
    {
        super(gameWidget);
    }

    @Override
    public PlayerInfoWidget createPlayerWidget(GameWidget gameWidget,
            GamePlayer player)
    {
        return new PlayerBitmapWidget(gameWidget, player);
    }

    @Override
    public Point2D getTopRightLocation()
    {
        Widget w = rootPanel;
        return new Point2D(w.getAbsoluteLeft() + w.getOffsetWidth(), w
                .getAbsoluteTop());
    }

    @Override
    public int getPlayerWidgetHeight()
    {
        return rootPanel.getWidget(0).getOffsetHeight();
    }

    @Override
    public Point2D getTopRightLocation(GamePlayer player)
    {
        return playersWidgets.get(player).getTopRightLocation();
    }
}