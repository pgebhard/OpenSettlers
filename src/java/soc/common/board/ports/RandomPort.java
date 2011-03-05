package soc.common.board.ports;

import soc.common.board.HexLocation;
import soc.common.board.RotationPosition;
import soc.common.internationalization.I18n;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.gwtClient.images.Resources;

/*
 * Placeholder for replacement of random ports at board preperation
 */
public class RandomPort extends AbstractPort
{
    private static final long serialVersionUID = 5964428508404257705L;
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().randomPort(), null,
                        null, null);

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "RandomPort";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().randomPortDescription();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().randomPortDescription();
        }

    };

    public RandomPort(HexLocation hexLocation, RotationPosition rotationPosition)
    {
        super(hexLocation, rotationPosition);
    }

    public RandomPort()
    {

    }

    @Override
    public Port copy()
    {
        return new RandomPort();
    }

    @Override
    public String getColor()
    {
        return "Gray";
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }

    @Override
    public boolean hasResource()
    {
        return false;
    }
}