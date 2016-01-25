<%-- 
    Document   : tire/list
    Author     : Jozef Sumaj
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:generic title="Tires">
<jsp:attribute name="body">
    
    <table class="table table-hover">
        <caption>List of available tires</caption>
        <thead>
        <tr>
            <th>
                <a href="<c:url value="/tire/list?sortBy=brand${asc?'':'&asc'}" />" >
                    Brand    
                </a>                
            </th>
            <th>
                <a href="<c:url value="/tire/list?sortBy=name${asc?'':'&asc'}" />" >
                    Name
                </a>   
            </th>
            <th>Description</th>
            <th>
                <a href="<c:url value="/tire/list?sortBy=width${asc?'':'&asc'}" />" >
                    Width
                </a>
            </th>
            <th>
                <a href="<c:url value="/tire/list?sortBy=ratio${asc?'':'&asc'}" />" >
                    Ratio
                </a>
            </th>
            <th>
                <a href="<c:url value="/tire/list?sortBy=rim${asc?'':'&asc'}" />" >
                    Rim
                </a>
            </th>
            <th>
                <a href="<c:url value="/tire/list?sortBy=price${asc?'':'&asc'}" />" >
                    Price
                </a>
            </th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${tires}" var="tire">
            <tr>
                <td><c:out value="${tire.brand}"/></td>
                <td><c:out value="${tire.name}"/></td>
                <td><c:out value="${tire.description}"/></td>
                <td>${tire.width}</td>
                <td>${tire.ratio}</td>
                <td>${tire.rim}</td>
                <td>${tire.price}</td>
                <td>
                    <a href="<c:url value="/tire/edit/${tire.id}" />" type="button" class="btn btn-warning btn-xs">                    
                       <span class="glyphicon glyphicon glyphicon-pencil" aria-hidden="true"></span>
                    </a>
                    <a type="button" class="btn btn-default btn-xs open-Modal" data-toggle="modal" data-id="${tire.id}" data-target=".bs-example-modal-sm">
                        <span class="glyphicon glyphicon glyphicon-trash" aria-hidden="true"></span>
                    </a>
                </td>                       
            </tr>        
        </c:forEach>
        </tbody>
    </table>
    
    <a href="<c:url value="/tire/new" />" type="button" class="btn btn-default">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        Create new tire
    </a>
    
    <div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="exampleModalLabel">Confirm tire removal</h4>
                </div>
                <div class="modal-body">
                    <p>Do you want to delete this tire entry?</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                    <a type="button" class="btn btn-danger btn-sm">
                        Remove
                    </a>
                </div>
            </div>
        </div>
    </div>

</jsp:attribute>
</t:generic>
    
<script>
$(document).on("click", ".open-Modal", function () {
    var tireId = $(this).data('id');
    $(".modal-footer a").attr('href', 'delete/' + parseInt(tireId) );
});
</script>