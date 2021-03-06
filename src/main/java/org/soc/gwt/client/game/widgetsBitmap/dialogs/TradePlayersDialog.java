package org.soc.gwt.client.game.widgetsBitmap.dialogs;

import java.util.HashMap;
import java.util.Map;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.OrderChangedEvent;
import org.soc.common.game.OrderChangedEvent.OrderChangedHandler;
import org.soc.common.game.PortList;
import org.soc.common.game.ResourceList;
import org.soc.common.game.ResourcesChangedEvent;
import org.soc.common.game.ResourcesChangedEvent.ResourcesChangedHandler;
import org.soc.common.game.TurnChangedEvent;
import org.soc.common.game.TurnChangedEvent.TurnChangedHandler;
import org.soc.common.game.trading.TradeOffer;
import org.soc.common.game.trading.TradeOfferedEvent;
import org.soc.common.game.trading.TradeOfferedEventHandler;
import org.soc.common.game.trading.TradePlayer;
import org.soc.common.game.trading.TradeRespondedEvent;
import org.soc.common.game.trading.TradeRespondedEventHandler;
import org.soc.common.game.trading.TradeResponse;
import org.soc.common.views.widgetsInterface.dialogs.TradePlayerDialog;
import org.soc.common.views.widgetsInterface.generic.Point2D;
import org.soc.common.views.widgetsInterface.generic.ResourceListWidget;
import org.soc.common.views.widgetsInterface.generic.ResourcePickerWidget;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.common.views.widgetsInterface.main.TradePlayerStatusWidget;
import org.soc.gwt.client.game.widgetsBitmap.generic.ResourceListBitmapWidget;
import org.soc.gwt.client.game.widgetsBitmap.generic.ResourcePickerBitmapWidget;
import org.soc.gwt.client.game.widgetsBitmap.main.TradePlayerStatusBitmapWidget;
import org.soc.gwt.client.images.R;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class TradePlayersDialog extends PopupPanel implements
        TradePlayerDialog, TurnChangedHandler,
        TradeOfferedEventHandler, OrderChangedHandler,
        TradeRespondedEventHandler, ResourcesChangedHandler
{
  private ResourceList wantResources = new ResourceList();
  private ResourceList giveResources = new ResourceList();
  private ResourceList playerHand;
  private ResourcePickerWidget giveResourcePickerWidget;
  private ResourcePickerWidget wantedResourcePickerWidget;
  private ResourceListWidget giveResourcesListWidget;
  private ResourceListWidget wantedResourceListWidget;
  private GameWidget gameWidget;
  private VerticalPanel pnlGiveResources;
  private VerticalPanel pnlWantResources;
  private Map<GamePlayer, TradePlayerStatusWidget> playerStatuses = new HashMap<GamePlayer, TradePlayerStatusWidget>();
  private VerticalPanel pnlTradeStatuses;
  private GamePlayer player;
  private HandlerRegistration tradeOfferHandler;
  private HandlerRegistration tradeResponseHandler;
  private Button btnOfferTrade;
  private Label lblOfferStatus;

  public TradePlayersDialog(GameWidget gameWidget, GamePlayer player)
  {
    this();
    this.gameWidget = gameWidget;
    this.player = player;
    this.playerHand = player.resources().copy();
    giveResourcesListWidget = createResourceListWidget(giveResources,
            playerHand, null);
    wantedResourceListWidget = createResourceListWidget(wantResources,
            null, null);
    giveResourcePickerWidget = createResourcePickerWidget(giveResources,
            null, playerHand, gameWidget);
    wantedResourcePickerWidget = createResourcePickerWidget(wantResources,
            null, null, gameWidget);
    for (GamePlayer opponent : gameWidget.game().players())
    {
      TradePlayerStatusWidget tradeStatus = new TradePlayerStatusBitmapWidget(
              gameWidget, opponent, player, this);
      playerStatuses.put(opponent, tradeStatus);
      pnlTradeStatuses.add(tradeStatus);
    }
    pnlGiveResources.add(giveResourcePickerWidget);
    pnlGiveResources.add(giveResourcesListWidget);
    pnlWantResources.add(wantedResourcePickerWidget);
    pnlWantResources.add(wantedResourceListWidget);
    gameWidget.game().addTurnChangedHandler(this);
    gameWidget.game().players().addOrderChangedHandler(this);
    wantResources.addResourcesChangedHandler(this);
    giveResources.addResourcesChangedHandler(this);
  }
  private ResourceListWidget createResourceListWidget(ResourceList resources,
          ResourceList bankResources, PortList ports)
  {
    return new ResourceListBitmapWidget(resources, bankResources, ports);
  }
  private ResourcePickerWidget createResourcePickerWidget(
          ResourceList resources, PortList ports,
          ResourceList bankResources, GameWidget gameWidget)
  {
    return new ResourcePickerBitmapWidget(resources, ports, bankResources,
            gameWidget);
  }
  /** @wbp.parser.constructor */
  public TradePlayersDialog()
  {
    super(true);
    HorizontalPanel horizontalPanel = new HorizontalPanel();
    setWidget(horizontalPanel);
    horizontalPanel.setSize("521px", "339px");
    VerticalPanel pnlPlayersResponse = new VerticalPanel();
    pnlPlayersResponse.setHeight("100%");
    pnlPlayersResponse
            .setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    horizontalPanel.add(pnlPlayersResponse);
    pnlTradeStatuses = new VerticalPanel();
    pnlPlayersResponse.add(pnlTradeStatuses);
    DockPanel dockPanel_1 = new DockPanel();
    pnlPlayersResponse.add(dockPanel_1);
    dockPanel_1.setWidth("100%");
    Label lblGet = new Label("get");
    dockPanel_1.add(lblGet, DockPanel.WEST);
    lblGet.setStyleName("label-title");
    Label lblGive = new Label("give");
    lblGive.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
    dockPanel_1.add(lblGive, DockPanel.EAST);
    lblGive.setStyleName("label-title");
    HorizontalPanel horizontalPanel_3 = new HorizontalPanel();
    horizontalPanel_3.setSpacing(10);
    pnlPlayersResponse.add(horizontalPanel_3);
    lblOfferStatus = new Label("New label");
    horizontalPanel_3.add(lblOfferStatus);
    HorizontalPanel horizontalPanel_5 = new HorizontalPanel();
    horizontalPanel_5
            .setVerticalAlignment(HasVerticalAlignment.ALIGN_BOTTOM);
    pnlPlayersResponse.add(horizontalPanel_5);
    Button button = new Button("New button");
    horizontalPanel_5.add(button);
    button.setStyleName("cancel-button");
    button.addClickHandler(new ClickHandler()
    {
      public void onClick(ClickEvent arg0)
      {
        hide();
      }
    });
    button.setText("Close");
    button.setWidth("101px");
    VerticalPanel pnlTradeOptions = new VerticalPanel();
    horizontalPanel.add(pnlTradeOptions);
    HorizontalPanel horizontalPanel_2 = new HorizontalPanel();
    horizontalPanel_2
            .setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
    horizontalPanel_2.setSpacing(10);
    pnlTradeOptions.add(horizontalPanel_2);
    Image image = new Image(R.icons().tradePlayer32());
    horizontalPanel_2.add(image);
    Label lblTradeWithOpponents = new Label("Trade with opponents");
    lblTradeWithOpponents.setStyleName("label-title");
    horizontalPanel_2.add(lblTradeWithOpponents);
    HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
    horizontalPanel_1.setHeight("156px");
    pnlTradeOptions.add(horizontalPanel_1);
    pnlWantResources = new VerticalPanel();
    pnlWantResources.setHeight("145px");
    pnlWantResources.setSpacing(10);
    horizontalPanel_1.add(pnlWantResources);
    Label label_1 = new Label("get");
    pnlWantResources.add(label_1);
    label_1.setStyleName("label-title");
    pnlGiveResources = new VerticalPanel();
    pnlGiveResources.setHeight("145px");
    pnlGiveResources.setSpacing(10);
    horizontalPanel_1.add(pnlGiveResources);
    Label label = new Label("give");
    label.setStyleName("label-title");
    label.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
    pnlGiveResources.add(label);
    DockPanel dockPanel = new DockPanel();
    dockPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
    dockPanel.setSpacing(10);
    pnlTradeOptions.add(dockPanel);
    HorizontalPanel horizontalPanel_4 = new HorizontalPanel();
    dockPanel.add(horizontalPanel_4, DockPanel.WEST);
    Label lblYouHave = new Label("You have ");
    horizontalPanel_4.add(lblYouHave);
    lblYouHave.setWidth("67px");
    Label lblX = new Label("X");
    horizontalPanel_4.add(lblX);
    lblX.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    lblX.setWidth("22px");
    Label lblTradesLeft = new Label("trades left");
    horizontalPanel_4.add(lblTradesLeft);
    lblTradesLeft.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
    btnOfferTrade = new Button("New button");
    dockPanel.add(btnOfferTrade, DockPanel.EAST);
    btnOfferTrade.setStyleName("ok-button");
    btnOfferTrade.addClickHandler(new ClickHandler()
    {
      public void onClick(ClickEvent event)
      {
        offerTrade();
      }
    });
    btnOfferTrade.setText("Offer trade");
  }
  /* (non-Javadoc)
   * 
   * @see com.google.gwt.user.client.ui.PopupPanel#show() */
  @Override public void show()
  {
    updateResources();
    for (TradePlayerStatusWidget tradeStatus : playerStatuses.values())
      tradeStatus.update(null);
    Point2D location = gameWidget.getPlayersInfoWidget()
            .getTopRightLocation();
    setPopupPosition(location.getX(), location.getY());
    super.show();
  }
  private void offerTrade()
  {
    TradeOffer offer = new TradeOffer();
    offer.setPlayer(player);
    offer.getOfferedResources().addList(giveResources);
    offer.getRequestedResources().addList(wantResources);
    gameWidget.doAction(offer);
  }
  private void updateResources()
  {
    // Get rid of any old resources
    giveResources.clear();
    wantResources.clear();
    playerHand = player.resources().copy();
    giveResourcePickerWidget.setBankResources(playerHand);
    giveResourcesListWidget.setBankResources(playerHand);
  }
  @Override public void onTurnChanged(TurnChangedEvent event)
  {
    if (tradeOfferHandler != null)
    {
      tradeOfferHandler.removeHandler();
      tradeOfferHandler = null;
    }
    if (player.isOnTurn())
    {
      tradeOfferHandler = gameWidget.game().turn()
              .getTradeOffers().addTradeOfferedEventHandler(this);
    }
  }
  @Override public void onTradeOffered(TradeOfferedEvent event)
  {
    btnOfferTrade.setEnabled(false);
    if (tradeResponseHandler != null)
    {
      tradeResponseHandler.removeHandler();
      tradeResponseHandler = null;
    }
    tradeResponseHandler = gameWidget.game().turn()
            .getTradeOffers().getLatestOffer().getResponses()
            .addTradeRespondedEventHandler(this);
    for (TradePlayerStatusWidget tradeStatus : playerStatuses.values())
      tradeStatus.setWaiting();
  }
  @Override public void onTradeResponded(TradeRespondedEvent event)
  {
    TradePlayerStatusWidget widget = playerStatuses.get(event
            .getTradeResponse().player());
    widget.update(event.getTradeResponse());
    checkUI();
  }
  /* Clears the list of trade statuses, and adds all trade statuses back to the containing panel.
   * This ensures the order of players is updated according to the list of players in the game.
   * 
   * @see org.soc.common.game.player.OrderChangedEventHandler#onOrderChanged(org.soc.common
   * .game.player.OrderChangedEvent) */
  @Override public void onOrderChanged(OrderChangedEvent event)
  {
    pnlTradeStatuses.clear();
    for (GamePlayer player : gameWidget.game().players())
      pnlTradeStatuses.add(playerStatuses.get(player));
  }
  @Override public void onResourcesChanged(ResourcesChangedEvent resourcesChanged)
  {
    checkUI();
  }
  private void checkUI()
  {
    if (wantResources.size() > 0 && giveResources.size() > 0)
    {
      if (gameWidget.game().turn().getTradeOffers()
              .getLatestOffer() != null)
      {
        if (gameWidget.game().turn().getTradeOffers()
                .getLatestOffer().isResponsesCompleted())
        {
          btnOfferTrade.setEnabled(true);
        } else
        {
          btnOfferTrade.setEnabled(false);
        }
      } else
      {
        btnOfferTrade.setEnabled(true);
      }
    } else
    {
      btnOfferTrade.setEnabled(false);
    }
  }
  @Override public void trade(TradeResponse tradeResponse)
  {
    gameWidget.doAction(new TradePlayer()
            .setOriginatingOffer(
                    gameWidget.game().turn()
                            .getTradeOffers()
                            .getLatestOffer())
            .setResponse(tradeResponse)
            .setTradeOpponent(tradeResponse.player())
            .setPlayer(player));
  }
}