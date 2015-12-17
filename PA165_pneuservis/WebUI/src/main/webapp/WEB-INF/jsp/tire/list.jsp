<%-- 
    Document   : tire/list
    Author     : Jozef Sumaj
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:generic title="Tires">
<jsp:attribute name="body">
    
    <a href="<c:url value="/" />">Create new Tire entry</a>
        
    <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>Brand</th>
            <th>Name</th>
            <th>Description</th>
            <th>Width</th>
            <th>Ratio</th>
            <th>Rim</th>
            <th>Price</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${tires}" var="tire">
            <tr>
                <td>${tire.id}</td>
                <td><c:out value="${tire.brand}"/></td>
                <td><c:out value="${tire.name}"/></td>
                <td><c:out value="${tire.description}"/></td>
                <td>${tire.width}</td>
                <td>${tire.ratio}</td>
                <td>${tire.rim}</td>
                <td>${tire.price}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</jsp:attribute>
</t:generic>