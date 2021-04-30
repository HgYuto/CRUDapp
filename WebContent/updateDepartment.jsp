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

	<title>部署マスター管理(更新)</title>
	<link rel="stylesheet" href="style.css">
</head>
<body>
<h1>部署マスター管理画面(更新)</h1>
 <script>
 $(function() {
 $('input[name=doj]').datepicker();
 });
 </script>
 <form action="/CRUDapp/DepartmentController" method="POST" >
<table id="dm">
  <tr>
    <th>取引先コード</th>
     <td>
      <input type="text" value="${department.custCode}" name="cust_code" size="11" <c:out value="${department.custCode}" /> />
     </td>
  </tr>
  <tr>
  <th>部署コード</th>
     <td>
      <input type="text" value="${department.deptCode}" name="dept_code" size="11" <c:out value="${department.deptCode}" /> />
     </td>
  </tr>
  <tr>
   <th>部署名１</th>
     <td>
      <input type="text" value="${department.deptName1}" name="dept_name1" size="21" <c:out value="${department.deptName1}" /> />
     </td>
  </tr>
  <tr>
   <th>部署名２</th>
     <td>
       <input type="text" value="${department.deptName2}" name="dept_name2" size="19" <c:out value="${department.deptName2}" /> />
     </td>
  </tr>
  <tr>
   <th>郵便番号</th>
     <td>
       <input type="text" value="${department.postCode}" name="post_code" size="11" <c:out value="${department.postCode}" /> />
     </td>
  </tr>
  <tr>
   <th>住所１</th>
     <td>
       <input type="text" value="${department.address1}" name="address1" size="11" <c:out value="${department.address1}" /> />
     </td>
  </tr>
  <tr>
   <th>住所２</th>
     <td>
       <input type="text" value="${department.address2}" name="address2" size="21" <c:out value="${department.address2}" /> />
     </td>
  </tr>
  <tr>
   <th>住所３</th>
     <td>
       <input type="text" value="${department.address3}"  name="address3" size="19" <c:out value="${department.address3}" /> />
     </td>
  </tr>
  <tr>
   <th>電話番号</th>
     <td>
       <input type="text" value="${department.tel}" name="tel" size="11" <c:out value="${department.tel}" /> />
     </td>
  </tr>
  <tr>
   <th>担当者</th>
     <td>
       <input type="text" value="${department.chargeName}" name="charge_name" size="11" <c:out value="${department.chargeName}" /> />
     </td>
  </tr>
  <tr>
   <th>メールアドレス</th>
     <td>
       <input type="text" value="${department.mail}" name="mail" size="21" <c:out value="${department.mail}" /> />
     </td>
  </tr>
</table>

<p id="dmBotan"><input type="hidden" name="action" value="update"><input type="Submit" value="更新"></p>
<p id="dmBotan"><input type="hidden" name="action" value="list"><input type="Submit" value="戻る"></p>
 </form>
</body>
</html>