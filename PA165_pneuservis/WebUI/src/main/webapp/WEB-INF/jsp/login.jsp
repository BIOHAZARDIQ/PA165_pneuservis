<%-- 
    Document   : login
    Author     : Jozef Sumaj
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:generic title="Pneuservis">
<jsp:attribute name="body">
    
    <div class="jumbotron">
    <h2>Please log in</h2>
    <p class="lead">In order to view customized or administrative content, please fill in your email and password</p>   
    
    <form:form method="post" action="${pageContext.request.contextPath}/login"
               modelAttribute="login" cssClass="form-horizontal form-signin">        
        
        <div class="row">
            <div class="col-sm-8 col-centered">
                <div class="form-group ${email_error?'has-error':''}">
                    <form:label path="email" cssClass="col-sm-2 col-sm-offset-2 control-label">Email</form:label>
                    <div class="col-sm-4 col-centered">
                        <form:input path="email" cssClass="form-control"  placeholder="admin@pneuservis.com"/>
                        <form:errors path="email" cssClass="help-block"/>
                    </div>
                </div>
            </div>
        </div>
            
        <div class="row">
            <div class="col-sm-8 col-centered">
                <div class="form-group ${password_error?'has-error':''}">
                    <form:label path="password" cssClass="col-sm-2 col-sm-offset-2 control-label">Password</form:label>
                    <div class="col-sm-4 col-centered">
                        <form:input type="password" placeholder="admin" path="password" cssClass="form-control"/>
                        <form:errors path="password" cssClass="help-block"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-8 col-centered">
                <div class="form-group">
                    <div class="col-sm-4 col-centered">
                        <button class="btn btn-success btn-sm" type="submit">Login</button>
                    </div>
                </div>
            </div>
        </div>
    </form:form>
    </div>

</jsp:attribute>
</t:generic>