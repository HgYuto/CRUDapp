<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" href="css/style.css" rel="stylesheet" />
	<link type="text/css" href="css/customer.css" rel="stylesheet" />
	<title>取引先マスター管理(追加)</title>
</head>
<body>
 <form id="form" name="form" action="/CRUDapp/CustomerController" method="POST" >
 <input type="hidden" name="key" value=""/>
 <div id="f">
 <h1>取引先マスター管理画面(追加)</h1>
<table>
 <tr>
  <th>取引先コード</th>
  <td>
   <input type="text" name="cust_code" size="15" <c:out value="${customer.custCode}" /> />
  </td>
 </tr>
 <tr>
  <th>取引先名</th>
  <td>
   <input type="text" name="cust_name" size="15" <c:out value="${customer.custName}" /> />
  </td>
 </tr>
 <tr>
  <th>URL</th>
  <td>
   <input type="text" name="url" size="30" <c:out value="${customer.Url}" /> />
  </td>
 </tr>
 <tr>
  <th>支払サイト</th>
  <td>
   <input type="text" name="payment_site" size="20" <c:out value="${customer.paymentSite}" /> />
  </td>
 </tr>
</table>
 </div>
 <div id="b">
<table>
<tr>
 <td>
  <button type="button" id="insert" name="insert" onclick ="inputCheck()">追加</button>
 </td>
  <td>
  <button type="button" id="list" name="list" onclick ="backList()">戻る</button>
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
 <script type="text/javascript" src="js/inputCheck_customer.js"></script>
</body>
</html>