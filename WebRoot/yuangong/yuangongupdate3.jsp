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
    
    <form method="post"   action="method!yuangongupdate2">
    <tr>
      <td class="td_bg"  height="23">所属门面</td>
      <td class="td_bg" >
     	 ${bean.menmian.name}
      </td>
     
    </tr>
    <tr>
      <td class="td_bg"  height="23">所属部门</td>
      <td class="td_bg" >
      ${bean.bumen.name}
      
      </td>
     
    </tr>
    
    <input type="hidden" name="id" value="${bean.id }"/>
    
     <tr>
      <td class="td_bg"  height="23">姓名</td>
      <td class="td_bg" ><input type="text" name="truename" value="${bean.truename }" readonly="readonly"/></td>
     
    </tr>
    
     <tr>
      <td class="td_bg"  height="23">联系电话</td>
      <td class="td_bg" ><input type="text" name="lianxidianhua" value="${bean.lianxidianhua }" readonly="readonly"/></td>
     
    </tr>
     <tr>
      <td class="td_bg"  height="23">地址</td>
      <td class="td_bg" ><input type="text" name="dizhi" value="${bean.dizhi }" readonly="readonly"/></td>
     
    </tr>
     <tr>
      <td class="td_bg"  height="23">学历</td>
      <td class="td_bg" ><input type="text" name="xueli" value="${bean.xueli }" readonly="readonly"/></td>
     
    </tr>
     <tr>
      <td class="td_bg"  height="23">籍贯</td>
      <td class="td_bg" ><input type="text" name="jiguan" value="${bean.jiguan }" readonly="readonly"/></td>
     
    </tr>
     <tr>
      <td class="td_bg"  height="23">入职时间</td>
      <td class="td_bg" ><input type="text" name="ruzhishijian" value="${bean.ruzhishijian }" readonly="readonly"/></td>

     </tr>
     <tr>
      <td class="td_bg"  height="23">性别</td>
      <td class="td_bg" ><input type="text" name="xingbie" value="${bean.xingbie }" readonly="readonly"/></td>

     </tr>
     <tr>
      <td class="td_bg"  height="23">备注</td>
      <td class="td_bg" >
      <textarea rows="7" cols="50" name="beizhu" readonly="readonly">${bean.beizhu }</textarea>
      </td>
     
    </tr>
    
    
    <tr>
      <td class="td_bg"  height="23">操作</td>
      <td class="td_bg" >

      <input  onclick="javascript:history.go(-1);"  type="button" value="返回" />
      </td>
     
    </tr>
    </from>
    
    
  
    
  </tbody>
</table>

</body>
</html>

