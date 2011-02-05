package soc.gwtClient.game.widgets.standard.bitmap.playerDetail;

import java.util.HashMap;
import java.util.Map;

import soc.common.board.pieces.Road;
import soc.common.board.pieces.pieceLists.PlayerPieceListChangedEvent;
import soc.common.board.pieces.pieceLists.PlayerPieceListChangedEventHandler;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.AbstractPlayerDetailWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;

public class RoadStockDetailWidget extends AbstractPlayerDetailWidget implements
        PlayerPieceListChangedEventHandler<Road>
{
    private Map<Road, Image> roadImages = new HashMap<Road, Image>();

    public RoadStockDetailWidget(GamePlayer player)
    {
        super(player);

        for (Road road : player.getStock().getRoads())
        {
            Image roadImage = new Image(Resources.icons().roadSmall());
            roadImages.put(road, roadImage);
            rootPanel.add(roadImage);
        }

        player.getStock().getRoads().addRoadsChangedEventHandler(this);
    }

    @Override
    public void onPlayerPieceListChanged(PlayerPieceListChangedEvent<Road> event)
    {
        if (event.getRemovedPiece() != null)
        {
            Image roadImage = roadImages.get(event.getRemovedPiece());
            rootPanel.remove(roadImage);
        }
        if (event.getAddedPiece() != null)
        {
            Image roadImage = new Image(Resources.icons().roadSmall());
            roadImages.put(event.getAddedPiece(), roadImage);
            rootPanel.add(roadImage);
        }
    }

}
