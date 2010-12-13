package soc.gwtClient.game.widgets.standard.bitmap;

import soc.common.game.Player;
import soc.gwtClient.game.abstractWidgets.AbstractActionsWidget;
import soc.gwtClient.game.abstractWidgets.IGamePanel;
import soc.gwtClient.game.abstractWidgets.factories.IActionWidgetFactory;

public class BitmapActionsWidget extends AbstractActionsWidget
{
    BitmapActionWidgetFactory widgetFactory;

    public BitmapActionsWidget(IGamePanel gamePanel, Player player)
    {
        super(gamePanel, player);
    }
    
    @Override
    public IActionWidgetFactory getActionWidgetFactory()
    {
        return new BitmapActionWidgetFactory();
    }
}