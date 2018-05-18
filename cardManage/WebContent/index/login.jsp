<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@taglib prefix="s" uri="/struts-tags" %> 
  <%
    String path=request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    
%>
  

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
	<head>
	<base href="<%=basePath %>">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Minimal and Clean Sign up / Login and Forgot Form by FreeHTML5.co</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Free HTML5 Template by FreeHTML5.co" />
	<meta name="keywords" content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive" />
	

  

  	<!-- Facebook and Twitter integration -->
	<meta property="og:title" content=""/>
	<meta property="og:image" content=""/>
	<meta property="og:url" content=""/>
	<meta property="og:site_name" content=""/>
	<meta property="og:description" content=""/>
	<meta name="twitter:title" content="" />
	<meta name="twitter:image" content="" />
	<meta name="twitter:url" content="" />
	<meta name="twitter:card" content="" />

	<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
	<link rel="shortcut icon" href="favicon.ico">

	<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300' rel='stylesheet' type='text/css'>
	
	<link rel="stylesheet" href="index/css/bootstrap.min.css">
	<link rel="stylesheet" href="index/css/animate.css">
	<link rel="stylesheet" href="index/css/style.css">


	<!-- Modernizr JS -->
	<script src="index/js/modernizr-2.6.2.min.js"></script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="index/js/respond.min.js"></script>
	<![endif]-->

	</head>
	<body>
	

		<div class="container">
			<div class="row">
			  <div class="col-md-4 col-md-offset-4">
			     
			    <!-- Start Sign In Form -->
			    <s:form action="user/login.action"  method ="post" class="fh5co-form animate-box" data-animate-effect="fadeIn">
			      <h2>登陆</h2>
			      <div class="form-group">
			        <label for="username" class="sr-only">Username</label>
			        <s:textfield class="form-control" id="uname" name="uname" placeholder="用户名" autocomplete="off"/>
                    <br>
                    <s:password class="form-control" id="upass" name="upass" placeholder="密码" autocomplete="off"/>
		          </div>
			      <div class="form-group">
			        <label for="password" class="sr-only">Password</label>
			      </div>
			      <div class="form-group">
			        <label for="remember">
			          <input type="checkbox" id="remember">
			          保存用户名密码</label>
		          </div>
			      <div class="form-group">
			        <p>没有注册？ <a href="index/register.jsp">注册</a> | <a href="index/forgot.html">忘记密码?</a></p>
		          </div>
			      <div class="form-group">
			        <input type="submit" value="登陆" class="btn btn-primary">
			      
		               
			      </div>
		        </s:form>
		        <br>
		            <s:fielderror/> 
			    <!-- END Sign In Form -->
		      </div>
				
			</div>
            <div class="copyrights">Collect from <a href="http://www.cssmoban.com/"  title="网站模板">网站模板</a></div>
			<div class="row"></div>
			<div class="row" style="padding-top: 60px; clear: both;">
				<div class="col-md-12 text-center">
				  <p><small>&copy; All Rights Reserved.</small></p></div>
			</div>
		</div>
	
	<!-- jQuery -->
	<script src="index/js/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="index/js/bootstrap.min.js"></script>
	<!-- Placeholder -->
	<script src="index/js/jquery.placeholder.min.js"></script>
	<!-- Waypoints -->
	<script src="index/js/jquery.waypoints.min.js"></script>
	<!-- Main JS -->
	<script src="index/js/main.js"></script>

  
	</body>
</html>

