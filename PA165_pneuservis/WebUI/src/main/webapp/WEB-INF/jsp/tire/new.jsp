<%-- 
    Document   : tire/new
    Author     : Jozef Sumaj
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:generic title="New Tire">
<jsp:attribute name="body">

    <h4>Create new tire</h4>    
    <br>
    
    <form:form method="post" action="${pageContext.request.contextPath}/tire/create"
               modelAttribute="tire" cssClass="form-horizontal">
        
        <div class="form-group ${brand_error?'has-error':''}">
            <form:label path="brand" cssClass="col-sm-2 control-label">Brand</form:label>
            <div class="col-sm-4">
                <form:input path="brand" cssClass="form-control"/>
                <form:errors path="brand" cssClass="help-block"/>
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
        <div class="form-group ${width_error?'has-error':''}">
            <form:label path="width" cssClass="col-sm-2 control-label">Width</form:label>
            <div class="col-sm-2">
                <form:input path="width" cssClass="form-control"/>
                <span id="helpBlock" class="help-block">Width of the tire (mm)</span>
                <form:errors path="width" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${ratio_error?'has-error':''}">
            <form:label path="ratio" cssClass="col-sm-2 control-label">Ratio</form:label>
            <div class="col-sm-2">
                <form:input path="ratio" cssClass="form-control"/>
                <span id="helpBlock" class="help-block">Aspect ratio (%)</span>
                <form:errors path="ratio" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${rim_error?'has-error':''}">
            <form:label path="rim" cssClass="col-sm-2 control-label">Rim</form:label>
            <div class="col-sm-2">
                <form:input path="rim" cssClass="form-control"/>
                <span id="helpBlock" class="help-block">Rim diameter (inches)</span>
                <form:errors path="rim" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${price_error?'has-error':''}">
            <form:label path="price" cssClass="col-sm-2 control-label">Price</form:label>
            <div class="col-sm-2">
                <form:input path="price" cssClass="form-control"/>
                <span id="helpBlock" class="help-block">Total amount (€)</span>
                <form:errors path="price" cssClass="help-block"/>                
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-2">
                <button class="btn btn-info btn-sm" type="submit">Submit</button>
            </div>  
        </div>
    </form:form>
    
    <p>
        <span class="glyphicon glyphicon-circle-arrow-left" aria-hidden="true"></span>
        <a href="<c:url value="/tire/list" />" >Return to list of tires</a>
    </p>

</jsp:attribute>
</t:generic>
