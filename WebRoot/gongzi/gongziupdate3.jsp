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

<table class="table" cellspacing="1" cellpadding="2" width="99%" align="center" border="0">
  <tbody>
    
    <form method="post"   action="method!gongziupdate2">
    
    <tr>
      <td class="td_bg"  height="23">所属员工</td>
      <td class="td_bg" >
       真实姓名：${bean.yuangong.truename }&nbsp;&nbsp;&nbsp;所属部门：${bean.yuangong.bumen.name }
      
      </td>
     
    </tr>
    <input type="hidden" name="id" value="${bean.id }"/>
    <tr>
      <td class="td_bg"  height="23">工资</td>
      <td class="td_bg" ><input type="text" name="gongzi" value="${bean.gongzi }" readonly="readonly"/></td>
     
    </tr>
    
    <tr>
      <td class="td_bg"  height="23">月份</td>
      <td class="td_bg" ><input type="text" name="yuefen" value="${bean.yuefen }" readonly="readonly"/></td>
     
    </tr>
    
   
    
    
    <tr>
      <td class="td_bg"  height="23">操作</td>
      <td class="td_bg" >

       &nbsp; &nbsp; &nbsp; &nbsp;
      <input  onclick="javascript:history.go(-1);"  type="button" value="返回" />
      </td>
     
    </tr>
    </from>
    
    
  
    
  </tbody>
</table>

</body>
</html>

