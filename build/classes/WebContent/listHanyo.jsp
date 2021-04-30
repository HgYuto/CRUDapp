<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>汎用コードマスター管理</title>
	<link rel="stylesheet" href="style.css">
</head>
<body>
  <script>
  function jump(link){
	let url = "HanyoController";
	let para;
	let para2 = getHanyoCode();
	if(link== 1){
		para = "?action=insertFace";
		location.href = url + para;
	}
	if(link== 2){
		para = "?action=updateFace&hanyoCode=";
		location.href = url + para + para2;
	}
	if(link== 3){
		para = "?action=delete&hanyoCode=";
		location.href = url + para + para2;
	}
  }
  function getHanyoCode(){
  	let hanyoCodes = document.getElementsByName("code");
  	let len = hanyoCodes.length;
  	let hanyoCode = '';
  	for(let i = 0;i < len; i++){
  		if(hanyoCodes.item(i).checked){
  			hanyoCode = hanyoCodes.item(i).value;
  		}
  	}
  	return hanyoCode;
  }
</script>
 <h1>汎用コードマスター管理画面</h1>
 <form action="/CRUDapp/HanyoController" method="POST" >
 <table id="hySearch">
  <tr>
   <th>汎用コード</th>
   <th>値コード</th>
   <th>値</th>
  </tr>
  <tr>
    <td>
      <input type="text" name="hanyo_code" size="11" <c:out value="${hanyo.hanyoCode}" /> />
    </td>
    <td>
      <input type="text" name="value_code" size="11" <c:out value="${hanyo.valueCode}" /> />
    </td>
    <td>
      <input type="text" name="value_name" size="21" <c:out value="${hanyo.valueName}" /> />
    </td>
    <td>
      <button type="Submit" name="action" value="search">検索</button>
    </td>
  </tr>
 </table>
 </form>

 <div id="hyFace">
 <table>
 <thead>
  <tr>
   <th id="hyCheck"></th>
   <th id="hanyo_code">汎用コード</th>
   <th id="value_code">値コード</th>
   <th id="value_name">値</th>
  </tr>
 </thead>
 <tbody>
 <c:forEach items="${hanyos}" var="hanyo">
 <tr>
  <td>
   <input type="radio" name="code" value="${hanyo.hanyoCode}"/>
  </td>
  <td>
   <c:out value="${hanyo.hanyoCode}"/>
  </td>
  <td>
   <c:out value="${hanyo.valueCode}" />
  </td>
  <td>
   <c:out value="${hanyo.valueName}" />
  </td>
 </tr>
  </c:forEach>
 </tbody>
</table>
</div>
<table id="dmBotan">
  <tr>
   <td><input type="button" value="追加" onclick="jump(1)"/></td>
   <td><input type="button" value="更新" onclick="jump(2)"/></td>
   <td><input type="button" value="削除" onclick="jump(3)"/></td>
  </tr>
</table>
</body>
</html>