<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@taglib prefix="s" uri="/struts-tags" %> 
      <%
    String path=request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台登录</title>

  <style type="text/css">
    table{
        text-align:center;
    }
    .textSize{
        width:120px;
        height:25px;
    }
    *{
    margin:10px;
    height:25px;
    }
    body{
        font-family:Arial,Helvetica,sans-serif;
        font-size:12px;
        margin:10px 10px auto;
        background-image:url(iamges/bb.jpg);
        
    }
    .errorMessage{font-weight:bold;color:red;}
    </style>
    <script type="text/javascript">
    function gogo(){
    	document.forms[0].submit();
    	
    }
    function cancel(){
    	document.forms[0].action="";
    	
    }
    </script>
</head>
<body>
    <s:form action="user/login.action" method ="post">
    <table>
        <tr>
            <td colspan="2"><img src="images/login.gif"></td>
         </tr>
         <tr>
             <td>姓名：</td>
             <td><s:textfield name="uname" cssClass="textSize"/></td>
         </tr>
         <tr>
             <td>密码:</td>
             <td><s:password name="upass" cssClass="textSize"/></td>
         </tr>
         <tr>
              <td colspan="2">
            
                  <input type="button" value="确定" onclick="gogo()">
                  <input type="button" value="取消" onclick="cancel()">
                  </td>
          </tr>
          
          </table>
          </s:form>
          <s:fielderror/>     
          

</body>
</html>