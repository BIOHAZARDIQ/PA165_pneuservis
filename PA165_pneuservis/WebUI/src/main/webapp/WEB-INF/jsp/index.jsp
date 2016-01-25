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
               modelAttribute="orderForm" cssClass="form-horizontal" id="order">
            
        <div class="panel panel-default">
            
            <div class="panel-heading">
              Select set of tires for your vehicle
            </div>
            <div class="row">
                <div class="col-sm-6">
                    <div class="panel-body">
                        <div class="form-group">
                            <form:label path="tireIds" cssClass="col-sm-2 col-sm-offset-2 control-label">
                                Tires
                            </form:label>
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
            <div class="row">
                <div class="col-sm-6">
                    <div class="panel-body">
                        <div class="form-group">
                            <form:label path="serviceIds" cssClass="col-sm-2 col-sm-offset-2 control-label">
                                Services
                            </form:label>
                            <div class="col-sm-8">
                                <c:forEach var="service" items="${services}">
                                    <div class="checkbox">
                                        <label>
                                          <form:checkbox id="services" 
                                                         path="serviceIds" 
                                                         value="${service.id}"
                                                         data-price="${service.price}"/>
                                          <c:out value="${service.name}"/>
                                        </label>
                                    </div>
                                </c:forEach>
                                <form:errors path="serviceIds" cssClass="help-block"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="servicePrice" class="panel-footer"></div>
        </div>
                            
        <div class="panel panel-default">
            <div class="panel-body">
                <h4 id="totalPrice"></h4>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-4">

                <c:choose>
                    <c:when test="${auth != null}">
                        <button class="btn btn-default" id="orderSubmit" type="submit">
                            Submit order
                        </button>
                    </c:when>
                    <c:otherwise>
                        <p>
                            <a href="<c:url value="/login" />" >In order to submit an order, please log in.</a>
                        </p>
                    </c:otherwise>
                
                </c:choose>
                

            </div>
        </div>
            
    </form:form>
    
</jsp:attribute>
</t:generic>

<script>

$(document).ready( function () {   
    var $tirePriceInt = 0;
    var $servicePriceInt = 0;
    var $totalPriceInt = 0;
    $("#orderSubmit").attr("disabled","disabled");
        
    var computeTotalPrice = function()
    {
        $totalPriceInt = $tirePriceInt + $servicePriceInt;
    };
    
    var printTotalPrice = function()
    {
        $("#totalPrice").text("Total price: " + $totalPriceInt + ",- €");
    };
    
    var disableSubmitButton = function()
    {
        if($totalPriceInt === 0)
        {
            $("#orderSubmit").attr("disabled","disabled");
        }
        else
        {
            $("#orderSubmit").removeAttr("disabled");
        }
    }   
    
    $("#tires").change(function()
    {
        var $selected = ( $(this).find(":selected") );
        
        var $tirePrice = $selected.attr('data-price');
        var $description = $selected.attr('data-description');
        var $name = $selected.attr('data-name');
        var $brand = $selected.attr('data-brand');
        var $id = $selected.val();
        
        $tirePriceInt = parseInt($tirePrice);
        $tirePriceInt = $tirePriceInt*4;
        if($tirePriceInt === 0)
        {
            $("#tireName").text("");
            $("#tireDescription").text("");
            $("#tirePrice").text("");
            $('#tireLink').hide();
        }
        else 
        {
            $("#tireName").text($brand + " " + $name);
            $("#tireDescription").text($description);
            $("#tirePrice").text("Tires price: " + $tirePriceInt + ",- €");
            $("#tireLink").attr('href', 'tire/' + parseInt($id));
            $('#tireLink').show();
        }
        computeTotalPrice();
        printTotalPrice();
        disableSubmitButton();
    });
    
    $('#order :checkbox').click(function() 
    {
        var $this = $(this);
        if ($this.is(':checked')) {
            $servicePriceInt = $servicePriceInt + parseInt($this.attr('data-price'));
        } else {
            $servicePriceInt = $servicePriceInt - parseInt($this.attr('data-price'));       
        }
        if($servicePriceInt === 0)
        {
            $("#servicePrice").text("");
        }
        else
        {
            $("#servicePrice").text("Service price: " + $servicePriceInt + ",- €");
        }
        computeTotalPrice();
        printTotalPrice();
        disableSubmitButton();
    });
});
</script>