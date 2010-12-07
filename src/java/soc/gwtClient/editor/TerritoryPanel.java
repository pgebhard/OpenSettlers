package soc.gwtClient.editor;

import soc.common.board.territories.Territory;
import soc.common.client.behaviour.editor.SetTerritoryBehaviour;
import soc.common.client.visuals.board.IBoardVisual;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

@SuppressWarnings("deprecation")
public class TerritoryPanel extends VerticalPanel
{
    SetTerritoryBehaviour behaviour;
    IBoardVisual board;
    final DialogBox dialogBox = new DialogBox();
    
    private HandlerManager handlerManager = new HandlerManager(this);

    @Override
    public void fireEvent(GwtEvent<?> event) {
        handlerManager.fireEvent(event);
    }

    public HandlerRegistration addBehaviourChangedEventHandler(
            IBehaviourChangedHandler handler) {
        return handlerManager.addHandler(BehaviourChanged.TYPE, handler);
    }
    
    
    public TerritoryPanel(SetTerritoryBehaviour b, final IBoardVisual board)
    {
        super();
        
        behaviour=b;
        this.board=board;
        
        createAddTerritoryWindow();
        createPanel();
    }
    
    private void createPanel()
    {
        PushButton btnShowTerritories = new PushButton(new Image("icons/32/ShowTerritories32.png"));
        btnShowTerritories.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // Show all territory visuals on TerritoryHexes
                board.showTerritories();
            }
        });
        this.add(btnShowTerritories);
        
        PushButton btnHideTerritories = new PushButton(new Image("icons/32/HideTerritories32.png"));
        btnHideTerritories.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // Show all territory visuals on TerritoryHexes
                board.hideTerritories();
            }
        });
        this.add(btnHideTerritories);   
        
        PushButton btnAddTerritory = new PushButton(new Image("icons/32/AddTerritory32.png"));
        btnAddTerritory.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                dialogBox.show();
            }
        });
        this.add(btnAddTerritory);   
        
        PushButton btnRemoveTerritory = new PushButton(new Image("icons/32/RemoveTerritory32.png"));
        btnRemoveTerritory.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // Show all territory visuals on TerritoryHexes
                board.hideTerritories();
            }
        });
        this.add(btnRemoveTerritory);
        
        for (final Territory t : board.getBoard().getTerritories())
        {
            String icon = ""; 
        
            if (t.isMainland())
            {
                icon="icons/32/mainland.png";
            }
            else
            {
                icon="icons/32/island" + t.getID() + ".png";
            }
            
            PushButton btnTerritory = new PushButton(new Image(icon));
            btnTerritory.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    behaviour.setTerritory(t);
                    fireEvent(new BehaviourChanged(behaviour));
                }
            });
            this.add(btnTerritory);
        }
    }

    private void createAddTerritoryWindow()
    {
        // create addTerritory dialogbox
        dialogBox.setText("Add a new territory");
        dialogBox.setAnimationEnabled(true);
        final Button closeButton = new Button("Close");
        final Button addButton = new Button("Add territory");
        // We can set the id of a widget by accessing its Element
        closeButton.getElement().setId("closeButton");
        final Label textToServerLabel = new Label();
        final TextBox txtName = new TextBox();
        final HTML serverResponseLabel = new HTML();
        VerticalPanel dialogVPanel = new VerticalPanel();
        dialogVPanel.addStyleName("dialogVPanel");
        dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
        dialogVPanel.add(textToServerLabel);
        dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
        dialogVPanel.add(serverResponseLabel);
        dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
        dialogVPanel.add(addButton);
        dialogVPanel.add(closeButton);
        dialogBox.setWidget(dialogVPanel);

        // Add a handler to close the DialogBox
        closeButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                dialogBox.hide();
            }
        });        
        
        closeButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                /*
                board.getBoard().getTerritories().add(
                        new Territory()
                            .setName(txtName.getText())
                            .setID
                dialogBox.hide();
                */
            }
        });
    }
}
