<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
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
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
</head>
<SCRIPT language=javascript>

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
/* $(function(){
	var msg="${msg}";
	if(msg!=""){
		alert(msg);
	}
}); */

</SCRIPT>
<body>
<input type="button" onclick="switchBar(this)" value="关闭左边管理菜单" name="SubmitBtn">  
<br/>
<form action="method!peixunlist" method="post">
<div align="left">





员工姓名：<input type="text" name="truename" value="${truename}">

<input type="submit" value="搜索">
</div>
</form>

<a href="method!peixunadd"><span style="font-size: 25px;">添加新的培训计划</span></a>
<table class="table" cellspacing="1" cellpadding="2" width="99%" align="center" border="0">
  <tbody>
    
    
    <tr>
      <td class="td_bg"  height="23">部门名称</td>
      
      <td class="td_bg"  height="23">培训计划</td>
       <td class="td_bg"  height="23">培训周期</td>
        <td class="td_bg"  height="23">培训地点</td>
        <td class="td_bg"  height="23">培训人员</td>
         <td class="td_bg"  height="23">添加时间</td>
         <td class="td_bg"  height="23">培训成绩</td>
		
      <td class="td_bg" >操作</td>
    </tr>
    <form method="post" action="${ctx }/peixunAction_updateGrade">
    <c:forEach items="${list}" var="bean">
    	<input value="${bean.id }" name="ids" type="hidden">
    <tr>
    
      <td class="td_bg"  height="23">${bean.yuangong.bumen.name }</td>
      <td class="td_bg"  height="23">${bean.peixunjihua }</td>
      <td class="td_bg"  height="23">${bean.peixunzhouqi }</td>
      <td class="td_bg"  height="23">${bean.peixundidian }</td>
      <td class="td_bg"  height="23">${bean.yuangong.truename }</td>
      <td class="td_bg"  height="23">${fn:substring(bean.createtime,0, 19)}</td>
      <c:if test="${user.role!=0}">
	  <td class="td_bg"  height="23"><input type="text" value="${bean.grade }" name="grades"></td>
	  </c:if>
	  <c:if test="${user.role==0}">
	  <td class="td_bg"  height="23">${bean.grade==null?"暂时无成绩":bean.grade }</td>
	  </c:if>
      <td class="td_bg" >
      <a href="method!peixunupdate3?id=${bean.id }"><span style="font-size: 15px;">查看培训计划</span></a>
      <a href="method!peixunupdate?id=${bean.id }"><span style="font-size: 15px;">更新培训计划</span></a>
      <a href="method!peixundelete?id=${bean.id }"><span style="font-size: 15px;">删除培训计划</span></a>
      </td>
    </tr>
    </c:forEach>
     <c:if test="${user.role!=0}">
    	<tr>
    		<td class="td_bg"  height="23" colspan="8" style="text-align: center;"><input id="submitbtn" type="submit" value="提交成绩" ></td>
    	</tr>
    </c:if>
    </form>
    
    
    
  
    
  </tbody>
</table>
${pagerinfo }
</body>
</html>

