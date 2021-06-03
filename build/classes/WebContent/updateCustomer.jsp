<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" href="css/style.css" rel="stylesheet" />
	<title>取引先マスター管理(更新)</title>
</head>
<body>
<script type="text/javascript">
function clickBtn1(){
	var form = document.getElementById("form");
	var ele= document.createElement("input");
	ele.setAttribute("type","hidden");
	ele.setAttribute("name","action");
	ele.setAttribute("value","update");
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
<form id="form" action="/CRUDapp/CustomerController" method="POST" onsubmit = "check_data()">
<div id="f">
<h1>取引先マスター管理画面(更新)</h1>
<table>
  <tr>
    <th>取引先コード</th>
     <td>
      <input type="text" value="${customer.custCode}" name="cust_code" id="gray" size="15" readonly />
     </td>
  </tr>
  <tr>
  <th>取引先名</th>
     <td>
      <input type="text" value="${customer.custName}" name="cust_name" size="15" <c:out value="${customer.custName}" /> />
     </td>
  </tr>
  <tr>
   <th>URL</th>
     <td>
      <input type="text" value="${customer.url}" name="url" size="30" <c:out value="${customer.url}" /> />
     </td>
  </tr>
  <tr>
   <th>支払いサイト</th>
     <td>
       <input type="text" value="${customer.paymentSite}" name="payment_site" size="20" <c:out value="${customer.paymentSite}" /> />
     </td>
  </tr>
 </tbody>
</table>
</div>
<div id="b">
<table>
<tr>
 <td>
  <input type="submit" id="update" value="更新" onclick="clickBtn1()">
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
<script type="text/javascript" src="js/updateCheck_customer.js"></script>
</body>
</html>