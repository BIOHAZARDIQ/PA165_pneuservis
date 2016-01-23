<%-- 
    Document   : list
    Created on : Dec 18, 2015, 12:12:21 PM
    Author     : Komoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:generic title="Orders - ${auth.getFirstName()}">
    <jsp:attribute name="body">        
        <table class="table">
            <thead>
                <tr>       
                    <th>Id</th>
                    <th>Total price</th>
                    <th>Date created</th>
                    <th>Date completed</th>
                    <th>Customer email</th>
                    <th>Customer name</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${orders}" varStatus="i">
                    <tr>
                         <td>${order[i.index].id}</td>
                         <td><c:out value="${orders[i.index].totalPrice}"/></td>
                         <td><c:out value="${orders[i.index].createDate}"/></td>
                         <td><c:out value="${orders[i.index].completeDate}"/></td>
                         <td><c:out value="${customers[i.index].email}"/></td>
                         <td><c:out value="${customers[i.index].firstName} ${customers[i.index].lastName}"/></td>
                     </tr>
                </c:forEach>
            </tbody>
        </table>
        
    </jsp:attribute>
</t:generic>