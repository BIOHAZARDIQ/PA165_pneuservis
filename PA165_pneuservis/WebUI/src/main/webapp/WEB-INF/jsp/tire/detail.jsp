<%-- 
    Document   : tire/detail
    Author     : Jozef Sumaj
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:generic title="Tire">
<jsp:attribute name="body">
    
    <p>${tire.name}</p>
    
    <p>
        <span class="glyphicon glyphicon-circle-arrow-left" aria-hidden="true"></span>
        <a href="<c:url value="/" />" >Return to home page</a>
    </p>

</jsp:attribute>
</t:generic>
