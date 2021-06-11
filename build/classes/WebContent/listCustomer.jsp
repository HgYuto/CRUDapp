<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" href="css/style.css" rel="stylesheet" />
	<link type="text/css" href="css/customer.css" rel="stylesheet" />
	<title>取引先マスター管理</title>
</head>
<body>
  <script>
  function jump(link){
	let url= "CustomerController";
	let para;
	let para1 = getCustCode();
	if(link == 1){
		para = "?action=insertFace";
		location.href = url + para;
	}
	if(link== 2){
		para = "?action=updateFace&custCode=";
		if(getCustCode()==''){
			window.alert("データを選択してください。");
		}else{
			location.href = url + para + para1;
		}
	}
	if(link== 3){
		para = "?action=delete&custCode=";
		if(getCustCode()==''){
			window.alert("データを選択してください。");
		}else{
			location.href = url + para + para1;
		}
	}
	if(link== 4){
		para = "?action=face";
		location.href = url + para;
	}
  }
  function getCustCode(){
  	let custCodes = document.getElementsByName("code");
  	let len = custCodes.length;
  	let custCode = '';
  	for(let i = 0;i < len; i++){
  		if(custCodes.item(i).checked){
  			custCode = custCodes.item(i).value;
  		}
  	}
  	return custCode;
  }
</script>
  <form action="/CRUDapp/CustomerController" method="POST" >
 <h1>取引先マスター管理画面</h1>
<div id="f">
 <table>
  <tr>
   <th>取引先コード</th>
   <th>取引先名</th>
   <th>URL</th>
   <th>支払いサイト</th>
  </tr>
  <tr>
    <td>
      <input type="text" name="cust_code" size="15" <c:out value="${customer.custCode}" /> />
    </td>
    <td>
      <input type="text" name="cust_name" size="15" <c:out value="${customer.custName}" /> />
    </td>
    <td>
      <p id ="omit"><input type="text" name="url"  size="20" <c:out value="${customer.url}" /> /></p>
    </td>
        <td>
      <input type="text" name="payment_site" size="9" <c:out value="${customer.paymentSite}" /> />
    </td>
    <td>
      <button type="Submit" name="action" value="search">検索</button>
    </td>
  </tr>
 </table>
 </div>
 </form>
 <div id="cScroll">
 <table id="custT">
 <thead>
  <tr>
   <th id="radio"></th>
   <th id="cust_code">取引先コード</th>
   <th id="cust_name">取引先名</th>
   <th id="url">URL</th>
   <th id="payment_site">支払いサイト</th>
  </tr>
 </thead>
 <tbody>
 <c:forEach items="${customers}" var="customer">
 <tr>
  <td id="radio">
   <input type="radio" name="code" value="${customer.custCode}"/>
  </td>
  <td>
   <c:out value="${customer.custCode}" />
  </td>
  <td>
   <c:out value="${customer.custName}" />
  </td>
  <td>
   <c:out value="${customer.url}" />
  </td>
  <td>
   <c:out value="${customer.paymentSite}" />
  </td>
 </tr>
  </c:forEach>
 </tbody>
</table>
</div>
<table id="b">
  <tr>
   <td><input type="button" value="追加" onclick="jump(1)"/></td>
   <td><input type="button" value="更新" onclick="jump(2)"/></td>
   <td><input type="button" value="削除" onclick="jump(3)"/></td>
   <td><input type="button" value="戻る" onclick="jump(4)"/></td>
  </tr>
</table>
 <div id="b">
<table>
<tr>
 <td><span id="err">${result}</span></td>
</tr>
</table>
 </div>
</body>
</html>