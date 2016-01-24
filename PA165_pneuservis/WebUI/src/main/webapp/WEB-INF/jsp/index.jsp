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

    <form:form method="post" action="${pageContext.request.contextPath}/order/submit"
               modelAttribute="orderForm" cssClass="form-horizontal">
            
        <div class="panel panel-default">
            
            <div class="panel-heading">
              Select set of tires for your vehicle
            </div>
            <div class="row">
                <div class="col-sm-6">
                    <div class="panel-body">
                        <div class="form-group">
                            <form:label path="tireIds" cssClass="col-sm-2 col-sm-offset-2 control-label">Tires</form:label>
                            <div class="col-sm-8">
                                <form:select id="tires" path="tireIds" cssClass="form-control" varStatus="i">
                                    <form:option data-price="0" value="-" label="-- Please select --" selected="selected"/>
                                    <c:forEach var="tire" items="${tires}">
                                        <form:option value="${tire.id}" 
                                                     data-price="${tire.price}" 
                                                     data-description="${tire.description}"
                                                     data-name="${tire.name}"
                                                     data-brand="${tire.brand}">
                                            <c:out value="${tire.name}"/>
                                        </form:option>
                                    </c:forEach>
                                </form:select>
                                <form:errors path="tireIds" cssClass="help-block"/>
                            </div>
                          </div>
                    </div>
                </div>
                <div class="col-sm-6" style="padding:15px">
                    <div class="media">

                        <div class="media-body">
                            <h4 class="media-heading" id="tireName"></h4>
                            <p id="tireDescription" style="padding-right:15px"></p>
                            <a id="tireLink" href="<c:url value="/tire/" />" type="button" class="btn btn-default" style="display: none;">
                                Details
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <div id="tirePrice" class="panel-footer"></div>
        </div>
                    
        <div class="panel panel-default">
            <div class="panel-heading">
              Select additional services
            </div>
            <div class="panel-body">
                <div class="form-group">
                    <form:label path="serviceIds" cssClass="col-sm-2 control-label">Services</form:label>
                    <div class="col-sm-4">
                        <form:select path="serviceIds" multiple="true" cssClass="form-control">
                            <form:option value="-" label="-- None --"/>
                            <form:options items="${services}" itemValue="id" itemLabel="name"/>
                        </form:select>
                        <form:errors path="serviceIds" cssClass="help-block"/>
                    </div>
                </div>
            </div>
            <div class="panel-footer">Total price: </div>
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

<script>
$("#tires").change(function(){
    var $selected = ( $(this).find(":selected") );
    var $price = $selected.attr('data-price');
    var $description = $selected.attr('data-description');
    var $name = $selected.attr('data-name');
    var $brand = $selected.attr('data-brand');
    var $id = $selected.val();
    if($price > 0) {
	$("#tirePrice").text("Tire price: " + $price);
        $("#tireDescription").text($description);
        $("#tireName").text($brand + " " + $name);
        $("#tireLink").attr('href', 'tire/' + parseInt($id));
        $('#tireLink').show();
    }
    else {
        $("#tirePrice").text("");
        $("#tireDescription").text("");
        $("#tireName").text("");
        $('#tireLink').hide();
    }
});
</script>