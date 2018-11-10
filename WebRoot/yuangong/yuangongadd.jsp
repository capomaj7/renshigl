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


 
<script src="js/Calendar.js"></script>
 

<script language="javascript" type="text/javascript">

function checkform()
{
	 
	
	if (document.getElementById('lianxidianhuaid').value=="")
	{
		alert("手机不能为空");
		return false;
	}
	
	valid=/^0?1[3,5,8][0,1,2,3,4,5,6,7,8,9]\d{8}$/;  
	if(!valid.test(document.getElementById('lianxidianhuaid').value)){
		alert("请输入正确的手机格式");
		return false;
	}
	
	
	return true;
	
}




</script>

<body>
<h1><input type="button" onclick="switchBar(this)" value="关闭左边管理菜单" name="SubmitBtn"> 

<table class="table" cellspacing="1" cellpadding="2" width="99%" align="center" border="0">
  <tbody>
    
    <form method="post"   action="method!yuangongadd2"  onsubmit="return checkform()">
    <tr>
      <td class="td_bg"  height="23">所属门店</td>
      <td class="td_bg" >
      <select name="menmian">
       <c:forEach items="${list3}" var="bean">
      <option value="${bean.id }">${bean.name }&nbsp;&nbsp;&nbsp;</option>
       </c:forEach>
      </select>
      
      </td>
     
    </tr>
    <tr>
      <td class="td_bg"  height="23">所属部门</td>
      <td class="td_bg" >
      <select name="bumen">
       <c:forEach items="${list2}" var="bean">
      <option value="${bean.id }">${bean.name }&nbsp;&nbsp;&nbsp;</option>
       </c:forEach>
      </select>
      
      </td>
     
    </tr>
    
     <tr>
      <td class="td_bg"  height="23">姓名</td>
      <td class="td_bg" ><input type="text" name="truename" /></td>
     
    </tr>
    
     <tr>
      <td class="td_bg"  height="23">联系电话</td>
      <td class="td_bg" ><input type="text" name="lianxidianhua" id="lianxidianhuaid" /></td>
     
    </tr>
     <tr>
      <td class="td_bg"  height="23">地址</td>
      <td class="td_bg" ><input type="text" name="dizhi" /></td>
     
    </tr>
     <tr>
      <td class="td_bg"  height="23">学历</td>
      <td class="td_bg" ><input type="text" name="xueli" /></td>
     
    </tr>
     <tr>
      <td class="td_bg"  height="23">籍贯</td>
      <td class="td_bg" ><input type="text" name="jiguan" /></td>
     
    </tr>
     <tr>
      <td class="td_bg"  height="23">入职时间</td>
      <td class="td_bg" >
      <input type="text" name="ruzhishijian" onfocus='setDayHM(this);'/>
      </td>
     
    </tr>

     <tr>
      <td class="td_bg"  height="23">性别</td>
      <td class="td_bg" >
      <select  name="xingbie">
      <option value="男">男</option>
      <option value="女">女</option>
      </select>
      </td>

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
</html>

