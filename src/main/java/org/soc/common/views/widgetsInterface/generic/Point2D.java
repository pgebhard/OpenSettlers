package org.soc.common.views.widgetsInterface.generic;

/*
 * Simplistic immutable encapsulation of an x,y coordinate.
 */
public class Point2D
{
    private int x;
    private int y;

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public Point2D(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}
