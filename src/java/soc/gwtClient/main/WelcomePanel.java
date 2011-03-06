package soc.gwtClient.main;

import soc.gwtClient.game.widgetsBitmap.dialogs.NewHotseatGame;
import soc.gwtClient.images.Resources;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class WelcomePanel extends HorizontalPanel implements CenterWidget
{
    private DockLayoutPanel rootPanel = new DockLayoutPanel(Unit.EM);
    private VerticalPanel menuPanel = new VerticalPanel();
    private Button btnMapCreator;
    private final MainWindow mainWindow;
    private NewHotseatGame newHotseatGameWidget;

    public WelcomePanel(MainWindow mainWindow)
    {
        this.mainWindow = mainWindow;

        newHotseatGameWidget = new NewHotseatGame(mainWindow);

        initialize();
    }

    private void initialize()
    {
        rootPanel.addWest(menuPanel, 15);

        menuPanel.setSpacing(5);

        Button btnHotseatGame = new Button();
        btnHotseatGame.setHTML("<H3><img src=\""
                        + Resources.icons().newHotseatGame().getURL()
                        + "\">HotSeat game</H3>Play a mock hotseat game");
        btnHotseatGame.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                rootPanel.add(newHotseatGameWidget);
            }
        });
        menuPanel.add(btnHotseatGame);

        Button btnvisitWikihelpWrite = new Button("<H3><img src=\""
                        + Resources.icons().wiki48().getURL()
                        + "\">Visit Wiki</H3>Help write our UI using wiki");
        btnvisitWikihelpWrite.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent arg0)
            {
                mainWindow.setCurrentWidget(mainWindow.getWikiPage());
            }
        });
        menuPanel.add(btnvisitWikihelpWrite);

        btnMapCreator = new Button();
        btnMapCreator.setHTML("<H3><img src=\""
                        + Resources.icons().mapEditor48().getURL()
                        + "\">MapCreator</H3>create a new map");
        btnMapCreator.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                mainWindow.setCurrentWidget(mainWindow.getMapEditor());
            }
        });
        menuPanel.add(btnMapCreator);

        Button btnJavadoc = new Button();
        btnJavadoc.setHTML("<H3><img src=\""
                        + Resources.icons().javadoc48().getURL()
                        + "\">View Javadoc</H3>create a new map");
        btnJavadoc.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                mainWindow.setCurrentWidget(mainWindow.getJavadoc());
            }
        });
        menuPanel.add(btnJavadoc);

        Button btnLobby = new Button();
        btnLobby.setHTML("<H3><img src=\""
                        + Resources.icons().lobby48().getURL()
                        + "\">Lobby</H3>Find games and players");
        btnLobby.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                mainWindow.setCurrentWidget(mainWindow.getLobby());
            }
        });
        menuPanel.add(btnLobby);
    }

    @Override
    public Panel getRootWidget()
    {
        return rootPanel;
    }
}
