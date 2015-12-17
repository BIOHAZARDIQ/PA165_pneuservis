<%-- 
    Document   : list
    Created on : 17.12.2015, 23:34:18
    Author     : Filip Meszaros <436321@mail.muni.cz>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:generic title="Customers">
    <jsp:attribute name="body">
        
        <a href="<c:url value="/" />">Create new Customer entry</a>
        
        <table class="table">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Street name</th>
                    <th>Street number</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Postal number</th>
                    <th>Phone number</th>
                    <th>E-mail</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${customers}" var="customer">
                    <tr>
                        <td>${customer.id}</td>
                        <td><c:out value="${customer.firstName}"/></td>
                        <td><c:out value="${customer.lastName}"/></td>
                        <td><c:out value="${customer.streetName}"/></td>
                        <td>${customer.streetNumber}</td>
                        <td>${customer.city}</td>
                        <td>${customer.state}</td>
                        <td>${customer.postalNumber}</td>
                        <td>${customer.phoneNumber}</td>
                        <td>${customer.email}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
    </jsp:attribute>
</t:generic>
