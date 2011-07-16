package soc.common.views.behaviour.board;

import soc.common.board.territories.Territory;
import soc.common.views.widgetsInterface.visuals.BoardVisual;
import soc.common.views.widgetsInterface.visuals.HexVisual;
import soc.common.views.widgetsInterface.visuals.PieceVisual;

public class SetTerritoryBehaviour implements BoardBehaviour
{
    private Territory territory;

    @Override
    public void clicked(PieceVisual pieceVisual, BoardVisual board)
    {
        HexVisual hexVisual = pieceVisual.getHexVisual();
        if (hexVisual != null && hexVisual.getHex().getTerritory() != null)
        {
            hexVisual.getTerritory().setTerritory(territory);
            hexVisual.getPortPossibilitiesVisual().updatePossibilities();
        }
    }

    /**
     * @return the territory
     */
    public Territory getTerritory()
    {
        return territory;
    }

    /**
     * @param territory
     *            the territory to set
     */
    public SetTerritoryBehaviour setTerritory(Territory territory)
    {
        this.territory = territory;
        return this;
    }

    public void mouseEnter(PieceVisual pieceVisual, BoardVisual board)
    {
        HexVisual hexVisual = pieceVisual.getHexVisual();
        if (hexVisual != null)
            hexVisual.setSelected(hexVisual.getTerritory() != null);
    }

    @Override
    public void mouseOut(PieceVisual hex, BoardVisual board)
    {
        hex.setSelected(false);
    }

}