<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" href="css/jquery-ui-1.10.4.custom.css" rel="stylesheet" />
	<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="js/jquery-ui-1.10.4.custom.min.js"></script>

	<title>汎用コードマスター管理(更新)</title>
	<link rel="stylesheet" href="style.css">
</head>
<body>
<h1>汎用コードマスター管理画面(更新)</h1>
 <script>
 $(function() {
 $('input[name=doj]').datepicker();
 });
 </script>
 <form action="/CRUDapp/HanyoController" method="POST" >
<table id="dm">
  <tr>
    <th>汎用コード</th>
     <td>
      <input type="text" value="${hanyo.hanyoCode}" name="hanyo_code" size="11" <c:out value="${hanyo.hanyoCode}" /> />
     </td>
  </tr>
  <tr>
  <th>値コード</th>
     <td>
      <input type="text" value="${hanyo.valueCode}" name="value_code" size="11" <c:out value="${hanyo.valueCode}" /> />
     </td>
  </tr>
  <tr>
   <th>値</th>
     <td>
      <input type="text" value="${hanyo.valueName}" name="value_name" size="21" <c:out value="${hanyo.valueName}" /> />
     </td>
  </tr>
</table>
<p id="dmBotan"><button type="Submit" name="action" value="update">更新</button></p>
<p id="dmBotan"><button type="Submit" name="action" value="list">戻る</button></p>
 </form>
</body>
</html>