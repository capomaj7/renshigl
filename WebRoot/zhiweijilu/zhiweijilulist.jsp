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
<form action="method!zhiweijilulist" method="post">
<div align="left">





职位名称：<input type="text" name="zhiweimingchen" value="${zhiweimingchen}">

<input type="submit" value="搜索">
</div>
</form>

<a href="method!zhiweijiluadd"><span style="font-size: 25px;">添加招聘记录</span></a>
<table class="table" cellspacing="1" cellpadding="2" width="99%" align="center" border="0">
  <tbody>
    
    
    <tr>
      <td class="td_bg"  height="23">职位名称</td>
      <td class="td_bg"  height="23">应聘人姓名</td>
      <td class="td_bg"  height="23">学历</td>
      <td class="td_bg"  height="23">联系电话</td>
       <td class="td_bg"  height="23">地址</td>
        <td class="td_bg"  height="23">性别</td>
        <td class="td_bg"  height="23">应聘结果</td>
         <td class="td_bg"  height="23">应聘时间</td>

      <td class="td_bg" >操作</td>
    </tr>
    <c:forEach items="${list}" var="bean">
    <tr>
      <td class="td_bg"  height="23">${bean.zhiwei.zhiweimingchen}</td>
      <td class="td_bg"  height="23">${bean.xingming }</td>
      <td class="td_bg"  height="23">${bean.xueli }</td>
      <td class="td_bg"  height="23">${bean.lianxidianhua }</td>
      <td class="td_bg"  height="23">${bean.addresss }</td>
      <td class="td_bg"  height="23">${bean.xingbie }</td>
       <td class="td_bg"  height="23">${bean.yingpingjieguo }</td>
      <td class="td_bg"  height="23">${fn:substring(bean.createtime,0, 19)}</td>

      <td class="td_bg" >
      <a href="method!zhiweijiluupdate3?id=${bean.id }"><span style="font-size: 15px;">查看应聘记录</span></a>
      <a href="method!zhiweijiluupdate?id=${bean.id }"><span style="font-size: 15px;">更新应聘记录</span></a>
      <a href="method!zhiweijiludelete?id=${bean.id }"><span style="font-size: 15px;">删除应聘记录</span></a>
      </td>
    </tr>
    </c:forEach>
    
    
    
  
    
  </tbody>
</table>
${pagerinfo }
</body>
</html>

