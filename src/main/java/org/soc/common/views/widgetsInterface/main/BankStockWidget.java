package org.soc.common.views.widgetsInterface.main;

import org.soc.common.game.Game;
import org.soc.common.game.Resource;
import org.soc.common.views.widgetsInterface.playerInfo.DevelopmentCardsAmountWidget;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.IsWidget;

public interface BankStockWidget extends IsWidget
{
    public ComplexPanel createRootPanel();

    public BankStockResourceWidget createBankStockResourceWidget(
            Resource resource);

    public DevelopmentCardsAmountWidget createDevelopmentCardsWidget(Game game);
}
