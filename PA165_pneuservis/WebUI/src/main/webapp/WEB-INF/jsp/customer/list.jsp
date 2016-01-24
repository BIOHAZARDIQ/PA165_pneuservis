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
        
        <table class="table table-condensed table-hover">
            <caption>List of registered customers</caption>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Address</th>
                    <th>Email</th>
                    <th>Phone number</th>
                    <th>Vehicle type</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${customers}" var="customer">
                    <c:if test="${customer.getEmail() != 'admin@pneuservis.com'}">
                    <tr>
                        <td>${customer.firstName} ${customer.lastName}</td>
                        <td>
                            <address>
                                ${customer.streetNumber}, ${customer.streetName}<br>
                                ${customer.city}, ${customer.state}<br>
                                ${customer.postalNumber}<br>
                            </address>
                        </td>
                        <td><a href="mailto:#">${customer.email}</a></td>
                        <td>${customer.phoneNumber}</td>
                        <td>${customer.vehicleType}</td>
                    </tr>
                    </c:if>
                </c:forEach>
            </tbody>
        </table>
        
        <a href="<c:url value="/customer/new" />" type="button" class="btn btn-default">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            Register new customer
        </a>
        
    </jsp:attribute>
</t:generic>
