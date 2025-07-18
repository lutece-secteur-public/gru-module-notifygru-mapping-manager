/*
 * Copyright (c) 2002-2025, City of Paris
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
package fr.paris.lutece.plugins.modulenotifygrumappingmanager.service;

import fr.paris.lutece.plugins.workflow.service.provider.ProviderManagerUtil;
import fr.paris.lutece.plugins.workflowcore.service.provider.IProviderManager;
import fr.paris.lutece.plugins.workflowcore.service.provider.ProviderDescription;
import java.util.Collection;


import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.util.ReferenceList;
import jakarta.enterprise.inject.spi.CDI;

public class NotifygruMappingManagerService
{
    public static final int MAPPING_POSITION_NONE = -1;

    private static String MESSAGE_MAPPING_NONE = "modulenotifygrumappingmanager.mapping.none";

    public static ReferenceList getListProvider( )
    {
        ReferenceList refenreceList = new ReferenceList( );

        for ( IProviderManagerWithMapping providerManager : CDI.current( ).select( IProviderManagerWithMapping.class ).stream( ).toList( ) )
        {
            Collection<ProviderDescription> collectionProviderDescriptions = providerManager.getAllProviderDescriptions( );

            for ( ProviderDescription providerDescription : collectionProviderDescriptions )
            {
                refenreceList.addItem( ProviderManagerUtil.buildCompleteProviderId( providerManager.getId( ), providerDescription.getId( ) ),
                        providerDescription.getLabel( ) );
            }
        }

        return refenreceList;
    }

    public static ReferenceList getMappingPropertiesOfProvider( String strKey )
    {

        ReferenceList refenreceList = new ReferenceList( );
        refenreceList.addItem( MAPPING_POSITION_NONE, I18nService.getLocalizedString( MESSAGE_MAPPING_NONE, I18nService.getDefaultLocale( ) ) );

        String strProviderManagerId = ProviderManagerUtil.fetchProviderManagerId( strKey );
        String strProviderId = ProviderManagerUtil.fetchProviderId( strKey );
        IProviderManager providerManager = ProviderManagerUtil.retrieveProviderManager( strProviderManagerId );

        if ( providerManager instanceof IProviderManagerWithMapping providerManagerWithMapping )
        {
            refenreceList.addAll( providerManagerWithMapping.getMappingPropertiesForProvider( strProviderId ) );
        }

        return refenreceList;
    }

}
