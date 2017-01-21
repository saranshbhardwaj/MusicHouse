<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
            <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%-- title of the Page--%>
        <title>Music House</title>
        <%-- importing CSS stylesheet --%>
        <link rel="stylesheet" href="styles/main.css">
        <script type="text/javascript" src="js/jquery-1.12.0.min.js"></script> 
        
        <link rel="stylesheet" 
              href="http://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css"></style>
        <script type="text/javascript" 
              src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>
              
        <!-- BootStrap -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        
        <script type="text/javascript">
        </script>
    </head>
<body>
        <div id="header">
            <nav id="header_menu" class=".navbar-inverse">
                <ul  class="left" >
                    <li><a class="btn btn-primary btn-lg outline" href="UserController?action=home">Guitar World</a></li>
                </ul>
                <ul class="right">
                        <li><c:choose> 
                            <c:when test="${theUser.userType == 'Customer'}">
                                <c:out value="Welcome, ${theUser.name}"/>
                            </c:when> 
                            <c:when test="${theAdmin.userType == 'Admin'}">
                                <c:out value="Welcome, ${theAdmin.name}"/>
                            </c:when>
                            <c:otherwise>
                                <a class="btn btn-link" href="login.jsp">Login</a>
                            </c:otherwise>
                        </c:choose>
                        </li>
                    <c:if test="${not empty theUser || not empty theAdmin}">
                        <li><a href="UserController?action=logout">Logout</a></li>
                        <li><a class="btn btn-primary outline" href="productController?action=showProducts">Product List</a></li>
                    </c:if>
                             
                             
                </ul>

            </nav>



        </div>
