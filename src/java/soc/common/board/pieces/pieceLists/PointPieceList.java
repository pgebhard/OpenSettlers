package soc.common.board.pieces.pieceLists;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import soc.common.board.layout.HasPoint;
import soc.common.board.layout.HexPoint;

public class PointPieceList implements Iterable<HasPoint>, Serializable
{
    private static final long serialVersionUID = -5650123247361039636L;
    private List<HasPoint> pointPieces = new ArrayList<HasPoint>();
    private List<HexPoint> points = new ArrayList<HexPoint>();

    @Override
    public Iterator<HasPoint> iterator()
    {
        return pointPieces.iterator();
    }

    public void add(HasPoint pointPiece)
    {
        pointPieces.add(pointPiece);
        points.add(pointPiece.getPoint());
    }

    public void remove(HasPoint pointPiece)
    {
        pointPieces.remove(pointPiece);
        points.remove(pointPiece.getPoint());
    }

    public void addList(PointPieceList listToAdd)
    {
        for (HasPoint pointPiece : listToAdd)
            add(pointPiece);
    }

    public boolean contains(HexPoint point)
    {
        return points.contains(point);
    }

    public int size()
    {
        return pointPieces.size();
    }

    public List<HexPoint> getPoints()
    {
        return points;
    }

    public HasPoint get(int i)
    {
        return pointPieces.get(0);
    }
}
