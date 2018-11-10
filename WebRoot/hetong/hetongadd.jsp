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

<script language="javascript" type="text/javascript">


function uploadform(){


document.getElementById("filenameid").value= document.getElementById("upload").value;
return true;
}
</script>
 

 

<body>
<div><input type="button" onclick="switchBar(this)" value="关闭左边管理菜单" name="SubmitBtn">  

<table class="table" cellspacing="1" cellpadding="2" width="99%" align="center" border="0">
  <tbody>
    
<form action="method!hetongadd2" method="post" enctype="multipart/form-data" onsubmit="return  uploadform()">

<input type="hidden" name="filename" id="filenameid">

	 <tr>
      <td class="td_bg"  height="23">课程编号</td>
      <td class="td_bg" ><input readonly="readonly" name="id"  type="text"  id="yid" /></td>
     
    </tr>
	<tr>
      <td class="td_bg"  height="23">课程名称</td>
      <td class="td_bg" ><input name="name"  type="text" /></td>
     
    </tr>
    <tr>
      <td class="td_bg"  height="23">上传课程资源</td>
      <td class="td_bg" ><input  name="uploadfile"  type="file"  id="upload"/></td>
     
    </tr>
    
    <tr>
      <td class="td_bg"  height="23">备注</td>
      <td class="td_bg" >
      <textarea rows="7" cols="50" name="beizhu"></textarea>
      </td>
     
    </tr>
   
   

    
    <tr>
      <td class="td_bg"  height="23">操作</td>
      <td class="td_bg" >
      <input type="submit" value="提交"/>
      &nbsp; &nbsp; &nbsp; &nbsp;
      <input type="reset" value="重置"/>
       &nbsp; &nbsp; &nbsp; &nbsp;
      <input  onclick="javascript:history.go(-1);"  type="button" value="返回" />
      </td>
     
    </tr>
    </from>
    
    
  
    
  </tbody>
</table>

</body>
<script>
document.getElementById("yid").value= new Date().getTime();
</script>
</html>

