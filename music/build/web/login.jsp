<%-- Include tag is used to import header page --%>
<%@include file="header.jsp"%>

<form class="form-horizontal" action="UserController" method="post">
    <p class="form-group has-error">
          <label class="control-label text-center"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${message}</label>
    </p>
    	<input type="hidden" name="action" value="login">
        <br>
        <div class="center-block" style="text-align: center;">
            <label> Please enter your email and password to login </label>
        </div>
        <br>
	<div class="form-group">
		<label class="col-sm-4 control-label"></label> 
		<div class="col-sm-4">
			<input type="email" class="form-control" name="email" placeholder="Email" />
	</div>
	</div>

	<div class="form-group">
		<label class="col-sm-4 control-label"></label>
		<div class="col-sm-4">
                    <input class="form-control" type="password" name="password" placeholder="password"  />
			   <c:if test="${message}">
			   <br/>
                           
            </c:if>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-4 col-sm-10">
			<input type="submit" value="Log in" class="btn btn-primary">
			<input type="submit" formaction="forgotpass.jsp"
				class="btn btn-secondary" value="Forgot Password">
                        <input type="submit" formaction="signup.jsp" value="Register" class="btn btn-success">
		</div>
	</div>

</form>
<br />
<br />
<br />

</form>
<%@include file="footer1.jsp"%>