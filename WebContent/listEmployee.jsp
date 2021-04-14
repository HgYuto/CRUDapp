<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>:: Employee List :: </title>
</head>
<body>
<table border=1>
 <thead>
 <tr>
 <th>ID</th>
 <th>Name</th>
 <th colspan=2>Action</th>
 </tr>
 </thead>
 <tbody>
 <c:forEach items="${employees}" var="employee">
 <tr>
 <td><c:out value="${employee.employeeId}" /></td>
 <td><c:out value="${employee.employeeName}" /></td>
 <td><a href="EmployeeServlet?action=edit&empId=<c:out value="${employee.employeeId}"/>">更新</a></td>
 <td><a href="EmployeeServlet?action=delete&empId=<c:out value="${employee.employeeId}"/>">削除</a></td>
 </tr>
 </c:forEach>
 </tbody>
 </table>
 <p><a href="EmployeeServlet?action=insert">追加</a></p>
</body>
</html>