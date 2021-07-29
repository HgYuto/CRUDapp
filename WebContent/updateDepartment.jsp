<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" href="css/style.css" rel="stylesheet" />
	<link type="text/css" href="css/department.css" rel="stylesheet" />
<!--<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="js/jquery-ui-1.10.4.custom.min.js"></script>
-->
	<title>部署マスター管理(更新)</title>
</head>
<body>
 <form id= "form" name="form" action="/CRUDapp/DepartmentController" method="POST" >
 <input type="hidden" name="key" value=""/>
 <div id="f">
 <h1>部署マスター管理画面(更新)</h1>
 <table>
  <tr>
    <th>取引先コード</th>
     <td>
      <input type="text" value="${department.custCode}" name="cust_code" id="gray" size="15" readonly />
     </td>
  </tr>
  <tr>
  <th>部署コード</th>
     <td>
      <input type="text" value="${department.deptCode}" name="dept_code" id="gray" size="35" readonly />
     </td>
  </tr>
  <tr>
   <th>部署名１</th>
     <td>
      <input type="text" value="${department.deptName1}" name="dept_name1" size="35" <c:out value="${department.deptName1}" /> />
     </td>
  </tr>
  <tr>
   <th>部署名２</th>
     <td>
       <input type="text" value="${department.deptName2}" name="dept_name2" size="35" <c:out value="${department.deptName2}" /> />
     </td>
  </tr>
  <tr>
   <th>郵便番号</th>
     <td>
       <input type="text" value="${department.postCode}" name="post_code" size="25" <c:out value="${department.postCode}" /> />
     </td>
  </tr>
  <tr>
   <th>住所１</th>
     <td>
       <input type="text" value="${department.address1}" name="address1" size="45" <c:out value="${department.address1}" /> />
     </td>
  </tr>
  <tr>
   <th>住所２</th>
     <td>
       <input type="text" value="${department.address2}" name="address2" size="45" <c:out value="${department.address2}" /> />
     </td>
  </tr>
  <tr>
   <th>住所３</th>
     <td>
       <input type="text" value="${department.address3}"  name="address3" size="45" <c:out value="${department.address3}" /> />
     </td>
  </tr>
  <tr>
   <th>電話番号</th>
     <td>
       <input type="text" value="${department.tel}" name="tel" size="25" <c:out value="${department.tel}" /> />
     </td>
  </tr>
  <tr>
   <th>担当者</th>
     <td>
       <input type="text" value="${department.chargeName}" name="charge_name" size="25" <c:out value="${department.chargeName}" /> />
     </td>
  </tr>
  <tr>
   <th>メールアドレス</th>
     <td>
       <input type="text" value="${department.mail}" name="mail" size="45" <c:out value="${department.mail}" /> />
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
<script type="text/javascript" src="js/updateCheck_department.js"></script>
</body>
</html>