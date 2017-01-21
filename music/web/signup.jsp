<%@include file="header.jsp" %>

<section>
    <br/><br/><br/>
        <form class="form-horizontal" action="UserController" method="post">
        <p class="form-group has-error">
            <label class="control-label text-center"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${message}</label>
    </p>
            <input type="hidden" name="action" value="create" />
            <input type="hidden" name="token" value=${token} >
        <br>
        <div class="center-block" style="text-align: center;">
            <label> Please enter your email and password to login(All fields are required) </label>
        </div>
        <br>
            <div class="form-group">
            <label class="col-sm-4 control-label"></label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="name" placeholder="Name" required value="<c:out value='${name}'/>"/>
            </div>
            </div>
            <div class="form-group">
            <label class="col-sm-4 control-label"></label>
            <div class="col-sm-4">
                <input type="email" class="form-control" name="email" placeholder="Email" required value="<c:out value='${email}' />"/>
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
            <input type="password" class="form-control" placeholder="Confirm password" name="confirm_password" required />  
            
            </div>
			</div>
			<div class="form-group">
    <div class="col-sm-offset-5">
            <input type="submit" value="Create Account" class="btn btn-primary">
            </div>
            </div>
            <br><br/><br/>
        </form>
</section>
<%@include file="footer.jsp" %>