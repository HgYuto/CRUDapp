<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>取引先マスター管理</title>
	<link rel="stylesheet" href="style.css">
</head>
<body>
 <h1>取引先マスター管理画面</h1>
<table id="cm">
 <thead>
 <tr>
 <th id="cust-code">取引先コード</th>
 <th id="cust-name">取引先名</th>
 <th id="url">URL</th>
 <th id="payment-site">支払いサイト</th>
 </tr>
 </thead>
 <tbody>
 <c:forEach items="${customers}" var="customer">
 <tr>
 <td><c:out value="${customer.custCode}" /></td>
 <td><c:out value="${customer.custName}" /></td>
 <td><c:out value="${customer.url}" /></td>
 <td><c:out value="${customer.paymentSite}" /></td>
  <td id="update"><a href="CustomerController?action=updateFace&empcode=<c:out value="${customer.custCode}"/>"><input type="Submit" value="更新"></a></td>
 <td id="delete"><a href="CustomerController?action=delete&empcode=<c:out value="${customer.custCode}"/>"><input type="Submit" value="削除"></a></td>
 </tr>
 </c:forEach>
 </tbody>
 </table>
 <p id="decision"><a href="CustomerController?action=insertFace"><input type="Submit" value="追加"></a></p>
</body>
</html>