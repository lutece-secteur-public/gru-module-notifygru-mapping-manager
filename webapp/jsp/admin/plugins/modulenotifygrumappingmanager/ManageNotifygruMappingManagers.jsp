<jsp:useBean id="managemodulenotifygrumappingmanagerNotifygruMappingManager" scope="session" class="fr.paris.lutece.plugins.modulenotifygrumappingmanager.web.NotifygruMappingManagerJspBean" />
<% String strContent = managemodulenotifygrumappingmanagerNotifygruMappingManager.processController ( request , response ); %>

<%@ page errorPage="../../ErrorPage.jsp" %>
<jsp:include page="../../AdminHeader.jsp" />

<%= strContent %>

<%@ include file="../../AdminFooter.jsp" %>
