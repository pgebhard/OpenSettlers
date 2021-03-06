package org.soc.common.game.board;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.soc.common.game.board.HexSide.HexSideImpl;

/** Represents an intersection between 3 HexLocations. The HexLocations may be outside the board when
 * the intersection itself is located on a board edge. In that case, a HexLocation would have a
 * negative value or a value outside the bounds of the HexGrid class keeping a list of Hexes.
 * 
 * TODO: cache values of neighbours either at construction time or lazy loading TODO: Abstract to
 * interface */
public class HexPoint implements Serializable {
  private HexLocation hex1;
  private HexLocation hex2;
  private HexLocation hex3;

  public enum PointPositionOnHex {
    //          TopMiddle,
    //              ^
    //    TopLeft  /  \  TopRight
    //            |    |
    //            |    |
    // BottomLeft  \  /  BottomRight
    //               +    
    //         BottomMiddle
    TopMiddle,
    TopRight,
    BottomRight,
    BottomMiddle,
    BottomLeft,
    TopLeft;
  }

  public enum HexPointType {
    // the point has 2 hexes on the highest row (1 on lowest)
    UpperRow2,
    // the point has 1 hex on the highest row (2 on lowest)
    UpperRow1;
  }

  public HexLocation hex1() {
    return hex1;
  }
  public HexLocation hex2() {
    return hex2;
  }
  public HexLocation hex3() {
    return hex3;
  }
  /** Returns the type of the hex: one or two Hexes on the upper row */
  public HexPointType pointType() {
    List<HexLocation> points = new ArrayList<HexLocation>();
    points.add(hex3);
    points.add(hex2);
    points.add(hex1);
    int h = 220;
    for (HexLocation point : points) {
      if (point.h() < h)
        h = point.h();
    }
    int count = 0;
    for (HexLocation p : points) {
      if (p.h() == h) {
        count++;
      }
    }
    if (count == 1) {
      // There is one Hex at the upper height coordinate
      return HexPointType.UpperRow1;
    } else {
      // There are two Hexes at the upper height coordinate
      return HexPointType.UpperRow2;
    }
  }
  /** Returns position on of this point on the top or top left most HexLocation */
  public PointPositionOnHex getHexPositionOnTopLeftMost() {
    return pointType() == HexPointType.UpperRow1 ? PointPositionOnHex.BottomMiddle
            : PointPositionOnHex.BottomRight;
  }
  /** Returns a list of HexSides with given hexside excluded */
  public List<HexSide> getOtherSides(HexSide side) {
    List<HexSide> result = new ArrayList<HexSide>();
    for (HexSide s : neighbourSides())
      if (!side.equals(s))
        result.add(s);
    return result;
  }
  /** Returns a list of three HexSides adjacent to this point */
  public List<HexSide> neighbourSides() {
    List<HexSide> result = new ArrayList<HexSide>();
    // add all three hex sides around point 
    result.add(new HexSideImpl(hex1, hex2));
    result.add(new HexSideImpl(hex1, hex3));
    result.add(new HexSideImpl(hex2, hex3));
    return result;
  }
  /** Returns the topmost hex of the three hexes */
  public HexLocation getTopMost() {
    List<HexLocation> points = new ArrayList<HexLocation>();
    points.add(hex3);
    points.add(hex2);
    points.add(hex1);
    int w = 220;
    int h = 220;
    for (HexLocation point : points) {
      if (point.w() < w)
        w = point.w();
      if (point.h() < h)
        h = point.h();
    }
    List<HexLocation> res = new ArrayList<HexLocation>();
    if (hex1.h() == h)
      res.add(hex1);
    if (hex2.h() == h)
      res.add(hex2);
    if (hex3.h() == h)
      res.add(hex3);
    if (res.size() == 1) {
      return res.get(0);
    } else {
      if (res.size() == 2)
      {
        HexLocation l = res.get(0);
        if (l.w() < res.get(1).w())
          return l;
        else
          return res.get(1);
      }
    }
    return null;
  }
  /** Returns a list of neighbour points */
  public List<HexPoint> getNeighbours() {
    List<HexPoint> result = new ArrayList<HexPoint>();
    HexLocation topmost = getTopMost();
    if (topmost.h() % 2 == 0) {
      // even rows
      if (pointType() == HexPointType.UpperRow1) {
        HexPoint p1 = new HexPoint(topmost, new HexLocation(
                topmost.w() - 1, topmost.h()),
                new HexLocation(topmost.w(),
                        topmost.h() + 1));
        result.add(p1);
        HexPoint p2 = new HexPoint(new HexLocation(topmost.w() + 1,
                topmost.h() + 1), new HexLocation(
                topmost.w(), topmost.h() + 1),
                new HexLocation(topmost.w(),
                        topmost.h() + 2));
        result.add(p2);
        HexPoint p3 = new HexPoint(topmost, new HexLocation(
                topmost.w() + 1, topmost.h() + 1),
                new HexLocation(topmost.w() + 1, topmost
                        .h()));
        result.add(p3);
      } else {
        HexPoint p1 = new HexPoint(topmost, new HexLocation(
                topmost.w() + 1, topmost.h()),
                new HexLocation(topmost.w() + 1, topmost
                        .h() - 1));
        result.add(p1);
        HexPoint p2 = new HexPoint(new HexLocation(topmost.w() + 2,
                topmost.h() + 1), new HexLocation(
                topmost.w() + 1, topmost.h() + 1),
                new HexLocation(topmost.w() + 1, topmost
                        .h()));
        result.add(p2);
        HexPoint p3 = new HexPoint(topmost, new HexLocation(
                topmost.w() + 1, topmost.h() + 1),
                new HexLocation(topmost.w(),
                        topmost.h() + 1));
        result.add(p3);
      }
    } else {
      // uneven rows
      if (pointType() == HexPointType.UpperRow1)
      {
        HexPoint p1 = new HexPoint(topmost, new HexLocation(
                topmost.w() - 1, topmost.h() + 1),
                new HexLocation(topmost.w() - 1, topmost
                        .h()));
        result.add(p1);
        HexPoint p2 = new HexPoint(new HexLocation(topmost.w(),
                topmost.h() + 1), new HexLocation(
                topmost.w() - 1, topmost.h() + 1),
                new HexLocation(topmost.w(),
                        topmost.h() + 2));
        result.add(p2);
        HexPoint p3 = new HexPoint(topmost, new HexLocation(
                topmost.w(), topmost.h() + 1),
                new HexLocation(topmost.w() + 1, topmost
                        .h()));
        result.add(p3);
      } else {
        HexPoint p1 = new HexPoint(topmost, new HexLocation(
                topmost.w(), topmost.h() - 1),
                new HexLocation(topmost.w() + 1, topmost
                        .h()));
        result.add(p1);
        HexPoint p2 = new HexPoint(new HexLocation(topmost.w() + 1,
                topmost.h()), new HexLocation(
                topmost.w() + 1, topmost.h() + 1),
                new HexLocation(topmost.w(),
                        topmost.h() + 1));
        result.add(p2);
        HexPoint p3 = new HexPoint(topmost, new HexLocation(
                topmost.w(), topmost.h() + 1),
                new HexLocation(topmost.w() - 1, topmost
                        .h() + 1));
        result.add(p3);
      }
    }
    return result;
  }
  /* Returns a list of neighbours, with the given neighbour excluded */
  public List<HexPoint> getOtherNeighbours(HexPoint ignore) {
    List<HexPoint> result = getNeighbours();
    result.remove(ignore);
    return result;
  }
  /* Creates a HexPoint using given three HexLocations */
  public HexPoint(HexLocation hex1, HexLocation hex2, HexLocation hex3) {
    this.hex1 = hex1;
    this.hex2 = hex2;
    this.hex3 = hex3;
    if (hex1.equals(hex2) || hex1.equals(hex3) || hex2.equals(hex3))
      throw new RuntimeException("Impossible hexpoint detected");
  }
  /* Returns true if given HexLocation is equal to one of the three hexes */
  public boolean hasLocation(HexLocation location) {
    return hex1.equals(location)
            || hex2.equals(location)
            || hex3.equals(location);
  }
  /* Creates a HexPoint using one HexLocation and a position on that HexLocation */
  public HexPoint(HexLocation hex, PointPositionOnHex relativePosition) throws Exception {
    // we must assume hex comes from a uneven row, and
    // relative position on the hex is never the two left positions
    if (hex.h() % 2 == 0)
      throw new Exception("WHooa!");
    hex1 = hex;
    switch (relativePosition) {
      case TopMiddle:
        hex2 = new HexLocation(hex.w() - 1, hex.h() - 1);
        hex3 = new HexLocation(hex.w(), hex.h() - 1);
        break;
      case TopRight:
        hex2 = new HexLocation(hex.w(), hex.h() - 1);
        hex3 = new HexLocation(hex.w() + 1, hex.h());
        break;
      case BottomRight:
        hex2 = new HexLocation(hex.w() + 1, hex.h());
        hex3 = new HexLocation(hex.w(), hex.h() + 1);
        break;
      case BottomMiddle:
        hex2 = new HexLocation(hex.w(), hex.h() + 1);
        hex3 = new HexLocation(hex.w() - 1, hex.h() + 1);
        break;
      default:
        throw new Exception("Whoa!");
    }
  }
  public HexPoint() {}
  // create a point out of two neighbouring sides
  public HexPoint(HexSide side1, HexSide side2) {
    List<HexLocation> allLocations = new ArrayList<HexLocation>();
    allLocations.add(side1.hex1());
    allLocations.add(side1.hex2());
    allLocations.add(side2.hex1());
    allLocations.add(side2.hex2());
    HexLocation equalHex = null;
    if (side1.hex1().equals(side2.hex1()))
      equalHex = side1.hex1();
    if (side1.hex1().equals(side2.hex2()))
      equalHex = side1.hex1();
    allLocations.remove(equalHex);
    this.hex1 = allLocations.get(0);
    this.hex2 = allLocations.get(1);
    this.hex3 = allLocations.get(2);
  }
  public String toString() {
    return "hex1: " + hex1.toString()
            + "hex2: " + hex2.toString()
            + "hex3: " + hex3.toString();
  }
  /* Returns containing three HexLocations in a list */
  public List<HexLocation> hexLocations() {
    List<HexLocation> result = new ArrayList<HexLocation>();
    result.add(hex1);
    result.add(hex2);
    result.add(hex3);
    return result;
  }
  @Override public int hashCode() {
    return hex1.hashCode() + hex2.hashCode() + hex3.hashCode();
  }
  @Override public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    HexPoint other = (HexPoint) obj;
    List<HexLocation> hexLocations = hexLocations();
    return hexLocations.contains(other.hex1())
            && hexLocations.contains(other.hex2())
            && hexLocations.contains(other.hex3());
  }
  /* Returns true when this HexPoint falls within the bounds of given width and height */
  public boolean fallsWithinBoardBounds(int width, int height) {
    if ((!hex1.fallsInsideBoardBounds(width, height))
            || (!hex2.fallsInsideBoardBounds(width, height))
            || (!hex3.fallsInsideBoardBounds(width, height)))
      return false;
    else
      return true;
  }
}
