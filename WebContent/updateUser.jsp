<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" href="css/style.css" rel="stylesheet" />
	<link type="text/css" href="css/user.css" rel="stylesheet" />
	<title>ユーザマスター管理(更新)</title>
</head>
<body>
<script type="text/javascript">
function backList(){
	var form = document.getElementById("form");
	var ele= document.createElement("input");
	ele.setAttribute("type","hidden");
	ele.setAttribute("name","action");
	ele.setAttribute("value","list");
	return form.appendChild(ele);
};
</script>
 <form id="form" name="form" action="/CRUDapp/UserController" method="POST" >
 <div id="f">
 <h1>ユーザマスター管理画面(更新)</h1>
 <input type="hidden" value="${user.userId}" name="preUserId" />
<table>
  <tr>
 <tr>
    <th>社員コード</th>
     <td>
      <input type="text" value="${user.syainCode}" name="syain_code"  id="gray" size="15" readonly/>
     </td>
  </tr>
  <tr>
  <th>社員名</th>
     <td>
      <input type="text" value="${user.syainName}" name="syain_name"  id="gray" size="15" readonly/>
     </td>
  </tr>
  <tr>
   <th>ユーザID</th>
     <td>
      <input type="text" value="${user.userId}" name="user_id" size="15" <c:out value="${user.userId}" /> />
     </td>
  </tr>
 <tr>
  <th>パスワード</th>
     <td>
      <input type="text" value="${user.password}" name="password" size="15" <c:out value="${user.password}" /> />
     </td>
  </tr>
  <tr>
  <th>権限</th>
     <td>
      <input type="text" value="${user.authority}" name="authority" size="9" <c:out value="${user.authority}" /> />
     </td>
  </tr>
</table>
</div>
<div id="b">
<table>
<tr>
 <td>
  <input type="submit" id="update" name="update" value="更新" onclick="updateCheck()">
 </td>
 <td>
  <input type="submit" id="list" name="list" value="戻る" onclick="backList()">
 </td>
</tr>
</table>
</div>
</form>
 <div id="b">
<table>
<tr>
 <td><span id="err">${result}</span></td>
</tr>
</table>
 </div>
<script type="text/javascript" src="js/check.js"></script>
<script type="text/javascript" src="js/updateCheck_user.js"></script>
</body>
</html>