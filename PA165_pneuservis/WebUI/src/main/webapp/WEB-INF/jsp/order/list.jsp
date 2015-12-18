<%-- 
    Document   : list
    Created on : Dec 18, 2015, 12:12:21 PM
    Author     : Komoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:generic title="Orders">
    <jsp:attribute name="body">        
        <table class="table">
            <thead>
                <tr>       
                    <th>Id</th>
                    <th>Total price</th>
                    <th>Date created</th>
                    <th>Date completed</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${orders}" var="order">
                    <tr>
                        <td>${order.id}</td>
                        <td><c:out value="${order.totalPrice}"/></td>
                        <td><c:out value="${order.createDate}"/></td>
                        <td><c:out value="${order.completeDate}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
    </jsp:attribute>
</t:generic>