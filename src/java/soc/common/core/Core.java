package soc.common.core;

import soc.common.server.entities.ServerUser;
import soc.common.server.entities.User;
import soc.common.server.randomization.ClientRandom;
import soc.common.server.randomization.Random;

import com.google.gwt.core.client.GWT;

/*
 * Shares system-wide instances of entities
 */
public class Core
{
    private User serverUser = new ServerUser();
    private static Core instance = new Core();
    private Random random;

    private Core()
    {
        if (GWT.isClient())
        {
            random = new ClientRandom();
        }
        else
        {
        }
    }

    public static Core get()
    {
        return instance;
    }

    /**
     * @return the user representing the server. Mainly used for referee
     */
    public User getServerUser()
    {
        return serverUser;
    }

    /**
     * @return a random instance
     */
    public Random getRandom()
    {
        return random;
    }
}
