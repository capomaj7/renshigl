<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>员工管理培训系统</title>
<style type="text/css">
<!--
a{ color:#008EE3}
a:link  { text-decoration: none;color:#008EE3}
A:visited {text-decoration: none;color:#666666}
A:active {text-decoration: underline}
A:hover {text-decoration: underline;color: #0066CC}
A.b:link {
	text-decoration: none;
	font-size:12px;
	font-family: "Helvetica,微软雅黑,宋体";
	color: #FFFFFF;
}
A.b:visited {
	text-decoration: none;
	font-size:12px;
	font-family: "Helvetica,微软雅黑,宋体";
	color: #FFFFFF;
}
A.b:active {
	text-decoration: underline;
	color: #FF0000;

}
A.b:hover {text-decoration: underline; color: #ffffff}

.table1 {
	border: 1px solid #CCCCCC;
}
.font {
	font-size: 12px;
	text-decoration: none;
	color: #999999;
	line-height: 20px;
	

}
.input {
	font-size: 12px;
	color: #999999;
	text-decoration: none;
	border: 0px none #999999;


}

td {
	font-size: 12px;
	color: #007AB5;
}
form {
	margin: 1px;
	padding: 1px;
}
input {
	border: 0px;
	height: 26px;
	color: #007AB5;

	.unnamed1 {
	border: thin none #FFFFFF;
}
.unnamed1 {
	border: thin none #FFFFFF;
}
select {
	border: 1px solid #cccccc;
	height: 18px;
	color: #666666;

	.unnamed1 {
	border: thin none #FFFFFF;
}
body {
	background-repeat: no-repeat;
	background-color: #9CDCF9;
	background-position: 0px 0px;

}
.tablelinenotop {
	border-top: 0px solid #CCCCCC;
	border-right: 1px solid #CCCCCC;
	border-bottom: 0px solid #CCCCCC;
	border-left: 1px solid #CCCCCC;
}
.tablelinenotopdown {

	border-top: 1px solid #eeeeee;
	border-right: 1px solid #eeeeee;
	border-bottom: 1px solid #eeeeee;
	border-left: 1px solid #eeeeee;
}
.style6 {FONT-SIZE: 9pt; color: #7b8ac3; }

-->
</style>
<style type="text/css"><!--body { background-image: url(Images/bg.jpg);}--></style>
</head>
<body>
<table width="812" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top: 120px;" height="410">
  <tr>
    <td width="353" height="259" align="center" valign="bottom" background="Images/login_1.gif">
    <table width="90%" border="0" cellspacing="3" cellpadding="0">
      <tr>
        
      </tr>
    </table></td>
    <td width="195" background="Images/login_2.gif"><table width="285" height="106" border="0" align="center" cellpadding="2" cellspacing="0">
      <form method="post"  name="NETSJ_Login" action="method!login">
            <tr>
              <td height="50" colspan="2" align="left">&nbsp;<span style="font-size: 30px;">员工管理及培训系统</span></td>
            </tr>
            <tr>
              <td width="60" height="30" align="left">用户名</td>
              <td><input name="username" type="TEXT" style="background:url(Images/login_6.gif) repeat-x; border:solid 1px #27B3FE; height:20px; background-color:#FFFFFF" id="UserName"size="14"></td>
            </tr>
            <tr>
              <td height="30" align="left">密码</td>
              <td><input name="password" TYPE="PASSWORD" style="background:url(Images/login_6.gif) repeat-x; border:solid 1px #27B3FE; height:20px; background-color:#FFFFFF" id="Password" size="16"></td>
            </tr>
             <tr>
              <td height="30" align="left">用户角色</td>
              <td>
              <select name="role">
            <option value="0">普通员工</option>
         <option value="1">系统管理员</option>
              </select>
              </td>
            </tr>
            
            
              <td colspan="2" align="center"><input type="submit" name="submit" style="background:url(Images/login_5.gif) no-repeat" value=" 登  录 "> 
			  <input type="reset" name="Submit" style="background:url(Images/login_5.gif) no-repeat" value=" 取  消 "></td>
            <tr>
              <td height="5" colspan="2"></td>

    </table></td>
    <td width="133" background="Images/login_3.gif">&nbsp;</td><td valign="top"><br></td><td valign="top"><br></td>
  </tr>
  <tr>
    <td height="161" colspan="3" background="Images/login_4.gif"><br></td><td valign="top"><br></td><td valign="top"><br></td>
  </tr>
</table>
</body>
</html>