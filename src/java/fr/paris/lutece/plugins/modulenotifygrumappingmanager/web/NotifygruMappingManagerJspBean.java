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

 
package fr.paris.lutece.plugins.modulenotifygrumappingmanager.web;

import fr.paris.lutece.plugins.modulenotifygrumappingmanager.business.NotifygruMappingManager;
import fr.paris.lutece.plugins.modulenotifygrumappingmanager.business.NotifygruMappingManagerHome;
import fr.paris.lutece.plugins.modulenotifygrumappingmanager.service.NotifygruMappingManagerService;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.util.mvc.admin.annotations.Controller;
import fr.paris.lutece.portal.util.mvc.commons.annotations.Action;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;
import fr.paris.lutece.util.ReferenceList;
import fr.paris.lutece.util.bean.BeanUtil;
import fr.paris.lutece.util.url.UrlItem;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


/**
 * This class provides the user interface to manage NotifygruMappingManager features ( manage, create, modify, remove )
 */
@Controller( controllerJsp = "ManageNotifygruMappingManagers.jsp", controllerPath = "jsp/admin/plugins/modulenotifygrumappingmanager/", right = "MODULENOTIFYGRUMAPPINGMANAGER_MANAGEMENT" )
public class NotifygruMappingManagerJspBean extends ManageModulenotifygrumappingmanagerJspBean
{

    ////////////////////////////////////////////////////////////////////////////
    // Constants

    // templates
    private static final String TEMPLATE_MANAGE_NOTIFYGRUMAPPINGMANAGERS = "/admin/plugins/modulenotifygrumappingmanager/manage_notifygrumappingmanagers.html";
    private static final String TEMPLATE_CREATE_NOTIFYGRUMAPPINGMANAGER = "/admin/plugins/modulenotifygrumappingmanager/create_notifygrumappingmanager.html";
    private static final String TEMPLATE_MODIFY_NOTIFYGRUMAPPINGMANAGER = "/admin/plugins/modulenotifygrumappingmanager/modify_notifygrumappingmanager.html";


    // Parameters
    private static final String PARAMETER_ID_NOTIFYGRUMAPPINGMANAGER = "id";
    private static final String PARAMS_REQUEST_BEAN_KEY= "beankey";
    private static final String PARAMS_REQUEST_EMAIL = "email";
    private static final String PARAMS_REQUEST_MOBILE_PHONE_NUMBER = "mobilephonenumber";
    private static final String PARAMS_REQUEST_FIXED_PHONE_NUMBER = "fixedphonenumber";

    // Properties for page titles
    private static final String PROPERTY_PAGE_TITLE_MANAGE_NOTIFYGRUMAPPINGMANAGERS = "modulenotifygrumappingmanager.manage_notifygrumappingmanagers.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_MODIFY_NOTIFYGRUMAPPINGMANAGER = "modulenotifygrumappingmanager.modify_notifygrumappingmanager.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_CREATE_NOTIFYGRUMAPPINGMANAGER = "modulenotifygrumappingmanager.create_notifygrumappingmanager.pageTitle";

    // Markers
    private static final String MARK_NOTIFYGRUMAPPINGMANAGER_LIST = "notifygrumappingmanager_list";
    private static final String MARK_NOTIFYGRUMAPPINGMANAGER = "notifygrumappingmanager";
    private static final String MARK_NOTIFYGRU_FORM_LIST_PROVIDER= "list_provider";
    private static final String MARK_NOTIFYGRU_FORM_LIST_POSITION= "list_position";

    private static final String JSP_MANAGE_NOTIFYGRUMAPPINGMANAGERS = "jsp/admin/plugins/modulenotifygrumappingmanager/ManageNotifygruMappingManagers.jsp";

    // Properties
    private static final String MESSAGE_CONFIRM_REMOVE_NOTIFYGRUMAPPINGMANAGER = "modulenotifygrumappingmanager.message.confirmRemoveNotifygruMappingManager";
    private static final String PROPERTY_DEFAULT_LIST_NOTIFYGRUMAPPINGMANAGER_PER_PAGE = "modulenotifygrumappingmanager.listNotifygruMappingManagers.itemsPerPage";
 
    private static final String VALIDATION_ATTRIBUTES_PREFIX = "modulenotifygrumappingmanager.model.entity.notifygrumappingmanager.attribute.";

