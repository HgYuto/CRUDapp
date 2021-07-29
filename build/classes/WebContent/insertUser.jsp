<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" href="css/style.css" rel="stylesheet" />
	<link type="text/css" href="css/user.css" rel="stylesheet" />
	<title>ユーザマスター管理(追加)</title>
</head>
<body>
 <form id="form"  name="form" action="/CRUDapp/UserController" method="POST" >
 <input type="hidden" name="key" value=""/>
<div id="f">
<h1>ユーザマスター管理画面(追加)</h1>
<table>
  <tr>
   	 <th>社員コード</th>
     <td id="syain_code">
      <input type="text" name="syain_code" size="11" <c:out value="${user.syainCode}" /> />
     </td>
  </tr>
  <tr>
  <th>ユーザID</th>
     <td id="user_id">
      <input type="text" name="user_id" size="15" <c:out value="${user.userId}" /> />
     </td>
  </tr>
  <tr>
   <th>パスワード</th>
     <td id="password">
      <input type="text" name="password" size="15" <c:out value="${user.password}" /> />
     </td>
  </tr>
 <tr>
  <th>権限</th>
     <td id="authority">
      <input type="text" name="authority" size="9" <c:out value="${user.authority}" /> />
     </td>
  </tr>
</table>
</div>
<div id="b">
<table>
<tr>
 <td>
  <button type="button" id="insert" name="insert" onclick = "inputCheck()">追加</button>
 </td>
 <td>
  <button type="button" id="list" name="list" onclick = "backList()">戻る</button>
 </td>
</tr>
</table>
</div>
 <div id="b">
<table>
<tr>
 <td><span id="err">${result}</span></td>
</tr>
</table>
 </div>
<script type="text/javascript" src="js/check.js"></script>
<script type="text/javascript" src="js/inputCheck_user.js"></script>
 </form>
</body>
</html>