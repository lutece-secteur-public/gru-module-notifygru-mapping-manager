package fr.paris.lutece.plugins.modulenotifygrumappingmanager.service;



import fr.paris.lutece.plugins.workflow.modules.notifygru.service.AbstractServiceProvider;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.ServiceConfigTaskForm;

import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.util.ReferenceList;

public class NotifygruMappingManagerService {

	
	 public static ReferenceList getListProvider()
	    {
	        ReferenceList refenreceList = new ReferenceList(  );
	        refenreceList.addItem("", "");

	        for ( AbstractServiceProvider provider : SpringContextService.getBeansOfType( AbstractServiceProvider.class ) )
	        {
	            if ( provider.isManagerProvider(  ) )
	            {
	            	 provider.updateListProvider(  );
	            	 
	            	
	                 refenreceList.addAll( provider.buildReferenteListProvider(  ) );
	            }
	            
	        }

	        return refenreceList;
	    }
	 
	
	 public static ReferenceList getListEntryOfProvider(String strKey)
	 {
		 
		 ReferenceList refenreceList = new ReferenceList(  );
		 
		 if(ServiceConfigTaskForm.isBeanExiste( strKey ))
		 {
			 AbstractServiceProvider provider = ServiceConfigTaskForm.getCostumizeBean( strKey  );
			 refenreceList=provider.getReferenteListEntityProvider();
		 }
		 
		 
		 
		 return  refenreceList ;
	 }
	 
}