    // Views
    private static final String VIEW_MANAGE_NOTIFYGRUMAPPINGMANAGERS = "manageNotifygruMappingManagers";
    private static final String VIEW_CREATE_NOTIFYGRUMAPPINGMANAGER = "createNotifygruMappingManager";
    private static final String VIEW_MODIFY_NOTIFYGRUMAPPINGMANAGER = "modifyNotifygruMappingManager";

    // Actions
    private static final String ACTION_CREATE_NOTIFYGRUMAPPINGMANAGER = "createNotifygruMappingManager";
    private static final String ACTION_MODIFY_NOTIFYGRUMAPPINGMANAGER = "modifyNotifygruMappingManager";
    private static final String ACTION_REMOVE_NOTIFYGRUMAPPINGMANAGER = "removeNotifygruMappingManager";
    private static final String ACTION_CONFIRM_REMOVE_NOTIFYGRUMAPPINGMANAGER = "confirmRemoveNotifygruMappingManager";

    // Infos
    private static final String INFO_NOTIFYGRUMAPPINGMANAGER_CREATED = "modulenotifygrumappingmanager.info.notifygrumappingmanager.created";
    private static final String INFO_NOTIFYGRUMAPPINGMANAGER_UPDATED = "modulenotifygrumappingmanager.info.notifygrumappingmanager.updated";
    private static final String INFO_NOTIFYGRUMAPPINGMANAGER_REMOVED = "modulenotifygrumappingmanager.info.notifygrumappingmanager.removed";
    
    // Session variable to store working values
    private NotifygruMappingManager _notifygrumappingmanager;
    
    
    /**
     * Build the Manage View
     * @param request The HTTP request
     * @return The page
     */
    @View( value = VIEW_MANAGE_NOTIFYGRUMAPPINGMANAGERS, defaultView = true )
    public String getManageNotifygruMappingManagers( HttpServletRequest request )
    {
        _notifygrumappingmanager = null;
        List<NotifygruMappingManager> listNotifygruMappingManagers = NotifygruMappingManagerHome.getNotifygruMappingManagersList(  );
        Map<String, Object> model = getPaginatedListModel( request, MARK_NOTIFYGRUMAPPINGMANAGER_LIST, listNotifygruMappingManagers, JSP_MANAGE_NOTIFYGRUMAPPINGMANAGERS );

        return getPage( PROPERTY_PAGE_TITLE_MANAGE_NOTIFYGRUMAPPINGMANAGERS, TEMPLATE_MANAGE_NOTIFYGRUMAPPINGMANAGERS, model );
    }

    /**
     * Returns the form to create a notifygrumappingmanager
     *
     * @param request The Http request
     * @return the html code of the notifygrumappingmanager form
     */
    @View( VIEW_CREATE_NOTIFYGRUMAPPINGMANAGER )
    public String getCreateNotifygruMappingManager( HttpServletRequest request )
    {
        _notifygrumappingmanager = ( _notifygrumappingmanager != null ) ? _notifygrumappingmanager : new NotifygruMappingManager(  );

        Map<String, Object> model = getModel(  );
        model.put( MARK_NOTIFYGRUMAPPINGMANAGER, _notifygrumappingmanager );
        model.put( MARK_NOTIFYGRU_FORM_LIST_PROVIDER, NotifygruMappingManagerService.getListProvider() );
        
        ReferenceList refenreceList = new ReferenceList(  );
        refenreceList.addItem(1, "telephone fixe");
        refenreceList.addItem(2, "telephone mobile");
        refenreceList.addItem(3, "email");
        refenreceList.addItem(4, "adresse");
        
        model.put( MARK_NOTIFYGRU_FORM_LIST_POSITION , refenreceList );

        return getPage( PROPERTY_PAGE_TITLE_CREATE_NOTIFYGRUMAPPINGMANAGER, TEMPLATE_CREATE_NOTIFYGRUMAPPINGMANAGER, model );
    }

