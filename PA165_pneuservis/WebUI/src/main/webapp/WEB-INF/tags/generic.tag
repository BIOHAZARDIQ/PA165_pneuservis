<%-- 
    Document   : generic
    Author     : Jozef Sumaj
--%>
<%@ tag description="Generic template used across all pages" pageEncoding="UTF-8"%>
<%@ attribute name="title" required="false" %>
<%@ attribute name="head" fragment="true" %>
<%@ attribute name="body" fragment="true" required="true" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title><c:out value="${title}"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"  crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/css/pneuservis.css"/>">
</head>
<body>
    <nav class="navbar navbar-default">
        <div class="container">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<c:url value="/" />">Pneuservis</a>
          </div>

          <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <c:choose>
                    <c:when test="${auth != null}">
                        <c:choose>
                            <c:when test="${auth.isAdmin}">
                                <li class="${fn:containsIgnoreCase(pageContext.request.requestURI, 'order')?'active':''}">
                                    <a href="<c:url value="/order/list" />">Orders</a>
                                </li>
                                <li class="${fn:containsIgnoreCase(pageContext.request.requestURI, 'tire')?'active':''}">
                                    <a href="<c:url value="/tire/list" />">Tires</a>
                                </li>
                                <li class="${fn:containsIgnoreCase(pageContext.request.requestURI, 'service')?'active':''}">
                                    <a href="<c:url value="/service/list" />">Services</a>
                                </li>
                                <li class="${fn:containsIgnoreCase(pageContext.request.requestURI, 'customer')?'active':''}">
                                    <a href="<c:url value="/customer/list" />">Customers</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="<c:url value="/order/list" />">My orders</a></li>
                            </c:otherwise>
                        </c:choose> 
                    </c:when>
                    <c:otherwise>
                        <!-- guest -->
                    </c:otherwise>
                </c:choose>

            </ul>
            
            <c:choose>
                <c:when test="${!fn:containsIgnoreCase(pageContext.request.requestURI, 'login')}">
                    <!-- login page is NOT displayed, show user info -->
                    <c:choose>
                        <c:when test="${auth != null}">
                        <!-- logged in -->
                            <p class="navbar-right" style="margin-top:10px">
                                <span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;
                                <a href="<c:url value="/order/list" />" class="navbar-link">${auth.getFirstName()} ${auth.getLastName()}</a>&nbsp;&nbsp;
                                <a href="<c:url value="/logout" />" title="Log out" type="button" class="btn btn-default btn-sm">
                                    Log out &nbsp;&nbsp;<span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
                                </a>
                            </p>
                        </c:when>
                        <c:otherwise>
                        <!-- logged out -->
                            <p class="navbar-right" style="margin-top:9px">
                                <a href="<c:url value="/login" />" title="Log in" type="button" class="btn btn-default btn-sm">
                                    Log in &nbsp;&nbsp;<span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>
                                </a>
                            </p>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                <!-- login page is displayed, hide user info -->
                </c:otherwise> 
            </c:choose>
                
          </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
        
    <div class="container">
        <c:if test="${not empty alert_success}">
            <div class="alert alert-success alert-dismissible fade in" role="alert"> 
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <p><c:out value="${alert_success}"/></p>    
            </div>
        </c:if>
        <c:if test="${not empty alert_danger}">
            <div class="alert alert-danger alert-dismissible fade in" role="alert"> 
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <p><c:out value="${alert_danger}"/></p>    
            </div>
        </c:if>
        
        <!-- page body -->
        <jsp:invoke fragment="body"/>

        <hr />
        
        <!-- footer -->
        <footer class="footer">
            <p>PA165 Pneuservis - Masaryk University 2015</p>
        </footer>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>