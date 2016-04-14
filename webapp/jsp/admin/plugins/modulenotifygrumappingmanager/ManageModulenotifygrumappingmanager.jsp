<%@ page errorPage="../../ErrorPage.jsp" %>

<jsp:include page="../../AdminHeader.jsp" />

<jsp:useBean id="managemodulenotifygrumappingmanager" scope="session" class="fr.paris.lutece.plugins.modulenotifygrumappingmanager.web.ManageModulenotifygrumappingmanagerJspBean" />

<% managemodulenotifygrumappingmanager.init( request, managemodulenotifygrumappingmanager.RIGHT_MANAGEMODULENOTIFYGRUMAPPINGMANAGER ); %>
<%= managemodulenotifygrumappingmanager.getManageModulenotifygrumappingmanagerHome ( request ) %>

<%@ include file="../../AdminFooter.jsp" %>
