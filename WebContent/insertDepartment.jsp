<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" href="css/style.css" rel="stylesheet" />
	<link type="text/css" href="css/department.css" rel="stylesheet" />
	<title>部署マスター管理(追加)</title>
</head>
<body>
 <script>
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

 <form id="form" action="/CRUDapp/DepartmentController" method="POST" >
<div id="f">
<h1>部署マスター管理画面(追加)</h1>
<table>
  <tr>
    <th>取引先コード</th>
     <td>
      <input type="text" name="cust_code" size="15" <c:out value="${department.custCode}" /> />
     </td>
  </tr>
  <tr>
  <th>部署コード</th>
     <td>
      <input type="text" name="dept_code" size="35" <c:out value="${department.deptCode}" /> />
     </td>
  </tr>
  <tr>
   <th>部署名１</th>
     <td>
      <input type="text" name="dept_name1" size="35" <c:out value="${department.deptName1}" /> />
     </td>
  </tr>
  <tr>
   <th>部署名２</th>
     <td>
       <input type="text" name="dept_name2" size="35" <c:out value="${department.deptName2}" /> />
     </td>
  </tr>
  <tr>
   <th>郵便番号</th>
     <td>
       <input type="text" name="post_code" size="25" <c:out value="${department.postCode}" /> />
     </td>
  </tr>
  <tr>
   <th>住所１</th>
     <td>
       <input type="text" name="address1" size="45" <c:out value="${department.address1}" /> />
     </td>
  </tr>
  <tr>
   <th>住所２</th>
     <td>
       <input type="text" name="address2" size="45" <c:out value="${department.address2}" /> />
     </td>
  </tr>

  <tr>
   <th>住所３</th>
     <td>
       <input type="text" name="address3" size="45" <c:out value="${department.address3}" /> />
     </td>
  </tr>
  <tr>
   <th>電話番号</th>
     <td>
       <input type="text" name="tel" size="25" <c:out value="${department.tel}" /> />
     </td>
  </tr>
  <tr>
   <th>担当者</th>
     <td>
       <input type="text" name="charge_name" size="25" <c:out value="${department.chargeName}" /> />
     </td>
  </tr>
  <tr>
   <th>メールアドレス</th>
     <td>
       <input type="text" name="mail" size="45" <c:out value="${department.mail}" /> />
     </td>
  </tr>
</table>
</div>
<div id="b">
<table>
<tr>
 <td>
  <input type="submit" name="insert" value="追加" onclick="clickBtn1()">
 </td>
  <td>
  <input type="submit" name="list" value="戻る" onclick="clickBtn2()">
 </td>
</tr>
<tr>

</tr>
</table>
</div>
 </form>
<script type="text/javascript" src="js/check.js"></script>
<script type="text/javascript" src="js/inputCheck_department.js"></script>
</body>
</html>