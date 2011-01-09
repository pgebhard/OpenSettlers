package soc.gwtClient.visuals.abstractVisuals;

import soc.common.board.ports.Port;

public interface PortVisual extends PieceVisual
{
    public PortVisual setPort(Port port);
    public Port getPort();

    // A PortVisual may showinvalid status, if the port is not allowed to be built
    // on the selected spot.
    public PortVisual setValid(boolean valid);
    public boolean isValid();
}