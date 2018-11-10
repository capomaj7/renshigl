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
<h1><input type="button" onclick="switchBar(this)" value="关闭左边管理菜单" name="SubmitBtn"> 
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; <font color="#000000"><strong>调动管理 </strong></font></h1></h1></h1></h1><form action="method!bumenlist" method="post"><div align="left"><br/>
<form action="method!yuangonglist3" method="post">
<div align="left">

所属部门:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<select name="bumen">
<option value="">所有部门</option>
<c:forEach items="${list2}" var="bean">
<option value="${bean.name }"  <c:if test="${bean.name==bumen  }">selected</c:if>>${bean.name }</option>
</c:forEach>


</select> 
 
 
 
姓名：<input type="text" name="truename" value="${truename}">

员工号：<input type="text" name="yuangonghao" value="${yuangonghao}">

<input type="submit" value="搜索">
</div>
</form>


<table class="table" cellspacing="1" cellpadding="2" width="99%" align="center" border="0">
  <tbody>
    
    
    <tr>
      <td class="td_bg"  height="23">现在所属部门</td>
       <td class="td_bg"  height="23">姓名</td>
        <td class="td_bg"  height="23">员工号</td>
		<td class="td_bg"  height="23">之前所属部门</td>
           


      <td class="td_bg" >操作</td>
    </tr>
    <c:forEach items="${list}" var="bean">
    <tr>
      <td class="td_bg"  height="23">${bean.bumen.name }</td>
       <td class="td_bg"  height="23">${bean.truename }</td>
        <td class="td_bg"  height="23">${bean.yuangonghao }</td>
        <c:if test="${bean.prebumen==null }">
        	<td class="td_bg"  height="23">未调动</td>
        </c:if>
        <c:if test="${bean.prebumen!=null }">
        	<td class="td_bg"  height="23">${bean.prebumen.name }</td>
        </c:if>
        
             
           

      <td class="td_bg" >
      <a href="method!yuangongupdate3?id=${bean.id }"><span style="font-size: 15px;">查看员工</span></a>
      <a href="method!yuangongupdate5?id=${bean.id }"><span style="font-size: 15px;">调换部门</span></a>
      </td>
    </tr>
    </c:forEach>
    
    
    
  
    
  </tbody>
</table>
${pagerinfo }
</body>
</html>

