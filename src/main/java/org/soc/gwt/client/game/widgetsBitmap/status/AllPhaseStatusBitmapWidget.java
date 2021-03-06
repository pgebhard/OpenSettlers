package org.soc.gwt.client.game.widgetsBitmap.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.soc.common.game.GamePhase;
import org.soc.common.game.GamePhaseChangedEvent;
import org.soc.common.game.GamePhaseChangedEvent.GamePhaseChangedHandler;
import org.soc.common.views.widgetsInterface.main.GamePhaseWidget;
import org.soc.common.views.widgetsInterface.main.GamePhaseWidget.GamePhaseStatusWidgetFactory;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.images.R;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class AllPhaseStatusBitmapWidget implements
        GamePhaseChangedHandler, IsWidget
{
  private HorizontalPanel rootPanel = new HorizontalPanel();
  private VerticalPanel phasesRootPanel = new VerticalPanel();
  private DeckPanel currentPhasePanel = new DeckPanel();
  private HorizontalPanel gamePhaseIconsPanel = new HorizontalPanel();
  private List<Image> gamePhaseIcons = new ArrayList<Image>();
  private Map<GamePhase, GamePhaseWidget> phaseWidgets = new HashMap<GamePhase, GamePhaseWidget>();
  private List<GamePhaseWidget> widgets = new ArrayList<GamePhaseWidget>();
  private GameWidget gameWidget;
  private Image currentPhaseIcon;

  public AllPhaseStatusBitmapWidget(GameWidget gameWidget)
  {
    this.gameWidget = gameWidget;
    GamePhaseStatusWidgetFactory factory = gameWidget.clientFactory()
            .getGamePhaseStatusWidgetFactory();
    for (GamePhase gamePhase : gameWidget.game().rules()
            .supportedPhases())
    {
      gamePhaseIcons.add(new Image(R.mediumIcon(gamePhase)));
      GamePhaseWidget widget = gamePhase
              .createGamePhaseStatusWidget(factory);
      phaseWidgets.put(gamePhase, widget);
      currentPhasePanel.add(widget);
      widgets.add(widget);
    }
    for (Image icon : gamePhaseIcons)
      gamePhaseIconsPanel.add(icon);
    phasesRootPanel.add(new Label("Game phases"));
    phasesRootPanel.add(gamePhaseIconsPanel);
    rootPanel.add(phasesRootPanel);
    rootPanel.add(currentPhasePanel);
    gameWidget.game().addGamePhaseChangedHandler(this);
    setCurrentPhase();
    rootPanel.setStyleName("allGamePhases");
    currentPhasePanel.setStyleName("phasePanel");
  }
  @Override public void onGamePhaseChanged(GamePhaseChangedEvent event)
  {
    setCurrentPhase();
  }
  private void setCurrentPhase()
  {
    GamePhase newPhase = gameWidget.game().phase();
    if (currentPhaseIcon != null)
      currentPhaseIcon.setStyleName("phaseNotSelectedIcon");
    GamePhaseWidget widget = null;
    for (Entry<GamePhase, GamePhaseWidget> entry : phaseWidgets
            .entrySet())
      if (entry.getKey().getClass() == newPhase.getClass())
      {
        widget = entry.getValue();
        break;
      }
    ImageResource newSelectedIcon = widget.getIcon();
    for (Image icon : gamePhaseIcons)
      if (icon.getUrl().equals(newSelectedIcon.getURL()))
        currentPhaseIcon = icon;
    currentPhaseIcon.setStyleName("phaseSelectedIcon");
    currentPhasePanel.showWidget(widgets.indexOf(widget));
  }
  @Override public Widget asWidget()
  {
    return rootPanel;
  }
}
