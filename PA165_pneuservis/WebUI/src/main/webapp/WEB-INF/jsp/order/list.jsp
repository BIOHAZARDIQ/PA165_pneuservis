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
            <caption>List of active orders</caption>
            <thead>
                <tr>       
                    <th>ID</th>
                    <th>Items</th>
                    <th>Created At</th>
                    <th>Customer</th>
                    <th>Cost</th>
                    <th>Status</th>
                    <c:if test="${auth.isAdmin}">
                        <th>Actions</th>
                    </c:if>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${pendingOrders}" var="order">
                    <tr>
                        <td><c:out value="${order.id}"/></td>
                        <td>
                            <c:forEach items="${order.orderItems}" var="orderItem">
                                ${orderItem.item.name}<br>
                            </c:forEach>
                        </td>
                        <td><c:out value="${order.createDate}"/></td>
                        <td>
                            <address>
                                ${order.customer.firstName} ${order.customer.lastName} - ${order.customer.phoneNumber}<br>
                                <a href="mailto:#">${order.customer.email}</a><br>
                                ${order.customer.streetNumber}, ${order.customer.streetName}<br>
                                ${order.customer.city}, ${order.customer.state}<br>
                                ${order.customer.postalNumber}<br>
                            </address>
                        </td>
                        <td><strong><c:out value="${order.totalPrice}"/></strong></td>
                        <c:choose>
                            <c:when test="${order.completeDate != null}">
                                <td>Completed</td>
                            </c:when>
                            <c:otherwise>
                                <td>Pending</td>
                            </c:otherwise>
                        </c:choose>
                        <c:if test="${auth.isAdmin}">
                            <th>
                                <a href="<c:url value="/order/complete/${order.id}" />" type="button" title="Complete this order"
                                   class="btn btn-success btn-xs">
                                    <span class="glyphicon glyphicon glyphicon-ok" aria-hidden="true"></span>
                                </a>
                                <a href="<c:url value="/order/cancel/${order.id}" />" type="button" title="Cancel this order"
                                   class="btn btn-default btn-xs">
                                    <span class="glyphicon glyphicon glyphicon-remove" aria-hidden="true"></span>
                                </a>
                            </th>
                        </c:if>
                     </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <table class="table">
            <caption>List of previous orders</caption>
            <thead>
                <tr>       
                    <th>ID</th>
                    <th>Items</th>
                    <th>Created At</th>
                    <th>Customer</th>
                    <th>Cost</th>
                    <th>Status</th>
                    <c:if test="${auth.isAdmin}">
                        <th>Actions</th>
                    </c:if>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${previousOrders}" var="order">
                    <tr>
                        <td><c:out value="${order.id}"/></td>
                        <td>
                            <c:forEach items="${order.orderItems}" var="orderItem">
                                ${orderItem.item.name}<br>
                            </c:forEach>
                        </td>
                        <td><c:out value="${order.createDate}"/></td>
                        <td>
                            <address>
                                ${order.customer.firstName} ${order.customer.lastName} - ${order.customer.phoneNumber}<br>
                                <a href="mailto:#">${order.customer.email}</a><br>
                                ${order.customer.streetNumber}, ${order.customer.streetName}<br>
                                ${order.customer.city}, ${order.customer.state}<br>
                                ${order.customer.postalNumber}<br>
                            </address>
                        </td>
                        <td><strong><c:out value="${order.totalPrice}"/></strong></td>
                        <c:choose>
                            <c:when test="${order.completeDate != null}">
                                <td>Completed</td>
                            </c:when>
                            <c:otherwise>
                                <td>Pending</td>
                            </c:otherwise>
                        </c:choose>
                        <c:if test="${auth.isAdmin}">
                            <th>
                                <a href="<c:url value="/order/delete/${order.id}" />" type="button" title="Delete this order"
                                   class="btn btn-default btn-xs">
                                    <span class="glyphicon glyphicon glyphicon-trash" aria-hidden="true"></span>
                                </a>
                            </th>
                        </c:if>
                     </tr>
                </c:forEach>
            </tbody>
        </table>
        
    </jsp:attribute>
</t:generic>