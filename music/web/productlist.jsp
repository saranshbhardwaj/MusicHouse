<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>


<script type="text/javascript">
$(document).ready(function() {
    $('#myTable').DataTable();
} );
</script>

<%@ include file="header.jsp" %>
<form action="productController" method="post">
    <div class='panel panel-primary'>
    <div class='panel-heading'>
        Product List
    </div>
    <div class='panel-body'>
        <div class='table-responsive'>
            <table id="myTable" class='table table-striped table-condensed table-hover'>
                <thead>
                <tr>
                    <th>Product</th>
                    <th>Code</th>
                    <th>Availability</th>
                    <th>Price</th>
                    <c:if test="${CustomerOrAdmin == 0}">
                    <th>Add to cart</th>
                    </c:if>
                    <c:if test="${CustomerOrAdmin == 1}">
                    <th>Update</th>
                    
                    <th>Remove</th>
                    </c:if>
                </tr>
            </thead>

              <tbody>
                  <c:forEach var="product" items="${products}">
                <tr >
                    <td>
                        <img src="${product.imageurl}">
                    </td>
                    <td><a>${product.productname}</a></td>
                    <td>${product.productstatus}</td>
                    <td>${product.price}</td>
                    <c:if test="${CustomerOrAdmin == 0}">
                    <td>
                        <form action="productController" method="post">
                            <input type="hidden" name="productid" value="<c:out value="${product.productid}"/>">
                            <c:if test="${product.productstatus == 'Available'}">
                            <input type="submit" class="btn btn-primary" value="Add To Cart">
                            </c:if>
                            <c:if test="${product.productstatus == 'Out of stock'}">
                                <input type="submit" class="btn btn-primary" value="Add To Cart" disabled>
                            </c:if>
                        </form>
                            </td>
                            </c:if>
                        
                      <c:if test="${CustomerOrAdmin == 1}">
                          <td>
                              <form action="productController" method="post">
                            <input type="hidden" name="productid" value="<c:out value="${product.productid}"/>">
                            <input type="hidden" name="productStatus" value="<c:out value="${product.productstatus}"/>">
                            <input type="hidden" name="productPrice" value="<c:out value="${product.price}"/>">
                            <input type="hidden" name="productName" value="<c:out value="${product.productname}"/>">
                            <input type="hidden" name="productImageurl" value="<c:out value="${product.imageurl}"/>">
                            <input type="submit" name="action" class="btn btn-primary" value="Update">
                            </form>
                            </td>
                          </c:if>  
                      <c:if test="${CustomerOrAdmin == 1}">
                    <td>
                        <form action="productController" method="post">
                            <input type="hidden" name="productid" value="<c:out value="${product.productid}"/>">
                        <input type="submit" name="action" class="btn btn-primary" value="Delete">
                        </form>
                    </td>
                    </c:if>
                </tr>
                </c:forEach>
            </tbody>
            </table>
        </div>
        <c:if test="${CustomerOrAdmin == 1}">
        <div class='table-responsive'>
            <form action="productController" method="post">
            <input type="submit" name="action" class="btn btn-primary" value="Add Product">
            </form>
        </div>
         <div class='table-responsive'>
            <form action="productController" method="post">
            <input type="submit" name="action" class="btn btn-primary" value="Assign Role to Users">
            </form>
        </div>
        </c:if>
    </div>
</div>   
</form>

</body>
<%@ include file="footer.jsp" %>