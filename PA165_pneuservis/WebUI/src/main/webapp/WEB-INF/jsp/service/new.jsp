<%-- 
    Document   : service/new
    Created on : 18.12.2015, 11:13:59
    Author     : Filip Meszaros <436321@mail.muni.cz>
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:generic title="New Service">
    <jsp:attribute name="body">
        
        <h4>Create new service</h4>    
        <br>
        
        <form:form method="post" action="${pageContext.request.contextPath}/service/create"
                   modelAttribute="service" cssClass="form-horizontal">
<!--            <div>
                <h6>Service type must be one of: TireChange, TireMaintenance, TireTelemetry, RimCleaning, WindowCleaning, BrakeRepair, LightsCleaning, InteriorCleaning</h6>
            </div>-->
            <div class="form-group ${serviceType_error?'has-error':''}">
                <form:label path="serviceType" cssClass="col-sm-2 control-label">Type</form:label>
                    <div class="col-sm-4">
                    <form:select path="serviceType">
                        <form:options items="${serviceTypes}" />
                    </form:select>
                    <form:errors path="serviceType" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${name_error?'has-error':''}">
                <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
                    <div class="col-sm-4">
                    <form:input path="name" cssClass="form-control"/>
                    <form:errors path="name" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${description_error?'has-error':''}">
                <form:label path="description" cssClass="col-sm-2 control-label">Description</form:label>
                    <div class="col-sm-6">
                    <form:textarea path="description" cssClass="form-control"/>
                    <form:errors path="description" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${price_error?'has-error':''}">
                <form:label path="price" cssClass="col-sm-2 control-label">Price</form:label>
                    <div class="col-sm-2">
                    <form:input path="price" cssClass="form-control"/>
                    <form:errors path="price" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-2">
                    <button class="btn btn-primary" type="submit">Create Service</button>
                </div>                            
            </div>
        </form:form>

        <p>
            <span class="glyphicon glyphicon-circle-arrow-left" aria-hidden="true"></span>
            <a href="<c:url value="/service/list" />" >Return to list of services</a>
        </p>
        
    </jsp:attribute>
</t:generic>
