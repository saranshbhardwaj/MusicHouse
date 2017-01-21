<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>

<div class='panel panel-primary'>
    <div class='panel-heading'>
        Your Cart
    </div>
<div class='panel-body'>
        <div class='table-responsive'>
<table class='table'>
    <thead>
  <tr>
    <th>Quantity</th>
    <th>Product Name</th>
    <th>Price</th>
    <th>Amount</th>
    <th>Remove</th>
  </tr>
</thead>
<tbody>
    <c:forEach var="item" items="${cartData.items}">
  <tr>
    <td>
      <form action="" method="post">
          <input type="hidden" name="productid" value="<c:out value='${item.product.productid}'/>">
          
        <input type=text name="quantity" 
               value="<c:out value='${item.quantity}'/>" id="quantity">
        <input type="submit" class="btn btn-primary" value="Update">
              </form>
        </td>
        <td>
                
              <c:out value='${item.product.productname}'/>
                  </td>
                  <td>
          
              <c:out value='${item.product.price}'/>
          </td>
          <td>${item.totalCurrencyFormat}</td>
      <form action="" method="post">
          <td>
        <input type="hidden" name="productid" 
               value="<c:out value='${item.product.productid}'/>">
        <input type="hidden" name="quantity" 
               value='0'>
        <input type="submit" class="btn btn-danger" value="Remove Item">
        </td>
      </form>
    </td>
  </tr>
  </c:forEach>
</tbody>
</table>
        </div>
</div>

<p><b>To change the quantity</b>, enter the new quantity 
      and click on the Update button.</p>
  
<form action="productController" method="post">
  <input type="hidden" name="action" value="showProducts">
  <input type="submit" name="showProducts" class="btn btn-primary" value="Continue Shopping">
</form>

<!--<form action="" method="post">
  <input type="hidden" name="action" value="checkout">
  <input type="submit" class="btn btn-primary" value="Checkout">
</form> -->
</div>
<%@ include file="footer.jsp" %>