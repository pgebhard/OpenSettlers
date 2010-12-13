package soc.gwtClient.game.widgets.standard.svg;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;

import soc.common.game.DiceChangedEvent;
import soc.common.game.dices.IDice;
import soc.common.game.dices.StandardDice;
import soc.gwtClient.game.abstractWidgets.AbstractStandardDiceWidget;
import soc.gwtClient.game.abstractWidgets.IGamePanel;

public class SvgStandardDiceWidget extends AbstractStandardDiceWidget
{
    SvgSingleDiceWidget dice1= new SvgSingleDiceWidget();
    SvgSingleDiceWidget dice2 = new SvgSingleDiceWidget();
    
    public SvgStandardDiceWidget(IGamePanel gamePanel)
    {
        super(gamePanel);

        rootPanel.add(dice1);
        rootPanel.add(dice2);
        
        gamePanel.getGame().addDiceChangedEventHandler(this);
    }

    @Override
    protected ComplexPanel createRootPanel()
    {
        return new HorizontalPanel();
    }

    @Override
    public void setStandardDice(StandardDice standardDice)
    {
        
    }

    @Override
    public void onDiceChanged(DiceChangedEvent event)
    {
        IDice dice = event.getNewDice();
        if (dice instanceof StandardDice)
        {
            StandardDice standardDice = (StandardDice)dice;
            dice1.setNumber(standardDice.getDice1());
            dice2.setNumber(standardDice.getDice2());
        }
    }
}