package fr.paris.lutece.plugins.modulenotifygrumappingmanager.service;

import java.util.Collection;

import fr.paris.lutece.plugins.workflow.modules.notifygru.service.provider.AbstractProviderManager;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.provider.ProviderDescription;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.provider.ProviderManagerUtil;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.util.ReferenceList;

public class NotifygruMappingManagerService
{

    private static String PARAM_MAPING_NONE = "Aucun";

    public static ReferenceList getListProvider( )
    {
        ReferenceList refenreceList = new ReferenceList( );

        for ( AbstractProviderManagerWithMapping providerManager : SpringContextService.getBeansOfType( AbstractProviderManagerWithMapping.class ) )
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
        refenreceList.addItem( -1, PARAM_MAPING_NONE );

        String strProviderManagerId = ProviderManagerUtil.fetchProviderManagerId( strKey );
        String strProviderId = ProviderManagerUtil.fetchProviderId( strKey );
        AbstractProviderManager providerManager = ProviderManagerUtil.fetchProviderManager( strProviderManagerId );

        if ( providerManager != null && providerManager instanceof AbstractProviderManagerWithMapping )
        {
            refenreceList.addAll( ( (AbstractProviderManagerWithMapping) providerManager ).getMappingPropertiesForProvider( strProviderId ) );
        }

        return refenreceList;
    }

}
