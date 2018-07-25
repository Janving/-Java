<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%
    String path=request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    
%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<html class="no-js">
<head>
<base href="<%=basePath %>">
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>客户管理系统主页</title>
  <meta name="description" content="这是一个 table 页面">
  <meta name="keywords" content="table">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="main/assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="main/assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="main/assets/css/amazeui.min.css"/>
  <link rel="stylesheet" href="main/assets/css/admin.css">
</head>
<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
<![endif]-->

<header class="am-topbar admin-header">
  <div class="am-topbar-brand">
    <strong>欢迎</strong> <small><s:property value="#session.user.uname"/>进入客户管理系统!</small>
  </div>

  <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>

  <div class="am-collapse am-topbar-collapse" id="topbar-collapse">

    <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
     <!-- <li><a href="javascript:;"><span class="am-icon-envelope-o"></span> 收件箱 <span class="am-badge am-badge-warning">5</span></a></li>  -->
      <li class="am-dropdown" data-am-dropdown>
        <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
          <span class="am-icon-users"></span> 管理员 <span class="am-icon-caret-down"></span>
        </a>
        <ul class="am-dropdown-content">
          <li><a href="main/userInfo.jsp" target="center"><span class="am-icon-user"></span> 资料</a></li>
          <li><a href="main/userInfo.jsp" target="center"><span class="am-icon-cog"></span> 设置</a></li>
          <li><a href="user/exit.action"><span class="am-icon-power-off"></span> 退出</a></li>
        </ul>
      </li>
      <li><a href="javascript:;" id="admin-fullscreen"><span class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>
    </ul>
  </div>
</header>

<div class="am-cf admin-main">
  <!-- sidebar start -->
  <div class="admin-sidebar">
    <ul class="am-list admin-sidebar-list">
      <li><a href="main/main.jsp"><span class="am-icon-home"></span> 首页</a></li>
      <li class="admin-parent">
        <a class="am-cf" data-am-collapse="{target: '#collapse-nav'}"><span class="am-icon-file"></span> 名片模块 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
        <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav">
          <li><a href="main/addCard.jsp" target="center"><span class="am-icon-check"></span> 添加名片<span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>

          <li><a href="card/queryCard.action?act=updateSelect" target="center"><span class="am-icon-th"></span> 删除/修改名片<span class="am-badge am-badge-secondary am-margin-right am-fr">24</span></a></li>

          <li><a href="product/queryProduct.action" target="center"><span class="am-icon-calendar"></span>产品管理</a>
           <li><a href="orderitem/queryallOrderItem.action" target="center"><span class="am-icon-table"></span> 订单页面<span class="am-badge am-badge-secondary am-margin-right am-fr">24</span></a></li>
        </ul>
      </li>
  
      <li><a href="main/userInfo.jsp" target="center"><span class="am-icon-pencil-square-o"></span> 基本信息</a></li>
      <li><a href="user/exit.action"><span class="am-icon-sign-out"></span> 注销</a></li>
    </ul>

    <div class="am-panel am-panel-default admin-sidebar-panel">
      <div class="am-panel-bd">
        <p><span class="am-icon-bookmark"></span> 公告</p>
        <p>#</p>
      </div>
    </div>

    <div class="am-panel am-panel-default admin-sidebar-panel">
      <div class="am-panel-bd">
        <p><span class="am-icon-tag"></span> wiki</p>
        <p>#</p>
      </div>
    </div>
  </div>
  <div class="admin-content">
    <iframe src="card/queryCard.action" name="center" height="1800px"   width="100%"  frameborder="0"></iframe>
  </div>

  <!-- sidebar end -->

  <!-- content start -->
  
  <!-- content end -->
</div>

<footer>
  <hr>
  <p class="am-padding-left">© 2018</p>
</footer>

<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="assets/js/polyfill/rem.min.js"></script>
<script src="assets/js/polyfill/respond.min.js"></script>
<script src="assets/js/amazeui.legacy.js"></script>
<![endif]-->

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="main/assets/js/jquery.min.js"></script>
<script src="main/assets/js/amazeui.min.js"></script>
<!--<![endif]-->
<script src="main/assets/js/app.js"></script>
</body>
</html>
