/*
 * Copyright (c) 2002-2015, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
 
package fr.paris.lutece.plugins.modulenotifygrumappingmanager.business;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import java.util.List;

/**
 * This class provides instances management methods (create, find, ...) for NotifygruMappingManager objects
 */

public final class NotifygruMappingManagerHome
{
    // Static variable pointed at the DAO instance

    private static INotifygruMappingManagerDAO _dao = SpringContextService.getBean( "modulenotifygrumappingmanager.notifygruMappingManagerDAO" );
    private static Plugin _plugin = PluginService.getPlugin( "modulenotifygrumappingmanager" );

    /**
     * Private constructor - this class need not be instantiated
     */
    private NotifygruMappingManagerHome(  )
    {
    }

    /**
     * Create an instance of the notifygruMappingManager class
     * @param notifygruMappingManager The instance of the NotifygruMappingManager which contains the informations to store
     * @return The  instance of notifygruMappingManager which has been created with its primary key.
     */
    public static NotifygruMappingManager create( NotifygruMappingManager notifygruMappingManager )
    {
        _dao.insert( notifygruMappingManager, _plugin );

        return notifygruMappingManager;
    }

    /**
     * Update of the notifygruMappingManager which is specified in parameter
     * @param notifygruMappingManager The instance of the NotifygruMappingManager which contains the data to store
     * @return The instance of the  notifygruMappingManager which has been updated
     */
    public static NotifygruMappingManager update( NotifygruMappingManager notifygruMappingManager )
    {
        _dao.store( notifygruMappingManager, _plugin );

        return notifygruMappingManager;
    }

    /**
     * Remove the notifygruMappingManager whose identifier is specified in parameter
     * @param nKey The notifygruMappingManager Id
     */
    public static void remove( int nKey )
    {
        _dao.delete( nKey, _plugin );
    }

    ///////////////////////////////////////////////////////////////////////////
    // Finders

    /**
     * Returns an instance of a notifygruMappingManager whose identifier is specified in parameter
     * @param nKey The notifygruMappingManager primary key
     * @return an instance of NotifygruMappingManager
     */
    public static NotifygruMappingManager findByPrimaryKey( int nKey )
    {
        return _dao.load( nKey, _plugin);
    }
    
    
    
    public static NotifygruMappingManager findByPrimaryKey( String strKey )
    {
        return _dao.load( strKey, _plugin);
    }

    /**
     * Load the data of all the notifygruMappingManager objects and returns them in form of a collection
     * @return the collection which contains the data of all the notifygruMappingManager objects
     */
    public static List<NotifygruMappingManager> getNotifygruMappingManagersList( )
    {
        return _dao.selectNotifygruMappingManagersList( _plugin );
    }
    
    /**
     * Load the id of all the notifygruMappingManager objects and returns them in form of a collection
     * @return the collection which contains the id of all the notifygruMappingManager objects
     */
    public static List<Integer> getIdNotifygruMappingManagersList( )
    {
        return _dao.selectIdNotifygruMappingManagersList( _plugin );
    }
}

