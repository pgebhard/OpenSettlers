package soc.gwtClient.visuals.behaviour;

import soc.gwtClient.visuals.abstractVisuals.BoardVisual;
import soc.gwtClient.visuals.abstractVisuals.GameBoardVisual;
import soc.gwtClient.visuals.abstractVisuals.PieceVisual;

/*
 * Proxies boardbehaviour through to GameBoardBaheviour of encapsulated GameBoardVisual
 */
public class ProxyBehaviour implements BoardBehaviour
{
    private GameBoardVisual gameBoardVisual;

    public ProxyBehaviour(GameBoardVisual gameBoardVisual)
    {
        super();
        this.gameBoardVisual = gameBoardVisual;
    }

    @Override
    public void clicked(PieceVisual pieceVisual, BoardVisual board)
    {
        gameBoardVisual.getBehaviour().clicked(pieceVisual, gameBoardVisual);
    }

    @Override
    public void mouseEnter(PieceVisual pieceVisual, BoardVisual board)
    {
        gameBoardVisual.getBehaviour().mouseEnter(pieceVisual, gameBoardVisual);
    }

    @Override
    public void mouseOut(PieceVisual pieceVisual, BoardVisual board)
    {
        gameBoardVisual.getBehaviour().mouseOut(pieceVisual, gameBoardVisual);
    }
}
