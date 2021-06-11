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
 <form id="form" name="form" action="/CRUDapp/HanyoController" method="POST" >
 <div id="f">
 <h1>汎用コードマスター管理画面(更新)</h1>
 <input type="hidden" name="hanyoCode" value="${hanyo.hanyoCode}"/>
 <input type="hidden" name="valueCode" value="${hanyo.valueCode}"/>
 <input type="hidden" name="valueName" value="${hanyo.valueName}"/>
<table>
  <tr>
    <th>汎用コード</th>
     <td>
      <input type="text" value="${hanyo.hanyoCode}" name="hanyo_code" size="15" <c:out value="${hanyo.hanyoCode}" /> />
     </td>
  </tr>
  <tr>
  <th>値コード</th>
     <td>
      <input type="text" value="${hanyo.valueCode}" name="value_code" size="30" <c:out value="${hanyo.valueCode}" /> />
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
  <input type="submit" id="update" name="update" value="更新" onclick="updateCheck()">
 </td>
 <td>
  <input type="submit" id="list" name="list" value="戻る" onclick="backList()">
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