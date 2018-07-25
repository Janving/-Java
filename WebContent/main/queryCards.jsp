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
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>客户管理系统</title>
  <meta name="description" content="这是一个 index 页面">
  <meta name="keywords" content="index">
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

 <div class="admin-content">

    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">首页</strong> / <small>#</small></div>
    </div>

    <ul class="am-avg-sm-1 am-avg-md-4 am-margin am-padding am-text-center admin-content-list ">
      <li><a  class="am-text-success"><span class="am-icon-btn am-icon-file-text"></span><br/>昨日新增订单<br/>${application.lastdayOrderItem}</a></li>
      <li><a  class="am-text-warning"><span class="am-icon-btn am-icon-briefcase"></span><br/>客户总数<br/>${application.cardCount}</a></li>
      <li><a  class="am-text-danger"><span class="am-icon-btn am-icon-recycle"></span><br/>昨日访问<br/>${application.lastdayCount}</a></li>
      <li><a  class="am-text-secondary"><span class="am-icon-btn am-icon-user-md"></span><br/>在线用户<br/>${application.onlineCount} </a></li>
    </ul>

    <div class="am-g">
      <div class="am-u-sm-12">
        <table class="am-table am-table-bd am-table-striped admin-content-table">
          <thead>
          <tr>
            <th>ID</th><th>用户名</th><th>所在公司</th><th>联系方式</th><th>pic</th><th>管理</th>
          </tr>
          </thead>
          <tbody>
        
         
           <s:iterator value="allCards" var="card">
             <tr onmousemove="changeColor(this)" onmouseout="changColor1(this)">
             <td><s:property value="id"/></td>
             <td><s:property value="name"/></td>
             <td><a href="https://www.amap.com/search?query=<s:property value="company"/>"><s:property value="company"/></a></td>
             <td><s:property value="telephone"/></td>
             <td><s:if test="newFileName!=null">
                   <img alt="" width="50px" height="10px" src="logos/<s:property value="newFileName"/>"/>
                </s:if></td>
     
                
            <td>
              <div class="am-dropdown" data-am-dropdown>
                <button class="am-btn am-btn-default am-btn-xs am-dropdown-toggle" data-am-dropdown-toggle><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>
                <ul class="am-dropdown-content">
                  <li><a href="card/selectACard.action?id=<s:property value="id"/>&act=updateAcard" target="center">1. 编辑</a></li>
                  <li><a href="card/selectACard.action?id=<s:property value="id"/>"target="center">2. 详情</a></li>
                
                </ul>
              </div>
            </td>
            </tr>
          </s:iterator>
         <tr>
             <td> 共${totalCount}条记录</td>
             <td></td>
             <td></td>
            <td></td>
            <td></td>
         </tr>
          
         

          </tbody>
        </table>
        <!-- 分页页码 -->
        <s:url var= "url_pre" value="card/queryCard.action">
              <s:param name="pageCur" value="pageCur-1"></s:param>
           </s:url>
           <s:url var="url_next" value="card/queryCard.action">
               <s:param name="pageCur" value="pageCur+1"></s:param>
            </s:url>
         <ul class="am-pagination am-fr admin-content-pagination">
         

              <li> <s:a href="%{url_pre}">&laquo;</s:a></li>
    
            <s:iterator value="listPage" var="listpage">
         
            <s:if test="pageCur==#listpage.page">
             <li class="am-active"> <s:a href="%{#listpage.url}"><s:property value="%{#listpage.page}"/></s:a></li>
            </s:if>
             <s:else>
              <li > <s:a href="%{#listpage.url}"><s:property value="%{#listpage.page}"/></s:a></li>
             </s:else>
         
             </s:iterator>
             
             
              <li><s:a href="%{url_next}">&raquo;</s:a></li>
            </ul>
              <!-- 分页页码 结束-->
      </div>
    </div>

    <div class="am-g">
      <div class="am-u-md-6">
 
       <iframe style=" width:510px; height:350px" scrolling="no" src="orderitem/queryallOrderItem?act=new&startpage=0&pagecount=3"></iframe>
     
      </div>

      <div class="am-u-md-6">
       <iframe style=" width:510px; height:600px" scrolling="no" src="message/queryMessage"></iframe>

       
      </div>
    </div>
  </div>
  <script src="main/assets/js/jquery.min.js"></script>
<script src="main/assets/js/amazeui.min.js"></script>
<!--<![endif]-->
<script src="main/assets/js/app.js"></script>
</body>
</html>
