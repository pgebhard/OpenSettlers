package soc.common.board.routing;

import soc.common.board.layout.HasSide;
import soc.common.board.layout.HexSide;
import soc.common.board.pieces.abstractPieces.PlayerPiece;
import soc.common.game.player.GamePlayer;

public class GraphSideImpl implements GraphSide
{
    private HexSide sideLocation;
    private PlayerPiece playerPiece;

    public GraphSideImpl(HexSide sideLocation)
    {
        super();
        this.sideLocation = sideLocation;
    }

    @Override
    public HexSide getSide()
    {
        return sideLocation;
    }

    public GraphSideImpl setSide(HexSide side)
    {
        this.sideLocation = side;

        return this;
    }

    @Override
    public PlayerPiece getPiece()
    {
        return playerPiece;
    }

    @Override
    public GamePlayer getPlayer()
    {
        return playerPiece == null ? null : playerPiece.getPlayer();
    }

    @Override
    public GraphElement setPlayerPiece(PlayerPiece piece)
    {
        this.playerPiece = piece;

        return this;
    }

    @Override
    public boolean isBuildable()
    {
        return playerPiece == null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        return sideLocation.hashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (obj instanceof HexSide)
        {
            return sideLocation.equals(((HexSide) obj));
        }
        if (getClass() != obj.getClass())
            return false;
        GraphSideImpl other = (GraphSideImpl) obj;
        if (sideLocation == null)
        {
            if (other.sideLocation != null)
                return false;
        }
        else if (!sideLocation.equals(other.sideLocation))
            return false;
        return true;
    }

    @Override
    public HasSide getSidePiece()
    {
        return (HasSide) playerPiece;
    }
}
