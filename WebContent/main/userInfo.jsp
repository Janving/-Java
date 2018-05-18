<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib prefix="s" uri="/struts-tags" %> 
   
    <%
    String path=request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    
%>
<!doctype html>
<html>
<head>
<base href="<%=basePath %>">
<meta charset="utf-8">
<title>userInfo</title>

  <link rel="icon" type="image/png" href="main/assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="main/assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="main/assets/css/amazeui.min.css"/>
  <link rel="stylesheet" href="main/assets/css/admin.css">
  
   <script type="text/javascript">
    //注册时检查输入项
     function allIsNull(){
   
    	var pwd = document.updateForm.upass.value;
    	var repwd=document.updateForm.reupass.value;
    
    	if(pwd==""){
    		alert("请输入新密码!");
    		document.updateForm.upass.focus();
    		return false;
    	}
    	if(repwd==""){
    		alert("请输入确认新密码");
    		document.updateForm.reupass.focus();
    		return false;
    	}
    	if(pwd!=repwd){
    		
    		alert("2次密码不一致，请重新输入！"+repwd);
    		document.updateForm.upass.value=pwd;
    		document.updateForm.reupass.value=pwd;
    		document.updateForm.upass.focus();
    		return false;
    	}
    
    	alert("开始修改");
    	document.updateForm.submit();
    	return true;
    }
    </script>
</head>

<body>
 <div class="admin-content">
    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">个人资料</strong> / <small>Personal information</small></div>
    </div>

    <hr/>

    <div class="am-g">

      <div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">
        <div class="am-panel am-panel-default">
          <div class="am-panel-bd">
            <div class="am-g">
              <div class="am-u-md-4">
                <img class="am-img-circle am-img-thumbnail" src="http://amui.qiniudn.com/bw-2014-06-19.jpg?imageView/1/w/1000/h/1000/q/80" alt=""/>
              </div>
              <div class="am-u-md-8">
                <p>你可以使用<a href="#">gravatar.com</a>提供的头像或者使用本地上传头像。 </p>
                <form class="am-form">
                  <div class="am-form-group">
                    <input type="file" id="user-pic">
                    <p class="am-form-help">请选择要上传的文件...</p>
                    <button type="button" class="am-btn am-btn-primary am-btn-xs">保存</button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>

        <div class="am-panel am-panel-default">
          
        </div>

      </div>

      <div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
        <form class="am-form am-form-horizontal" action="user/updateUser.action" method="post" name="updateForm">
          <div class="am-form-group">
            <label for="uname" class="am-u-sm-3 am-form-label">姓名 / Name</label>
            <div class="am-u-sm-9">
              <input type="text" id="uname" name="uname" placeholder="姓名 / Name"  value="<s:property value="#session.user.uname"/>"/>
             
              <small>姓名 </small>
            </div>
          </div>

          <div class="am-form-group">
            <label for="email" class="am-u-sm-3 am-form-label">电子邮件 / Email</label>
            <div class="am-u-sm-9">
              <input type="text" id="email" name="email" placeholder="输入你的电子邮件 / Email" value="<s:property value="#session.user.Email"/>"/>
              <small>邮箱你懂得...</small>
            </div>
          </div>

        

          <div class="am-form-group">
            <label for="upass" class="am-u-sm-3 am-form-label">若修改密码请输入新密码</label>
            <div class="am-u-sm-9">
              <input type="password" id="upass" name="upass" placeholder="你的密码" value="<s:property value="#session.user.upass"/>"/>
            </div>
          </div>
          
            <div class="am-form-group">
            <label for="reupass" class="am-u-sm-3 am-form-label">确认新密码</label>
            <div class="am-u-sm-9">
              <input type="password" id="reupass" name="reupass" placeholder="你的密码" value="<s:property value="#session.user.upass"/>"/>
            </div>
          </div>
       

         

          <div class="am-form-group">
            <div class="am-u-sm-9 am-u-sm-push-3">
              <button type="button" class="am-btn am-btn-primary" onclick="allIsNull()">保存修改</button>
             
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
  <script src="main/assets/js/jquery.min.js"></script>
<script src="main/assets/js/amazeui.min.js"></script>
<!--<![endif]-->
<script src="main/assets/js/app.js"></script>
</body>
</html>
