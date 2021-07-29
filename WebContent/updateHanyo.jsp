<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" href="css/style.css" rel="stylesheet" />
	<link type="text/css" href="css/hanyo.css" rel="stylesheet" />
	<title>汎用コードマスター管理(更新)</title>
</head>
<body>
 <form id="form" name="form" action="/CRUDapp/HanyoController" method="POST">
<input type="hidden" name="key" value=""/>
 <div id="f">
 <h1>汎用コードマスター管理画面(更新)</h1>
<table>
  <tr>
    <th>汎用コード</th>
     <td>
      <input type="text" value="${hanyo.hanyoCode}" name="hanyo_code" id="gray" size="15" readonly />
     </td>
  </tr>
  <tr>
  <th>値コード</th>
     <td>
      <input type="text" value="${hanyo.valueCode}" name="value_code" id="gray" size="30" readonly />
     </td>
  </tr>
  <tr>
   <th>値</th>
     <td>
      <input type="text" value="${hanyo.valueName}" name="value_name" size="30" <c:out value="${hanyo.valueName}" /> />
     </td>
  </tr>
</table>
</div>
<div id="b">
<table>
<tr>
 <td>
  <button type="button" id="update" name="update" onclick = "updateCheck()">更新</button>
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
 </form>
 <script type="text/javascript" src="js/check.js"></script>
 <script type="text/javascript" src="js/updateCheck_hanyo.js"></script>
</body>
</html>