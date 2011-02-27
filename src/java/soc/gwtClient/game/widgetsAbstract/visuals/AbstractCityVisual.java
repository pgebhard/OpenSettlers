package soc.gwtClient.game.widgetsAbstract.visuals;

import soc.common.board.pieces.City;
import soc.common.views.widgetsInterface.visuals.CityVisual;

public class AbstractCityVisual extends AbstractPieceVisual implements
        CityVisual
{
    protected City city;

    public AbstractCityVisual(City city)
    {
        super();
        this.city = city;
    }

    @Override
    public City getCity()
    {
        return city;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.gwtClient.game.widgetsAbstract.visuals.AbstractPieceVisual#getCityVisual
     * ()
     */
    @Override
    public CityVisual getCityVisual()
    {
        return this;
    }

}
