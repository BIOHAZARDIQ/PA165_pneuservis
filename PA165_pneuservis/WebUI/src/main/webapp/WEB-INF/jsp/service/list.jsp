<%-- 
    Document   : list
    Created on : 18.12.2015, 0:34:28
    Author     : Filip Meszaros <436321@mail.muni.cz>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:generic title="Services">
    <jsp:attribute name="body">
        
        <table class="table">
            <caption>List of available services</caption>
            <thead>
                <tr>
                    <th>Service type</th>
                    <th>Service name</th>
                    <th>Service description</th>
                    <th>Service price</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${services}" var="service">
                    <tr>
                        <td><c:out value="${service.serviceType}"/></td>
                        <td><c:out value="${service.name}"/></td>
                        <td><c:out value="${service.description}"/></td>
                        <td>${service.price}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <a href="<c:url value="/service/new" />" type="button" class="btn btn-default">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            Create new service
        </a>
        
    </jsp:attribute>
</t:generic>
