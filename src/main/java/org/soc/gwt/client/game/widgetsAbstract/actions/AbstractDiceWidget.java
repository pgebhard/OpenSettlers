package org.soc.gwt.client.game.widgetsAbstract.actions;

import org.soc.common.game.DiceChangedEvent.DiceChangedHandler;
import org.soc.common.views.widgetsInterface.actions.DiceWidget;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractDiceWidget implements DiceWidget, DiceChangedHandler
{
  protected ComplexPanel rootPanel;

  @Override public Widget asWidget()
  {
    return rootPanel;
  }
}
