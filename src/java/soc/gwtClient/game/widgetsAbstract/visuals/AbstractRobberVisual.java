package soc.gwtClient.game.widgetsAbstract.visuals;

import soc.common.board.pieces.Robber;
import soc.gwtClient.game.widgetsInterface.visuals.RobberVisual;

public class AbstractRobberVisual extends AbstractPieceVisual implements
        RobberVisual
{
    protected Robber robber;

    public AbstractRobberVisual(Robber robber)
    {
        super();
        this.robber = robber;
    }

    @Override
    public Robber getRober()
    {
        return robber;
    }

}