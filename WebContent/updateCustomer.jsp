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
 <form action="/CRUDapp/CustomerController" method="POST" >
<table id="cm">
 <thead>
 <tr>
 <th id="cust_code">取引先コード</th>
 <th id="cust_name">取引先名</th>
 <th id="url">URL</th>
 <th id="payment_site">支払いサイト</th>
 </tr>
 </thead>
<tbody>
 <tr>
 <td>
 <input type="text" value="${customer.custCode}" name="cust_code" size="11" <c:out value="${customer.custCode}" /> />
 </td>
 <td>
 <input type="text" value="${customer.custName}" name="cust_name" size="11" <c:out value="${customer.custName}" /> />
</td>
 <td>
 <input type="text" value="${customer.url}" name="url" size="21" <c:out value="${customer.url}" /> />
 </td>
 <td>
 <input type="text" value="${customer.paymentSite}" name="payment_site" size="19" <c:out value="${customer.paymentSite}" /> />
</td>
 </tr>
 </tbody>
</table>
<p id="decision"><input type="hidden" name="action" value="update"><input type="Submit" value="更新"></p>
<p id="back"><input type="Submit" value="戻る"></p>
 </form>
</body>
</html>