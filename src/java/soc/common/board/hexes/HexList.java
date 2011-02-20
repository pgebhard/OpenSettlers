package soc.common.board.hexes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import soc.common.server.randomization.Random;

public class HexList implements Collection<Hex>, Serializable
{
    private static final long serialVersionUID = 1628486402283426134L;
    private List<Hex> hexes = new ArrayList<Hex>();

    public boolean add(Hex hex)
    {
        return hexes.add(hex);
    }

    public Hex grabRandom(Random random)
    {
        int index = random.nextInt(hexes.size() - 1, false);
        Hex result = hexes.get(index);
        hexes.remove(result);
        return result;
    }

    @Override
    public Iterator<Hex> iterator()
    {
        return hexes.iterator();
    }

    public void addList(HexList hexListToAdd)
    {
        hexes.addAll(hexListToAdd);
    }

    @Override
    public boolean addAll(Collection<? extends Hex> arg0)
    {
        return hexes.addAll(arg0);
    }

    @Override
    public void clear()
    {
        hexes.clear();
    }

    @Override
    public boolean contains(Object arg0)
    {
        return hexes.contains(arg0);
    }

    @Override
    public boolean containsAll(Collection<?> arg0)
    {
        return hexes.containsAll(arg0);
    }

    @Override
    public boolean isEmpty()
    {
        return hexes.isEmpty();
    }

    @Override
    public boolean remove(Object arg0)
    {
        return hexes.remove(arg0);
    }

    @Override
    public boolean removeAll(Collection<?> arg0)
    {
        return hexes.removeAll(arg0);
    }

    @Override
    public boolean retainAll(Collection<?> arg0)
    {
        return hexes.retainAll(arg0);
    }

    @Override
    public int size()
    {
        return hexes.size();
    }

    @Override
    public Object[] toArray()
    {
        return hexes.toArray();
    }

    @Override
    public <T> T[] toArray(T[] arg0)
    {
        return hexes.toArray(arg0);
    }
}
