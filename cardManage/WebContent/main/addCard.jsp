<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>无标题文档</title>

  <link rel="icon" type="image/png" href="assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="assets/css/amazeui.min.css"/>
  <link rel="stylesheet" href="assets/css/admin.css">
</head>

<body>

 <div class="admin-content">
    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">添加信息</strong> / <small>add Card</small></div>
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
                <p>上传头像 </p>
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
      <s:form  method="post" action="../card/addCard.action" enctype="multipart/form-data" class="am-form am-form-horizontal">
    
          <div class="am-form-group">
            <label for="name" class="am-u-sm-3 am-form-label">姓名 / Name</label>
            <div class="am-u-sm-9">
              <s:textfield name="name" id="name" placeholder="姓名 / Name"/>
             
            </div>
          </div>

          <div class="am-form-group">
            <label for="email" class="am-u-sm-3 am-form-label">电子邮件 / Email</label>
            <div class="am-u-sm-9">
              <s:textfield name="email" id="email" placeholder="输入电子邮件 / Email"/>
              <small>邮箱你懂得...</small>
            </div>
          </div>

          <div class="am-form-group">
            <label for="telephone" class="am-u-sm-3 am-form-label">电话 / Telephone</label>
            <div class="am-u-sm-9">
              <s:textfield name="telephone" id="telephone" placeholder="输入电话号码 / Telephone"/>
            </div>
          </div>

           <div class="am-form-group">
            <label for="company" class="am-u-sm-3 am-form-label">单位 / Company</label>
            <div class="am-u-sm-9">
              <s:textfield name="company" id="company" placeholder="输入单位 / Company"/>
            </div>
          </div>
            <div class="am-form-group">
            <label for="post" class="am-u-sm-3 am-form-label">职务 / Post</label>
            <div class="am-u-sm-9">
              <s:textfield name="post" id="post" placeholder="输入职务 / Post"/>
            </div>
          </div>
            <div class="am-form-group">
            <label for="address" class="am-u-sm-3 am-form-label">地址 / Address</label>
            <div class="am-u-sm-9">
              <s:textfield name="address" id="address" placeholder="输入地址 / Address"/>
            </div>
          </div>
        

      
         

          <div class="am-form-group">
            <div class="am-u-sm-9 am-u-sm-push-3">
              <s:submit class="am-btn am-btn-primary" value="提交"/>
              <s:reset class="am-btn am-btn-primary" value="重置"/>
            </div>
          </div>
       </s:form>
      </div>
    </div>
  </div>
  <script src="assets/js/jquery.min.js"></script>
<script src="assets/js/amazeui.min.js"></script>
<!--<![endif]-->
<script src="assets/js/app.js"></script>

</body>
</html>
