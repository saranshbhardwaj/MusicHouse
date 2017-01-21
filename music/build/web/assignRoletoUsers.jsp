<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>

<form action="productController" method="post">
    <div class='panel panel-primary'>
    <div class='panel-heading'>
        Assign role to users
    </div>
        <div class='panel-body'>
        <div class='table-responsive'>
            <table class='table'>
                <thead>
                    <tr>
                    <th>User Name</th>
                    <th>Role/Update</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="userAC" items="${user}">
                        <tr>
                            <td>
                                ${userAC.email}
                            </td>
                                <td>
                         <form action="productController" method="post">
                            <input type="hidden" name="useremail" value="<c:out value="${userAC.email}"/>">
                            <span>
                           <c:if test="${userAC.userType == 'Customer'}">
                               <select name="userstatus" class="col-sm-4">
                            <option value="1">Customer</option>
                            <option value="2">Admin</option>
                               </select>
                                </c:if>
                         <c:if test="${userAC.userType == 'Admin'}">
                               <select name="userstatus" class="col-sm-4">
                            <option value="2">Admin</option>
                            <option value="1">Customer</option>
                               </select>
                                </c:if>
                                </span>
                        <input type="submit" name="action" class="btn btn-primary" value="Change the role">
                        </form>
                                </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
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