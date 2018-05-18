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
<title>无标题文档</title>
  <link rel="icon" type="image/png" href="main/assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="main/assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="main/assets/css/amazeui.min.css"/>
  <link rel="stylesheet" href="main/assets/css/admin.css">
  
<script src="main/assets/js/jquery.min.js"></script>
<script type="text/javascript">  
    $(function(){  
        //实现全选与反选  
        $("#all").click(function() {   
            if (this.checked){    
                $("input[name='ids']:checkbox").each(function(){   
                	$(this).prop("checked",true);    
                });  
            } else {     
                $("input[name='ids']:checkbox").each(function() {     
                	$(this).prop("checked",false);   
                });  
            }    
        });  
          
   
   
    });  
</script>  
  
  <script type="text/javascript">
  
  
  
    function confirmDelete(){
    	
    	var n =document.deleteForm.ids.length;
   
    	var count=0;//统计没有选中的个数
    	for(var i=0;i<n;i++){
    		if(!document.deleteForm.ids[i].checked){
    			count++;
    		}else{
    			break;
    		}
    	}
    	if(n>1){//多个名片
    		//所有名片都没有选择
    		if(count==n){
    			alert("请选择删除的名片!");
    			count=0;
    			return false;
    		}
    	}else{
    		//一个名片
    		//就一个名片并且没有选择
    		if(!document.deleteForm.ids.check){
    			alert("请选择删除的名片");
    			return false;
    		}
    	}
    	if(window.confirm("真的删除吗？")){
    		document.deleteForm.submit();
    		return true;
    	}
    	return false;
    }
    
    function checkDel(id){
        if(window.confirm("是否删除该名片?")){
        	window.location.href="/cardManage/card/deleteCard.action?act=link&id="+id;
        }	
    }
    function changeColor(obj){
    	obj.className="bgcolor";
    }
    function changeColor1(obj){
    	obj.className="";
    }
    </script>
</head>

<body>

 <div class="admin-content">
<s:form action="card/deleteCard.action?act=button" name="deleteForm">
    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">编辑/删除</strong> / <small>Table</small></div>
    </div>

    <div class="am-g">
      <div class="am-u-md-6 am-cf">
        <div class="am-fl am-cf">
          <div class="am-btn-toolbar am-fl">
            <div class="am-btn-group am-btn-group-xs">
              <button type="button" class="am-btn am-btn-default"><span class="am-icon-plus"></span> 新增</button>
              <button type="button" class="am-btn am-btn-default"><span class="am-icon-save"></span> 保存</button>
              <button type="button" class="am-btn am-btn-default"><span class="am-icon-archive"></span> 审核</button>
              <button type="button" class="am-btn am-btn-default" onclick="confirmDelete()"><span class="am-icon-trash-o"></span> 删除</button>
            </div>

        
          </div>
        </div>
      </div>
    
    </div>

    <div class="am-g">
      <div class="am-u-sm-12">
        <form class="am-form">
     
     
          <table class="am-table am-table-striped am-table-hover table-main">
            <thead>
              <tr>
                <th class="table-check"><input type="checkbox" id="all"/>全选</th><th class="table-id">ID</th><th class="table-title">名字</th><th class="table-type">单位</th><th class="table-author">邮箱</th><th class="table-date">职务</th><th class="table-set">操作</th>
              </tr>
          </thead>
            
          <tbody>
            <s:iterator value="allCards" var="c">
          <tr >
              <td>
                  <input type="checkbox" name="ids" value="<s:property value="id"/>"/>
			  </td>
              
                 <td> <s:property value="id"/>
               </td>
               <td><s:property value="name"/></td>
               <td><s:property value="company"/></td>
               <td><s:property value="email"></s:property></td>
               <td><s:property value="post"/></td>
          <td>
             <div class="am-btn-toolbar">
                  <div class="am-btn-group am-btn-group-xs">
                    <button  type="reset" class="am-btn am-btn-default am-btn-xs am-text-secondary" ><a href="card/selectACard.action?id=<s:property value="id"/>&act=updateAcard" target="center"><span class="am-icon-pencil-square-o"> </span>编辑</a></button>
                    <button type="reset" class="am-btn am-btn-default am-btn-xs"><span class="am-icon-copy"><a href="card/selectACard.action?id=<s:property value="id"/>"target="center"></span> 详情</a></button>
                    <button type="reset" class="am-btn am-btn-default am-btn-xs am-text-danger"><a href="javascript:checkDel('<s:property value="id"/>')"><span class="am-icon-trash-o"></span> 删除</a></button>
                  </div>
                </div>
                </td>
           </tr>
           </s:iterator>
  
          </tbody>
        </table>
        
          <div class="am-cf">
  共 ${totalCount} 条记录
  
      <s:url var= "url_pre" value="card/queryCard.action">
          <s:param name="pageCur" value="pageCur-1"></s:param>
      </s:url>
       <s:url var="url_next" value="card/queryCard.action">
          <s:param name="pageCur" value="pageCur+1"></s:param>
       </s:url>
  <div class="am-fr">
    <ul class="am-pagination">
      <li ><s:a href="%{url_pre}">«</s:a></li>
      <li class="am-active"><a href="#">1</a></li>
      <li><a href="#">2</a></li>
      <li><a href="#">3</a></li>
      <li><a href="#">4</a></li>
      <li><a href="#">5</a></li>
      <li><s:a href="%{url_next}">»</s:a></li>
    </ul>
  </div>
</div>
    
        </form>
      </div>

    </div>
      </s:form>
      <s:form action="card/queryCard.action?act=deleteSelect" method="post" target="center">
   <div class="am-u-md-3 am-cf">
        <div class="am-fr">
          <div class="am-input-group am-input-group-sm">
         
            <input type="text" name="SearchKey" class="am-form-field">
                <span class="am-input-group-btn">
                  <button class="am-btn am-btn-default" type="submit" >搜索</button>
                </span>
             
          </div>
        </div>
      </div>
      </s:form>
  </div>
      <hr />
 <script src="main/assets/js/jquery.min.js"></script>
<script src="main/assets/js/amazeui.min.js"></script>
<!--<![endif]-->
<script src="main/assets/js/app.js"></script>
 
</body>
</html>
