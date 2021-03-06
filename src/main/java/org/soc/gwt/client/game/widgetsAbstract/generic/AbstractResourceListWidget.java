package org.soc.gwt.client.game.widgetsAbstract.generic;

import org.soc.common.game.PortList;
import org.soc.common.game.Resource;
import org.soc.common.game.ResourceList;
import org.soc.common.game.ResourcesChangedEvent;
import org.soc.common.views.widgetsInterface.generic.ResourceClickedEvent;
import org.soc.common.views.widgetsInterface.generic.ResourceListWidget;
import org.soc.common.views.widgetsInterface.generic.ResourceWidget;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractResourceListWidget implements ResourceListWidget
{
  protected ComplexPanel rootPanel;
  protected int maximumResources;
  protected ResourceList resources;
  protected ResourceList bankResources;
  protected PortList ports;
  protected HandlerRegistration resourcesRegistration;

  public AbstractResourceListWidget(ResourceList resources,
          ResourceList bankResources, PortList ports)
  {
    this.ports = ports;
    this.bankResources = bankResources;
    rootPanel = createRootPanel();
    rootPanel.setHeight("100px");
    setResources(resources);
    addResources(resources);
  }
  /** @return the resources */
  @Override public ResourceList getResources()
  {
    return resources;
  }
  /* (non-Javadoc)
   * 
   * @see org.soc.gwt.client.game.widgets.abstractWidgets.IResourceListWidget#setEnabled (boolean) */
  @Override public ResourceListWidget setEnabled(boolean enabled)
  {
    for (int i = 0; i < rootPanel.getWidgetCount(); i++)
    {
      ResourceWidget resourceWidget = (ResourceWidget) rootPanel
              .getWidget(i);
      resourceWidget.setEnabled(enabled);
    }
    return this;
  }
  /** @param resources the resources to set */
  @Override public ResourceListWidget setResources(ResourceList resources)
  {
    // Remove handler from old resourcelist
    if (resourcesRegistration != null)
    {
      resourcesRegistration.removeHandler();
    }
    this.resources = resources;
    // Get rid of any old widgets
    rootPanel.clear();
    // Add new widgets if any
    for (Resource resource : resources)
    {
      ResourceWidget resourceWidget = createResourceWidget(resource);
      resourceWidget.addResourceClickedHandler(this);
      rootPanel.add(resourceWidget);
    }
    // Listen to changes on the new resource list
    resourcesRegistration = resources.addResourcesChangedHandler(this);
    return this;
  }
  public ResourceListWidget setBankResources(ResourceList bankResources)
  {
    this.bankResources = bankResources;
    return this;
  }
  /* (non-Javadoc)
   * 
   * @see org.soc.common.board.resources.ResourcesChangedEventHandler#onResourcesChanged
   * (org.soc.common.board.resources.ResourcesChangedEvent) */
  @Override public void onResourcesChanged(ResourcesChangedEvent resourcesChanged)
  {
    if (resourcesChanged.getAddedResources() != null)
    {
      addResources(resourcesChanged.getAddedResources());
    }
    if (resourcesChanged.getRemovedResources() != null)
    {
      removeResources(resourcesChanged.getRemovedResources());
    }
  }
  private void addResources(ResourceList resourcesToAdd)
  {
    for (Resource resource : resourcesToAdd)
    {
      ResourceWidget resourceWidget = createResourceWidget(resource);
      resourceWidget.addResourceClickedHandler(this);
      rootPanel.add(resourceWidget);
    }
  }
  private void removeResources(ResourceList resourcesToRemove)
  {
    ResourceList resToRemove = resourcesToRemove.copy();
    for (Resource resourceToRemove : resToRemove)
    {
      for (int i = 0; i < rootPanel.getWidgetCount(); i++)
      {
        Object o = rootPanel.getWidget(i);
        ResourceWidget resourceWidget = (ResourceWidget) rootPanel
                .getWidget(i);
        if (resourceWidget.getResource().getClass() == resourceToRemove
                .getClass())
        {
          rootPanel.remove(i);
          break;
        }
      }
    }
  }
  @Override public Widget asWidget()
  {
    return rootPanel;
  }
  @Override public void onResourceClicked(ResourceClickedEvent event)
  {
    Resource removedResource = event.getResource();
    if (ports != null)
    {
      ResourceList resourcesToRemove = new ResourceList();
      for (int i = 0; i < ports.amountNeededToTrade(removedResource); i++)
      {
        resourcesToRemove.add(removedResource.copy());
      }
      resources.remove(resourcesToRemove, false);
      if (bankResources != null)
      {
        bankResources.addList(resourcesToRemove);
      }
    }
    else
    {
      resources.remove(removedResource);
      if (bankResources != null)
      {
        bankResources.add(removedResource);
      }
    }
  }
}
