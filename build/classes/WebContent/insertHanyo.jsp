<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" href="css/style.css" rel="stylesheet" />
	<link type="text/css" href="css/hanyo.css" rel="stylesheet" />
	<title>汎用コードマスター管理(追加)</title>
</head>
<body>
<script type="text/javascript">
function clickBtn1(){
	var form = document.getElementById("form");
	var ele= document.createElement("input");
	ele.setAttribute("type","hidden");
	ele.setAttribute("name","action");
	ele.setAttribute("value","insert");
	return form.appendChild(ele);
};

function clickBtn2(){
	var form = document.getElementById("form");
	var ele= document.createElement("input");
	ele.setAttribute("type","hidden");
	ele.setAttribute("name","action");
	ele.setAttribute("value","list");
	return form.appendChild(ele);
};
</script>
<form id="form" action="/CRUDapp/HanyoController" method="POST" >
<div id="hf1">
<h1>汎用コードマスター管理画面(追加)</h1>
<table>
  <tr>
    <th>汎用コード</th>
     <td>
      <input type="text" name="hanyo_code" size="15" <c:out value="${hanyo.hanyoCode}" /> />
     </td>
  </tr>
  <tr>
  <th>値コード</th>
     <td>
      <input type="text" name="value_code" size="30" <c:out value="${hanyo.valueCode}" /> />
     </td>
  </tr>
  <tr>
   <th>値</th>
     <td>
      <input type="text" name="value_name" size="30" <c:out value="${hanyo.valueName}" /> />
     </td>
  </tr>
</table>
</div>
<div id="hb2">
<table>
<tr>
 <td>
  <input type="submit" id="insert" value="追加" onclick="clickBtn1()">
 </td>
  <td>
  <input type="submit" id="list" value="戻る" onclick="clickBtn2()">
 </td>
</tr>
<tr>

</tr>
</table>
</div>
</form>
<script type="text/javascript" src="js/check.js"></script>
<script type="text/javascript" src="js/inputCheck_hanyo.js"></script>
</body>
</html>