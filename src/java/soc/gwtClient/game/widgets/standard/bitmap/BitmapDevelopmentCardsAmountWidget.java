package soc.gwtClient.game.widgets.standard.bitmap;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import soc.common.game.Player;
import soc.common.game.developmentCards.DevelopmentCardsChangedEvent;
import soc.gwtClient.game.abstractWidgets.AbstractDevelopmentCardsAmountWidget;

public class BitmapDevelopmentCardsAmountWidget extends AbstractDevelopmentCardsAmountWidget
{
    Image devcardImage = new Image("icons/48/BlankCard48.png");
    Label amountDevcards = new Label();
    
    public BitmapDevelopmentCardsAmountWidget(Player player)
    {
        super(player);
        
        devcardImage.setSize("24px", "24px");
        amountDevcards.setText(Integer.toString(player.getDevelopmentCardsCount()));
        
        rootPanel.add(devcardImage);
        rootPanel.add(amountDevcards);
    }

    /* (non-Javadoc)
     * @see soc.gwtClient.game.abstractWidgets.AbstractDevelopmentCardsAmountWidget#onDevelopmentCardsChanged(soc.common.game.developmentCards.DevelopmentCardsChangedEvent)
     */
    public void onDevelopmentCardsChanged(DevelopmentCardsChangedEvent event)
    {
        amountDevcards.setText(
                Integer.toString(player.getDevelopmentCardsCount()));
    }
}