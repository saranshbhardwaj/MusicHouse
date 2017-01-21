<%-- Include tag is used to import header page --%>
<%@include file="header.jsp"%>

<form class="form-horizontal" action="UserController" method="post">
    <p>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${message}
    </p>
	<input type="hidden" name="action" value="updatepass">
	<div class="form-group">
            <label class="col-sm-4 control-label"></label> 
            <div class="col-sm-4">
                <input type="email" class="form-control" name="email" placeholder="Email" value="<c:out value='${sessionScope.email}'/>" readonly>
            </div>
		
	
	</div>

	<div class="form-group">
            <label class="col-sm-4 control-label"></label>
            <div class="col-sm-4">
                <input type="password" class="form-control" placeholder="Password" name="password" required/>
            </div>
            </div>
            <div class="form-group">
            <label class="col-sm-4 control-label"></label> 
            <div class="col-sm-4">
                <input type="password" class="form-control" placeholder="confirm password" name="confirm_password" required />  
            
            </div>
			</div>
			<div class="form-group">
    <div class="col-sm-offset-5">
            <input type="submit" value="Reset Password" class="btn btn-success">
            </div>
            </div>
</form>


<%@include file="footer.jsp"%>