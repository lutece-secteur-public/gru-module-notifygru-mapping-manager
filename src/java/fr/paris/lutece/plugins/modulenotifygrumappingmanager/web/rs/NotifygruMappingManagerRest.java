 /*
 * Copyright (c) 2002-2012, Mairie de Paris
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
package fr.paris.lutece.plugins.modulenotifygrumappingmanager.web.rs;

import fr.paris.lutece.plugins.modulenotifygrumappingmanager.business.NotifygruMappingManager;
import fr.paris.lutece.plugins.modulenotifygrumappingmanager.business.NotifygruMappingManagerDAO;
import fr.paris.lutece.plugins.modulenotifygrumappingmanager.business.NotifygruMappingManagerHome;
import fr.paris.lutece.plugins.modulenotifygrumappingmanager.service.NotifygruMappingManagerService;
import fr.paris.lutece.plugins.rest.service.RestConstants;
import fr.paris.lutece.plugins.rest.util.json.JSONUtil;
import fr.paris.lutece.plugins.rest.util.xml.XMLUtil;
import fr.paris.lutece.util.ReferenceItem;
import fr.paris.lutece.util.ReferenceList;
import fr.paris.lutece.util.xml.XmlUtil;
import fr.paris.lutece.portal.service.util.AppLogService;

import java.io.IOException;

import net.sf.json.JSONObject;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Page resource
 */
 
@Path( RestConstants.BASE_PATH + Constants.PLUGIN_PATH + Constants.NOTIFYGRUMAPPINGMANAGER_PATH )
public class NotifygruMappingManagerRest
{
    private static final String KEY_NOTIFYGRUMAPPINGMANAGERS = "notifygrumappingmanagers";
    private static final String KEY_NOTIFYGRUMAPPINGMANAGER = "notifygrumappingmanager";
    
    private static final String KEY_ID = "id";
    private static final String KEY_BEANKEY = "beankey";
    private static final String KEY_MOBILEPHONENUMBER = "mobilephonenumber";
    private static final String KEY_FIXEDPHONENUMBER = "fixedphonenumber";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_FORM = "form";
    private static final String KEY_FORM_VALUE = "formValue";

    
    @POST
    public Response createNotifygruMappingManager(
    @FormParam( Constants.KEY_BEAN ) String strKey,   
    @HeaderParam(HttpHeaders.ACCEPT) String accept, @QueryParam( Constants.FORMAT_QUERY ) String format) throws IOException
    {
    
        String entity="{}";
        String mediaType = MediaType.APPLICATION_JSON;
        
        if ( (accept != null && accept.contains(MediaType.APPLICATION_JSON)) || (format != null && format.equals(Constants.MEDIA_TYPE_JSON)) )
        {
            entity = getlistPositionOfFormJSON( strKey );
          
        }
      
        return Response
            .ok(entity, mediaType)
            .build();
    }
    
    
    public String getlistPositionOfFormJSON( String strKey )
    {
        JSONObject json = new JSONObject(  );
        String strJson = "";

        try
        {
            ReferenceList listPosition =  NotifygruMappingManagerService.getListEntryOfProvider(strKey);
        	NotifygruMappingManager mappingConfig = NotifygruMappingManagerHome.findByPrimaryKey(strKey);
           

            if ( listPosition != null )
            {
            	addListPositionJson( json, listPosition );             
            }
            
            if(mappingConfig!=null)
            {
            
            	addNotifygruMappingManagerJson(json, mappingConfig);
            }
            
            
            strJson = json.toString( );
        }        
        catch ( Exception e )
        {
            strJson = JSONUtil.formatError( "NotifygruMappingManager not found", 1 );
        }

        return strJson;
    }
    
    private void addNotifygruMappingManagerJson( JSONObject json, NotifygruMappingManager notifygrumappingmanager )
    {
        JSONObject jsonNotifygruMappingManager = new JSONObject(  );
        jsonNotifygruMappingManager.accumulate( KEY_ID , notifygrumappingmanager.getId( ) );
        jsonNotifygruMappingManager.accumulate( KEY_BEANKEY, notifygrumappingmanager.getBeanKey( ) );
        jsonNotifygruMappingManager.accumulate( KEY_MOBILEPHONENUMBER, notifygrumappingmanager.getMobilePhoneNumber( ) );
        jsonNotifygruMappingManager.accumulate( KEY_FIXEDPHONENUMBER, notifygrumappingmanager.getFixedPhoneNumber( ) );
        jsonNotifygruMappingManager.accumulate( KEY_EMAIL, notifygrumappingmanager.getEmail( ) );
        json.accumulate( KEY_FORM_VALUE, jsonNotifygruMappingManager );
    }
    
    
    private void addListPositionJson( JSONObject json, ReferenceList listPosition )
    {    	
    	  JSONObject jsonData = new JSONObject(  );    	  
    	for (ReferenceItem referenceItem : listPosition) {   		 
    		jsonData.accumulate( referenceItem.getCode() , referenceItem.getName() );   	      
		}    	   
    	
    	json.accumulate(KEY_FORM, jsonData);
    }
    
  
 




}