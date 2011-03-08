package soc.common.views.widgetsInterface.visuals;

import soc.common.board.layout.HexPoint;

public interface PointVisual extends PieceVisual
{
    public HexPoint getHexPoint();

    public void addPieceVisual(PieceVisual pieceVisual);
}
