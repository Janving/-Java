<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@taglib prefix="s" uri="/struts-tags" %> 
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


<script type="text/javascript">
    //输入姓名后，调用该方法，判断用户名是否可用
    function nameIsNull(){
    	var name=document.registForm.uname.value;
    	if(name==""){
    		//alert("请输入姓名！");
    		document.registForm.uname.focus();
    		return false;
    	}
    	document.registForm.flag.value="0";
    	document.registForm.submit();
    	return true;
    }
    //注册时检查输入项
    function allIsNull(){
    	var name=document.registForm.uname.value;
    	var pwd = document.registForm.upass.value;
    	var repwd=document.registForm.reupass.value;
    	if(name==""){
    		alert("请输入姓名!");
    		document.registForm.uname.focus();
    		return false;
    	}
    	if(pwd==""){
    		alert("请输入密码!");
    		document.registForm.upass.focus();
    		return false;
    	}
    	if(repwd==""){
    		alert("请输入确认密码");
    		document.registForm.reupass.focus();
    		return false;
    	}
    	if(pwd!=repwd){
    		
    		alert("2次密码不一致，请重新输入！"+repwd);
    		document.registForm.upass.value="";
    		document.registForm.reupass.value="";
    		document.registForm.upass.focus();
    		return false;
    	}
    	document.registForm.flag.value="1";
    	document.registForm.submit();
    	return true;
    }
    
    </script>
	</head>
	<body>

		<div class="container">
			<div class="row">
				
			<div class="row">
				<div class="col-md-4 col-md-offset-4">
					

					<!-- Start Sign In Form -->
					<form action="user/regist.action"  method="post" class="fh5co-form animate-box"  name="registForm" data-animate-effect="fadeIn">
						<input type="hidden" name="flag">
						<h2>注册</h2>
						
							
						<div class="form-group">
							<label for="name" class="sr-only">Name</label>
							<input type="text" class="form-control" id="uname" name="uname" value="${requestScope.uname}" placeholder="用户名" autocomplete="off" onblur="nameIsNull()">
						   <s:if test="#request.isExit=='false'">
                               <font color=red size=2>×  该用户名不可用</font>
                           </s:if>
                           <s:if test="#request.isExit=='true'">
                             <font color=green size=2>√  用户名合法可使用</font>
                           </s:if>
						</div>
						
						<div class="form-group">
							<label for="password" class="sr-only">Password</label>
							<input type="password" class="form-control" id="upass" name="upass" placeholder="密码" autocomplete="off">
						</div>
						<div class="form-group">
							<label for="re-password" class="sr-only">Re-type Password</label>
							<input type="password" class="form-control" id="reupass" name="reupass" placeholder="再次确认密码" autocomplete="off">
						</div>
						
						<div class="form-group">
							<p>已经有账号? <a href="../index/login.jsp">前往登陆</a></p>
						</div>
						<div class="form-group">
							<input type="submit" value="注册" class="btn btn-primary">
						</div>
					</form>
					<!-- END Sign In Form -->

				</div>
			</div>
			<div class="row" style="padding-top: 60px; clear: both;">
				<div class="col-md-12 text-center"><p><small>&copy; All Rights Reserved. More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> - Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a></small></p></div>
			</div>
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

