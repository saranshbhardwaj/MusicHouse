<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>

<form action="productController" method="post">
        <p class="form-group has-error">
          <label class="control-label text-center"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${message}</label>
    </p>
    <div class='panel panel-primary'>
     <div class='panel-heading'>
        Product List
    </div>
        <div class='panel-body'>
            <div class='table-responsive'>
                <table class='table'>
                    <thead>
                <tr>
                    <th>Product</th>
                    <th>Code</th>
                    <th>Availability</th>
                    <th>Price</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>
                        <img src="${cartDataToDelete.imageurl}"><br>
                    </td>
                    <td><a>${cartDataToDelete.productname}</a></td>
                    <td>
                        <c:if test="${cartDataToDelete.productstatus == 'Available'}">
                            <select name="productstatus"> 
                                <option value="1">Available</option>
                            <option value="2">Out of stock</option>
                        </select>
                        </c:if>
                        <c:if test="${cartDataToDelete.productstatus == 'Out of stock'}">
                            <select name="productstatus">
                                <option value="2">Out of stock</option>
                            <option value="1">Available</option>
                        </select>
                        </c:if>
                    </td>
                    <td>
                        <input type=text name="productprice" 
               value="<c:out value='${cartDataToDelete.price}'/>" id="productprice">
                    </td>
                    <td style="text-align: left">
                        <form action="productController" method="post">
                            <input type="hidden" name="productid" value="<c:out value="${cartDataToDelete.productid}"/>">
                            <span style="visibility: hidden">
                            <c:if test="${cartDataToDelete.productstatus == 'Available'}">
                            <select name="productstatus"> 
                                <option value="1">Available</option>
                            <option value="2">Out of stock</option>
                        </select>
                        </c:if>
                        <c:if test="${cartDataToDelete.productstatus == 'Out of stock'}">
                            <select name="productstatus">
                                <option value="2">Out of stock</option>
                            <option value="1">Available</option>
                        </select>
                        </c:if>
                                </span>
                            <input type="hidden" name="productprice" value="<c:out value="${cartDataToDelete.price}"/>">
                        <input type="submit" name="action" class="btn btn-primary" value="Submit">
                        </form>
                    </td>
                </tr>
            </tbody>
                </table>
                        <div class='table-responsive'>
                        <input type="submit" name="action" class="btn btn-primary" value="Go Back">
                        </div>
            </div>
            
        </div>
    </div>
</form>


<%@ include file="footer.jsp" %>