    /**
     * Process the data capture form of a new notifygrumappingmanager
     *
     * @param request The Http Request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_CREATE_NOTIFYGRUMAPPINGMANAGER )
    public String doCreateNotifygruMappingManager( HttpServletRequest request )
    {
        populate(request );

        // Check constraints
        if ( !validateBean( _notifygrumappingmanager, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirectView( request, VIEW_CREATE_NOTIFYGRUMAPPINGMANAGER );
        }

        NotifygruMappingManagerHome.create( _notifygrumappingmanager );
        addInfo( INFO_NOTIFYGRUMAPPINGMANAGER_CREATED, getLocale(  ) );

        return redirectView( request, VIEW_MANAGE_NOTIFYGRUMAPPINGMANAGERS );
    }
    
    protected void populate(HttpServletRequest request )
    {
    	_notifygrumappingmanager.setBeanKey( request.getParameter( PARAMS_REQUEST_BEAN_KEY ) );    
    	_notifygrumappingmanager.setEmail( Integer.parseInt( request.getParameter( PARAMS_REQUEST_EMAIL ) ));
    	_notifygrumappingmanager.setFixedPhoneNumber( Integer.parseInt( request.getParameter( PARAMS_REQUEST_MOBILE_PHONE_NUMBER ) ));
    	_notifygrumappingmanager.setMobilePhoneNumber( Integer.parseInt( request.getParameter( PARAMS_REQUEST_FIXED_PHONE_NUMBER ) ));
      
    }

    /**
     * Manages the removal form of a notifygrumappingmanager whose identifier is in the http
     * request
     *
     * @param request The Http request
     * @return the html code to confirm
     */
    @Action( ACTION_CONFIRM_REMOVE_NOTIFYGRUMAPPINGMANAGER )
    public String getConfirmRemoveNotifygruMappingManager( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_NOTIFYGRUMAPPINGMANAGER ) );
        UrlItem url = new UrlItem( getActionUrl( ACTION_REMOVE_NOTIFYGRUMAPPINGMANAGER ) );
        url.addParameter( PARAMETER_ID_NOTIFYGRUMAPPINGMANAGER, nId );

        String strMessageUrl = AdminMessageService.getMessageUrl( request, MESSAGE_CONFIRM_REMOVE_NOTIFYGRUMAPPINGMANAGER,
                url.getUrl(  ), AdminMessage.TYPE_CONFIRMATION );

        return redirect( request, strMessageUrl );
    }

    /**
     * Handles the removal form of a notifygrumappingmanager
     *
     * @param request The Http request
     * @return the jsp URL to display the form to manage notifygrumappingmanagers
     */
    @Action( ACTION_REMOVE_NOTIFYGRUMAPPINGMANAGER )
    public String doRemoveNotifygruMappingManager( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_NOTIFYGRUMAPPINGMANAGER ) );
        NotifygruMappingManagerHome.remove( nId );
        addInfo( INFO_NOTIFYGRUMAPPINGMANAGER_REMOVED, getLocale(  ) );

        return redirectView( request, VIEW_MANAGE_NOTIFYGRUMAPPINGMANAGERS );
    }

    /**
     * Returns the form to update info about a notifygrumappingmanager
     *
     * @param request The Http request
     * @return The HTML form to update info
     */
    @View( VIEW_MODIFY_NOTIFYGRUMAPPINGMANAGER )
    public String getModifyNotifygruMappingManager( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_NOTIFYGRUMAPPINGMANAGER ) );

        if ( _notifygrumappingmanager == null || ( _notifygrumappingmanager.getId(  ) != nId ))
        {
            _notifygrumappingmanager = NotifygruMappingManagerHome.findByPrimaryKey( nId );
        }

        Map<String, Object> model = getModel(  );
        model.put( MARK_NOTIFYGRUMAPPINGMANAGER, _notifygrumappingmanager );

        return getPage( PROPERTY_PAGE_TITLE_MODIFY_NOTIFYGRUMAPPINGMANAGER, TEMPLATE_MODIFY_NOTIFYGRUMAPPINGMANAGER, model );
    }

    /**
     * Process the change form of a notifygrumappingmanager
     *
     * @param request The Http request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_MODIFY_NOTIFYGRUMAPPINGMANAGER )
    public String doModifyNotifygruMappingManager( HttpServletRequest request )
    {
        populate( _notifygrumappingmanager, request );

        // Check constraints
        if ( !validateBean( _notifygrumappingmanager, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirect( request, VIEW_MODIFY_NOTIFYGRUMAPPINGMANAGER, PARAMETER_ID_NOTIFYGRUMAPPINGMANAGER, _notifygrumappingmanager.getId( ) );
        }

        NotifygruMappingManagerHome.update( _notifygrumappingmanager );
        addInfo( INFO_NOTIFYGRUMAPPINGMANAGER_UPDATED, getLocale(  ) );

        return redirectView( request, VIEW_MANAGE_NOTIFYGRUMAPPINGMANAGERS );
    }
}
