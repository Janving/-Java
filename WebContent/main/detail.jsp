<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String path=request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    
%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html>
<head>
<base href="<%=basePath %>">
<meta charset="utf-8">
<title>无标题文档</title>

  <link rel="icon" type="image/png" href="main/assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="main/assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="main/assets/css/amazeui.min.css"/>
  <link rel="stylesheet" href="main/assets/css/admin.css">
  
  <style type="text/css">
#preview{width:300px;height:270px;border:1px solid #000;overflow:hidden; border:none; }
#imghead {filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);}
img{ border:none;}
</style>
  
  <script type="text/javascript">
      //图片上传预览    IE是用了滤镜。
        function previewImage(file)
        {
          var MAXWIDTH  = 300; 
          var MAXHEIGHT = 270;
          var div = document.getElementById('preview');
          if (file.files && file.files[0])
          {
              div.innerHTML ='<img id=imghead>';
              var img = document.getElementById('imghead');
              img.onload = function(){
                var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
                img.width  =  rect.width;
                img.height =  rect.height;
//                 img.style.marginLeft = rect.left+'px';
                img.style.marginTop = rect.top+'px';
              }
              var reader = new FileReader();
              reader.onload = function(evt){img.src = evt.target.result;}
              reader.readAsDataURL(file.files[0]);
          }
          else //兼容IE
          {
            var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
            file.select();
            var src = document.selection.createRange().text;
            div.innerHTML = '<img id=imghead>';
            var img = document.getElementById('imghead');
            img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
            status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
            div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;"+sFilter+src+"\"'></div>";
          }
        }
        function clacImgZoomParam( maxWidth, maxHeight, width, height ){
            var param = {top:0, left:0, width:width, height:height};
            if( width>maxWidth || height>maxHeight )
            {
                rateWidth = width / maxWidth;
                rateHeight = height / maxHeight;
                 
                if( rateWidth > rateHeight )
                {
                    param.width =  maxWidth;
                    param.height = Math.round(height / rateWidth);
                }else
                {
                    param.width = Math.round(width / rateHeight);
                    param.height = maxHeight;
                }
            }
            param.left = Math.round((maxWidth - param.width) / 2);
            param.top = Math.round((maxHeight - param.height) / 2);
            return param;
        }
</script>   
  
  
</head>

<body>

 <div class="admin-content">
    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">详细信息</strong> / <small>Update Card</small></div>
    </div>

    <hr/>

    <div class="am-g">

      <div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">
        <div class="am-panel am-panel-default">
          <div class="am-panel-bd">
             <div class="am-g">
              <div class="am-u-md-4" id="preview">
             
               <s:if test="acard.newFileName!=null">
                   <img alt="" width="250" height="250" src="logos/<s:property value="acard.newFileName"/>"/>
                </s:if>
               
              
              </div>
               <hr>
              <div class="am-u-md-8">
               
                
                  <div class="am-form-group">
                   
                
                  </div>
               
              </div>
            </div>
          </div>
        </div>

        <div class="am-panel am-panel-default">
          
        </div>

      </div>

      <div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
    <s:form  method="post" action="" enctype="multipart/form-data" class="am-form am-form-horizontal">
    
    
    
         <div class="am-form-group">
            <label for="id" class="am-u-sm-3 am-form-label">ID</label>
            <div class="am-u-sm-9">
              <s:textfield name="id"
                cssStyle="border-width:1pt;border-style:dashed;border-color:red"
                value="%{acard.id}"
                readonly="true"/>
              
            </div>
          </div>
          
          <div class="am-form-group">
            <label for="name" class="am-u-sm-3 am-form-label">姓名 / Name</label>
            <div class="am-u-sm-9">
              <s:textfield name="name" id="name" readonly="true" value="%{acard.name}" />
            
            </div>
          </div>

          <div class="am-form-group">
            <label for="email" class="am-u-sm-3 am-form-label">电子邮件 / Email</label>
            <div class="am-u-sm-9">
              <s:textfield name="email" id="email" readonly="true" value="%{acard.email}" />
            
            </div>
          </div>

          <div class="am-form-group">
            <label for="telephone" class="am-u-sm-3 am-form-label">电话 / Telephone</label>
            <div class="am-u-sm-9">
              <s:textfield name="telephone" id="telephone" readonly="true" value="%{acard.telephone}" />
            </div>
          </div>

           <div class="am-form-group">
            <label for="company" class="am-u-sm-3 am-form-label">单位 / Company</label>
            <div class="am-u-sm-9">
              <s:textfield name="company" id="company" readonly="true" value="%{acard.company}" />
            </div>
          </div>
            <div class="am-form-group">
            <label for="post" class="am-u-sm-3 am-form-label">职务 / Post</label>
            <div class="am-u-sm-9">
              <s:textfield name="post" id="post" readonly="true" value="%{acard.post}" />
            </div>
          </div>
            <div class="am-form-group">
            <label for="address" class="am-u-sm-3 am-form-label">地址 / Address</label>
            <div class="am-u-sm-9">
              <s:textfield name="address" id="address" readonly="true" value="%{acard.address}" />
            </div>
          </div>
        <div>
               
                   
        </div>

      
         

          <div class="am-form-group">
            <div class="am-u-sm-9 am-u-sm-push-3">
            <a href="card/selectACard.action?id=<s:property value="id"/>&act=updateAcard" >
              <input type="button" class="am-btn am-btn-primary" value="编辑"/></a>
            
            </div>
          </div>
  </s:form>
      </div>
    </div>
  </div>
  <script src="main/assets/js/jquery.min.js"></script>
<script src="main/assets/js/amazeui.min.js"></script>
<!--<![endif]-->
<script src="main/assets/js/app.js"></script>

</body>
</html>
