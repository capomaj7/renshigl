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
<input onClick="switchBar(this)" type="button" value="关闭左边管理菜单" name="SubmitBtn" />
<br/>



<table class="table" cellspacing="1" cellpadding="2" width="99%" align="center" border="0">
  <tbody>
    
    
    <tr>
      <td class="td_bg"  height="23">部门名称</td>
      <td class="td_bg"  height="23">员工姓名</td>
      <td class="td_bg"  height="23">员工号</td>
       <td class="td_bg"  height="23">名称</td>
      <td class="td_bg"  height="23">月份</td>
      <td class="td_bg"  height="23">金额</td>
	  <td class="td_bg"  height="23">添加时间</td>

      <td class="td_bg" >操作</td>
    </tr>
    <c:forEach items="${list}" var="bean">
    <tr>
      <td class="td_bg"  height="23">${bean.yuangong.bumen.name }</td>
      <td class="td_bg"  height="23">${bean.yuangong.truename }</td>
      <td class="td_bg"  height="23">${bean.yuangong.yuangonghao }</td>
       <td class="td_bg"  height="23">${bean.mingchen }</td>
      <td class="td_bg"  height="23">${bean.yuefen }</td>
      <td class="td_bg"  height="23">${bean.gongzi }</td>
      <td class="td_bg"  height="23">${fn:substring(bean.createtime,0, 19)}</td>

      <td class="td_bg" >
      <a href="method!baoxianfuliupdate3?id=${bean.id }"><span style="font-size: 15px;">查看保险福利</span></a>
  
      </td>
    </tr>
    </c:forEach>
    
    
    
  
    
  </tbody>
</table>
${pagerinfo }
</body>
</html>

