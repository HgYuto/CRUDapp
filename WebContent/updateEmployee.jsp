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

	<title>取引先マスター管理(更新)</title>
	<link rel="stylesheet" href="style.css">
</head>
<body>
<h1>取引先マスター管理画面(更新)</h1>
 <script>
 $(function() {
 $('input[name=doj]').datepicker();
 });
 </script>
 <form method="POST" action='EmployeeServlet' name="frmAddEmployee">
<table border=1>
 <thead>
 <tr>
 <th>取引先コード</th>
 <th>取引先名</th>
 <th>URL</th>
 <th>支払いサイトコード</th>
 </tr>
 </thead>
 <tbody>
 <tr>
 <td><input type="text" readonly="readonly" name="id" value="<c:out value="${employee.employeeId}" />" /> <br />
 </td>
 <td><input type="text" name="name" value="<c:out value="${employee.employeeName}" />" /> <br /></td>
 </tr>
 </tbody>
</table>
<p> <input type="Submit" value="更新"></p>
 </form>
</body>
</html>