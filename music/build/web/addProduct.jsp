<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>

<form action="productController" method="post">
            <p class="form-group has-error">
          <label class="control-label text-center"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${message}</label>
    </p>
    <div class='panel panel-primary'>
     <div class='panel-heading'>
        Add Product
    </div>
        <div class='panel-body'>
            <div class='table-responsive'>
                <div class="form-group">
                <label class="col-sm-4 control-label">Product Name</label>
                <input type="text" class="form-control" name="productName" value="${product.productname}" required placeholder="Product Name"/>
                </div>
            <br>
             <div class="form-group">
                <label class="col-sm-4 control-label">Availability</label>
                <select name="productstatus" class="form-control"> 
                                <option value="1">Available</option>
                            <option value="2">Out of stock</option>
                        </select>
                </div>
              <div class="form-group">
               <label class="col-sm-4 control-label">Price</label>
                <input type="text" class="form-control" name="productPrice" value="${product.price}" required placeholder="Product price"/>
                </div>
              <div class="form-group">
                <label class="col-sm-4 control-label"></label>
                 <input type="submit" name="action" class="btn btn-success" value="Add">
                </div>
            </div>
        </div>
    </div>
    
</form>
                <form action="productController" method="post">
              <div class="form-group">
                <label class="col-sm-4 control-label"></label>
                 <input type="submit" name="action" class="btn btn-primary" value="Go Back">
                </div>
</form>
<%@ include file="footer.jsp" %>