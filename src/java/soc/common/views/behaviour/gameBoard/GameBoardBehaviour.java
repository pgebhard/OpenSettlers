package soc.common.views.behaviour.gameBoard;

import soc.common.actions.gameAction.GameAction;
import soc.common.views.widgetsInterface.visuals.GameBoardVisual;
import soc.common.views.widgetsInterface.visuals.PieceVisual;

public interface GameBoardBehaviour
{
    public void start(GameBoardVisual gameVisual);

    public void setNeutral(GameBoardVisual visual);

    public void clicked(PieceVisual pieceVisual, GameBoardVisual gameBoardVisual);

    public void mouseEnter(PieceVisual pieceVisual,
            GameBoardVisual gameBoardVisual);

    public void mouseOut(PieceVisual pieceVisual,
            GameBoardVisual gameBoardVisual);

    public GameAction getGameAction();
}