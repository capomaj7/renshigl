<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="Images/css1/css.css" rel="stylesheet" type="text/css">
</head>
<SCRIPT language=javascript>
<!--
var displayBar=true;
function switchBar(obj){
	if (displayBar)
	{
		parent.frame.cols="0,*";
		displayBar=false;
		obj.value="打开左边管理菜单";
	}
	else{
		parent.frame.cols="195,*";
		displayBar=true;
		obj.value="关闭左边管理菜单";
	}
}

function fullmenu(url){
	if (url==null) {url = "admin_left.asp";}
	parent.leftFrame.location = url;
}

//-->
</SCRIPT>


 

 

<body>
<input type="button" onclick="switchBar(this)" value="关闭左边管理菜单" name="SubmitBtn">  
<br> 
<form action="method!bumenlist" method="post"> 
<div align="left"> 
部门名称：<input type="text" name="name" value="${name}"> 
 
<input type="submit" value="搜索"> 
 
</div> 
</form> 
 
<a href="method!bumenadd"><span style="font-size: 25px;">添加新部门</span></a> 
<table width="99%" cellspacing="1" cellpadding="2" border="0" align="center" class="table"> 
  <tbody> 
     
    <tr> 
      <td height="23" class="td_bg">序号</td> 
      <td height="23" class="td_bg">部门编号</td> 
      <td height="23" class="td_bg">部门名称</td> 
 
 
      <td class="td_bg">操作</td> 
    </tr> 
    <c:forEach items="${list}" var="bean" varStatus="vs"> 
    <tr> 
    	
      <td height="23" class="td_bg">${vs.count }</td> 
      <td height="23" class="td_bg">${bean.bianhao }</td> 
      <td height="23" class="td_bg">${bean.name }</td> 
 
      <td class="td_bg"> 
      <a href="method!bumenupdate3?id=${bean.id }"><span style="font-size: 15px;">查看部门</span></a> 
      <a href="method!bumenupdate?id=${bean.id }"><span style="font-size: 15px;">更新部门</span></a> 
      <a href="method!bumendelete?id=${bean.id }"><span style="font-size: 15px;">删除部门</span></a> 
      </td> 
    </tr> 
    </c:forEach> 
     
     
     
   
     
  </tbody> 
</table> 
${pagerinfo } 
</body>
</html>

