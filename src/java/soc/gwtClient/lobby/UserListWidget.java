package soc.gwtClient.lobby;

import soc.common.server.entities.UserList;

import com.google.gwt.user.client.ui.IsWidget;

public interface UserListWidget extends IsWidget
{
    public void setUsers(UserList users);

}