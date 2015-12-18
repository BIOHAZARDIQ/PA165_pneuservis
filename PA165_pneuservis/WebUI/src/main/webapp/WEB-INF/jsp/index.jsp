<%-- 
    Document   : index
    Author     : Jozef Sumaj
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:generic title="Pneuservis">
<jsp:attribute name="body">

        <div class="well well-sm">
            Use admin/admin or user/user for logging in section with different roles to view existing content or ...
        </div>

    <form:form method="post" action="${pageContext.request.contextPath}/order/submit"
               modelAttribute="orderForm" cssClass="form-horizontal">
            
        <div class="well well-sm">
            1. Choose a set of tires for your vehicle
        </div>
        
        <div class="form-group">
            <form:label path="tireIds" cssClass="col-sm-2 control-label">Tires</form:label>
            <div class="col-sm-4">
                <form:select path="tireIds" multiple="true" cssClass="form-control">
                    <form:options items="${tires}" itemValue="id" itemLabel="name"/>
                </form:select>
                <form:errors path="tireIds" cssClass="help-block"/>
            </div>
        </div>

        <div class="well well-sm">
            2. Select additional services
        </div>
            
        <div class="form-group">
            <form:label path="serviceIds" cssClass="col-sm-2 control-label">Services</form:label>
            <div class="col-sm-4">
                <form:select path="serviceIds" multiple="true" cssClass="form-control">
                    <form:options items="${services}" itemValue="id" itemLabel="name"/>
                </form:select>
                <form:errors path="serviceIds" cssClass="help-block"/>
            </div>
        </div>
            
        <div class="form-group">
            <form:label path="email" cssClass="col-sm-2 control-label">E-mail</form:label>
            <div class="col-sm-4">
                <form:input path="email" cssClass="form-control"/>
                <form:errors path="email" cssClass="help-block"/>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-2">
                <button class="btn btn-success btn-sm" type="submit">Submit order</button>
            </div>
        </div>
            
    </form:form>
    
</jsp:attribute>
</t:generic>
