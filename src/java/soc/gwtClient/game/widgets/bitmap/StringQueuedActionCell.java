package soc.gwtClient.game.widgets.bitmap;

import soc.common.game.logs.QueuedAction;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;

public class StringQueuedActionCell extends AbstractCell<QueuedAction>
{

    @Override
    public void render(QueuedAction value, Object key, SafeHtmlBuilder sb)
    {
        SafeHtml safeValue = SafeHtmlUtils.fromString(value.getAction()
                .getToDoMessage());
        sb.append(safeValue);
    }

}
