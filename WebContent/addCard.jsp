<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>addCard.jsp</title>
<link href="css/common.css" type="text/css" rel="stylesheet">
</head>

<body>
<s:form  method="post" action="card/addCard.action" enctype="multipart/form-data">
  <table  border="1" style="border-collapse:collapse">
    <caption>
        <font size=4 face=华文新魏>添加名片</font>
    </caption>
    <tr>
      <td >姓名<font color="red">*</font></td>
      <td ><s:textfield name="name"/></td>
    </tr>
    <tr>
      <td>电话<font color="red">*</font></td>
      <td><s:textfield name="telephone"/></td>
    </tr>
    <tr>
      <td>E-Mail</td>
      <td><s:textfield name="email"/></td>
    </tr>
    <tr>
      <td>单位</td>
      <td><s:textfield name="company"/></td>
    </tr>
    <tr>
      <td>职务</td>
      <td><s:textfield name="post"/></td>
    </tr>
    <tr>
      <td>地址</td>
      <td><s:textfield name="address"/></td>
    </tr>
    <tr>
      <td>logo</td>
      <td><s:file  name="logo"/></td>
  
    </tr>
    <tr>
      <td><s:submit  value="提交" /></td>
      <td><s:reset value="重置" /></td>
    </tr>
</table>
</s:form>
<s:fielderror/>
</body>
</html>