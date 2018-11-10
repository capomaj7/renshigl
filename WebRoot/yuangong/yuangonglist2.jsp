<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
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
$(function(){
	$("select").change(function(){
		var value=$(this).val();
		 if(value!="0"){
			location.href="${ctx}/"+value;
		}
		 /*else if(value=="1"){
			location.href="${ctx}/method!yuangongdelete3?id=${bean.id }&title=1";
		}else if(value=="2"){
			location.href="${ctx}/method!yuangongdelete3?id=${bean.id }&title=2";
		}else if(value=="3"){
			location.href="${ctx}/method!yuangongdelete3?id=${bean.id }&title=3";
		} */
	});
});


	
	

</SCRIPT>


 

 

<body>
<h1><input type="button" onclick="switchBar(this)" value="关闭左边管理菜单" name="SubmitBtn"> 
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; <font color="#000000"><strong>职务管理 </strong></font></h1></h1></h1></h1><form action="method!bumenlist" method="post"><div align="left">
<br/>
<form action="method!yuangonglist2" method="post">
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
      <td class="td_bg"  height="23">所属部门</td>
       <td class="td_bg"  height="23">姓名</td>
        <td class="td_bg"  height="23">员工号</td>
         
       <td class="td_bg"  height="23">职务</td>
           


      <td class="td_bg" >操作</td>
    </tr>
    <c:forEach items="${list}" var="bean">
    <tr>
      <td class="td_bg"  height="23">${bean.bumen.name }</td>
       <td class="td_bg"  height="23">${bean.truename }</td>
        <td class="td_bg"  height="23">${bean.yuangonghao }</td>
        
               <td class="td_bg"  height="23">
               <c:if test="${bean.zhiwu==0}">部门员工</c:if>
                <c:if test="${bean.zhiwu==1}">${bean.bumen.name }的部门领班</c:if>
                <c:if test="${bean.zhiwu==2}">${bean.bumen.name }的部门经理</c:if>
                <c:if test="${bean.zhiwu==3}">${bean.bumen.name }的部门管理者</c:if>
              </td>
           

      <td class="td_bg" >
      <a href="method!yuangongupdate3?id=${bean.id }"><span style="font-size: 15px;">查看员工</span></a>
      <%-- <a href="method!yuangongdelete3?id=${bean.id }"><span style="font-size: 15px;">提升为部门负责人</span></a> --%>
      <!-- 0代表员工，1代表领班，2代表门店经理，3代表管理者 -->
      <select id="myselect${bean.id }">
    	  <option value="0">职务提升</option>
    	  <option value="method!yuangongdelete3?id=${bean.id }&title=1">提升为领班</option>
    	  <option value="method!yuangongdelete3?id=${bean.id }&title=2">提升为门店经理</option>
    	  <option value="method!yuangongdelete3?id=${bean.id }&title=3">提升为管理者</option>
      </select>
      </td>
    </tr>
    </c:forEach>
    
    
    
  
    
  </tbody>
</table>
${pagerinfo }
</body>
</html>

