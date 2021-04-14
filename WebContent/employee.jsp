<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link type="text/css" href="css/jquery-ui-1.10.4.custom.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.10.4.custom.min.js"></script>

<title>:: Add Employee ::</title>
</head>
<body>
 <script>
 $(function() {
 $('input[name=doj]').datepicker();
 });
 </script>

 <form method="POST" action='EmployeeServlet' name="frmAddEmployee">

 ID   : <input type="text" readonly="readonly" name="id" value="<c:out value="${employee.employeeId}" />" /> <br />
 名前 : <input type="text" name="name" value="<c:out value="${employee.employeeName}" />" /> <br />
 <input type="Submit" value="Submit" />

 </form>
</body>
</html>