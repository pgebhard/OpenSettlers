package org.soc.common.game.trading;

import org.soc.common.game.Game;
import org.soc.common.game.GamePhase;
import org.soc.common.game.TurnPhase;
import org.soc.common.game.actions.GameAction;
import org.soc.common.game.actions.GameAction.AbstractGameAction;
import org.soc.common.game.actions.GameBehaviour;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.widgetsInterface.actions.ActionWidget;
import org.soc.common.views.widgetsInterface.actions.ActionWidget.ActionWidgetFactory;
import org.soc.common.views.widgetsInterface.main.GameWidget;

public class AcceptTradeOffer extends AbstractGameAction implements TradeResponse {
  private TradeOffer originatingOffer;

  @Override public Icon icon() {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public String getLocalizedName() {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public String getDescription() {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public TradeOffer getOriginatingOffer() {
    return originatingOffer;
  }
  public TradeResponse setOriginatingOffer(TradeOffer originatingOffer) {
    this.originatingOffer = originatingOffer;
    return this;
  }
  @Override public String toDoMessage() {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public void perform(Game game) {
    game.addTradeResponse(this);
    // TODO: fix message
    super.perform(game);
  }
  @Override public boolean isAllowed(TurnPhase turnPhase) {
    return turnPhase.isTrading();
  }
  @Override public boolean isAllowed(GamePhase gamePhase) {
    return gamePhase.isPlayTurns();
  }
  @Override public void setTradeResources(TradePlayer tradePlayer) {
    tradePlayer.getOfferedResources().addList(
            originatingOffer.getOfferedResources());
    tradePlayer.getRequestedResources().addList(
            originatingOffer.getRequestedResources());
  }
  @Override public boolean isExpectedQueueType(GameAction actualAction) {
    return actualAction instanceof TradeResponse;
  }
  @Override public ActionWidget createActionWidget(ActionWidgetFactory actionWidgetFactory) {
    return null;
  }
  @Override public GameBehaviour next(GameWidget gameWidget) {
    return null;
  }
  @Override public GameBehaviour begin(GameWidget gameWidget) {
    return null;
  }
  @Override public boolean isAccepted() {
    return true;
  }
  @Override public boolean isCounterOffer() {
    return false;
  }
  @Override public boolean isRejection() {
    return false;
  }
}
